package com.server.enrollment.db.preference.repository;

import com.server.enrollment.db.preference.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PreferenceRepository extends JpaRepository<Preference, UUID> {
}
