package com.mita.loan.dto.account;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class RequestTokenDTO {

    private String username;

    private String password;

    private String subject;

    private String secretKey;

    private String audience;

}
