package com.example.abstractionapp.services;

import com.example.abstractionapp.dto.OperationImplementationDto;
import com.example.abstractionapp.models.OperationImplementation;
import com.example.abstractionapp.repositories.OperationImplementationRepository;
import com.example.abstractionapp.services.interfaces.OperationImplementationService;
import org.springframework.beans.factory.annotation.Autowired;

public class OperationImplementationServiceImp implements OperationImplementationService {

    @Autowired
    OperationImplementationRepository operationImplementationRepository;

    @Override
    public OperationImplementation save(OperationImplementationDto operationImplementationDto) {
        return null;
    }

    @Override
    public Iterable<OperationImplementation> findAll() {
        return null;
    }

    @Override
    public OperationImplementation findById(Long id) {
        return null;
    }

    @Override
    public OperationImplementation findByUuid(String uuid) {
        return null;
    }

    @Override
    public Iterable<OperationImplementation> findByTaskId(long taskId) {
        return null;
    }

    @Override
    public Iterable<OperationImplementation> findByOperationId(long operationId) {
        return null;
    }

    @Override
    public Iterable<OperationImplementation> findByCommunicationId(long communicationId) {
        return null;
    }
}
