package com.ltnc.be.rest.filter;

import com.ltnc.be.rest.security.SecurityUserService;
import com.ltnc.be.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
  private static final String AUTH_HEADER = "Authorization";
  private static final String AUTH_BEARER = "Bearer ";
  private static final Integer JWT_POSITION = 7;

  private final JwtService jwtService;
  private final SecurityUserService securityUserService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    var token = parseJwtFromRequestHeader(request);
    if (jwtService.validateToken(token)) {
      var email = jwtService.extractCredential(token);
      var principal = securityUserService.loadUserByUsername(email);
      var authentication =
          new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String parseJwtFromRequestHeader(HttpServletRequest request) {
    var header = request.getHeader(AUTH_HEADER);
    if (StringUtils.hasText(header) && header.startsWith(AUTH_BEARER))
      return header.substring(JWT_POSITION);
    return Strings.EMPTY;
  }
}
