package com.example.abstractionapp.services;

import com.example.abstractionapp.dto.OperationImplementationDto;
import com.example.abstractionapp.models.OperationImplementation;
import com.example.abstractionapp.repositories.OperationImplementationRepository;
import com.example.abstractionapp.services.interfaces.OperationImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationImplementationServiceImp implements OperationImplementationService {

    @Autowired
    OperationImplementationRepository operationImplementationRepository;

    @Override
    public OperationImplementation save(OperationImplementationDto operationImplementationDto) {
        try{
            OperationImplementation operationImplementation = new OperationImplementation(operationImplementationDto.getTask(),
                    operationImplementationDto.getOperation(), operationImplementationDto.getCommunication(),
                    operationImplementationDto.getAttributes(), operationImplementationDto.getCreatedBy());

            return operationImplementationRepository.save(operationImplementation);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }//save

    @Override
    public Iterable<OperationImplementation> findAll() {
        return null;
    }

    @Override
    public Optional<OperationImplementation> findById(Long id) {
        return operationImplementationRepository.findById(id);
    }

    @Override
    public OperationImplementation findByUuid(String uuid) {
        return operationImplementationRepository.findByUuid(uuid);
    }

    @Override
  //  public Iterable<OperationImplementation> findByTaskId(long taskId) {
    public OperationImplementation findByTaskId(long taskId) {
        return operationImplementationRepository.findByTaskId(taskId);
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
