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
import com.example.MakeAnything.external.client.naver.NaverApiClient;
import com.example.MakeAnything.external.client.naver.dto.NaverUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverAuthService implements SocialAuthService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final NaverApiClient naverApiClient;

    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginSocialRequest request) {
        NaverUserResponse userInfo = naverApiClient.getUserInfo(request.getToken());

        User user = userRepository.findBySocialId(userInfo.getId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_USER));

        String accessToken = jwtService.createJwt(user.getId());
        String refreshToken = jwtService.createRefreshToken();

        refreshTokenRepository.save(RefreshToken.of(user.getId(), refreshToken));

        return LoginResponse.of(user.getId(), accessToken, refreshToken);
    }

    @Override
    public LoginResponse signUp(SignUpSocialRequest request) {

        NaverUserResponse userInfo = naverApiClient.getUserInfo(request.getToken());

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
