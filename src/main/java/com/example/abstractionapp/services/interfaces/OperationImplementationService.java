package com.example.abstractionapp.services.interfaces;

import com.example.abstractionapp.dto.OperationImplementationDto;
import com.example.abstractionapp.models.OperationImplementation;

public interface OperationImplementationService {

    public OperationImplementation save(OperationImplementationDto operationImplementationDto);

    public Iterable<OperationImplementation> findAll();

    public  OperationImplementation findById(Long id);

    public  OperationImplementation findByUuid(String uuid);

    public  Iterable<OperationImplementation> findByTaskId(long taskId);

    public  Iterable<OperationImplementation> findByOperationId(long operationId);

    public  Iterable<OperationImplementation> findByCommunicationId(long communicationId);

}
