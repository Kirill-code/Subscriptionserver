package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.Utils;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.service.SubscriptionService;
import com.ninja.subscription.server.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class VisitController {
    @Autowired
    private VisitService service;

    private static Logger log = Logger.getLogger(SubscriptionController.class.getName());

    Utils checker = new Utils();
    //TODO check which methods delete
    @RequestMapping(value = "/visits", method = RequestMethod.GET)
    @ResponseBody
    public List<VisitDate> getAllSubscriptioners() {
        return service.getAll();
    }

    @RequestMapping(value = "/savenewvisit", method = RequestMethod.POST)
    @ResponseBody
    public VisitDate saveRemider(@RequestBody VisitDate visitDate) {
        return service.save(visitDate);
    }

}
