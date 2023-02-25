package com.server.enrollment;

import com.server.auth.UserService;
import com.server.enrollment.db.enrollment.model.Enrollment;
import com.server.enrollment.db.enrollment.repository.EnrollmentRepository;
import com.server.enrollment.db.preference.model.Preference;
import com.server.enrollment.db.preference.repository.EventPreferenceRepository;
import com.server.enrollment.db.preference.repository.PreferenceRepository;
import com.server.enrollment.db.relation.model.EnrollmentRelation;
import com.server.enrollment.db.relation.repository.EnrollmentRelationRepository;
import com.server.enrollment.dto.*;
import com.server.enrollment.mapper.EnrollmentMapper;
import com.server.enrollment.mapper.PreferenceMapper;
import com.server.exception.InvalidPreferencesException;
import com.server.managment.ManagementService;
import com.server.managment.subject.db.StudySubject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRelationRepository enrollmentRelationRepository;
    private final EventPreferenceRepository eventPreferenceRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PreferenceRepository preferenceRepository;
    private final ManagementService managementService;
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
        var subjects = new HashSet<>(managementService.findSubjectByIds(enrollmentCreationDTO.getSubjectIds()));
        enrollmentRepository.save(EnrollmentMapper.fromDTO(enrollmentCreationDTO, subjects));
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

    public void saveUserPreferences(String username, UserPreferencesDTO preferencesDTO) {
        var user = userService.findUserByUserEmail(username);

        var subjectIds = preferencesDTO.getSubjectPreferences().stream()
                .map(SubjectPreferencesDTO::getId)
                .toList();
        var savedSubjects = managementService.findSubjectByIds(subjectIds);
        validatePreferences(savedSubjects, preferencesDTO);

        var preferences = preferencesDTO.getSubjectPreferences()
                .stream()
                .map(PreferenceMapper::fromDTO)
                .collect(Collectors.toSet());

        var eventPreferences = preferences.stream()
                .map(Preference::getEventPreferences)
                .flatMap(Collection::stream)
                .toList();

        eventPreferenceRepository.saveAll(eventPreferences);

        user.setPreferences(preferences);
        userService.saveUser(user);
    }

    private void validatePreferences(List<StudySubject> subjects, UserPreferencesDTO preferencesDTO) {
        preferencesDTO.getSubjectPreferences().forEach(subjectPreference -> {
            // find subject in db
            var savedSubject = subjects.stream()
                    .filter(subject -> subject.getId().equals(subjectPreference.getId()))
                    .findFirst()
                    .orElseThrow(() -> new InvalidPreferencesException("Subject with id %s cannot be found."
                            .formatted(subjectPreference.getId())));

            subjectPreference.getEventPreferences().forEach(eventPreferences -> {
                // find event in db
                savedSubject.getEvents().stream()
                        .filter(event -> event.getId().equals(eventPreferences.getId()))
                        .findFirst()
                        .orElseThrow(() -> new InvalidPreferencesException("Event with id %s cannot be found."
                                .formatted(eventPreferences.getId())));

            });
        });
    }
}
