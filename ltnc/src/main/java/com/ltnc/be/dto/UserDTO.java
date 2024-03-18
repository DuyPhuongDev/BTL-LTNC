package com.ltnc.be.dto;

import java.util.Date;

import com.ltnc.be.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private Long userId;
  private String userRole;
  private String userEmail;
  private Date userDob;
  private String userPhoneNumber;

  public static UserDTO fromDomain(User user) {
    return UserDTO.builder()
        .userId(user.getId())
        .userRole(user.getRole().name())
        .userEmail(user.getEmail())
        .userDob(user.getDob())
        .userPhoneNumber(user.getPhoneNumber())
        .build();
  }
}
