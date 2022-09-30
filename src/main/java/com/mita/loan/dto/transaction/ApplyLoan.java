package com.mita.loan.dto.transaction;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class ApplyLoan {
    private int id;
    @NotNull
    private int loanId;

}
