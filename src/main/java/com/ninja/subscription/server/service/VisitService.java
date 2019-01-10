package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Subscription;
import com.ninja.subscription.server.model.VisitDate;

import java.util.List;

public interface VisitService {
    List<VisitDate> getAll();
    VisitDate getByID(long id);
    VisitDate save(VisitDate visit);
    void remove(long id);
}
