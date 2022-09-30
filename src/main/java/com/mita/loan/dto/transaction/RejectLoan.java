package com.mita.loan.dto.transaction;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class RejectLoan {
    @NotNull
    private int transactionId;
}
