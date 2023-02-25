package com.server.web;

import com.server.enrollment.EnrollmentService;
import com.server.enrollment.dto.EnrollmentCreationDTO;
import com.server.enrollment.dto.EnrollmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentDTO> findAllEnrollments() {
        return enrollmentService.findAllEnrollments();
    }

    @GetMapping("{id}")
    public List<EnrollmentDTO> findUserEnrollments(@PathVariable UUID id) {
        return enrollmentService.findUserEnrollments(id);
    }

    @PostMapping
    public void createEnrollment(@RequestBody EnrollmentCreationDTO enrollmentCreationDTO) {
        enrollmentService.createEnrollment(enrollmentCreationDTO);
    }
}
