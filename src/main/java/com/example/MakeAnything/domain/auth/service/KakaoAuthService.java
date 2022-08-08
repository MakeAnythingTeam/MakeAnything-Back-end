package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.model.RefreshToken;
import com.example.MakeAnything.domain.auth.repository.RefreshTokenRepository;
import com.example.MakeAnything.domain.auth.service.dto.LoginResponse;
import com.example.MakeAnything.domain.auth.service.dto.LoginSocialRequest;
import com.example.MakeAnything.domain.auth.service.dto.SignUpSocialRequest;
import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import com.example.MakeAnything.external.client.kakao.KakaoApiClient;
import com.example.MakeAnything.external.client.kakao.dto.KakaoUserResponse;
import com.example.MakeAnything.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService implements SocialAuthService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final KakaoApiClient kakaoApiClient;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginSocialRequest request) {
        KakaoUserResponse userInfo = kakaoApiClient.getUserInfo(request.getToken());

        User user = userRepository.findBySocialId(userInfo.getId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_USER));

        String accessToken = jwtService.createJwt(user.getId());
        String refreshToken = jwtService.createRefreshToken();

        refreshTokenRepository.save(RefreshToken.of(user.getId(), refreshToken));

        return LoginResponse.of(user.getId(), accessToken, refreshToken);
    }

    @Override
    public LoginResponse signUp(SignUpSocialRequest request) {

        KakaoUserResponse userInfo = kakaoApiClient.getUserInfo(request.getToken());

        if (userRepository.existsBySocialId(userInfo.getId())) {
            throw new BaseException(ErrorCode.CONFLICT_USER);
        }

        User user = userRepository.save(User.newSocialInstance(request, userInfo.getId() ,userInfo.getEmail()));
        String accessToken = jwtService.createJwt(user.getId());
        String refreshToken = jwtService.createRefreshToken();

        refreshTokenRepository.save(RefreshToken.of(user.getId(), refreshToken));

        return LoginResponse.of(user.getId(), accessToken, refreshToken);
    }
}
