package com.server.enrollment.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserPreferencesDTO {
    private UUID enrollmentId;
    private List<SubjectPreferencesDTO> subjectPreferences;
}
