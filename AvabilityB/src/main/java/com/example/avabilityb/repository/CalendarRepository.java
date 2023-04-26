package com.example.avabilityb.repository;

import com.example.avabilityb.model.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    Optional<CalendarEntity> findByEmail(String Email);
    boolean existsByEmail(String Email);
}
