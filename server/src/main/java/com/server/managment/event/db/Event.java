package com.server.managment.event.db;

import com.server.managment.event.enums.EventType;
import com.server.managment.event.enums.WeekType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @ElementCollection
    private Set<UUID> enrolledParticipantIds;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String localization;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<WeekType> weekTypes;
    private Integer groupSize;
    private Date date;
}
