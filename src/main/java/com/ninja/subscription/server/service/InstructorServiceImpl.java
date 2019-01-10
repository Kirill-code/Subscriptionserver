package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getByID(long id) {
        return instructorRepository.getOne(id);
    }

    @Override
    public Instructor save(Instructor instructor) {
        return instructorRepository.saveAndFlush(instructor);
    }

    @Override
    public void remove(long id) {
        instructorRepository.delete(id);
    }
}
