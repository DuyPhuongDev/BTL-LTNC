package com.ltnc.be.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

import com.ltnc.be.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
  // TODO: For team members, dto class is a transfer class
  // We only return necessary information by use-case, don't expose too much
  private Long userId;
  private String userRole;
  private String userEmail;
  private Date userDob;
  private String userPhoneNumber;

  public static UserResponse toUserResponse(UserDTO user) {
    return UserResponse.builder()
        .userId(user.getUserId())
        .userRole(user.getUserRole())
        .userEmail(user.getUserEmail())
        .userDob(user.getUserDob())
        .userPhoneNumber(user.getUserPhoneNumber())
        .build();
  }
}