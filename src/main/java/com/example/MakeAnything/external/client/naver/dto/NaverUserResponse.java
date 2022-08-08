package com.example.MakeAnything.external.client.naver.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class NaverUserResponse {
    private Response response;

    @ToString
    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class Response {
        private String id;
        private String nickname;
        private String email;
    }

    public String getNickName() {
        return this.response.getNickname();
    }

    public String getId() {
        return this.response.getId();
    }

    public String getEmail() {
        return this.response.getEmail();
    }
}
