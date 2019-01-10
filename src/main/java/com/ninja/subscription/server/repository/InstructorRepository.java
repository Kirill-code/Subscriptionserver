package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
