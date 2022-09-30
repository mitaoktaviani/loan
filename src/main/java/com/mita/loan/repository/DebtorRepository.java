package com.mita.loan.repository;

import com.mita.loan.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DebtorRepository extends JpaRepository<Debtor, Integer> {
    @Query(value = """
            SELECT * FROM Debtor deb 
            INNER JOIN Account AS acc ON deb.Username = acc.Username
            WHERE deb.Username = :username
            """,
            nativeQuery = true)
    Debtor getByUsername(@Param("username") String username);
}
