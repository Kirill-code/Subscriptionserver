package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select i from Instructor i where i.uid = ?1")
    Instructor findByUid(String uid);
}
