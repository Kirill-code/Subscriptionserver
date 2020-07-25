package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.Price;

import java.util.List;

public interface PriceService {
    List<Price> getAll();
    Price getByID(long id);
}
