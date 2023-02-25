package com.server.managment;

import com.server.managment.event.db.EventRepository;
import com.server.managment.event.mapper.EventMapper;
import com.server.managment.subject.db.StudySubject;
import com.server.managment.subject.db.StudySubjectRepository;
import com.server.managment.subject.dto.SubjectCreationDTO;
import com.server.managment.subject.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagementService {

    private final StudySubjectRepository studySubjectRepository;
    private final EventRepository eventRepository;

    public List<StudySubject> findAllSubjects() {
        return studySubjectRepository.findAll();
    }

    public StudySubject createSubject(SubjectCreationDTO subjectCreationDTO) {
        var eventsToSave = subjectCreationDTO.getEvents().stream()
                .map(EventMapper::fromDTO)
                .toList();
        eventsToSave = eventRepository.saveAll(eventsToSave);

        var subjectToSave = SubjectMapper.fromDTO(subjectCreationDTO.getName(), eventsToSave);
        return studySubjectRepository.save(subjectToSave);
    }

    public List<StudySubject> findSubjectByIds(List<UUID> uuids) {
        return studySubjectRepository.findAllByIdIn(uuids);
    }
}
