package com.example.taskapi.controller;

import com.example.taskapi.dto.TaskRequest;
import com.example.taskapi.entity.Task;
import com.example.taskapi.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "message", "Task API is running",
                "version", "2.0"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable Integer id) {
        return service.getTaskById(id);
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskRequest request) {
        return service.createTask(request);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Integer id,
                           @RequestBody TaskRequest request) {
        return service.updateTask(id, request);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Integer id) {
        service.deleteTask(id);
    }
}