package com.server.enrollment.db.relation.repository;

import com.server.enrollment.db.relation.model.EnrollmentRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnrollmentRelationRepository extends JpaRepository<EnrollmentRelation, UUID> {
    List<EnrollmentRelation> findEnrollmentRelationsByParticipantId(UUID participantId);
    List<EnrollmentRelation> deleteAllByEnrollmentIdAndParticipantId(UUID enrollmentId, UUID participantId);
}
