package com.server.auth.db.repository;

import com.server.auth.db.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserRepository extends JpaRepository<CustomUser, UUID> {
    Optional<CustomUser> findCustomUserByEmail(String email);
    boolean existsCustomUserByEmail(String email);
}
