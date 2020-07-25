package com.ninja.subscription.server.service;

import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.FirebaseUsers;
import com.ninja.subscription.server.model.Price;
import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.VisitDateDTO;
import com.ninja.subscription.server.model.dto.incomeSubscriptionDTO;
import com.ninja.subscription.server.repository.PriceRepository;
import com.ninja.subscription.server.repository.SubscriptionRepository;
import com.ninja.subscription.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    Logger log = Logger.getLogger(SubscriptionServiceImpl.class.getName());

    Date current = new java.sql.Date(System.currentTimeMillis());

    @Autowired
    SubscriptionRepository subRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    PriceRepository priceRepository;

    @Override
    public SubscriptionDTO getByUidDto(String uid) {
        SubscriptionDTO tempDTO = new SubscriptionDTO();
        if(subRepository.findByUid(uid, Boolean.TRUE)!=null) {

            Subscription temp = subRepository.findByUid(uid, Boolean.TRUE);

            tempDTO.setId(temp.getId());
            tempDTO.setPrice(temp.getAssociatedPrice().getCost());
            tempDTO.setUid(temp.getAssociatedFirebaseUsers().getUid());
            tempDTO.setUserName(temp.getAssociatedFirebaseUsers().getName());
            tempDTO.setUserSurName(temp.getAssociatedFirebaseUsers().getSurname());
            tempDTO.setUserMobile(temp.getAssociatedFirebaseUsers().getMobile());
            tempDTO.setDescription(temp.getDescription());
            tempDTO.setSaleDate(temp.getSaleDate());
            tempDTO.setFinishDate(temp.getFinishDate());
            tempDTO.setInstructorId(temp.getAssociatedInstructor().getId());
            tempDTO.setInstrName(temp.getAssociatedInstructor().getName());
            tempDTO.setInstrSurname(temp.getAssociatedInstructor().getSurname());
            tempDTO.setVisitDates(temp.getVisitDates().stream().map(vd -> new VisitDateDTO(vd.getId(), vd.getDate(), vd.getInstr_id(), vd.getTime())).collect(Collectors.toSet()));
            Logger log = Logger.getLogger(SubscriptionDTO.class.getName());

            log.info(" Subscription " + uid + " selected - : " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));
        }
        else {
            tempDTO.setDescription("Subscription doesn't exist.");
        }
        return tempDTO;
    }

    public Subscription convertNewDTO2Sub(incomeSubscriptionDTO subscriptionDTO) {
        //change to get correct one
        Price price=priceRepository.findOne(subscriptionDTO.getPrice());

        Calendar calendar = Calendar.getInstance();
        Date currentDate=calendar.getTime();
        calendar.add(Calendar.DATE,  price.getDuration());
        Date finishDate = calendar.getTime();

        Subscription temp = new Subscription();
        temp.setAssociatedFirebaseUsers(userRepository.getOne(subscriptionDTO.getAssociatedUserId()));
        temp.setDescription(subscriptionDTO.getDescription());
        temp.setAssociatedPrice(price);
        temp.setDescription(subscriptionDTO.getDescription());
        //finish date getpriceDAYS+current
        temp.setSaleDate(currentDate);
        temp.setFinishDate(finishDate);

        return temp;
    }

    @Override
    public boolean save(incomeSubscriptionDTO sub) {
        try{
            subRepository.saveAndFlush(convertNewDTO2Sub(sub));
            log.info( new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date())+" Subscription created" );

            return true;
        }catch (Exception ex){
            log.warning(new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date())+" Subscription create failed : "+ex.getMessage());
            return false;
        }

    }

    @Override
    public void remove(long id) {

    }




}
