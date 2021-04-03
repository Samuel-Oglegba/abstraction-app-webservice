package com.example.abstractionapp.services;


import com.example.abstractionapp.models.AbstractType;
import com.example.abstractionapp.dto.AbstractTypeDto;
import com.example.abstractionapp.repositories.AbstractTypeRepository;
import com.example.abstractionapp.services.interfaces.AbstractTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbstractTypeServiceImp implements AbstractTypeService {

    @Autowired
    private AbstractTypeRepository abstractTypeRepository;

    @Override
    public AbstractType save(AbstractTypeDto abstractTypeDto) {
        try {
            AbstractType abstractType = new AbstractType(abstractTypeDto.getName(), abstractTypeDto.getCreatedBy());

            return abstractTypeRepository.save(abstractType);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }//save

    @Override
    public Iterable<AbstractType> findAll() {
        return abstractTypeRepository.findAll();
    }//findAll

    @Override
    public Optional<AbstractType> findById(Long id) {
        return abstractTypeRepository.findById(id);
    }//findById

    @Override
    public AbstractType findByUuid(String uuid) {
        return abstractTypeRepository.findByUuid(uuid);
    }

    @Override
    public AbstractType findByName(String name) {
        return abstractTypeRepository.findByName(name);
    }//findByName
}
