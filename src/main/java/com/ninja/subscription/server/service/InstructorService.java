package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Instructor;
import java.util.List;

public interface InstructorService {
    List<Instructor> getAll();
    Instructor getByID(long id);
    Instructor save(Instructor instructor);
    void remove(long id);
}
