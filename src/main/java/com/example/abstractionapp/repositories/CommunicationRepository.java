package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication,Long> {

    Communication findByVariableName(String variableName);

    Communication findByUuid(String uuid);
}
