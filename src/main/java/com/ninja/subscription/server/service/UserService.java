package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    //User getByID(long id);
    User error();
}
