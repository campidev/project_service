package com.tcc.project_service.repositories;

import com.tcc.project_service.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByTaskId(Long taskId);
    List<Assignment> findByUserId(Long userId);
    Optional<Assignment> findByTaskIdAndUserId(Long taskId, Long userId);
}

