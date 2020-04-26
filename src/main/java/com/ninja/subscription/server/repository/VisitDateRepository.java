package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface VisitDateRepository extends JpaRepository<VisitDate, Long> {

    @Query("SELECT COUNT(d.id), i.name, i.surname FROM Instructor i JOIN i.visitInstr d GROUP BY i.name")
    List<VisitDate> visitsGrouped();

    @Query("SELECT v.instr_id, v.date, count(v.associatedSub) FROM VisitDate v where  v.instr_id= ?1 and (v.date between ?2 and ?3) group by v.date order by v.date")
    List<VisitDateDTO> visitsByDate(long instr_id, Date dateStart, Date dateEnd);

}
