package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Instructor;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.InstructorDTO;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    Date current=new java.sql.Date(System.currentTimeMillis());
    @Autowired
    SubscriptionRepository subRepository;
    /*
    * This update db
    * */
    @Override
    public SubscriptionDTO getByUidDto(String uid) {
        Subscription temp=subRepository.findByUid(uid);

        Set<VisitDate> visits=temp.getVisitDates();

       VisitDate crD=new VisitDate();
        crD.setDate(current);
        crD.setInstr_id(1);
        visits.add(crD);
        temp.setVisitDates(visits);
        save(temp);
       SubscriptionDTO test= new SubscriptionDTO();
                test.setId(temp.getId());
                test.setPrice(temp.getPrice());
                test.setUserid(temp.getUserid());
                test.setDescription(temp.getDescription());
                test.setSaleDate(temp.getSaleDate());
                test.setFinishDate(temp.getFinishDate());
                test.setInstructorId(temp.getAssociatedInstructor().getId());
                test.setInstrName(temp.getAssociatedInstructor().getName());
                test.setInstrSurname(temp.getAssociatedInstructor().getSurname());
        Set<VisitDateDTO> list = new HashSet<>();
        for (VisitDate vd : temp.getVisitDates()) {
            VisitDateDTO visitDateDTO = new VisitDateDTO(vd.getId(), vd.getDate());
            list.add(visitDateDTO);
        }
        test.setVisitDates(list);
        return test;
    }

   /* @Override*/
   /* public void addNewOne(String uid) {*/
   /*     Subscription newOne=new Subscription(uid,current,current,111,"TEst",))*/
   /* }*/

/*    @Override
    public SubscriptionDTO getByID(long id) {
        Subscription temp=subRepository.getOne(id);
        return new SubscriptionDTO(temp.getId(),temp.getPrice(),temp.getUserid(),
                temp.getDescription(),temp.getSaleDate(),
                new InstructorDTO(temp.getAssociatedInstructor().getId(),temp.getAssociatedInstructor().getName(),temp.getAssociatedInstructor().getSurname()),
                temp.getVisitDates().stream().map(vd->new VisitDateDTO(vd.getId(),vd.getDate())).collect(Collectors.toList()));
    }*/

    @Override
    public Subscription save(Subscription sub) {

        return subRepository.saveAndFlush(sub);
    }

    @Override
    public void remove(long id) {
        subRepository.delete(id);
    }

}
