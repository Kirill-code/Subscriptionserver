package com.ninja.subscription.server.controller;


import com.ninja.subscription.server.model.FirebaseUsers;

import com.ninja.subscription.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/fireusers", method = RequestMethod.GET)
    @ResponseBody
    public List<FirebaseUsers> getUsers(/*@RequestHeader("token") String idToken*/) {

        return service.getAll();
    }
/*method which return user list after check IdToken from requestbody*/

}
