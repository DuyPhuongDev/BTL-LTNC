package com.ltnc.be.rest.security;

import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.exception.PermissionDeniedException;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {
  private final UserRepository userRepository;

  private static final String ANONYMOUS_USER = "anonymousUser";

  @SneakyThrows
  public static User getCurrentUser() {
    var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (ANONYMOUS_USER.equals(principal)) throw new PermissionDeniedException();
    var securityUser = (SecurityUser) principal;

    return securityUser.getUser();
  }

  @Override
  @SneakyThrows
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    var user =
        this.userRepository.findById(Long.parseLong(id)).orElseThrow(EntityNotFoundException::new);
    return SecurityUser.build(user);
  }
}
