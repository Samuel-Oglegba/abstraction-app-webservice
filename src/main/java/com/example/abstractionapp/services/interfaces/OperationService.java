package com.example.abstractionapp.services.interfaces;

import com.example.abstractionapp.models.Operation;
import com.example.abstractionapp.dto.OperationDto;

public interface OperationService {

    public Operation save(OperationDto operationDto);

    public Iterable<Operation> findAll();

    public  Operation findById(Long id);

    public  Operation findByUuid(String uuid);

    public  Operation findByName(String name);

}
