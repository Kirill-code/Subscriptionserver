package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.VisitDate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitDateRepository extends JpaRepository<VisitDate, Long> {
}
