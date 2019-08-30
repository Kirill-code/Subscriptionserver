package com.ninja.subscription.server.service;


import com.ninja.subscription.server.model.VisitDate;
import com.ninja.subscription.server.repository.VisitDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    VisitDateRepository visitRepository;

    @Override
    public List<VisitDate> getAll() {
        List<VisitDate> liss=visitRepository.findAll();

        return liss;
    }

    @Override
    public VisitDate getByID(long id) {
        return visitRepository.getOne(id);
    }

    @Override
    public VisitDate save(VisitDate VisitDate) {
        return visitRepository.saveAndFlush(VisitDate);
    }

    @Override
    public void remove(Long id) {
        visitRepository.deleteById(id);
    }
}
