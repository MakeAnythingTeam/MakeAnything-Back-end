package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.model.RefreshToken;
import com.example.MakeAnything.domain.auth.repository.RefreshTokenRepository;
import com.example.MakeAnything.domain.auth.service.dto.*;
import com.example.MakeAnything.domain.common.exception.BaseException;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import com.example.MakeAnything.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Override
    public FindEmailResponse findEmailByPhoneNumber(FindEmailRequest request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_USER));

        return FindEmailResponse.of(user.getEmail());
    }

    @Transactional
    @Override
    public UpdatePWResponse updatePWByEmail(UpdatePWRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_USER));

        String tempPassword = getTempPassword();

        String encryptPassword = new SHA256().encrypt(tempPassword);

        System.out.println("encryptPassword = " + encryptPassword);

        user.updatePassword(encryptPassword);

        return UpdatePWResponse.of(tempPassword);
    }

    private String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String tempPassword = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            tempPassword += charSet[idx];
        }
        return tempPassword;
    }

}
