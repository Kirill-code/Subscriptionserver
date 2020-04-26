package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.FirebaseUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<FirebaseUsers, Long> {
    @Query("select u from FirebaseUsers u  where u.email = ?1")
    FirebaseUsers findByEmail(String email);
}
