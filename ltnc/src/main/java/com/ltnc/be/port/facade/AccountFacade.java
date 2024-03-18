package com.ltnc.be.port.facade;

import com.ltnc.be.rest.request.LoginRequest;
import com.ltnc.be.rest.request.UpsertUserRequest;
import com.ltnc.be.rest.response.LoginResponse;

public interface AccountFacade {
  LoginResponse login(LoginRequest request);

  void signUp(UpsertUserRequest request);
}
