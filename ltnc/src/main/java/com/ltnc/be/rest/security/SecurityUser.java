package com.ltnc.be.rest.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.ltnc.be.domain.user.User;
import com.ltnc.be.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {
  private Long id;
  @JsonIgnore private String password;
  private Collection<? extends GrantedAuthority> authorities;
  private User user;

  private static final String ROLE_PREFIX = "ROLE_%s";

  public static SecurityUser build(User user) {
    final List<GrantedAuthority> authorities = new LinkedList<>();
    if (user.isAdmin()) authorities.add(buildRole(UserRole.ADMIN.name()));
    else if (user.isMember()) authorities.add(buildRole(UserRole.MEMBER.name()));

    return new SecurityUser(user.getId(), user.getPassword(), authorities, user);
  }

  private static GrantedAuthority buildRole(String role) {
    return new SimpleGrantedAuthority(String.format(ROLE_PREFIX, role));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return String.valueOf(this.id);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
