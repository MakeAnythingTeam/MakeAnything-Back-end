package com.example.MakeAnything.domain.auth.service.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private Long userId;

    private String accessToken;

    private String refreshToken;

    public static LoginResponse of(Long userId, String accessToken, String refreshToken) {
        return new LoginResponse(userId, accessToken, refreshToken);
    }
}
