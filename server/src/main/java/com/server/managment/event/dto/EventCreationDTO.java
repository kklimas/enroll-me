package com.server.managment.event.dto;

import com.server.managment.event.enums.EventType;
import com.server.managment.event.enums.WeekType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventCreationDTO {

    private String localization;
    private EventType eventType;
    private Integer groupSize;
    private List<WeekType> weekTypes;
    private Date date;
}
