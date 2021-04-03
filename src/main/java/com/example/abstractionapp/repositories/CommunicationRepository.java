package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication,Long> {

    //TODO: use the proper unique identifier
    //this is not unique, so i has to be iterable
    Communication findByVariableName(String variableName);

    Communication findByUuid(String uuid);
}
