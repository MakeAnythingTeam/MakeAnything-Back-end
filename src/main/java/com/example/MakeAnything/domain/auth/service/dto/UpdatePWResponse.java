package com.example.MakeAnything.domain.auth.service.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePWResponse {

    private String password;

    public static UpdatePWResponse of(String password) {
        return new UpdatePWResponse(password);
    }
}
