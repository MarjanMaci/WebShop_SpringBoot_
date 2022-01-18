package com.example.demo.service.Impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        return null;
    }
}
