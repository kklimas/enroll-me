package com.server.config;

import com.server.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.server.auth.mapper.CustomUserMapper.toDetails;


@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig {
    private final CustomUserService userAuthService;

    @Bean
    public UserDetailsService findUserByUsername() {
        return email -> toDetails(userAuthService.findUserByUserEmail(email));
    }
}
