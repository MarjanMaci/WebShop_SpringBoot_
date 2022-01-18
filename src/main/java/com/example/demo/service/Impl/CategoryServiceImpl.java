package com.example.demo.service.Impl;

import com.example.demo.model.Category;
import com.example.demo.repository.InMemoryCategoryRepository;
import com.example.demo.repository.jpa.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    public final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void deleteByName(String name) {
        categoryRepository.deleteByNameLike(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByNameLike(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }

    @Override
    public Category findByName(String text){
        return categoryRepository.findByName(text);
    }
}
