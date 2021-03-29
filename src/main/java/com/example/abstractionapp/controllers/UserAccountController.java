package com.example.abstractionapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserAccountController {
    public UserAccountController(){
            }


    @RequestMapping(value = "api/v1/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "Abstraction webservice is working";
    }

}//UserAccountController
