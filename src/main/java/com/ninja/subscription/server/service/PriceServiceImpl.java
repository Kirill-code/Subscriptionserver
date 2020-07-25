package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Price;
import com.ninja.subscription.server.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceServiceImpl implements PriceService  {
    @Autowired
    PriceRepository repository;
    @Override
    public List<Price> getAll() {
        return repository.findAll();
    }

    @Override
    public Price getByID(long id) {
        return repository.findOne(id);
    }
}
