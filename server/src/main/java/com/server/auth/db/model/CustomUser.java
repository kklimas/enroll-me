package com.server.auth.db.model;

import com.server.auth.enums.CustomUserRole;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private CustomUserRole role;

    @NotNull
    private String address;
    private Date joinDate;
}

