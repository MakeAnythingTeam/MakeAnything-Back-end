package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.model.CustomUserDetails;
import com.example.MakeAnything.domain.auth.model.RefreshToken;
import com.example.MakeAnything.domain.auth.repository.RefreshTokenRepository;
import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenResponse;
import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Value("${app.auth.token.refresh-cookie-key}")
    private String cookieKey;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public RefreshTokenResponse refreshAccessToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {

        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_REFRESH_TOKEN));

        if (!jwtTokenProvider.validateToken(oldRefreshToken)) {
            throw new BaseException(ErrorCode.INVALID_AUTH_TOKEN);
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        RefreshToken refreshToken = refreshTokenRepository.findById(user.getName()).get();
        String savedToken = refreshToken.getRefreshToken();

        if (!savedToken.equals(oldRefreshToken)) {
            throw new BaseException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        jwtTokenProvider.createRefreshToken(authentication, response);

        return RefreshTokenResponse.of(accessToken);
    }
}
