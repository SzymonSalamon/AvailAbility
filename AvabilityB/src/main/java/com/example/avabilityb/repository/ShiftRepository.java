package com.example.avabilityb.repository;

import com.example.avabilityb.model.entity.ShiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShiftRepository extends JpaRepository<ShiftEntity, Long> {

    @Query(value = "SELECT s.* FROM Shifts s " +
            "INNER JOIN Calendar c ON c.calendar_id = s.calendar_id " +
            "INNER JOIN Users u on c.employee_id = u.user_id " +
            "INNER JOIN Shift_Status ss ON s.shift_status = ss.status_id " +
            "WHERE u.user_id = :employeeId", nativeQuery = true)
    List<ShiftEntity> findAllByEmployeeId(@Param("employeeId") Long employeeId);


}
