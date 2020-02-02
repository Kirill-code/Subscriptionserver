package com.ninja.subscription.server.service;

import com.ninja.subscription.server.controller.UserController;
import com.ninja.subscription.server.model.FirebaseUsers;
import com.ninja.subscription.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserRepository userRepository;

    @Override
    public List<FirebaseUsers> getAll() {
        return userRepository.findAll();
    }

    @Override
    public FirebaseUsers getByEmail(String email) {
        FirebaseUsers temp=userRepository.findByEmail(email);
        return temp;
    }


    @Override
    public FirebaseUsers newUser(FirebaseUsers newUser) {
        log.info(" New user inserted for " + newUser.getUid() + ": " + new SimpleDateFormat("yyyy.MM.dd HH:mm ").format(new Date()));
            return userRepository.saveAndFlush(newUser);
    }


}
