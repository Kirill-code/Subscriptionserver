package com.ninja.subscription.server.controller;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.dto.InstructorDTO;
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
    public List<InstructorDTO> getAllSubscriptioners() {
        return service.getAll();
    }

    @RequestMapping(value = "/newinstructor", method = RequestMethod.POST)
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
