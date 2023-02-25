package com.server.managment.subject.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudySubjectRepository extends JpaRepository<StudySubject, UUID> {
    List<StudySubject> findAllByIdIn(List<UUID> ids);
}
