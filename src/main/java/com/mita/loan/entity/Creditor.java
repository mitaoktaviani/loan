package com.mita.loan.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Creditor")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class Creditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CreditorID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Username")
    private Account account;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "JobTitle")
    private String jobTitle;

    @Column(name = "Address")
    private String address;

}
