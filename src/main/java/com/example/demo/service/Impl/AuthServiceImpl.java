package com.example.demo.service.Impl;

import com.example.demo.model.Exceptions.InvalidArgumentsException;
import com.example.demo.model.Exceptions.InvalidUserCredentialsException;
import com.example.demo.model.User;
import com.example.demo.repository.InMemoryUserRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final InMemoryUserRepository inMemoryUserRepository;

    public AuthServiceImpl(InMemoryUserRepository inMemoryUserRepository){
        this.inMemoryUserRepository=inMemoryUserRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return inMemoryUserRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
