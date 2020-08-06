package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.FirebaseProvider;
import com.ninja.subscription.server.model.IdentityProvider;
import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.dto.InstructorDTO;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class InstructorController extends ControllerUtils {
    IdentityProvider checker = new FirebaseProvider();

    @Autowired
    private InstructorService service;

    @RequestMapping(value = "/instructors", method = RequestMethod.GET)
    @ResponseBody
    public List<InstructorDTO> getAllInstructors() {
        return service.getAll();
    }

    @RequestMapping(value = "/newinstructor", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveInstructors(@RequestHeader("token") String idToken,@RequestBody InstructorDTO instructor) {
        if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
            return service.save(instructor);
        }else {
            return false;
        }
    }

    @RequestMapping(value = "/deleteinstructor/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/uidinstructor/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public InstructorDTO getInstructor(@RequestHeader("token") String idToken, @PathVariable("uid") String InstructorUID) {
        if (Boolean.TRUE.equals(checker.checkUsers(idToken))) {
            log.info(" Correct token: "+ new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));

            return service.getByUidDto(InstructorUID);
        }else {
            log.warning(" Wrong token: "+ new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));
            InstructorDTO empty = new InstructorDTO();
            empty.setName("Wrong token");
            return empty;
        }

    }

}
