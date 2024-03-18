package com.ltnc.be.service;

import com.ltnc.be.dto.jwt.request.LoginRequest;
import com.ltnc.be.dto.jwt.response.JwtResponse;

public interface AuthService {

    JwtResponse login(LoginRequest request);

}