package com.server.enrollment.dto;

import com.server.enrollment.db.preference.model.EventPreference;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SubjectPreferencesDTO {
    private UUID id;
    private List<EventPreference> eventPreferences;
}
