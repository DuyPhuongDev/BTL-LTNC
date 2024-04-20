package com.ltnc.be.port.repository;

import com.ltnc.be.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT t FROM Task t " +
            "JOIN t.employee e " +
            "WHERE (:employeeId IS NULL OR e.id = :employeeId) ")
    Optional<List<Task>> findAllByEmployeeIdAndCriteria(@Param("employeeId") Long employeeId);

}
