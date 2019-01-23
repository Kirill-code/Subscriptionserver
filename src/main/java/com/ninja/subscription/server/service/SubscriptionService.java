package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAll();
    Subscription getByID(long id);
    Subscription save(Subscription Subscription);
    void remove(long id);
    Subscription error();
}
