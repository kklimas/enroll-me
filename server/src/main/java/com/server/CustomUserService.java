package com.server;

import com.server.auth.db.model.CustomUser;
import com.server.auth.db.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configuration
@Service
@RequiredArgsConstructor
public class CustomUserService {

    private final CustomUserRepository customUserRepository;

    public CustomUser findUserByUserEmail(String email) {
        return customUserRepository.findCustomUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: %s was not found.", email)));
    }
}
