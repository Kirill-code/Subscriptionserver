package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.VisitDate;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAll();
    Subscription getByID(long id);
    Subscription save(Subscription Subscription);
    void remove(long id);
    Subscription error();
    List<VisitDate> getDates(long id);
    Subscription getByUid(String uid);
}
