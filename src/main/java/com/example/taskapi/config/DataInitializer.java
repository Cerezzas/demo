package com.example.taskapi.config;

import com.example.taskapi.entity.Task;
import com.example.taskapi.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {

        return args -> {

            if (repository.count() == 0) {

                repository.save(new Task("Buy milk", false));
                repository.save(new Task("Learn Spring Boot", false));
                repository.save(new Task("Finish assignment", false));

            }

        };
    }
}