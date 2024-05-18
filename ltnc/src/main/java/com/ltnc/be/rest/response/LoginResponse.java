package com.ltnc.be.rest.response;

import com.ltnc.be.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {
  private String accessToken;
  private Long userId;
  private String name;
  private UserRole role;
}
