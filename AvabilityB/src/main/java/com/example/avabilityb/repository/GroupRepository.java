package com.example.avabilityb.repository;

import com.example.avabilityb.model.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    /*@Query("SELECT e.* FROM Employee e INNER JOIN ")
    List<EmployeeEntity> findAllEmployeesByGroupId(Long groupId);
    boolean existsByEmail(String Email);*/

}
