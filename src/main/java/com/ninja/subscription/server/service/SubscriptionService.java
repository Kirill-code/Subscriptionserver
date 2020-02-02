package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;

public interface SubscriptionService {
    //SubscriptionDTO getByID(long id);
    Subscription save(Subscription Subscription);
    void remove(long id);
    SubscriptionDTO getByUidDto(String uid);
    void insertNew();
    //void addNewOne(String uid);


}
