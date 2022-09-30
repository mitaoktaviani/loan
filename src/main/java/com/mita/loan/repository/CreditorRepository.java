package com.mita.loan.repository;

import com.mita.loan.entity.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreditorRepository extends JpaRepository<Creditor, Integer> {

    @Query(value = """
            SELECT * FROM Creditor cre 
            INNER JOIN Account AS acc ON cre.Username = acc.Username
            WHERE cre.Username = :username
            """,
            nativeQuery = true)
    Creditor getByUserName(@Param("username") String username);
}
