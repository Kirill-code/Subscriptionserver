package com.ninja.subscription.server.controller;


import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.IdentityProvider;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    private static Logger log = Logger.getLogger(SubscriptionController.class.getName());

    IdentityProvider checker = new FirebaseProvider();

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Boolean> verifyUser(@RequestHeader("token") String idToken) {
        if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
            return Collections.singletonMap("success", true);
        } else return Collections.singletonMap("success", false);
    }

    @CrossOrigin
    @RequestMapping(value = "/uidsubscription/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public SubscriptionDTO getSubscriptioner(/*@RequestHeader("token") String idToken,*/@PathVariable("uid") String SubscriptionUID) {
       /* if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
        }*/
        return service.getByUidDto(SubscriptionUID);
    }

    /*DELETE*/
    @RequestMapping(value = "/deletesubscriptions/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/savesubscription", method = RequestMethod.POST)
    @ResponseBody
    public void saveSubscription(@RequestBody Subscription Subscription) {
        service.save(Subscription);
    }

    @RequestMapping(value = "/insertsubscription", method = RequestMethod.POST)
    @ResponseBody
    public void createSubscription(@RequestBody SubscriptionDTO subscription) {
        service.insertNew(subscription);
    }


}
