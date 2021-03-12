package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.AbstractType;
import com.example.abstractionapp.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {

    AbstractType findByName(String name);

    AbstractType findByUuid(String uuid);
}
