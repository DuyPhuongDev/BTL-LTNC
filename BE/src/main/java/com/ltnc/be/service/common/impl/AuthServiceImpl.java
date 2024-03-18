package com.ltnc.be.service.common.impl;

import com.lgsi.lgsibe.dto.jwt.request.LoginRequest;
import com.lgsi.lgsibe.dto.jwt.response.JwtResponse;
import com.lgsi.lgsibe.entity.UserEntity;
import com.lgsi.lgsibe.exception.CommonException;
import com.lgsi.lgsibe.exception.enums.ErrorCode;
import com.lgsi.lgsibe.service.common.AuthService;
import com.lgsi.lgsibe.service.common.JwtService;
import com.lgsi.lgsibe.service.common.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public JwtResponse login(LoginRequest request) {
        log.info("login(check in service) request : {}", request);
        UserEntity user = userService.findUserByUsername(request.getUsername());
        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())){
            throw new CommonException(ErrorCode.PASSWORD_DOES_NOT_MATCH);
        }
        return new JwtResponse(jwtService.generateToken(request.getUsername()), LocalDateTime.now());
    }

}
