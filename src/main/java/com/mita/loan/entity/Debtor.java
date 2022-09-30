package com.mita.loan.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Debtor")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class Debtor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DebtorID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Username")
    private Account account;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "IDCardNumber")
    private String idCardNumber;

    @Column(name = "Age")
    private long age;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Address")
    private String address;

    @Column(name = "ContactNumber")
    private String contactNumber;

    @Column(name = "Company")
    private String company;

    @Column(name = "Professions")
    private String profession;



}
