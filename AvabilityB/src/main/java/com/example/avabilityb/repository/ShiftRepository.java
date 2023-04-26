package com.example.avabilityb.repository;

import com.example.avabilityb.model.entity.ShiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Long> {

    @Query(value = "SELECT s.* FROM Shifts s " +
            "INNER JOIN Calendar c ON c.calendar_id = s.calendar_id " +
            "INNER JOIN Employee e on c.employee_id = e.employee_id" +
            "INNER JOIN Shift_Status ss ON s.shift_status = ss.status_id"+
            "WHERE  = e.employee_id :EmployeeId", nativeQuery = true)
    List<ShiftEntity> findAllByEmployeeId(@Param("EmployeeId") String EmployeeId);

}
