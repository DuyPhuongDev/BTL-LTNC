package com.ltnc.be.service.common.impl;

import com.ltnc.be.dto.user.request.SaveUserRequest;
import com.ltnc.be.entity.UserEntity;
import com.ltnc.be.repository.UserRepository;
import com.ltnc.be.service.common.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername (check in service} username {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username = " + username));
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        log.info("findUserByUsername (check in service} username {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username = " + username));
    }

    @Override
    public boolean existAdminUser() {
        log.info("existAdminUser (check in service}");
        return userRepository.existsByUsername("admin");
    }

    @Override
    @Transactional
    public void saveUser(SaveUserRequest request) {
        UserEntity user = new UserEntity(request);
        userRepository.save(user);
    }

}
