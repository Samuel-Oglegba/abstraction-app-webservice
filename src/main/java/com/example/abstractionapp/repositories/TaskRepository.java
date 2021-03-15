package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Task findByName(String name);

    Task findByUuid(String uuid);
}
