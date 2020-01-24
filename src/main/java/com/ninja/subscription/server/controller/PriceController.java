package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.FirebaseUsers;
import com.ninja.subscription.server.model.Price;
import com.ninja.subscription.server.service.PriceService;
import com.ninja.subscription.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {
    @Autowired
    private PriceService service;

    @RequestMapping(value = "/fullprice", method = RequestMethod.GET)
    @ResponseBody
    public List<Price> getUsers(/*@RequestHeader("token") String idToken*/) {

        return service.getAll();
    }
}
