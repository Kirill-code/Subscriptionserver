package com.ninja.subscription.server.controller;


import com.google.firebase.auth.*;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.Utils;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    private static Logger log = Logger.getLogger(SubscriptionController.class.getName());

    Utils checker = new Utils();
    //TODO check which methods delete


    @RequestMapping(value = "/uidsubscription/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public SubscriptionDTO getSubscriptioner(@PathVariable("uid") String SubscriptionUID) {
        return service.getByUidDto(SubscriptionUID);
    }

    /*DELETE*/
    @RequestMapping(value = "/subscriptions/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

    /*GET BY UID*/  /*
    @RequestMapping(value = "/subscription/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public Subscription getSubscriptionByUid(@PathVariable("uid") String uid,@RequestHeader("token") String idToken) {
        if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
            return service.getByUid(uid);}
       else {
            return  service.error();
        }
    }*/
    @RequestMapping(value = "/savesubscriptions", method = RequestMethod.POST)
    @ResponseBody
    public Subscription saveRemider(@RequestBody Subscription Subscription) {
        return service.save(Subscription);
    }



}
