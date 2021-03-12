package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Task findByName(String name);

    Task findByUuid(String uuid);
}
