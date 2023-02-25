package com.server.managment.subject.mapper;

import com.server.managment.event.db.Event;
import com.server.managment.subject.db.StudySubject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectMapper {

    public static StudySubject fromDTO(String name, List<Event> events) {
        return StudySubject.builder()
                .name(name)
                .events(new HashSet<>(events))
                .build();
    }
}
