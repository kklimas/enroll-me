package com.server.auth;

import com.server.auth.db.repository.CustomUserRepository;
import com.server.auth.dto.AuthResponseDTO;
import com.server.auth.dto.UserLoginDTO;
import com.server.auth.dto.UserRegisterDTO;
import com.server.auth.jwt.JwtService;
import com.server.exception.UserExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.server.auth.mapper.CustomUserMapper.fromDTO;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserRepository customUserRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtUtils;

    public AuthResponseDTO login(UserLoginDTO userLoginDTO) {
        authenticate(userLoginDTO);
        var user = userDetailsService.loadUserByUsername(userLoginDTO.getEmail());
        return givenAuthResponse(user);
    }

    public AuthResponseDTO register(UserRegisterDTO userRegisterDTO) {
        isEmailTaken(userRegisterDTO.getEmail());
        var encodedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());
        userRegisterDTO.setPassword(encodedPassword);
        customUserRepository.save(fromDTO(userRegisterDTO));
        var user = userDetailsService.loadUserByUsername(userRegisterDTO.getEmail());
        return givenAuthResponse(user);
    }

    private void isEmailTaken(String email) {
        var isEmailTaken = customUserRepository.existsCustomUserByEmail(email);
        if (isEmailTaken) {
            throw new UserExistsException("User with given email already exists.");
        }
    }

    private void authenticate(UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );
    }

    private AuthResponseDTO givenAuthResponse(UserDetails user) {
        var accessToken = jwtUtils.generateToken(user, true);
        var refreshToken = jwtUtils.generateToken(user, false);
        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
