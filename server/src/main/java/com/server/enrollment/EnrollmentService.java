package com.server.enrollment;

import com.server.UserService;
import com.server.enrollment.db.enrollment.model.Enrollment;
import com.server.enrollment.db.enrollment.repository.EnrollmentRepository;
import com.server.enrollment.db.relation.model.EnrollmentRelation;
import com.server.enrollment.db.relation.repository.EnrollmentRelationRepository;
import com.server.enrollment.dto.EnrollUserDTO;
import com.server.enrollment.dto.EnrollmentCreationDTO;
import com.server.enrollment.dto.EnrollmentDTO;
import com.server.enrollment.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRelationRepository enrollmentRelationRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;

    public List<EnrollmentDTO> findAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentMapper::toDTO)
                .toList();
    }

    public List<EnrollmentDTO> findUserEnrollments(UUID id) {
        var enrollmentIds = enrollmentRelationRepository.findEnrollmentRelationsByParticipantId(id)
                .stream()
                .map(EnrollmentRelation::getEnrollmentId)
                .toList();
        var enrollments = findEnrollmentsByIds(enrollmentIds);
        return enrollments.stream()
                .map(EnrollmentMapper::toDTO)
                .toList();
    }

    public void createEnrollment(EnrollmentCreationDTO enrollmentCreationDTO) {
        enrollmentRepository.save(EnrollmentMapper.fromDTO(enrollmentCreationDTO));
    }

    public void enrollUsers(EnrollUserDTO enrollUserDTO) {
        var enrollment = findEnrollmentById(enrollUserDTO.getEnrollmentId());
        var users = userService.findUsersByIds(enrollUserDTO.getUserIds());

        var enrollmentRelations = users.stream()
                .map(user -> EnrollmentRelation.builder()
                        .enrollmentId(enrollment.getId())
                        .participantId(user.getId())
                        .build())
                .toList();

        enrollmentRelationRepository.saveAll(enrollmentRelations);
    }

    @Transactional
    public void unrollUsers(EnrollUserDTO unrollUserDTO) {
        var enrollment = findEnrollmentById(unrollUserDTO.getEnrollmentId());
        var users = userService.findUsersByIds(unrollUserDTO.getUserIds());
        users.forEach(user -> enrollmentRelationRepository
                .deleteAllByEnrollmentIdAndParticipantId(enrollment.getId(), user.getId()));
    }

    private List<Enrollment> findEnrollmentsByIds(List<UUID> uuids) {
        return uuids.stream()
                .map(this::findEnrollmentById)
                .toList();
    }

    private Enrollment findEnrollmentById(UUID id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment with id %s was not found.".formatted(id)));
    }

}
