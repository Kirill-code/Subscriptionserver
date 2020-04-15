package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface VisitService {
    ArrayList<VisitDateDTO> getAll();
    VisitDate getByID(long id);
    boolean save(String visit);
    void remove(long id);
   List<VisitDateDTO> visitsByDate(long instr_id, Date date);

}
