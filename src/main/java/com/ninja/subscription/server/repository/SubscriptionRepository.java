package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("select s from Subscription s where s.userid = ?1 and s.current = ?2")
    Subscription findByUid(String userid, Boolean current);
    /*@Query("select s from Subscription s where s.visitDates = ?1")
    Subscription findByDate(String userid);*/
}
