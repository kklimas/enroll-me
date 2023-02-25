package com.server.enrollment.db.preference.model;

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
public class Preference {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference_id", referencedColumnName = "id")
    private Set<EventPreference> eventPreferences;
}
