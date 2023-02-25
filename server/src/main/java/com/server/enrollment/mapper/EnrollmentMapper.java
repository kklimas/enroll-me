package com.server.enrollment.mapper;

import com.server.enrollment.db.enrollment.model.Enrollment;
import com.server.enrollment.dto.EnrollmentCreationDTO;
import com.server.enrollment.dto.EnrollmentDTO;
import com.server.managment.subject.db.StudySubject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentMapper {

    public static EnrollmentDTO toDTO(Enrollment enrollment) {
        return EnrollmentDTO.builder()
                .id(enrollment.getId())
                .description(enrollment.getDescription())
                .subjects(enrollment.getSubjects())
                .isOpened(enrollment.isOpened())
                .build();
    }

    public static Enrollment fromDTO(EnrollmentCreationDTO enrollmentCreationDTO, Set<StudySubject> subjects) {
        return Enrollment.builder()
                .subjects(subjects)
                .description(enrollmentCreationDTO.getDescription())
                .build();
    }
}
