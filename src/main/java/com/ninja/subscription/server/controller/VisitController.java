package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.IdentityProvider;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

@RestController
public class VisitController {
    @Autowired
    private VisitService service;

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

}
