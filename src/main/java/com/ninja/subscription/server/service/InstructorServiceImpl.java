package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.dto.InstructorDTO;
import com.ninja.subscription.server.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

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
