package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(String name, String description);

    Category update(String name, String description);

    List<Category> findAll();

    List<Category> findAllByNameLike(String text);

    void deleteByName(String name);

    Category findByName(String text);
}

