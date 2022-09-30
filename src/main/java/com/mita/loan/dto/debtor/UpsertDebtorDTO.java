package com.mita.loan.dto.debtor;

import com.mita.loan.validation.Compare;
import com.mita.loan.validation.UniqueUsername;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
@Compare(firstField = "password", secondField = "confirmPassword", message = "Password did not match")
public class UpsertDebtorDTO {

    private int id;

    @UniqueUsername(message = "Username already exist")
    private String username;

    private String password;
    private String confirmPassword;

    @NotBlank(message = "First Name is required")
    private String firstName;

    private String lastName;

    @NotBlank(message = "ID Card Number is required")
    private String idCardNumber;

    @NotNull(message = "Birth Date is required")
    @Min(18)
    private long age;

    @NotBlank(message = "Gender is required")
    private String gender;

    private String address;
    private String contactNumber;
    private String company;
    private String profession;

}
