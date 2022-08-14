package com.example.MakeAnything.domain.auth.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpLocalRequest {

    private String email;

    private String password;

    private String userName;

    private String nickName;

    private String phoneNumber;

    private String address;
}
