package com.example.abstractionapp.services;

import com.example.abstractionapp.models.Task;
import com.example.abstractionapp.dto.TaskDto;
import com.example.abstractionapp.repositories.TaskRepository;
import com.example.abstractionapp.services.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskServiceImp implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task save(TaskDto taskDto) {
        return null;
    }

    @Override
    public Iterable<Task> findAll() {
        return null;
    }

    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public Task findByUuid(String uuid) {
        return null;
    }

    @Override
    public Task findByName(String name) {
        return null;
    }

}
