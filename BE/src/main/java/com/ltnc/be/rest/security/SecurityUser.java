package com.ltnc.be.rest.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {
  private Long id;
  private String email;
  @JsonIgnore private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static SecurityUser build(User user) {
    final List<GrantedAuthority> authorities = new LinkedList<>();
    if (user.isAdmin()) authorities.add(buildAuthority(UserRole.ADMIN.name()));
    else if (user.isMember()) authorities.add(buildAuthority(UserRole.MEMBER.name()));

    return new SecurityUser(user.getId(), user.getEmail(), user.getPassword(), authorities);
  }

  private static GrantedAuthority buildAuthority(String authority) {
    return new SimpleGrantedAuthority(authority);
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
    return this.email;
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
