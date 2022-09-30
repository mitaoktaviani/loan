package com.mita.loan.service;

import com.mita.loan.dto.transaction.AcceptLoan;
import com.mita.loan.dto.transaction.ApplyLoan;
import com.mita.loan.dto.transaction.RejectLoan;
import com.mita.loan.entity.*;
import com.mita.loan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private DebtorRepository debtorRepository;

    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    private LoanOffersRepository loanOffersRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Override
    public Integer addTransaction(ApplyLoan dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Debtor debtor1 = debtorRepository.getByUsername(username);

        Optional<LoanOffers> theLoanOffers = loanOffersRepository.findById(dto.getLoanId());
        LoanOffers loanOffers = null;
        if(theLoanOffers.isPresent()){
            loanOffers = theLoanOffers.get();
        }

        Transaction transaction = new Transaction();
        transaction.setPending(true);
        transaction.setReject(false);
        transaction.setAccept(false);
        transaction.setLoanId(dto.getLoanId());
        transaction.setLoanOffers(loanOffers);
        transaction.setDebtorId(debtor1.getId());
        transaction.setDebtor(debtor1);
        transaction.setLoanDate(LocalDate.now());

        transactionRepository.save(transaction);

        return transaction.getId();
    }

    @Override
    public Integer reject(RejectLoan dto) {
        Optional<Transaction> theTransaction = transactionRepository.findById(dto.getTransactionId());
        Transaction transaction = null;
        if(theTransaction.isPresent()){
            transaction= theTransaction.get();}

        transaction.setReject(true);
        transaction.setPending(false);

        transactionRepository.save(transaction);
        return transaction.getId();
    }

    @Override
    public Integer accept(AcceptLoan dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Creditor creditor = creditorRepository.getByUserName(username);

        Optional<Transaction> theTransaction = transactionRepository.findById(dto.getTransactionId());
        Transaction transaction = null;
        if(theTransaction.isPresent()){
            transaction= theTransaction.get();}

        transaction.setAccept(true);
        transaction.setPending(false);
        transaction.setReject(false);
        transactionRepository.save(transaction);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTransactionId(transaction.getId());
        transactionDetail.setTransaction(transaction);
        transactionDetail.setCreditor(creditor);
        transactionDetail.setCreditorId(creditor.getId());
        transactionDetail.setPayAmount(dto.getPayAmount());
        transactionDetail.setPaymentDueDate(dto.getPaymentDueDate());
        transactionDetail.setTerm(transaction.getLoanOffers().getLoanTerm());
        transactionDetail.setInstalment(dto.getInstalment());
        transactionDetail.setPaid(false);

        transactionDetailRepository.save(transactionDetail);

        return transaction.getId();
    }

    @Override
    public List<Transaction> getAllTransaction() {
      List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }
}
