package com.example.abstractionapp.services.interfaces;

import com.example.abstractionapp.dto.CommunicationDto;
import com.example.abstractionapp.models.Communication;

public interface CommunicationService {

    public Communication save(CommunicationDto communicationDto);

    public Iterable<Communication> findAll();

    public  Communication findById(Long id);

    public  Communication findByUuid(String uuid);

    public  Communication findByName(String name);
}
