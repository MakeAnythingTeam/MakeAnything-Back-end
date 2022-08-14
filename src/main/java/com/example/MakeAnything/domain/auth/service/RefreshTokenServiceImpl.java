package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.model.RefreshToken;
import com.example.MakeAnything.domain.auth.repository.RefreshTokenRepository;
import com.example.MakeAnything.domain.auth.service.dto.LoginResponse;
import com.example.MakeAnything.domain.auth.service.dto.RefreshTokenRequest;
import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Override
    public LoginResponse refreshAccessToken(RefreshTokenRequest request) {

        jwtService.validateRefreshToken(request.getRefreshToken());

        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_AUTH_TOKEN));

        User user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_USER));

        String accessToken = jwtService.createJwt(user.getId());

        return LoginResponse.of(user.getId(), accessToken, request.getRefreshToken());
    }

    @Override
    public void logout(Long userId) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_REFRESH_TOKEN));

        refreshTokenRepository.delete(refreshToken);
    }
}
