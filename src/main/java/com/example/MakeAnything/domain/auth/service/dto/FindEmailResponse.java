package com.example.MakeAnything.domain.auth.service.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FindEmailResponse {

    private String email;

    public static FindEmailResponse of(String email) {
        return new FindEmailResponse(email);
    }
}
