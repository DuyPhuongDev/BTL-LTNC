package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.exception.EntityAlreadyExistedException;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.exception.PasswordNotMatchException;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.domain.user.UserRole;
import com.ltnc.be.port.facade.AccountFacade;
import com.ltnc.be.port.repository.UserRepository;
import com.ltnc.be.port.service.JwtService;
import com.ltnc.be.rest.request.LoginRequest;
import com.ltnc.be.rest.request.UpsertUserRequest;
import com.ltnc.be.rest.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class AccountFacadeImpl implements AccountFacade {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public LoginResponse login(LoginRequest request) {
        User user =
                userRepository.findByUsername(request.getUsername()).orElseThrow(EntityNotFoundException::new);
        if (!BCrypt.checkpw(request.getPassword(), user.getPassword()))
            throw new PasswordNotMatchException();

        String accessToken = jwtService.generateToken(user.getId().toString());

        return LoginResponse.builder().accessToken(accessToken).userId(user.getId()).build();
    }

    @Override
    @SneakyThrows
    public void signUp(UpsertUserRequest request) {
        var existedUser =
                this.userRepository.findByUsername(request.getUsername());
        if (existedUser.isPresent())
            throw new EntityAlreadyExistedException(
                    "Username is already existed in the system");

        User newUser =
                User.builder()
                        .username(request.getUsername())
                        .email(request.getEmail())
                        .fullName(request.getFullName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(UserRole.MEMBER)
                        .build();
        userRepository.save(newUser);
    }
}
