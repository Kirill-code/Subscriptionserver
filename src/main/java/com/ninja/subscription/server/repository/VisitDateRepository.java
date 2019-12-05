package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.VisitDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.logging.Logger;


public interface VisitDateRepository extends JpaRepository<VisitDate, Long> {
      Logger log = Logger.getLogger(UserController.class.getName());

    @Query("SELECT COUNT(v.id), v.instr_id, instructor.name, instructor.surname FROM VisitDate v,Instructor instructor  GROUP BY v.instr_id")
    List<VisitDate> visitsGrouped();
}
