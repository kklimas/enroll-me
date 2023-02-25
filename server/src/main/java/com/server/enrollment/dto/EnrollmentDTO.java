package com.server.enrollment.dto;

import com.server.managment.subject.db.StudySubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {

    private UUID id;
    private Set<StudySubject> subjects;
    private String description;
    private boolean isOpened;
}
