package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.AbstractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractTypeRepository extends JpaRepository<AbstractType, Long> {

    AbstractType findByName(String name);

    AbstractType findByUuid(String uuid);
}
