package com.todolist.todolist.service;

import com.todolist.todolist.model.Task;
import com.todolist.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    // CREATE
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // READ (Get one by ID)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // READ (Get all)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // UPDATE
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(task);
    }

    // DELETE
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
