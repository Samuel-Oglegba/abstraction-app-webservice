package com.example.abstractionapp.controllers;

import com.example.abstractionapp.dto.AbstractTypeDto;
import com.example.abstractionapp.models.AbstractType;
import com.example.abstractionapp.services.AbstractTypeServiceImp;
import com.example.abstractionapp.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AbstractTypeController {

    @Autowired
    AbstractTypeServiceImp abstractTypeServiceImp;

    public AbstractTypeController(){
            }

    /**
     * get all the abstract type
     * @return
     */
    @RequestMapping(value = "/api/v1/abstraction-type/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<AbstractType> getAbstractTypes(){

        return abstractTypeServiceImp.findAll();

    }//getAbstractTypes

    /**
     * create a new abstract type
     * @param request
     * @param abstractTypeDto
     * @return
     */
    @RequestMapping(value = "/api/v1/abstraction-type/add", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse addAbstractionType(final HttpServletRequest request, @Valid final AbstractTypeDto abstractTypeDto){

        try{
            AbstractType abstractType = abstractTypeServiceImp.save(abstractTypeDto);
            if(abstractType == null){
                return new GenericResponse("Something went wrong","Duplicate entry - "+abstractTypeDto.getName());
            }
            return new GenericResponse("success");
        }
        catch (Exception e){
            return new GenericResponse("Something went wrong",e.getMessage());
        }

    }//addAbstractionType

}
