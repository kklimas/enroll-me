package com.server.auth.mapper;

import com.server.auth.db.model.CustomUser;
import com.server.auth.dto.UserRegisterDTO;
import com.server.auth.enums.CustomUserRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomUserMapper {

    public static UserDetails toDetails(CustomUser user) {
        var authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    public static CustomUser fromDTO(UserRegisterDTO registerDTO) {
        return CustomUser.builder()
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword())
                .role(CustomUserRole.ROLE_USER)
                .address(registerDTO.getAddress())
                .joinDate(new Date(System.currentTimeMillis()))
                .build();
    }
}
