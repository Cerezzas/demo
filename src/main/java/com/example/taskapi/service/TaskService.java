package com.example.taskapi.service;

import com.example.taskapi.dto.TaskRequest;
import com.example.taskapi.entity.Task;
import com.example.taskapi.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task not found"));
    }

    public Task createTask(TaskRequest request) {

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Title is required");
        }

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDone(false);

        return repository.save(task);
    }

    public Task updateTask(Integer id, TaskRequest request) {

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Title is required");
        }

        Task task = getTaskById(id);

        task.setTitle(request.getTitle());

        return repository.save(task);
    }

    public void deleteTask(Integer id) {

        Task task = getTaskById(id);

        repository.delete(task);
    }
}