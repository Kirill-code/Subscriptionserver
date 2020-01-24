package com.ninja.subscription.server.service;

import com.ninja.subscription.server.model.FirebaseUsers;
import com.ninja.subscription.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<FirebaseUsers> getAll() {
        return userRepository.findAll();
    }
}
