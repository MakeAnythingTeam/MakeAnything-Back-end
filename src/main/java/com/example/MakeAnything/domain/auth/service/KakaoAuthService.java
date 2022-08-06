package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.service.dto.LoginResponse;
import com.example.MakeAnything.domain.auth.service.dto.LoginSocialRequest;
import com.example.MakeAnything.domain.auth.service.dto.SignUpSocialRequest;
import org.springframework.stereotype.Service;

@Service
public class KakaoAuthService implements SocialAuthService {
    @Override
    public LoginResponse login(LoginSocialRequest request) {
        return null;
    }

    @Override
    public LoginResponse signUp(SignUpSocialRequest request) {
        return null;
    }
}
