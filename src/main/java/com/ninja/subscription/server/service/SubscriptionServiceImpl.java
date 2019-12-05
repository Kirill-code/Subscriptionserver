package com.ninja.subscription.server.service;

import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    Date current=new java.sql.Date(System.currentTimeMillis());

    @Autowired
    SubscriptionRepository subRepository;

    @Override
    public SubscriptionDTO getByUidDto(String uid) {

        Subscription temp=subRepository.findByUid(uid,Boolean.TRUE);
/*
       VisitDate crD=new VisitDate();
        crD.setDate(current);
        crD.setInstr_id(1);

        visits.add(crD);
        temp.setVisitDates(visits);*/
       SubscriptionDTO test= new SubscriptionDTO();
                test.setId(temp.getId());
                test.setPrice(temp.getPrice());
                test.setAssociatedUserId(temp.getAssociatedFirebaseUsers().getUid()
                );
                test.setUserName(temp.getAssociatedFirebaseUsers().getName());
                test.setUserSurName(temp.getAssociatedFirebaseUsers().getSurname());
                test.setUserMobile(temp.getAssociatedFirebaseUsers().getMobile());
                test.setDescription(temp.getDescription());
                test.setSaleDate(temp.getSaleDate());
                test.setFinishDate(temp.getFinishDate());
                test.setInstructorId(temp.getAssociatedInstructor().getId());
                test.setInstrName(temp.getAssociatedInstructor().getName());
                test.setInstrSurname(temp.getAssociatedInstructor().getSurname());
                test.setVisitDates( temp.getVisitDates().stream().map(vd->new VisitDateDTO(vd.getId(),vd.getDate(),vd.getTime())).collect(Collectors.toSet()));
        Logger log = Logger.getLogger(UserController.class.getName());

        log.info(" Subscriptions - : " + new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").format(new Date()));

        return test;
    }
    public Subscription convertDTO2Sub(SubscriptionDTO subscriptionDTO){
        Subscription temp=new Subscription();
        temp.setPrice(subscriptionDTO.getPrice());
        temp.setDescription(subscriptionDTO.getDescription());
        temp.setSaleDate(subscriptionDTO.getSaleDate());
        temp.setFinishDate(subscriptionDTO.getFinishDate());

        return temp;
    }

    @Override
    public Subscription save(Subscription sub) {
        return subRepository.saveAndFlush(sub);
    }


    @Override
    public void insertNew(SubscriptionDTO subscription) {
        //TODO create new user in FireBase


        save(convertDTO2Sub(subscription));
    }

    @Override
    public void remove(long id) {
        subRepository.delete(id);
    }

}
