package com.Kurvits.bacchusback.User.service;


import com.Kurvits.bacchusback.User.User;
import com.Kurvits.bacchusback.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersByProductName(String productName){
        return userRepository.findByProductName(productName);
    }

    public User saveUser(User user) {
        return userRepository.save(user);

    }
}
