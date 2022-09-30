package com.mita.loan.dto.paymentHistory;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class PaymentHistoryDTO {
    private int historyId;
    private int instalment;
    private BigDecimal totalPaid;
    private LocalDate paidDate;
    private int transactionDetailID;
}
