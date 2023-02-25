package com.server.enrollment.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EnrollmentCreationDTO {
    private String description;
    private List<UUID> subjectIds;
}
