package com.todolist.todolist.service;

import com.todolist.todolist.dto.CreateTaskDTO;
import com.todolist.todolist.dto.UpdateTaskDTO;
import com.todolist.todolist.exception.custom.ResourceNotFoundException;
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
    public Task createTask(CreateTaskDTO createTaskDTO) {
        Task task = new Task(createTaskDTO.getTitle(), createTaskDTO.getDescription());
        return taskRepository.save(task);
    }

    // READ (Get one by ID)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with an id: " + id));
    }

    // READ (Get all)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // UPDATE
    public Task updateTask(Long id, UpdateTaskDTO updateTaskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with an id: " + id));

        task.setTitle(updateTaskDTO.getTitle());
        task.setDescription(updateTaskDTO.getDescription());
        task.setCompleted(updateTaskDTO.isCompleted());

        return taskRepository.save(task);
    }

    // DELETE
    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with an id: " + id);
        }

        taskRepository.deleteById(id);
    }
}
