package com.ninja.subscription.server.controller;


import com.google.firebase.auth.*;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.User;
import com.ninja.subscription.server.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
    @ResponseBody
    public List<Subscription> getAllSubscriptioners() {
        return service.getAll();
    }

    //?????????where put it????
    boolean checkUsers(String idToken) {
        FirebaseToken decodedToken = null;
        boolean result;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            System.out.println(decodedToken.getClaims());
            log.info(decodedToken.getEmail() + " logged at " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));
            result = true;
        } catch (FirebaseAuthException e) {
            log.info("Auth error at " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public List<Subscription> getTest(@RequestHeader("token") String idToken) {
        List<Subscription> list = new ArrayList<>();

        if (checkUsers(idToken)) {
            list = service.getAll();
        } else {
            list.add(service.error());
        }

        return list;
    }


    @RequestMapping(value = "/subscriptions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Subscription getSubscriptioner(@PathVariable("id") long SubscriptionID) {
        return service.getByID(SubscriptionID);
    }

    @RequestMapping(value = "/subscriptions", method = RequestMethod.POST)
    @ResponseBody
    public Subscription saveRemider(@RequestBody Subscription Subscription) {
        return service.save(Subscription);
    }

    @RequestMapping(value = "/subscriptions/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

}
