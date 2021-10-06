package com.example.abstractionapp.controllers;
/**
 * this controller holds all the api endpoints
 * the first call is to upload a task graph or process task graph text input
 * call for node/task details
 * call for edge/communication details
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
     * extract the graph from uploaded file (.dg supported)
     */
    @RequestMapping(value = "/api/v1/upload-graph",method = RequestMethod.POST)
    @ResponseBody
    public String drawGraphFromUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId){
        try {
            //validate the file
            if(file.isEmpty()){
                System.out.println("File is empty!");
                return "";
            }

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            String dotGraph = new String(bytes, StandardCharsets.UTF_8);
            //System.out.println(dotGraph);

            String originalFileName = file.getOriginalFilename();
            Path path = Paths.get("example/" + originalFileName);
            Files.write(path, bytes);

            GraphParser parser = new GraphParser(new FileInputStream("example/"+originalFileName));
            //System.out.println(parser.getGraphId());

            Map<String, GraphNode> nodes = parser.getNodes();
            Map<String, GraphEdge> edges = parser.getEdges();

            //create all nodes and edges
            //initialize the config class
            UserInputInitializer userInputInitializer = new UserInputInitializer(
                    taskServiceImp,abstractTypeServiceImp,communicationServiceImp,operationServiceImp,operationImplementationServiceImp
            );
            userInputInitializer.createNodesAndEdges(nodes,edges,userId);

            return dotGraph;

        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * draw the graph from text input
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
