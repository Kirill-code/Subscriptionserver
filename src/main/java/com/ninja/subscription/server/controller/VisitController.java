package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.Utils;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

@RestController
public class VisitController {
    @Autowired
    private VisitService service;

//    private static Logger log = Logger.getLogger(SubscriptionController.class.getName());

    Utils checker = new Utils();
    /*//TODO check which methods delete
    @RequestMapping(value = "/visits", method = RequestMethod.GET)
    @ResponseBody
    public List<VisitDate> getAllSubscriptioners() {
        return service.getAll();
    }*/

    @RequestMapping(value = "/savenewvisit", method = RequestMethod.POST)
    @ResponseBody
    public VisitDate saveRemider(@RequestBody VisitDate visitDate) {
        return service.save(visitDate);
    }

    @RequestMapping(value = "/instructorgrouped", method = RequestMethod.GET)
    @ResponseBody
    public Set<VisitDateDTO> VisitController() {
        Logger log = Logger.getLogger(UserController.class.getName());

        log.info(" Controller error: " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

Set<VisitDateDTO> tmp=service.getAll();
        System.out.println("Hello");
        return tmp;
    }

}
