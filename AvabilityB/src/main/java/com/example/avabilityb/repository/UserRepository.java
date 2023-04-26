package com.example.avabilityb.repository;

import com.example.avabilityb.config.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByMail(String mail);

    boolean existsByMail(String mail);

}