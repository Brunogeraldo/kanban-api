package com.example.kanban.service;

import ch.qos.logback.core.status.Status;
import com.example.kanban.model.Task;
import com.example.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus("A Fazer"); // Definindo status padrão
        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatusOrderByPriorityAsc(status);
    }

    // Outros métodos para atualizar, deletar, etc.

    public void moveTask(Long id, Status newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setStatus(String.valueOf(newStatus));
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());

        return taskRepository.save(task);
    }

    public Task changeTaskStatus(Long id, String newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

}