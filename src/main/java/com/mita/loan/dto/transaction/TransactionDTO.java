package com.mita.loan.dto.transaction;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class TransactionDTO {
    private int transactionId;
    private int loanId;
    private int debtorId;
    private boolean pending;
    private boolean reject;
    private boolean accept;
}
