package com.server.enrollment.db.preference.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class EventPreference {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private Integer weight;
}
