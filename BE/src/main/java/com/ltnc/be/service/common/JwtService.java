package com.ltnc.be.service.common;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {

    public String generateToken(String userName);

    public String extractUsername(String token);

    public Date extractExpiration(String token);

    public Boolean validateToken(String token, UserDetails userDetails);

}
