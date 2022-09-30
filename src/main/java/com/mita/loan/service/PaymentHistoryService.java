package com.mita.loan.service;

import com.mita.loan.dto.paymentHistory.PaymentHistoryDTO;
import com.mita.loan.entity.PaymentHistory;

import java.util.List;

public interface PaymentHistoryService {
    List<PaymentHistoryDTO> getAllHistory(String username);
}
