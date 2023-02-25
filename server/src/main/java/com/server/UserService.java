package com.server;

import com.server.auth.db.model.User;
import com.server.auth.db.repository.UserRepository;
import com.server.enrollment.db.enrollment.model.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Configuration
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id: %s was not found.", id)));
    }

    public List<User> findUsersByIds(List<UUID> uuids) {
        return uuids.stream()
                .map(this::findUserById)
                .toList();
    }

    public User findUserByUserEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: %s was not found.", email)));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
