package com.mita.loan.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "PaymentHistory")
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HistoryPaymentID")
    private int id;

    @Column(name = "TransactionDetailID")
    private int transactionDetailId;

    @ManyToOne
    @JoinColumn(name = "TransactionDetailID",
            insertable = false,
            updatable = false)
    private TransactionDetail transactionDetail;

    @Column(name = "Instalment")
    private int instalment;

    @Column(name = "TotalPaid")
    private BigDecimal totalPaid;
}
