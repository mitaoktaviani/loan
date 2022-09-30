package com.mita.loan.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Transactional")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private int id;

    @Column(name = "IsPending")
    private boolean pending;
    @Column(name = "IsReject")
    private boolean reject;
    @Column(name = "IsAccept")
    private boolean accept;

    @Column(name = "LoanID")
    private int loanId;


    @Column(name = "DebtorID")
    private int debtorId;

    @ManyToOne
    @JoinColumn(name = "LoanID",
            insertable = false,
            updatable = false)
    private LoanOffers loanOffers;

    @ManyToOne
    @JoinColumn(name = "DebtorID",
            insertable = false,
            updatable = false)
    private Debtor debtor;


    @Column(name = "LoanApplicationDate")
    private LocalDate loanDate;

}
