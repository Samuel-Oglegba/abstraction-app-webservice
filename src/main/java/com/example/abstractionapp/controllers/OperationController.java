package com.example.abstractionapp.controllers;

import com.example.abstractionapp.dto.OperationImplementationDto;
import com.example.abstractionapp.models.Communication;
import com.example.abstractionapp.models.OperationImplementation;
import com.example.abstractionapp.models.Task;
import com.example.abstractionapp.services.CommunicationServiceImp;
import com.example.abstractionapp.services.OperationImplementationServiceImp;
import com.example.abstractionapp.services.OperationServiceImp;
import com.example.abstractionapp.services.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Locale;


@Controller
@CrossOrigin(
        origins = "*")
public class OperationController {

    @Autowired
    TaskServiceImp taskServiceImp;
    @Autowired
    OperationServiceImp operationServiceImp;
    @Autowired
    OperationImplementationServiceImp operationImplementationServiceImp;
    @Autowired
    CommunicationServiceImp communicationServiceImp;

    //@CrossOrigin(origins = "http://localhost:3000")

    /**
     * this method returns the task implementation detail
     * @param taskName
     * @return
     */
    @RequestMapping(value = "/api/v1/operation-task/{taskName}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    //public OperationImplementationDto getTaskImplementation(@PathVariable("taskName") final @NotNull String taskName){
    public ArrayList<OperationImplementationDto> getTaskImplementation(@PathVariable("taskName") final @NotNull String taskName){

        Task task = taskServiceImp.findByName(taskName.toUpperCase());
        Iterable<OperationImplementation> operationImplementation = operationImplementationServiceImp.findByTaskId(task.getId());
        //Iterator iterator = operationImplementation.iterator();
        //initialize the data transfer object
        ArrayList<OperationImplementationDto> operationImplementationList = loadOperationImplementationList(operationImplementation);

        return operationImplementationList;

    }//getTaskImplementation

    @RequestMapping(value = "/api/v1/edge-task/{edgeName}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public ArrayList<OperationImplementationDto> getEdgeTask(@PathVariable("edgeName") @NotNull String edgeName){
        if(edgeName.contains(":")){
            String[] theEdgeName = edgeName.split(":");
            edgeName = theEdgeName[0].trim();
        }
        Communication communication = communicationServiceImp.findByVariableName(edgeName.toUpperCase());
        Iterable<OperationImplementation> operationImplementation = operationImplementationServiceImp.findByCommunicationId(communication.getId());

        ArrayList<OperationImplementationDto> operationImplementationList = loadOperationImplementationList(operationImplementation);

        return operationImplementationList;
    }//getEdgeTask

    private ArrayList<OperationImplementationDto> loadOperationImplementationList(Iterable<OperationImplementation> operationImplementation) {

        ArrayList<OperationImplementationDto> operationImplementationList = new ArrayList<>();

        for (OperationImplementation implementation : operationImplementation) {
            //set the data transfer object
            OperationImplementationDto operationImplementationDto = new OperationImplementationDto();
            operationImplementationDto.setOperation(implementation.getOperation());
            operationImplementationDto.setCommunication(implementation.getCommunication());
            operationImplementationDto.setTask(implementation.getTask());
            operationImplementationDto.setAttributes(implementation.getAttributes());
            operationImplementationDto.setId(implementation.getId());
            operationImplementationDto.setCreatedBy(implementation.getCreatedBy());

            operationImplementationList.add(operationImplementationDto);
        }
        return operationImplementationList;

    }//loadOperationImplementationList


}
