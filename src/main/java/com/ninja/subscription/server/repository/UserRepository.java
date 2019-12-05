package com.ninja.subscription.server.repository;

import com.ninja.subscription.server.model.FirebaseUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<FirebaseUsers, Long> {

}
