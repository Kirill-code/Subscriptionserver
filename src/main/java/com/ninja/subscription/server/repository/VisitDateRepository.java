package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


public interface VisitDateRepository extends JpaRepository<VisitDate, Long> {
      Logger log = Logger.getLogger(UserController.class.getName());

      @Query("SELECT COUNT(d.id), i.name, i.surname FROM Instructor i JOIN i.visitInstr d GROUP BY i.name")
    List<VisitDate> visitsGrouped();
    @Query("SELECT v.id,v.instr_id,v.date,v.time FROM VisitDate v where  v.instr_id= ?1 and v.date= ?2")
    List<VisitDateDTO> visitsByDate(long instr_id, Date date);
/*
*  @Query("select s from Subscription s inner join s.associatedFirebaseUsers u where u.uid = ?1 and s.current = ?2")
    Subscription findByUid(String uid, Boolean current);
* */
}
