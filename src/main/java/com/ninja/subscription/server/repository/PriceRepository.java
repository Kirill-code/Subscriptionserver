package com.ninja.subscription.server.repository;


import com.ninja.subscription.server.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
