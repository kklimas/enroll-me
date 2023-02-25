package com.server.config;

import com.server.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.server.auth.mapper.UserMapper.toDetails;


@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig {
    private final UserService userAuthService;

    @Bean
    public UserDetailsService findUserByUsername() {
        return email -> toDetails(userAuthService.findUserByUserEmail(email));
    }
}
