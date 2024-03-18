package com.ltnc.be.rest.interceptor;

import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.service.JsonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@ControllerAdvice
@RequiredArgsConstructor
public class SecurityEntryPointInterceptor implements AuthenticationEntryPoint {
  private final JsonService jsonService;

  private static final String MISSING_CREDENTIAL_EXCEPTION =
      "You're missing credential to access this resource because full authentication is required";

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    if (authException != null) {
      response
          .getWriter()
          .write(
              jsonService.toJson(
                  BaseResponse.error(
                      MISSING_CREDENTIAL_EXCEPTION,
                      ExceptionUtils.getRootCauseMessage(authException),
                      Arrays.toString(authException.getStackTrace()))));
    }
  }
}
