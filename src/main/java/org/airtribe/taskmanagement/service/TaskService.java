package org.airtribe.taskmanagement.service;

import org.airtribe.taskmanagement.entity.Task;
import org.airtribe.taskmanagement.entity.User;
import org.airtribe.taskmanagement.enums.Status;
import org.airtribe.taskmanagement.repository.TaskRepository;
import org.airtribe.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // Update a task
    public Task updateTask(Long taskId, Task updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setStatus(updatedTask.getStatus());
        task.setAssignedTo(updatedTask.getAssignedTo());
        task.setTeam(updatedTask.getTeam());

        return taskRepository.save(task);
    }

    // Delete a task
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    // Filter tasks by assigned user
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAssignedToId(userId);
    }

    // Filter tasks by team
    public List<Task> getTasksByTeamId(Long teamId) {
        return taskRepository.findByTeamId(teamId);
    }

    // Filter tasks by status
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    // Search tasks by title or description
    public List<Task> searchTasks(String keyword) {
        return taskRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword);
    }

    public Task assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setAssignedTo(user);
        return taskRepository.save(task);
    }
}

