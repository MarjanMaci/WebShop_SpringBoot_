package com.example.demo.model.Exceptions;

import com.example.demo.model.ShoppingCart;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(Long id){super(String.format("ShoppingCart with id: %d is not found", id));}
}
