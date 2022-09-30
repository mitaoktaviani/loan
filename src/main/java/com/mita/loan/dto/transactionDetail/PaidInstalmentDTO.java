package com.mita.loan.dto.transactionDetail;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class PaidInstalmentDTO {
    @NotNull
    private int transactionDetailID;
}
