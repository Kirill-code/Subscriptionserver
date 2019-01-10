package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
