package com.mita.loan.service;

import com.mita.loan.dto.transactionDetail.PaidInstalmentDTO;

public interface TransactionDetailService {
    Integer paidLoan(PaidInstalmentDTO dto);
}
