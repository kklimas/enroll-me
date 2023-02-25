package com.server.web;

import com.server.managment.ManagementService;
import com.server.managment.subject.db.StudySubject;
import com.server.managment.subject.dto.SubjectCreationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/management")
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService managementService;

    @GetMapping
    public List<StudySubject> findAllSubjects() {
        return managementService.findAllSubjects();
    }

    @PostMapping
    public StudySubject createSubject(@RequestBody SubjectCreationDTO subjectCreationDTO) {
        return managementService.createSubject(subjectCreationDTO);
    }
}
