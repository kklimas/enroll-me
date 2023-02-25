package com.server.managment.subject.db;

import com.server.enrollment.db.enrollment.model.Enrollment;
import com.server.managment.event.db.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudySubject {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Set<Event> events;
}
