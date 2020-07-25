package com.ninja.subscription.server.controller;


import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.IdentityProvider;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.incomeSubscriptionDTO;
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

    IdentityProvider checker = new FirebaseProvider();

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Boolean> verifyUserToken(@RequestHeader("token") String idToken) {
        if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
            return Collections.singletonMap("success", true);
        } else return Collections.singletonMap("success", false);
    }

    @CrossOrigin
    @RequestMapping(value = "/uidsubscription/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public SubscriptionDTO getSubscriptioner(@RequestHeader("token") String idToken, @PathVariable("uid") String SubscriptionUID) {
        if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
            return service.getByUidDto(SubscriptionUID);
        } else {
            SubscriptionDTO empty = new SubscriptionDTO();
            empty.setDescription("Wrong token");
            return empty;
        }

    }

    /*DELETE*/
    @RequestMapping(value = "/deletesubscriptions/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/newsubscription", method = RequestMethod.POST)
    @ResponseBody
    public void saveSubscription(/*@RequestHeader("token") String idToken,*/ @RequestBody incomeSubscriptionDTO subscription) {
        /*if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {

        } else {

        }*/
        service.save(subscription);
    }


}
