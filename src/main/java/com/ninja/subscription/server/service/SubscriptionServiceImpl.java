package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepository subRepository;

    @Override
    public List<Subscription> getAll() {
        return subRepository.findAll();
    }

    @Override
    public Subscription getByID(long id) {
        return subRepository.getOne(id);
    }

    @Override
    public Subscription save(Subscription subscription) {
        return subRepository.saveAndFlush(subscription);
    }

    @Override
    public void remove(long id) {
        subRepository.delete(id);
    }

    public Subscription error() {
        Subscription sub=new Subscription();
        sub.setDescription("Error");
        sub.setId(1);
        sub.setPrice(0);
        sub.setUserId("1");
        return sub;
    }
}
