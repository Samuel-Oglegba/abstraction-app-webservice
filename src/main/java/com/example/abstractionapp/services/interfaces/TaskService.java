package com.example.abstractionapp.services.interfaces;

import com.example.abstractionapp.models.Task;
import com.example.abstractionapp.dto.TaskDto;

import java.util.Optional;

public interface TaskService {

    public Task save(TaskDto taskDto);

    public Iterable<Task> findAll();

    public Optional<Task> findById(Long id);

    public  Task findByUuid(String uuid);

    public  Task findByName(String name);


}
