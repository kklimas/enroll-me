package com.server.auth.db.repository;

import com.server.auth.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
