package com.example.avabilityb.repository;

import com.example.avabilityb.model.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, UUID> {

    Optional<ManagerEntity> findByEmail(String Email);

    boolean existsByEmail(String username);

}