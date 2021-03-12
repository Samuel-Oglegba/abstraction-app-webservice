package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.Communication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationRepository extends JpaRepository<Communication,Long> {

    Communication findByVariableName(String variableName);

    Communication findByUuid(String uuid);
}
