package com.example.abstractionapp.services;

import com.example.abstractionapp.dto.CommunicationDto;
import com.example.abstractionapp.models.Communication;
import com.example.abstractionapp.repositories.CommunicationRepository;
import com.example.abstractionapp.services.interfaces.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommunicationServiceImp implements CommunicationService{

    @Autowired
    CommunicationRepository communicationRepository;

    @Override
    public Communication save(CommunicationDto communicationDto) {
        return null;
    }

    @Override
    public Iterable<Communication> findAll() {
        return null;
    }

    @Override
    public Communication findById(Long id) {
        return null;
    }

    @Override
    public Communication findByUuid(String uuid) {
        return null;
    }

    @Override
    public Communication findByName(String name) {
        return null;
    }
}
