package com.example.abstractionapp.controllers;
;
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


@Controller
@CrossOrigin(origins = "*")
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
    public OperationImplementationDto getTaskImplementation(@PathVariable("taskName") final @NotNull String taskName){

        Task task = taskServiceImp.findByName(taskName.toUpperCase());
        OperationImplementation operationImplementation = operationImplementationServiceImp.findByTaskId(task.getId());

        //initialize the data transfer object
        OperationImplementationDto operationImplementationDto = new OperationImplementationDto();

        //set the data transfer object
        operationImplementationDto.setOperation(operationImplementation.getOperation());
        operationImplementationDto.setCommunication(operationImplementation.getCommunication());
        operationImplementationDto.setTask(operationImplementation.getTask());
        operationImplementationDto.setAttributes(operationImplementation.getAttributes());
        operationImplementationDto.setId(operationImplementation.getId());
        operationImplementationDto.setCreatedBy(operationImplementation.getCreatedBy());
        
        return operationImplementationDto;

    }//getTaskImplementation


    @RequestMapping(value = "/api/v1/edge-task/{edgeName}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String getEdgeTask(@PathVariable("edgeName") final @NotNull String edgeName){
        Communication communication = communicationServiceImp.findByVariableName(edgeName);
        return "Testing";
    }


}
