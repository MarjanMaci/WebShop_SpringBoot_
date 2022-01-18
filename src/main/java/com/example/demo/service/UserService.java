package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname);
}
