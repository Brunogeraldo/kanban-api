package com.example.kanban.controller;

import ch.qos.logback.core.status.Status;
import com.example.kanban.model.Task;
import com.example.kanban.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getTasksByStatus("A Fazer");
        tasks.addAll(taskService.getTasksByStatus("Em Progresso"));
        tasks.addAll(taskService.getTasksByStatus("Concluído"));
        return ResponseEntity.ok(tasks);
    }

    // Outros endpoints para mover, atualizar e deletar tarefas

    @PutMapping("/{id}/move/{newStatus}")
    public ResponseEntity<Void> moveTask(@PathVariable Long id, @PathVariable Status newStatus) {
        taskService.moveTask(id, newStatus);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status/{newStatus}")
    public ResponseEntity<Task> changeTaskStatus(@PathVariable Long id, @PathVariable String newStatus) {
        Task updatedTask = taskService.changeTaskStatus(id, newStatus);
        return ResponseEntity.ok(updatedTask);
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

}