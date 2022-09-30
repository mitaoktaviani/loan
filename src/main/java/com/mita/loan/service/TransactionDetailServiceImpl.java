package com.mita.loan.service;

import com.mita.loan.dto.transactionDetail.PaidInstalmentDTO;
import com.mita.loan.entity.PaymentHistory;
import com.mita.loan.entity.TransactionDetail;
import com.mita.loan.repository.PaymentHistoryRepository;
import com.mita.loan.repository.TransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public Integer paidLoan(PaidInstalmentDTO dto) {

        Optional<TransactionDetail> theTransactions = transactionDetailRepository.findById(dto.getTransactionDetailID());
        TransactionDetail transactionDetail = null;
        if(theTransactions.isPresent()){
            transactionDetail = theTransactions.get();
        }

        transactionDetail.setPaid(true);

        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setInstalment(transactionDetail.getInstalment());
        paymentHistory.setTotalPaid(transactionDetail.getPayAmount());
        paymentHistory.setTransactionDetailId(transactionDetail.getTransactionId());
        paymentHistory.setTransactionDetail(transactionDetail);
        paymentHistory.setPaidDate(LocalDate.now());

        paymentHistoryRepository.save(paymentHistory);

        transactionDetail.setInstalment(transactionDetail.getInstalment() + 1);
        transactionDetail.setPaymentDueDate(transactionDetail.getPaymentDueDate().plusMonths(1));
        transactionDetail.setPaid(false);

        transactionDetailRepository.save(transactionDetail);
        return transactionDetail.getId();
    }
}
