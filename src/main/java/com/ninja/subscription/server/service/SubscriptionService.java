package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.dto.SubscriptionDTO;
import com.ninja.subscription.server.model.dto.incomeSubscriptionDTO;

public interface SubscriptionService {
    //SubscriptionDTO getByID(long id);
    boolean save(incomeSubscriptionDTO Subscription);
    void remove(long id);
    SubscriptionDTO getByUidDto(String uid);


}
