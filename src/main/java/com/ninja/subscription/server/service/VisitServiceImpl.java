package com.ninja.subscription.server.service;


import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.GroupedVisitDatesDTO;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.repository.SubscriptionRepository;
import com.ninja.subscription.server.repository.VisitDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Service
public class VisitServiceImpl implements VisitService {
    Logger log = Logger.getLogger(VisitServiceImpl.class.getName());

    @Autowired
    VisitDateRepository visitRepository;
    @Autowired
    SubscriptionRepository subRepository;

    @Override
    public ArrayList<VisitDateDTO> getAll() {
        List<VisitDate> tempList = visitRepository.visitsGrouped();

        log.info(" Instructors chart generated : " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));
        ArrayList cats = new ArrayList();

        for (int i = 0; i < tempList.size(); i++) {
            cats.add(tempList.get(i));
        }

//check why can't stream this list
        return cats;

    }

    public VisitDate convertDTO2Visit(VisitDateDTO visitDateDTO) {
        Subscription tempSub = subRepository.findByUid(visitDateDTO.getUid(), Boolean.TRUE);
        VisitDate temp = new VisitDate();
        temp.setInstr_id(visitDateDTO.getInstr_id());
        temp.setAssociatedSub(tempSub);
        temp.setDate(visitDateDTO.getDate());
        temp.setTime(visitDateDTO.getTime());
        return temp;
    }

    @Override
    public VisitDate getByID(long id) {
        return visitRepository.getOne(id);
    }

    @Override
    public boolean save(String visit) {
        //todo compare current date with finish
        boolean result;
        try {
            Subscription tempSub = subRepository.findByUid(visit, Boolean.TRUE);
            VisitDate temp = new VisitDate();
            temp.setInstr_id(tempSub.getAssociatedInstructor().getId());
            temp.setAssociatedSub(tempSub);
            temp.setDate(new Date(System.currentTimeMillis()));
            visitRepository.saveAndFlush(temp);
            log.info(" New date inserted for " + visit + ": " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();

        }
        return result;
    }

    @Override
    public void remove(long id) {
        visitRepository.delete(id);
    }

    @Override
    public List<GroupedVisitDatesDTO> visitsByDate(long instr_id, Date dateStart, Date dateEnd) {
        log.info(" Visits sent for "+instr_id+": " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));
        return visitRepository.visitsByDate(instr_id,dateStart,dateEnd);
    }
}
