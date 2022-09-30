package com.mita.loan.dto.loanOffers;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class UpsertLoanOffersDTO {
    private int id;
    @NotNull
    private int categoryId;
    @NotBlank(message = "Loan Name is required")
    private String loanName;
    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    @NotNull(message = "Range")
    private String  loanTerm;
    @NotNull(message = "Interest")
    private String interestRate;
    @NotNull
    private BigDecimal repayment;
    @NotNull
    private BigDecimal totalPayment;
}
