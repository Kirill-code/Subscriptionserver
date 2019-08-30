package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    LocalDateTime current=LocalDateTime.now();

    @Autowired
    SubscriptionRepository subRepository;
    /*
    * This update db
    * */
    @Override
    public SubscriptionDTO getByUidDto(String uid) {

        Subscription temp=subRepository.findByUid(uid,Boolean.TRUE);

        Set<VisitDate> visits=temp.getVisitDates();

       VisitDate crD=new VisitDate();
        //crD.setDate(current);
        crD.setInstr_id(1);
        visits.add(crD);
        temp.setVisitDates(visits);
       SubscriptionDTO test= new SubscriptionDTO();
                test.setId(temp.getId());
                test.setPrice(temp.getPrice());
                test.setUserid(temp.getUserid());
                test.setUserName(temp.getAssociatedUser().getName());
                test.setUserSurName(temp.getAssociatedUser().getSurname());
                test.setUserMobile(temp.getAssociatedUser().getMobile());
                test.setDescription(temp.getDescription());
                test.setSaleDate(temp.getSaleDate());
                test.setFinishDate(temp.getFinishDate());
                test.setInstructorId(temp.getAssociatedInstructor().getId());
                test.setInstrName(temp.getAssociatedInstructor().getName());
                test.setInstrSurname(temp.getAssociatedInstructor().getSurname());
               // test.setVisitDates( temp.getVisitDates().stream().map(vd->new VisitDateDTO(vd.getId(),vd.getDate())).collect(Collectors.toSet()));
        return test;
    }
    public Subscription convertDTO2Sub(SubscriptionDTO subscriptionDTO){
        Subscription temp=new Subscription();
        temp.setPrice(subscriptionDTO.getPrice());
        temp.setUserid(subscriptionDTO.getUserid());
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
    public void remove(Long id) {
        subRepository.deleteById(id);
    }

}
