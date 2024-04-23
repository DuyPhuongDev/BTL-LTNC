package com.ltnc.be.rest.response;

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
}
