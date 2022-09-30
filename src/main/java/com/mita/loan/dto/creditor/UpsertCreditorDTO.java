package com.mita.loan.dto.creditor;

import com.mita.loan.validation.Compare;
import com.mita.loan.validation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
@Compare(firstField = "password", secondField = "confirmPassword", message = "Password did not match")
public class UpsertCreditorDTO {

    private int id;
    @UniqueUsername(message = "Username already exist")
    private String username;
    private String password;
    private String confirmPassword;
    @NotBlank(message = "First Name is required")
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String address;
}
