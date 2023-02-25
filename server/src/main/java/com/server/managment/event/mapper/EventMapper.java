package com.server.managment.event.mapper;

import com.server.managment.event.db.Event;
import com.server.managment.event.dto.EventCreationDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventMapper {

    public static Event fromDTO(EventCreationDTO eventCreationDTO) {
        return Event.builder()
                .localization(eventCreationDTO.getLocalization())
                .eventType(eventCreationDTO.getEventType())
                .weekTypes(new HashSet<>(eventCreationDTO.getWeekTypes()))
                .date(eventCreationDTO.getDate())
                .groupSize(eventCreationDTO.getGroupSize())
                .build();
    }
}
