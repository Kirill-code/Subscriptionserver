package com.ninja.subscription.server.service;


import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.repository.VisitDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {


    @Autowired
    VisitDateRepository visitRepository;

    @Override
    public Set<VisitDateDTO> getAll() {
        List<VisitDate> tempList=visitRepository.visitsGrouped();
        Logger log = Logger.getLogger(UserController.class.getName());

        log.info(" visited: " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

        return tempList.stream().map(vd->new VisitDateDTO(vd.getId(),vd.getDate(),vd.getTime())).collect(Collectors.toSet());
      // return visitRepository.findAll();
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
