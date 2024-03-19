package com.ltnc.be.port.repository;

import com.ltnc.be.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
