package com.example.abstractionapp.config;

import com.example.abstractionapp.models.*;
import com.example.abstractionapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AbstractionAppInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AbstractTypeRepository abstractTypeRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CommunicationRepository communicationRepository;

    @Autowired
    OperationImplementationRepository operationImplementationRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        /**
         * TODO: carry out code initial setup
         */
        //populate the default abstract type
        AbstractType abstractType = createDefaultAbstractType("QUEUE", 1);

        //populate the default operations
        Operation operation1 = createDefaultOperations("PUSH", "Any", "void", 1, abstractType);
        Operation operation2 = createDefaultOperations("POP", "void", "Any", 1, abstractType);
        Operation operation3 = createDefaultOperations("PEEK", "void", "Any", 1, abstractType);
        Operation operation4 = createDefaultOperations("REMOVE", "void", "void", 1, abstractType);

        //populate the default task for deduplication
        Task task1 = createDefaultTask("Fragment", 1);
        Task task2 = createDefaultTask("FragmentRefine", 1);
        Task task3 = createDefaultTask("Deduplicate", 1);
        Task task4 = createDefaultTask("Reorder", 1);

        //TODO -- populate the communication --check the uniqueness of variable name
        Communication communication1 = createDefaultCommunication("REFINE_QUE : QUEUE", abstractType, 1);
        Communication communication2 = createDefaultCommunication("DEDUPLICATE_QUE : QUEUE", abstractType, 1);
        Communication communication3 = createDefaultCommunication("COMPRESS_QUE : QUEUE", abstractType, 1);
        Communication communication4 = createDefaultCommunication("REORDER_QUE : QUEUE", abstractType, 1);

        //TODO -- populate the default operation implementation
        createDefaultOperationImplementation(task1, operation1, communication1, "", 1);
        createDefaultOperationImplementation(task2, operation2, communication2, "", 1);
        createDefaultOperationImplementation(task3, operation3, communication3, "", 1);
        createDefaultOperationImplementation(task4, operation4, communication4, "", 1);
    }

    /**
     * this method creates the default operation implementation
     *
     * @param task
     * @param operation
     * @param communication
     * @param attributes
     * @param createdBy
     */
    private void createDefaultOperationImplementation(Task task, Operation operation, Communication communication,
                                                      String attributes, long createdBy) {
       if(task != null && operation != null && communication != null){
           OperationImplementation operationImplementation = new OperationImplementation(task, operation, communication,
                   attributes, createdBy);

           operationImplementationRepository.save(operationImplementation);
       }

    }//createDefaultOperationImplementation

    /**
     * populate the default communications
     *
     * @param variableName
     * @param abstractType
     */
    private Communication createDefaultCommunication(String variableName, AbstractType abstractType, long createdBy) {

        //TODO: TEMPORARY -- find the default communication link by variable name
        final Communication communicationExist = communicationRepository.findByVariableName(variableName);

        if (communicationExist == null) {
            final Communication communication = new Communication(variableName, abstractType, createdBy);
            return communicationRepository.save(communication);
        }
        return null;

    }//createDefaultCommunication

    /**
     * populate the default deduplication task
     *
     * @param name
     * @param createdBy
     */
    private Task createDefaultTask(String name, long createdBy) {

        //find the task by name
        final Task taskExist = taskRepository.findByName(name);

        if (taskExist == null) {
            final Task task = new Task(name, createdBy);
            return taskRepository.save(task);
        }

        return null;
    }//createDefaultTask

    /**
     * this method populates the default deduplication operations
     *
     * @param name
     * @param inputType
     * @param outputType
     * @param createdBy
     * @param abstractType
     */
    private Operation createDefaultOperations(String name, String inputType, String outputType, long createdBy, AbstractType abstractType) {

        //find the operation by name
        final Operation operationExist = operationRepository.findByName(name);

        if (operationExist == null) {
            final Operation operation = new Operation(name, inputType, outputType, createdBy, abstractType);
            return operationRepository.save(operation);
        }

        return null;

    }//createDefaultOperations

    /**
     * this method populates the default abstract types for deduplication
     *
     * @param name
     * @param createdBy
     */
    public AbstractType createDefaultAbstractType(String name, long createdBy) {

        //find the abstract type by name
        final AbstractType abstractTypeCheck = abstractTypeRepository.findByName(name);

        if (abstractTypeCheck == null) {
            final AbstractType abstractType = new AbstractType(name, createdBy);
            return abstractTypeRepository.save(abstractType);
        }

        return null;

    }//createDefaultAbstractType

}
