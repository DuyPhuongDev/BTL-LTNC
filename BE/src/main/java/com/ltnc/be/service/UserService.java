package com.ltnc.be.service;

import com.ltnc.be.dto.user.request.SaveUserRequest;
import com.ltnc.be.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity findUserByUsername(String username);

    boolean existAdminUser();

    void saveUser(SaveUserRequest request);

}
