package org.airtribe.taskmanagement.repository;

import org.airtribe.taskmanagement.entity.Task;
import org.airtribe.taskmanagement.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedToId(Long userId);

    List<Task> findByTeamId(Long teamId);

    List<Task> findByStatus(Status status);

    List<Task> findByTitleContainingOrDescriptionContaining(String title, String description);
}
