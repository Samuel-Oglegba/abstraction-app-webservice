package com.example.abstractionapp.configs;

import com.example.abstractionapp.models.*;
import com.example.abstractionapp.repositories.*;
import com.example.abstractionapp.services.*;
import com.example.abstractionapp.utils.StringFormat;
import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Map;


public class UserInputInitializer {


    private AbstractTypeServiceImp abstractTypeRepository;

    private OperationServiceImp operationRepository;

    private TaskServiceImp taskRepository;

    private CommunicationServiceImp communicationRepository;

    private OperationImplementationServiceImp operationImplementationRepository;

    private StringFormat stringFormat;

    //constructor
    public UserInputInitializer(){
        stringFormat = new StringFormat();
    }
    public UserInputInitializer(TaskServiceImp taskServiceImp,
            AbstractTypeServiceImp abstractTypeServiceImp,
            CommunicationServiceImp communicationServiceImp,
            OperationServiceImp operationServiceImp,
            OperationImplementationServiceImp operationImplementationServiceImp
     ){

        stringFormat = new StringFormat();
        abstractTypeRepository= abstractTypeServiceImp;
        taskRepository = taskServiceImp;
        communicationRepository = communicationServiceImp;
        operationRepository = operationServiceImp;
        operationImplementationRepository = operationImplementationServiceImp;
    }

    @Transactional
    public void createNodesAndEdges(Map<String, GraphNode> nodes, Map<String, GraphEdge> edges, long userId){
        //create the nodes
        createNodes(nodes,userId);

        //create the edges
        createEdges(edges,userId);

    }//createNodesAndEdges

    /**
     * this method creates the nodes if it does not exist
     * @param nodes
     */
    @Transactional
    private void createNodes(Map<String, GraphNode> nodes, long userId) {
        for (GraphNode node : nodes.values()) {
            //System.out.println(node.getId() + " " + node.getAttributes());
            //create the task/node name if it doesn't exist
            createDefaultTask(node.getId(),userId);
        }//for

    }//createNodes

    /**
     * this method creates the edges if it does not exist
     * @param edges
     */
    @Transactional
    private void createEdges(Map<String, GraphEdge> edges, long userId){
        stringFormat = new StringFormat();
        for (GraphEdge edge : edges.values()) {
            //System.out.println(edge.getNode1().getId() + "->" + edge.getNode2().getId() + " " + edge.getAttributes() + " "+edge.getAttributes().get("label"));
           String[] edgeInfo =  stringFormat.splitByColon(edge.getAttributes().get("label").toString());

            if(edgeInfo != null){
                //crate the abstract type input
                AbstractType abstractType = createDefaultAbstractType(edgeInfo[1].trim(),userId);

                //create the edge name
                Communication communication = createDefaultCommunication(edgeInfo[0].trim(), abstractType, userId);

                //create the operations
                Operation operation1 = createDefaultOperations("PUSH", "Any", "void", userId, abstractType);
                Operation operation2 = createDefaultOperations("POP", "void", "Any", userId, abstractType);

                //create the implementation detail
                final Task task1 = taskRepository.findByName(edge.getNode1().getId());
                final Task task2 = taskRepository.findByName(edge.getNode2().getId());
                createDefaultOperationImplementation(task1,task2, operation1, communication, null, userId);
                createDefaultOperationImplementation(task2,task1, operation2, communication, null, userId);

            }//if

        }
    }//createEdges



    /**
     * this method creates the default operation implementation
     *
     * @param task
     * @param operation
     * @param communication
     * @param attributes
     * @param createdBy
     */
    public void createDefaultOperationImplementation(Task task, Task task2, Operation operation, Communication communication,
                                                      String attributes, long createdBy) {
        if (task != null && task2 != null && operation != null && communication != null) {
            OperationImplementation operationImplementation = new OperationImplementation(task, task2, operation, communication,
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
    public Communication createDefaultCommunication(String variableName, AbstractType abstractType, long createdBy) {

        //TODO: TEMPORARY -- find the default communication link by variable name
        final Communication communicationExist = communicationRepository.findByVariableName(variableName);

        if (communicationExist == null) {
            final Communication communication = new Communication(variableName, abstractType, createdBy);
            return communicationRepository.save(communication);
        }
        return communicationExist;

    }//createDefaultCommunication

    /**
     * populate the default deduplication task
     *
     * @param name
     * @param createdBy
     */
    public Task createDefaultTask(String name, long createdBy) {

        //find the task by name
        final Task taskExist = taskRepository.findByName(name);

        if (taskExist == null) {
            final Task task = new Task(name, createdBy);
            return taskRepository.save(task);
        }

        return taskExist;
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
    public Operation createDefaultOperations(String name, String inputType, String outputType, long createdBy, AbstractType abstractType) {

        //find the operation by name
        final Operation operationExist = operationRepository.findByName(name);

        if (operationExist == null) {
            final Operation operation = new Operation(name, inputType, outputType, createdBy, abstractType);
            return operationRepository.save(operation);
        }

        return operationExist;

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

        return abstractTypeCheck;

    }//createDefaultAbstractType

}
