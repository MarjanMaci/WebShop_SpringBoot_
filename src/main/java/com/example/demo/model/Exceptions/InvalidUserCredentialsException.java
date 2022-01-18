package com.example.demo.model.Exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid User Credentials!");
    }
}
