package com.example.abstractionapp.services;

import com.example.abstractionapp.models.Task;
import com.example.abstractionapp.dto.TaskDto;
import com.example.abstractionapp.repositories.TaskRepository;
import com.example.abstractionapp.services.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task save(TaskDto taskDto) {
        try{
            Task task = new Task(taskDto.getName(), taskDto.getCreatedBy());
            return taskRepository.save(task);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Task save(Task task) {
        try{
            return taskRepository.save(task);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Iterable<Task> findAll() {
        return null;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task findByUuid(String uuid) {
        return taskRepository.findByUuid(uuid);
    }

    @Override
    public Task findByName(String name) {
        return taskRepository.findByName(name);
    }

}
