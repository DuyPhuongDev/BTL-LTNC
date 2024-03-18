package com.ltnc.be.domain.user;

import com.ltnc.be.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends BaseEntity {
  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private UserRole role;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "dob")
  @Temporal(TemporalType.DATE)
  private Date dob;

  @Column(name = "phone_number")
  private String phoneNumber;

  public boolean isMember() {
    return UserRole.MEMBER.equals(this.role);
  }

  public boolean isAdmin() {
    return UserRole.ADMIN.equals(this.role);
  }

  private User(String email, String password, UserRole role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public static User createMember(String email, String password) {
    return new User(email, password, UserRole.MEMBER);
  }

  public static User createAdmin(String email, String password) {
    return new User(email, password, UserRole.ADMIN);
  }
}
