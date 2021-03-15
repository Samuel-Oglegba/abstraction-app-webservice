package com.example.abstractionapp.config;

import com.example.abstractionapp.models.AbstractType;
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

        //createDefaultAbstractType();
    }

    /**
     * this method creates the default abstract types
     */
    public final void createDefaultAbstractType(){
        final AbstractType abstractTypeCheck = abstractTypeRepository.findByName("QUEUE");

        if(abstractTypeCheck == null){
            final AbstractType abstractType = new AbstractType();
            abstractType.setName("queue");
            abstractType.setCreatedBy("defUser");

            abstractTypeRepository.save(abstractType);
        }
    }
}
