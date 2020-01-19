package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;

import java.util.ArrayList;

public interface VisitService {
    ArrayList<VisitDateDTO> getAll();
    VisitDate getByID(long id);
    boolean save(String visit);
    void remove(long id);
   // List<VisitDateDTO> getGrpouped();

}
