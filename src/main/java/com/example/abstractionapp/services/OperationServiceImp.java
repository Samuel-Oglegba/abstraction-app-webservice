package com.example.abstractionapp.services;

import com.example.abstractionapp.models.Operation;
import com.example.abstractionapp.dto.OperationDto;
import com.example.abstractionapp.repositories.OperationRepository;
import com.example.abstractionapp.services.interfaces.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImp implements OperationService {

    @Autowired
    OperationRepository operationRepository;

    @Override
    public Operation save(OperationDto operationDto) {
        return null;
    }

    @Override
    public Iterable<Operation> findAll() {
        return null;
    }

    @Override
    public Operation findById(Long id) {
        return null;
    }

    @Override
    public Operation findByUuid(String uuid) {
        return null;
    }

    @Override
    public Operation findByName(String name) {
        return null;
    }
}
