package com.example.vuewithspring.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RestController
public class mainController {

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public  String searchGet(){
        return "hello";
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public  String searchPost(){
        return "olleh";
    }
}
