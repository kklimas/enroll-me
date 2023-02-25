package com.server.enrollment.mapper;

import com.server.enrollment.db.preference.model.Preference;
import com.server.enrollment.dto.SubjectPreferencesDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PreferenceMapper {
    public static Preference fromDTO(SubjectPreferencesDTO subjectPreference) {
        var preferencesSet = new HashSet<>(subjectPreference.getEventPreferences());
        return Preference.builder()
                .eventPreferences(preferencesSet)
                .build();
    }
}
