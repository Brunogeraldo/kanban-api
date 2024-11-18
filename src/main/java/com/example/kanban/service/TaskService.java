package com.example.kanban.service;

import com.example.kanban.model.Priority;
import com.example.kanban.model.Task;
import com.example.kanban.model.Status;
import com.example.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatusOrderByPriorityAsc(status);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // Certifique-se de que os métodos correspondem aos nomes dos campos na classe Task
        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public void moveTask(Long id, Status newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setStatus(newStatus);
        taskRepository.save(task);
    }

    public List<Task> filterTasks(Priority priority, LocalDateTime dueDate) {
        if (priority != null && dueDate != null) {
            return taskRepository.findByPriorityAndDueDateBefore(priority, dueDate);
        } else if (priority != null) {
            return taskRepository.findByPriority(priority);
        } else if (dueDate != null) {
            return taskRepository.findByDueDateBefore(dueDate);
        }
        return taskRepository.findAll();
    }
}