package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    RefreshTokenResponse refreshAccessToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken);

}
