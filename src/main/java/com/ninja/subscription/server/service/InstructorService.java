package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.dto.InstructorDTO;

import java.util.List;

public interface InstructorService {
    List<InstructorDTO> getAll();
    Instructor getByID(long id);
    boolean save(InstructorDTO instructor);
    void remove(long id);

    InstructorDTO getByUidDto(String instructorUID);
}
