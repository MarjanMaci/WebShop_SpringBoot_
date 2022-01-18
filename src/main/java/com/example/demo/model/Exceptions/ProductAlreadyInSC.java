package com.example.demo.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
public class ProductAlreadyInSC extends RuntimeException{
    public ProductAlreadyInSC(Long id, String username){super(String.format("%s has already added product with %d to ShoppingCart",username,id));}
}
