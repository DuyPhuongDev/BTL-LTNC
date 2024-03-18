package com.ltnc.be.service;

import org.springframework.security.core.Authentication;

import java.security.Key;

public interface JwtService {
  Boolean validateToken(String token);

  String generateToken(Authentication authentication);

  String generateToken(String principal, Long jwtExpirationMs);

  String generateToken(String principal);

  Key generateSecretKey();

  String extractCredential(String token);

  String extractPrincipal(Authentication authentication);
}