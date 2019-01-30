package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.User;


import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {


    @Override
    public List<User> getAll() {
        List<User> list=new ArrayList<>();
        list.add(new User("Test","Test",false));
        list.add(new User("Test2","Test2",true));

        return list;
    }


    @Override
    public User error() {
        return new User("Error","Error",false);
    }
}
