package com.ltnc.be.rest.controller;

import com.ltnc.be.port.facade.AccountFacade;
import com.ltnc.be.rest.request.LoginRequest;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {
  private final AccountFacade accountFacade;

  @PostMapping("/login")
  @Operation(tags = "Account APIs")
  @ResponseStatus(HttpStatus.OK)
  BaseResponse<LoginResponse> login(LoginRequest request) {
    return BaseResponse.of(accountFacade.login(request));
  }
}
