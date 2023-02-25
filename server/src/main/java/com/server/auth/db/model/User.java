package com.server.auth.db.model;

import com.server.auth.enums.UserRole;
import com.server.enrollment.db.preference.model.Preference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participant")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull
    private String address;
    private Date joinDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private Set<Preference> preferences;
}

