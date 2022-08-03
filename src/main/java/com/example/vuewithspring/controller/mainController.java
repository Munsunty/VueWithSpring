package com.example.vuewithspring.controller;


import com.example.vuewithspring.work.doI;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
@RestController
public class mainController {

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public  String searchGet(){
        doI i = new doI();
        return i.getJsonData();
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public  String searchPost(){
        return "olleh";
    }
}
