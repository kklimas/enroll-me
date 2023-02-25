package com.server.enrollment.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EnrollUserDTO {
    private List<UUID> userIds;
    private UUID enrollmentId;
}
