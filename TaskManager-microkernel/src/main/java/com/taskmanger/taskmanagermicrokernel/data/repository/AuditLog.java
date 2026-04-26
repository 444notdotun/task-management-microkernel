package com.taskmanger.taskmanagermicrokernel.data.repository;

import com.taskmanger.taskmanagermicrokernel.data.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLog extends JpaRepository<Audit, Long> {
}
