package com.example.abstractionapp.services.interfaces;

import com.example.abstractionapp.models.AbstractType;
import com.example.abstractionapp.dto.AbstractTypeDto;

import java.util.Optional;

public interface AbstractTypeService {

    public AbstractType save(AbstractTypeDto abstractTypeDto);

    public Iterable<AbstractType> findAll();

    public Optional<AbstractType> findById(Long id);

    public  AbstractType findByUuid(String uuid);

    public  AbstractType findByName(String name);
}
