package com.mita.loan.dto.transaction;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class AcceptLoan {
    @NotNull
    private int transactionId;
    @NotNull
    private LocalDate paymentDueDate;
    @NotNull
    private BigDecimal payAmount;

}
