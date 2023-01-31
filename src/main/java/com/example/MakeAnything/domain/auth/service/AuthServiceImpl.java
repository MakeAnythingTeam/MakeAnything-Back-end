package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.model.CustomUserDetails;
import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenResponse;
import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.user.repository.UserRepository;
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

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public RefreshTokenResponse refreshAccessToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {

        String oldRefreshToken = CookieUtil.getCookie(request, cookieKey)
                .map(Cookie::getValue)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_REFRESH_TOKEN));

        if (!jwtTokenProvider.validateToken(oldRefreshToken)) {
            throw new BaseException(ErrorCode.INVALID_AUTH_TOKEN);
        }


        System.out.println("oldRefreshToken = " + oldRefreshToken);
        System.out.println("oldAccessToken = " + oldAccessToken);

        Authentication authentication = jwtTokenProvider.getAuthentication(oldAccessToken);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Long id = Long.valueOf(user.getName());

        String savedToken = userRepository.getRefreshTokenById(id);

        if (!savedToken.equals(oldRefreshToken)) {
            throw new RuntimeException("Not Matched Refresh Token");
        }

        // 4. JWT 갱신
        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        jwtTokenProvider.createRefreshToken(authentication, response);

        return RefreshTokenResponse.of(accessToken);
    }
}
