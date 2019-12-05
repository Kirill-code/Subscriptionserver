package com.ninja.subscription.server.service;


import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.repository.VisitDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Service
public class VisitServiceImpl implements VisitService {


    @Autowired
    VisitDateRepository visitRepository;

    @Override
    public ArrayList<VisitDateDTO> getAll() {
        List<VisitDate> tempList=visitRepository.visitsGrouped();
        Logger log = Logger.getLogger(UserController.class.getName());

        log.info(" Instructors chart generated : " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));
        ArrayList cats = new ArrayList();


        for (int i=0;i<tempList.size();i++){
           cats.add(tempList.get(i));
        }

//check why can't stream this list
        return cats;

    }

    @Override
    public VisitDate getByID(long id) {
        return visitRepository.getOne(id);
    }

    @Override
    public VisitDate save(VisitDate VisitDate) {
        return visitRepository.saveAndFlush(VisitDate);
    }

    @Override
    public void remove(long id) {
        visitRepository.delete(id);
    }

    /*@Override
    public List<VisitDateDTO> getGrpouped() {
        List<VisitDate> temp=visitRepository.visitsGrouped();




        return converted;

    }*/


}
