package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;

import java.util.Set;

public interface VisitService {
    Set<VisitDateDTO> getAll();
    VisitDate getByID(long id);
    VisitDate save(VisitDate visit);
    void remove(long id);
   // List<VisitDateDTO> getGrpouped();

}
