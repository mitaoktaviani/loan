package com.mita.loan.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TransactionDetail")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionDetailID")
    private int id;

    @Column(name = "TransactionID")
    private int transactionId;
    @ManyToOne
    @JoinColumn(name = "TransactionID",
            insertable = false,
            updatable = false)
    private Transaction transaction;

    @Column(name = "PaymentDueDate")
    private LocalDate paymentDueDate;

    @Column(name = "PayAmount")
    private BigDecimal payAmount;

    @Column(name = "CreditorID")
    private int creditorId;

    @ManyToOne
    @JoinColumn(name = "CreditorID",
            insertable = false,
            updatable = false)
    private Creditor creditor;

    @Column(name = "Term")
    private String term;

    @Column(name = "Instalment")
    private int instalment;

    @Column(name = "IsPaid")
    private boolean isPaid;


}
