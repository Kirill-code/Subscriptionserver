package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {

    @Autowired
    private InstructorService service;

    @RequestMapping(value = "/instructors", method = RequestMethod.GET)
    @ResponseBody
    public List<Instructor> getAllSubscriptioners() {
        return service.getAll();
    }

    @RequestMapping(value = "/instructors/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Instructor getInstructor(@PathVariable("id") long InstructorID) {
        return service.getByID(InstructorID);
    }

    @RequestMapping(value = "/instructors", method = RequestMethod.POST)
    @ResponseBody
    public Instructor saveInstructors(@RequestBody Instructor instructor) {
        return service.save(instructor);
    }

    @RequestMapping(value = "/instructors/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable long id) {
        service.remove(id);
    }

}
