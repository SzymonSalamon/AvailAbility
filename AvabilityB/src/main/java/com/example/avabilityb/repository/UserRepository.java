package com.example.avabilityb.repository;

import com.example.avabilityb.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByMail(String mail);

    boolean existsByMail(String mail);

    @Query("SELECT c.user FROM CalendarEntity c WHERE c.group.id = :groupId")
    List<UserEntity> findAllByGroupId(@Param("groupId") Long groupId);




}
