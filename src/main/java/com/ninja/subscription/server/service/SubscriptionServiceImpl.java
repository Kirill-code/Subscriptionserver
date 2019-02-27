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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {


    @Autowired
    SubscriptionRepository subRepository;

    @Override
    public SubscriptionDTO getByUidDto(String uid) {
        Subscription temp=subRepository.findByUid(uid);
        List<VisitDate> visits=temp.getVisitDates();
        visits.add(new VisitDate(new java.sql.Date(System.currentTimeMillis()),temp));
        temp.setVisitDates(visits);
        SubscriptionDTO test= new SubscriptionDTO(
                temp.getId(),
                temp.getPrice(),
                temp.getUserid(),
                temp.getDescription(),
                temp.getSaleDate(),
                temp.getFinishDate(),
                new InstructorDTO(
                        temp.getAssociatedInstructor().getId(),
                        temp.getAssociatedInstructor().getName(),
                        temp.getAssociatedInstructor().getSurname()),
                temp.getVisitDates().stream().map(vd->new VisitDateDTO(vd.getId(),vd.getDate())).collect(Collectors.toList()));
        save(temp);
        return test;
    }

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
