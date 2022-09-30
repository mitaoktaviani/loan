package com.mita.loan.repository;

import com.mita.loan.dto.paymentHistory.PaymentHistoryDTO;
import com.mita.loan.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory,Integer> {
    @Query("""
            SELECT new com.mita.loan.dto.paymentHistory.PaymentHistoryDTO(
            ph.id, ph.instalment, ph.totalPaid, ph.paidDate, ph.transactionDetailId)
            FROM PaymentHistory AS ph
                    JOIN ph.transactionDetail AS td
                    JOIN td.transaction AS tr
                    JOIN tr.debtor AS deb
                    JOIN deb.account AS acc
            WHERE acc.username = :username
            """)
    List<PaymentHistoryDTO> getHistory(@Param("username") String username);
}
