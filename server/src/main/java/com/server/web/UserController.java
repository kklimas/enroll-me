package com.server.web;

import com.server.auth.UserService;
import com.server.auth.db.model.User;
import com.server.enrollment.EnrollmentService;
import com.server.enrollment.dto.EnrollUserDTO;
import com.server.enrollment.dto.UserPreferencesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final EnrollmentService enrollmentService;
    private final UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/enroll")
    public void enrollUsers(@RequestBody EnrollUserDTO enrollUserDTO) {
        enrollmentService.enrollUsers(enrollUserDTO);
    }

    @DeleteMapping("/unroll")
    public void unrollUsers(@RequestBody EnrollUserDTO unrollUserDTO) {
        enrollmentService.unrollUsers(unrollUserDTO);
    }

    @PostMapping("/preferences/{username}")
    public void saveUserPreferences(@PathVariable String username, @RequestBody UserPreferencesDTO preferencesDTO) {
        enrollmentService.saveUserPreferences(username, preferencesDTO);
    }
}
