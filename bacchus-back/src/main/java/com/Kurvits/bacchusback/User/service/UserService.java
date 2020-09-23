package com.Kurvits.bacchusback.User.service;

import com.Kurvits.bacchusback.User.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User saveUser(User user);
}
