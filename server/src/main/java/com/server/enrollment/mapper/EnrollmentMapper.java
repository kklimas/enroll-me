package com.server.enrollment.mapper;

import com.server.enrollment.db.enrollment.model.Enrollment;
import com.server.enrollment.dto.EnrollmentCreationDTO;
import com.server.enrollment.dto.EnrollmentDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentMapper {

    public static EnrollmentDTO toDTO(Enrollment enrollment) {
        return EnrollmentDTO.builder()
                .id(enrollment.getId())
                .description(enrollment.getDescription())
                .isOpened(enrollment.isOpened())
                .build();
    }

    public static Enrollment fromDTO(EnrollmentCreationDTO enrollmentCreationDTO) {
        return Enrollment.builder()
                .description(enrollmentCreationDTO.getDescription())
                .build();
    }
}
