package com.example.MakeAnything.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@RequiredArgsConstructor
@Component
public class AuthServiceFinder {

    private static final HashMap<String, SocialAuthService> authServiceMap = new HashMap<>();

    private final KakaoAuthService kakaoAuthService;

    @PostConstruct
    void initializeAuthServicesMap() {
        authServiceMap.put("KAKAO", kakaoAuthService);
    }


    public SocialAuthService getAuthService(String socialType) {
        return authServiceMap.get(socialType);
    }

}
