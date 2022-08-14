package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.service.dto.LoginResponse;
import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenRequest;

public interface RefreshTokenService {

    LoginResponse refreshAccessToken(RefreshTokenRequest request);

    void logout(Long userId);
}
