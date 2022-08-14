package com.example.MakeAnything.domain.auth.service;

import com.example.MakeAnything.domain.auth.service.dto.*;

public interface LocalAuthService {

    LoginResponse login(LoginLocalRequest request);

    LoginResponse signUp(SignUpLocalRequest request);

    FindEmailResponse findEmailByPhoneNumber(FindEmailRequest request);

    UpdatePWResponse updatePWByEmail(UpdatePWRequest request);

}
