package com.example.taskapi.controller;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.TaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    private int nextId = 1;

    @GetMapping("/")
    public Map<String, Object> home() {

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Task API is running");
        response.put("version", "1.0");

        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {

        return Map.of("status", "ok");
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable int id) {

        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Task not found"
        );
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskRequest request) {

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Title is required"
            );
        }

        Task task = new Task(
                nextId++,
                request.getTitle(),
                false
        );

        tasks.add(task);

        return task;
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable int id,
                           @RequestBody TaskRequest request) {

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Title is required"
            );
        }

        for (Task task : tasks) {

            if (task.getId() == id) {

                task.setTitle(request.getTitle());

                return task;
            }
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Task not found"
        );
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) {

        boolean removed = tasks.removeIf(task -> task.getId() == id);

        if (!removed) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Task not found"
            );
        }
    }
}