package com.server.enrollment.db.enrollment.repository;

import com.server.enrollment.db.enrollment.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
}
