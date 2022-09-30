package com.mita.loan.service;

import com.mita.loan.dto.paymentHistory.PaymentHistoryDTO;
import com.mita.loan.entity.PaymentHistory;
import com.mita.loan.repository.PaymentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService{

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public List<PaymentHistoryDTO> getAllHistory(String username) {
        List<PaymentHistoryDTO> histories = paymentHistoryRepository.getHistory(username);

        return histories;
    }
}
