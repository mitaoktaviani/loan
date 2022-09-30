package com.mita.loan.repository;

import com.mita.loan.entity.LoanOffers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface LoanOffersRepository extends JpaRepository<LoanOffers,Integer> {

    @Query(
            value = """
                    SELECT * 
                    FROM LoanOffers AS lo 
                    WHERE lo.loanName = :loanName
                    """,
            nativeQuery = true
    )
    LoanOffers getByName(@PathVariable("loanName") String loanName);
}
