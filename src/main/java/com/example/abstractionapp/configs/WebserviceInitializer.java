package com.example.abstractionapp.configs;

import com.example.abstractionapp.models.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class WebserviceInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
       //  UserInputInitializer userInputInitializer = new UserInputInitializer();
        /**
         * TODO: carry out code initial setup
         */
        /*
        //populate the default abstract testing to see how the code work
        AbstractType abstractType = userInputInitializer.createDefaultAbstractType("QUEUE", 1);

        //populate the default operations
        //TODO -- use just push and pop for now
        Operation operation1 = userInputInitializer.createDefaultOperations("PUSH", "Any", "void", 1, abstractType);
        Operation operation2 = userInputInitializer.createDefaultOperations("POP", "void", "Any", 1, abstractType);
        //Operation operation3 = userInputInitializer.createDefaultOperations("PEEK", "void", "Any", 1, abstractType);
        //Operation operation4 = userInputInitializer.createDefaultOperations("REMOVE", "void", "void", 1, abstractType);

        //populate the default task for deduplication
        Task task1 = userInputInitializer.createDefaultTask("Fragment", 1);
        Task task2 = userInputInitializer.createDefaultTask("FragmentRefine", 1);
        Task task3 = userInputInitializer.createDefaultTask("Deduplicate", 1);
        Task task4 = userInputInitializer.createDefaultTask("Reorder", 1);
        Task task5 = userInputInitializer.createDefaultTask("Compress", 1);

        //TODO -- populate the communication --check the uniqueness of variable name
        Communication communication1 = userInputInitializer.createDefaultCommunication("REFINE_QUE", abstractType, 1);
        Communication communication2 = userInputInitializer.createDefaultCommunication("DEDUPLICATE_QUE", abstractType, 1);
        Communication communication3 = userInputInitializer.createDefaultCommunication("COMPRESS_QUE", abstractType, 1);
        Communication communication4 = userInputInitializer.createDefaultCommunication("REORDER_QUE", abstractType, 1);

        //TODO -- populate the default operation implementation
        //node 1 -> node 2
        userInputInitializer.createDefaultOperationImplementation(task1, task2, operation1, communication1, "", 1);
        userInputInitializer.createDefaultOperationImplementation(task2, task1, operation2, communication1, "", 1);
        //TODO -- GET THE CORRECT ORDER OF IMPLEMENTATION

        //userInputInitializer.createDefaultOperationImplementation(task3, operation1, communication3, "", 1);
        //userInputInitializer.createDefaultOperationImplementation(task4, operation2, communication4, "", 1);
        //userInputInitializer.createDefaultOperationImplementation(task5, operation1, communication4, "", 1);

        //node 2 -> node 3
        userInputInitializer.createDefaultOperationImplementation(task2, task3, operation1, communication2, "", 1);
        userInputInitializer.createDefaultOperationImplementation(task3, task2, operation2, communication2, "", 1);

        //node 3 -> node 5
        userInputInitializer.createDefaultOperationImplementation(task3, task5, operation1, communication3, "", 1);
        userInputInitializer.createDefaultOperationImplementation(task5,task3, operation2, communication3, "", 1);

        //node 3 -> node 4
        userInputInitializer.createDefaultOperationImplementation(task3, task4, operation1, communication4, "", 1);
        userInputInitializer.createDefaultOperationImplementation(task4, task3, operation2, communication4, "", 1);

        //node 4 -> node 5
        userInputInitializer.createDefaultOperationImplementation(task4, task5, operation1, communication4, "", 1);
        userInputInitializer.createDefaultOperationImplementation(task5, task4, operation2, communication4, "", 1);
     */
    }


}
