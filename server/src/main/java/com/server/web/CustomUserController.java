package com.server.web;

import com.server.auth.db.model.CustomUser;
import com.server.auth.db.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CustomUserController {
    private final CustomUserRepository customUserRepository;
    @GetMapping
    public List<CustomUser> findAllUsers() {
        return customUserRepository.findAll();
    }
}
