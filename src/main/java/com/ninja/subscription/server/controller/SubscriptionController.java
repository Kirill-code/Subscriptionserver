package com.ninja.subscription.server.controller;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.ninja.subscription.server.configuration.DatabaseConfig;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;


    @RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
    @ResponseBody
    public List<Subscription> getAllSubscriptioners() {
        return service.getAll();
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
