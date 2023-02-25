package com.server.enrollment.db.relation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRelation {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID participantId;
    private UUID enrollmentId;
}
