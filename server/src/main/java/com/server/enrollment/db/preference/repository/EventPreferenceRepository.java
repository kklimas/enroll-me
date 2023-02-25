package com.server.enrollment.db.preference.repository;

import com.server.enrollment.db.preference.model.EventPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventPreferenceRepository extends JpaRepository<EventPreference, UUID> {
}
