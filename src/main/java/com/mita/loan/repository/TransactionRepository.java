package com.mita.loan.repository;

import com.mita.loan.dto.transaction.TransactionDTO;
import com.mita.loan.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("""
            SELECT new com.mita.loan.dto.transaction.TransactionDTO(
            tr.id, tr.loanId, tr.debtorId, tr.pending, tr.reject, tr.accept)
            FROM Transaction AS tr
            JOIN tr.debtor AS deb
            WHERE deb.account.username = :username
            """)
    List<TransactionDTO> getAll(@Param("username") String username);
}
