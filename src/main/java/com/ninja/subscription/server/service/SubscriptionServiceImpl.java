package com.ninja.subscription.server.service;

import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.FirebaseUsers;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    Date current = new java.sql.Date(System.currentTimeMillis());

    @Autowired
    SubscriptionRepository subRepository;

    @Autowired
    private UserService service;

    @Autowired
    private InstructorService instructorService;

    @Override
    public SubscriptionDTO getByUidDto(String uid) {

        Subscription temp = subRepository.findByUid(uid, Boolean.TRUE);

        SubscriptionDTO test = new SubscriptionDTO();
        test.setId(temp.getId());
        test.setPrice(temp.getAssociatedPrice().getCost());
        test.setAssociatedUserId(temp.getAssociatedFirebaseUsers().getUid());
        test.setUserName(temp.getAssociatedFirebaseUsers().getName());
        test.setUserSurName(temp.getAssociatedFirebaseUsers().getSurname());
        test.setUserMobile(temp.getAssociatedFirebaseUsers().getMobile());
        test.setDescription(temp.getDescription());
        test.setSaleDate(temp.getSaleDate());
        test.setFinishDate(temp.getFinishDate());
        test.setInstructorId(temp.getAssociatedInstructor().getId());
        test.setInstrName(temp.getAssociatedInstructor().getName());
        test.setInstrSurname(temp.getAssociatedInstructor().getSurname());
        test.setVisitDates(temp.getVisitDates().stream().map(vd -> new VisitDateDTO(vd.getId(), vd.getDate(),vd.getInstr_id(), vd.getTime())).collect(Collectors.toSet()));
        Logger log = Logger.getLogger(UserController.class.getName());

        log.info(" Subscription " + uid + " selected - : " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));

        return test;
    }

   /* public Subscription convertDTO2Sub(SubscriptionDTO subscriptionDTO) {
        Subscription temp = new Subscription();
        temp.setPrice(subscriptionDTO.getPrice());
        temp.setDescription(subscriptionDTO.getDescription());
        temp.setSaleDate(subscriptionDTO.getSaleDate());
        temp.setFinishDate(subscriptionDTO.getFinishDate());

        return temp;
    }*/

    @Override
    public Subscription save(Subscription sub) {
        return subRepository.saveAndFlush(sub);
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void insertNew() {
        Subscription newSub=new Subscription();
            newSub.setCurrent(true);
            newSub.setDescription("Sint test");
            /*
            * взять все абонементы пользователя и проверить
            * */

            newSub.setFinishDate(new Date(System.currentTimeMillis()));
            newSub.setCount(0);
            newSub.setSaleDate(new Date(System.currentTimeMillis()));
            //newSub.setPrice(1200);
            newSub.setAssociatedFirebaseUsers(service.getByEmail("test@test.com"));
            newSub.setAssociatedInstructor(instructorService.getByID(3));

        subRepository.saveAndFlush(newSub);
    }



}
