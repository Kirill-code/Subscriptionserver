package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("select s from Subscription s inner join s.associatedUser u where u.uid = ?1 and s.current = ?2")
    Subscription findByUid(String uid, Boolean current);

}
