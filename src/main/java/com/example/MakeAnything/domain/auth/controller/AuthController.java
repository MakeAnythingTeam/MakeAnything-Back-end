package com.example.MakeAnything.domain.auth.controller;

import com.example.MakeAnything.domain.auth.service.AuthService;
import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenRequest;
import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenResponse;
import com.example.MakeAnything.domain.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ApiResponse<RefreshTokenResponse> refreshAccessToken(HttpServletRequest request, HttpServletResponse response,
                                                                @RequestBody RefreshTokenRequest tokenRequest) {
        return ApiResponse.success(authService.refreshAccessToken(request, response, tokenRequest.getAccessToken()));
    }
}
