package com.example.abstractionapp.services.interfaces;

import com.example.abstractionapp.dto.OperationImplementationDto;
import com.example.abstractionapp.models.OperationImplementation;

import java.util.Optional;

public interface OperationImplementationService {

    public OperationImplementation save(OperationImplementationDto operationImplementationDto);

    public Iterable<OperationImplementation> findAll();

    public Optional<OperationImplementation> findById(Long id);

    public  OperationImplementation findByUuid(String uuid);

   // public  Iterable<OperationImplementation> findByTaskId(long taskId);
    public Iterable<OperationImplementation> findByTaskId(long taskId);

    public  Iterable<OperationImplementation> findByOperationId(long operationId);

    public  Iterable<OperationImplementation> findByCommunicationId(long communicationId);

    public  Iterable<OperationImplementation> findByCommunicationAndTask(long communicationId, long taskId1, long taskId2);

}
