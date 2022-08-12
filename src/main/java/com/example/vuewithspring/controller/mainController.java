package com.example.vuewithspring.controller;


import com.example.vuewithspring.work.doI;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
@RestController
public class mainController {
    doI i = new doI();

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public  String searchGet(){
        return i.getJsonData();
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public  @ResponseBody String save(HttpServletRequest req, HttpServletResponse res){
        try {
            i.Save(req,res);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i.getJsonData();
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public  String searchPost(){
        return "olleh";
    }
}
