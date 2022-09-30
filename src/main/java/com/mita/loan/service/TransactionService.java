package com.mita.loan.service;

import com.mita.loan.dto.transaction.AcceptLoan;
import com.mita.loan.dto.transaction.ApplyLoan;
import com.mita.loan.dto.transaction.RejectLoan;

public interface TransactionService {
    Integer addTransaction(ApplyLoan dto);

    Integer reject(RejectLoan dto);

    Integer accept(AcceptLoan dto);
}
