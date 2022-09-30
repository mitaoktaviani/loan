package com.mita.loan.repository;

import com.mita.loan.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account,String > {

    @Query("""
            SELECT COUNT(*)
            FROM Account AS acc
            WHERE acc.username = :username
            """)
    public Long countUsername(@Param("username") String username);
}
