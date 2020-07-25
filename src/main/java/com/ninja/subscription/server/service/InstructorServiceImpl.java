package com.ninja.subscription.server.service;

import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.dto.InstructorDTO;
import com.ninja.subscription.server.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    Logger log = Logger.getLogger(InstructorServiceImpl.class.getName());


    @Override
    public List<InstructorDTO> getAll() {
        return instructorRepository.findAll()
                .stream().map(tmp -> {
                    InstructorDTO newOne = new InstructorDTO();
                    newOne.setId(tmp.getId());
                    newOne.setName(tmp.getName());
                    newOne.setSurname(tmp.getSurname());
                    return newOne;
                }).collect(Collectors.toList());
    }

    @Override
    public Instructor getByID(long id) {
        System.out.println("Hello From Insrt");
        return instructorRepository.getOne(id);
    }

    @Override
    public boolean save(InstructorDTO instructor) {
        boolean result = false;
        try {
            if(instructorRepository.findByUid(instructor.getUid())==null) {
                Instructor convertedInstructor = new Instructor();
                convertedInstructor.setUid(instructor.getUid());
                convertedInstructor.setName(instructor.getName());
                convertedInstructor.setSurname(instructor.getSurname());
                instructorRepository.saveAndFlush(convertedInstructor);
                log.info(" Instructor " + instructor.getUid() + " created - : " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));
            }else return false;
            result = true;
        } catch (Exception ex) {
            log.warning(" Instructor " + instructor.getUid() + " not created - : " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()) + ex.getMessage());

        }
        return result;
    }

    @Override
    public void remove(long id) {
        instructorRepository.delete(id);
    }

    @Override
    public InstructorDTO getByUidDto(String instructorUID) {
        InstructorDTO response = new InstructorDTO();
        if (instructorRepository.findByUid(instructorUID) != null) {
            Instructor requested = instructorRepository.findByUid(instructorUID);
                response.setId(requested.getId());
                response.setName(requested.getName());
                response.setSurname(requested.getSurname());
                response.setUid(requested.getUid());
            }else {
            response.setName("Instructor doesn't exist");
        }
        return response;
    }
}
