package com.server.auth.mapper;

import com.server.auth.db.model.User;
import com.server.auth.dto.UserRegisterDTO;
import com.server.auth.enums.UserRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserDetails toDetails(User user) {
        var authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public static User fromDTO(UserRegisterDTO registerDTO) {
        return User.builder()
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .role(UserRole.ROLE_USER)
                .address(registerDTO.getAddress())
                .joinDate(new Date(System.currentTimeMillis()))
                .build();
    }
}
