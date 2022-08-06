package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.model.RefreshToken;
import com.example.MakeAnything.domain.auth.repository.RefreshTokenRepository;
import com.example.MakeAnything.domain.auth.service.dto.LoginLocalRequest;
import com.example.MakeAnything.domain.auth.service.dto.LoginResponse;
import com.example.MakeAnything.domain.auth.service.dto.SignUpLocalRequest;
import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import com.example.MakeAnything.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalAuthServiceImpl implements LocalAuthService{

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtService jwtService;

    public LoginResponse login(LoginLocalRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_USER));

        String encryptPassword = new SHA256().encrypt(request.getPassword());

        if (user.getPassword().equals(encryptPassword)) {
            String accessToken = jwtService.createJwt(user.getId());
            String refreshToken = jwtService.createRefreshToken();

            refreshTokenRepository.save(RefreshToken.of(user.getId(), refreshToken));

            return LoginResponse.of(user.getId(), accessToken, refreshToken);
        } else {
            throw new BaseException(ErrorCode.FAIL_TO_LOGIN);
        }
    }

    @Override
    public LoginResponse signUp(SignUpLocalRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BaseException(ErrorCode.CONFLICT_USER);
        }

        String encryptPassword = new SHA256().encrypt(request.getPassword());

        User user = userRepository.save(User.newLocalInstance(request, encryptPassword));
        String accessToken = jwtService.createJwt(user.getId());
        String refreshToken = jwtService.createRefreshToken();

        refreshTokenRepository.save(RefreshToken.of(user.getId(), refreshToken));

        return LoginResponse.of(user.getId(), accessToken, refreshToken);
    }


}
