package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.IdentityProvider;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class VisitController {
    @Autowired
    private VisitService service;

    Logger log = Logger.getLogger(UserController.class.getName());


    @RequestMapping(value = "/savenewvisit", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveRemider(@RequestBody String visit) {

        return service.save(visit);
    }

    @RequestMapping(value = "/instructorgrouped", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VisitDateDTO> visitsEndpoint() {
        return service.getAll();
    }

    @RequestMapping(value = "/visitsbydate", method = RequestMethod.GET)
    @ResponseBody
    public List<VisitDateDTO> visitsbydate(@RequestHeader("instid") long instr_id ,@RequestHeader("date") String date) {
       List<VisitDateDTO> result;
       try{
           Date converted=new SimpleDateFormat("yyyy-MM-dd").parse(date);
           result=service.visitsByDate(instr_id, converted);
       }catch (ParseException ex){
           log.info(" Wrong date : " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date())+ex.getMessage());
           result=null;
       }
       return result;
    }

}
