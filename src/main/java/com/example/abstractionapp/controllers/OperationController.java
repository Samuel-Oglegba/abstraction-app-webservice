package com.example.abstractionapp.controllers;
/**
 * this class -- TODO- complete the purpose of the class
 */

import com.example.abstractionapp.configs.UserInputInitializer;
import com.example.abstractionapp.dto.DotInputInitializerDto;
import com.example.abstractionapp.dto.OperationImplementationDto;
import com.example.abstractionapp.models.Communication;
import com.example.abstractionapp.models.OperationImplementation;
import com.example.abstractionapp.models.Task;
import com.example.abstractionapp.services.*;
import com.example.abstractionapp.utils.StringFormat;
import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

@Controller
@CrossOrigin(
        origins = "*")
public class OperationController {

    @Autowired
    TaskServiceImp taskServiceImp;
    @Autowired
    AbstractTypeServiceImp abstractTypeServiceImp;
    @Autowired
    OperationServiceImp operationServiceImp;
    @Autowired
    OperationImplementationServiceImp operationImplementationServiceImp;
    @Autowired
    CommunicationServiceImp communicationServiceImp;

    //@CrossOrigin(origins = "http://localhost:3000")

    /**
     * draw the graph
     */
    @RequestMapping(value = "/api/v1/draw-graph",method = RequestMethod.POST,produces = "application/json", consumes = "application/json")
    @ResponseBody
    public boolean drawGraph(@RequestBody DotInputInitializerDto dotInputInitializerDto){
        try {
            //get json parameters
            String dotInput = dotInputInitializerDto.getDotInput();
            long userId = dotInputInitializerDto.getUserId();

            //TODO-- validate dotInput
            StringFormat stringFormat = new StringFormat();
            if(!stringFormat.validateDotInput(dotInput)){
               return false;
            }

            InputStream inputStream = new ByteArrayInputStream(dotInput.getBytes(Charset.forName("UTF-8")));
            //initialize the parser
            GraphParser parser = new GraphParser(inputStream);
            //System.out.println(parser.getGraphId());

            Map<String, GraphNode> nodes = parser.getNodes();
            Map<String, GraphEdge> edges = parser.getEdges();

            //create all nodes and edges
            //initialize the config class
            UserInputInitializer userInputInitializer = new UserInputInitializer(
                    taskServiceImp,abstractTypeServiceImp,communicationServiceImp,operationServiceImp,operationImplementationServiceImp
            );
            userInputInitializer.createNodesAndEdges(nodes,edges,userId);

            return true;
        /*
        FileWriter myWriter = new FileWriter("example/filename.dg");
        myWriter.write(dotInput);
        myWriter.close();
        // GraphParser parser = new GraphParser(new FileInputStream("example/filename.dg"));
      */
        /*

            System.out.println("--- nodes:");
            for (GraphNode node : nodes.values()) {
                //log(node.getId() + " " + node.getAttributes());
                System.out.println(node.getId() + " " + node.getAttributes());
            }
            System.out.println("--- edges:");
            for (GraphEdge edge : edges.values()) {
                //log(edge.getNode1().getId() + "->" + edge.getNode2().getId() + " " + edge.getAttributes());
                System.out.println(edge.getNode1().getId() + "->" + edge.getNode2().getId() + " " + edge.getAttributes() + " "+edge.getAttributes().get("label"));
            }
        */

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * this method returns the task implementation detail
     * @param taskName
     * @return
     */
    @RequestMapping(value = "/api/v1/operation-task/{taskName}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    //public OperationImplementationDto getTaskImplementation(@PathVariable("taskName") final @NotNull String taskName){
    public ArrayList<OperationImplementationDto> getTaskImplementation(@PathVariable("taskName") final @NotNull String taskName){
        try{
            Task task = taskServiceImp.findByName(taskName.toUpperCase());
            Iterable<OperationImplementation> operationImplementation = operationImplementationServiceImp.findByTaskId(task.getId());
            //Iterator iterator = operationImplementation.iterator();
            //initialize the data transfer object
            ArrayList<OperationImplementationDto> operationImplementationList = loadOperationImplementationList(operationImplementation);

            return operationImplementationList;
        }
        catch (Exception e){
            return null;
        }

    }//getTaskImplementation

    @RequestMapping(value = "/api/v1/edge-task/{edgeName}/{taskName1}/{taskName2}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public ArrayList<OperationImplementationDto> getEdgeTask(@PathVariable("edgeName") @NotNull String edgeName,
                                                             @PathVariable("taskName1") @NotNull String taskName1,

                                                             @PathVariable("taskName2") @NotNull String taskName2){

       try{
           if(edgeName.contains(":")){
               String[] theEdgeName = edgeName.split(":");
               edgeName = theEdgeName[0];
           }//if

           Communication communication = communicationServiceImp.findByVariableName(edgeName.toUpperCase().trim());
           Task task1 = taskServiceImp.findByName(taskName1.toUpperCase());
           Task task2 = taskServiceImp.findByName(taskName2.toUpperCase());
           //Iterable<OperationImplementation> operationImplementation = operationImplementationServiceImp.findByCommunicationId(communication.getId());
           Iterable<OperationImplementation> operationImplementation = operationImplementationServiceImp.findByCommunicationAndTask(communication.getId(), task1.getId(), task2.getId());

           ArrayList<OperationImplementationDto> operationImplementationList = loadOperationImplementationList(operationImplementation);

           return operationImplementationList;
       }
       catch (Exception e){
           return null;
       }
    }//getEdgeTask

    /**
     * this method returns operation implementation arrayList
     * @param operationImplementation
     * @return
     */
    private ArrayList<OperationImplementationDto> loadOperationImplementationList(Iterable<OperationImplementation> operationImplementation) {

        ArrayList<OperationImplementationDto> operationImplementationList = new ArrayList<>();

        for (OperationImplementation implementation : operationImplementation) {
            //set the data transfer object
            OperationImplementationDto operationImplementationDto = new OperationImplementationDto();

            operationImplementationDto.setOperation(implementation.getOperation());
            operationImplementationDto.setAbstractType(implementation.getOperation().getAbstractType());
            operationImplementationDto.setCommunication(implementation.getCommunication());
            operationImplementationDto.setTask(implementation.getTask());
            operationImplementationDto.setTask2(implementation.getTask2());
            operationImplementationDto.setAttributes(implementation.getAttributes());
            operationImplementationDto.setId(implementation.getId());
            operationImplementationDto.setCreatedBy(implementation.getCreatedBy());

            operationImplementationList.add(operationImplementationDto);
        }
        return operationImplementationList;

    }//loadOperationImplementationList


}
