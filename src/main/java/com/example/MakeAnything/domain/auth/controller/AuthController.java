package com.example.MakeAnything.domain.auth.controller;

import com.example.MakeAnything.domain.auth.service.*;
import com.example.MakeAnything.domain.auth.service.dto.*;
import com.example.MakeAnything.domain.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PostMapping("/auth/login/social")
    public ApiResponse<LoginResponse> loginSocial(@RequestBody LoginSocialRequest request) {

        SocialAuthService socialAuthService = authServiceFinder.getAuthService(request.getSocialType());

        return ApiResponse.success(socialAuthService.login(request));
    }

    @PostMapping("/auth/signup/social")
    public ApiResponse<LoginResponse> signUpSocial(@RequestBody SignUpSocialRequest request) {

        SocialAuthService socialAuthService = authServiceFinder.getAuthService(request.getSocialType());

        return ApiResponse.success(socialAuthService.signUp(request));
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

    @PostMapping("/auth/find-email")
    public ApiResponse<FindEmailResponse> findEmailByPhoneNumber(@RequestBody FindEmailRequest request) {

        return ApiResponse.success(localAuthService.findEmailByPhoneNumber(request));
    }

    @PatchMapping("/auth/update-pw")
    public ApiResponse<UpdatePWResponse> updatePWByEmail(@RequestBody UpdatePWRequest request) {

        return ApiResponse.success(localAuthService.updatePWByEmail(request));
    }

}
