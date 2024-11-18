package com.example.kanban.controller;

import com.example.kanban.model.Priority;
import com.example.kanban.model.Task;
import com.example.kanban.model.Status;
import com.example.kanban.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable Status status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/move/{newStatus}")
    public ResponseEntity<Void> moveTask(@PathVariable Long id, @PathVariable Status newStatus) {
        taskService.moveTask(id, newStatus);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Task>> filterTasks(@RequestParam(required = false) String priority,
                                                  @RequestParam(required = false) String dueDate) {
        Priority priorityEnum = priority != null ? Priority.valueOf(priority.toUpperCase()) : null;
        LocalDateTime dueDateTime = dueDate != null ? LocalDateTime.parse(dueDate) : null;
        List<Task> tasks = taskService.filterTasks(priorityEnum, dueDateTime);
        return ResponseEntity.ok(tasks);
    }
}