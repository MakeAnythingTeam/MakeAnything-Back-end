package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.service.dto.LoginLocalRequest;
import com.example.MakeAnything.domain.auth.service.dto.LoginResponse;
import com.example.MakeAnything.domain.auth.service.dto.SignUpLocalRequest;

public interface LocalAuthService {

    LoginResponse login(LoginLocalRequest request);

    LoginResponse signUp(SignUpLocalRequest request);

}
