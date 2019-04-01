package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.VisitDateDTO;

import java.util.List;

public interface SubscriptionService {
    //SubscriptionDTO getByID(long id);
    Subscription save(Subscription Subscription);
    void remove(long id);
    SubscriptionDTO getByUidDto(String uid);
    void insertNew(SubscriptionDTO subscription);
    //void addNewOne(String uid);

}
