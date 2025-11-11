package com.todolist.todolist.controller;

import com.todolist.todolist.dto.CreateTaskDTO;
import com.todolist.todolist.dto.UpdateTaskDTO;
import com.todolist.todolist.model.Task;
import com.todolist.todolist.service.TaskService;
import jakarta.servlet.UnavailableException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    // CREATE (Maps to: POST /api/tasks)
    @PostMapping
    public Task createTask(@RequestBody @Valid CreateTaskDTO task) {
        return taskService.createTask(task);
    }

    // READ (Maps to: GET /api/tasks)
    @GetMapping
    public List<Task> getAllTasks() throws UnavailableException {
        return taskService.getAllTasks();
    }

    // READ (Maps to: GET /api/tasks/{id})
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // UPDATE (Maps to: PUT /api/tasks/{id})
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody @Valid UpdateTaskDTO updateTaskDTO) {
        return taskService.updateTask(id, updateTaskDTO);
    }

    // DELETE (Maps to: DELETE /api/tasks/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
