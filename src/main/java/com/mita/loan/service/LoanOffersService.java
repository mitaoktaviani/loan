package com.mita.loan.service;

import com.mita.loan.dto.loanOffers.UpsertLoanOffersDTO;
import com.mita.loan.entity.LoanOffers;

import java.util.List;

public interface LoanOffersService {
    Integer addLoanOffers(UpsertLoanOffersDTO dto);

    List<LoanOffers> getAllLoanOffers();

    LoanOffers getLoanOffersByName(String loanName);

    LoanOffers getLoanOffers(Integer id);

    LoanOffers updateLoanOffers(Integer id, UpsertLoanOffersDTO dto);

    void deleteLoanOffer(UpsertLoanOffersDTO dto);
}
