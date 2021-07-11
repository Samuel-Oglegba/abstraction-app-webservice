package com.example.abstractionapp.services;

import com.example.abstractionapp.dto.CommunicationDto;
import com.example.abstractionapp.models.Communication;
import com.example.abstractionapp.repositories.CommunicationRepository;
import com.example.abstractionapp.services.interfaces.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunicationServiceImp implements CommunicationService{

    @Autowired
    CommunicationRepository communicationRepository;

    @Override
    public Communication save(CommunicationDto communicationDto) {
        try{
            Communication communication = new Communication(communicationDto.getVariableName(),
                    communicationDto.getAbstractType(), communicationDto.getCreatedBy());
            return communicationRepository.save(communication);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }//save

    public Communication save(Communication communication) {
        try{
             return communicationRepository.save(communication);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }//save

    @Override
    public Iterable<Communication> findAll() {
        return null;
    }//findAll

    @Override
    public Communication findById(Long id) {
        return null;
    }

    @Override
    public Communication findByUuid(String uuid) {
        return null;
    }//findById

   @Override
   public Communication findByVariableName(String name) {
       return communicationRepository.findByVariableName(name);
    }

}
