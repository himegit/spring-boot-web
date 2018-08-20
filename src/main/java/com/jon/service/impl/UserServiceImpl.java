package com.jon.service.impl;

import com.jon.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void userSave() {
        System.out.println(1);
    }
}
