package com.server.enrollment.db.enrollment.model;

import com.server.managment.subject.db.StudySubject;
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
public class Enrollment {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enrollment_id", referencedColumnName = "id")
    private Set<StudySubject> subjects;
    private boolean isOpened = false;
}
