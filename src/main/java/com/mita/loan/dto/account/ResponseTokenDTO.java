package com.mita.loan.dto.account;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter
@ToString
public class ResponseTokenDTO {

    private String username;

    private String role;

    private String token;
}
