package com.mita.loan.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "LoanOffers")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class LoanOffers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanID")
    private int id;

    @Column(name = "LoanName")
    private String loanName;

    @Column(name = "LoanAmount")
    private BigDecimal amount;

    @Column(name = "LoanTerm")
    private String loanTerm;

    @Column(name = "InterestRate")
    private String interestRate;

    @Column(name = "Repayment")
    private BigDecimal repayment;

    @Column(name = "TotalPayment")
    private BigDecimal totalCost;

    @Column(name = "CategoryID")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name = "CategoryID",
            insertable = false,
            updatable = false)
    private Categories categories;

}
