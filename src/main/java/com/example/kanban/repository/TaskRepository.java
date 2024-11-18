package com.example.kanban.repository;

import com.example.kanban.model.Priority;
import com.example.kanban.model.Task;
import com.example.kanban.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusOrderByPriorityAsc(Status status);
    List<Task> findByPriorityAndDueDateBefore(Priority priority, LocalDateTime dueDate);
    List<Task> findByPriority(Priority priority);
    List<Task> findByDueDateBefore(LocalDateTime dueDate);
}