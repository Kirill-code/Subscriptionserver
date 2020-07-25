package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.FirebaseUsers;

import java.util.List;

public interface UserService {
    List<FirebaseUsers> getAll();
    FirebaseUsers getByEmail(String email);
    FirebaseUsers newUser(FirebaseUsers user);
    FirebaseUsers getOne(long id);
    boolean checkUser(String uid);
}
