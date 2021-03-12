package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.OperationImplementation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationImplementationRepository extends JpaRepository<OperationImplementation,Long> {

    OperationImplementation findByUuid(String uuid);

    Iterable<OperationImplementation> findByTaskId(long taskId);

    Iterable<OperationImplementation> findByOperationId(long operationId);

    Iterable<OperationImplementation> findByCommunicationId(long taskId);
}
