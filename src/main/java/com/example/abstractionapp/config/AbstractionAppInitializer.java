package com.example.abstractionapp.config;

import com.example.abstractionapp.repositories.AbstractTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AbstractionAppInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AbstractTypeRepository abstractTypeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        /**
         * TODO: carry out code initial setup
         */
    }
}
