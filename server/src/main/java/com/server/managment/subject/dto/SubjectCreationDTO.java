package com.server.managment.subject.dto;

import com.server.managment.event.dto.EventCreationDTO;
import lombok.Data;

import java.util.List;

@Data
public class SubjectCreationDTO {

    private String name;
    private List<EventCreationDTO> events;
}