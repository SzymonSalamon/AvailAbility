package com.example.avabilityb.repository;

import com.example.avabilityb.model.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface OwnersRepository extends JpaRepository<OwnerEntity, UUID> {

    Optional<OwnerEntity> findByEmail(String Email);

    boolean existsByEmail(String Email);

}