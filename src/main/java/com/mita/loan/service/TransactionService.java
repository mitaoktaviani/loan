package com.mita.loan.service;

import com.mita.loan.dto.transaction.AcceptLoan;
import com.mita.loan.dto.transaction.ApplyLoan;
import com.mita.loan.dto.transaction.RejectLoan;
import com.mita.loan.dto.transaction.TransactionDTO;
import com.mita.loan.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Integer addTransaction(ApplyLoan dto);

    Integer reject(RejectLoan dto);

    Integer accept(AcceptLoan dto);

    List<Transaction> getAllTransaction();

    List<TransactionDTO> getAllTransactions(String username);
}
