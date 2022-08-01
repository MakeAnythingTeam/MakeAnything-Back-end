package com.example.MakeAnything.domain.auth.controller;

import com.example.MakeAnything.domain.auth.service.AuthServiceFinder;
import com.example.MakeAnything.domain.auth.service.JwtService;
import com.example.MakeAnything.domain.auth.service.LocalAuthService;
import com.example.MakeAnything.domain.auth.service.RefreshTokenService;
import com.example.MakeAnything.domain.auth.service.dto.LoginLocalRequest;
import com.example.MakeAnything.domain.auth.service.dto.LoginResponse;
import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenRequest;
import com.example.MakeAnything.domain.auth.service.dto.SignUpLocalRequest;
import com.example.MakeAnything.domain.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceFinder authServiceFinder;

    private final JwtService jwtService;

    private final LocalAuthService localAuthService;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/auth/login/local")
    public ApiResponse<LoginResponse> loginLocal(@RequestBody LoginLocalRequest request) {

        return ApiResponse.success(localAuthService.login(request));
    }

    @PostMapping("/auth/signup/local")
    public ApiResponse<LoginResponse> signUpLocal(@RequestBody SignUpLocalRequest request) {

        return ApiResponse.success(localAuthService.signUp(request));
    }

    @PostMapping("/refresh/token")
    public ApiResponse<LoginResponse> refreshAccessToken(@RequestBody RefreshTokenRequest request) {
        return ApiResponse.success(refreshTokenService.refreshAccessToken(request));
    }

    @PostMapping("/auth/logout")
    public ApiResponse<Object> logout() {
        Long userId = jwtService.getUserId();

        refreshTokenService.logout(userId);

        return ApiResponse.success(null);
    }

}
