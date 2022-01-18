package com.example.demo.repository;

import com.example.demo.bootstrap.DataHolder;
import com.example.demo.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {
    public List<Category> findAll() {
        return DataHolder.categories;
    }

    public Category save(Category c) {
        if(c==null || c.getName().isEmpty()){
            return null;
        }
        DataHolder.categories.removeIf(i -> i.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }

    public Optional<Category> findByName(String name) {
        if(name==null || name.isEmpty()){
            return null;
        }
        return DataHolder.categories.stream().filter(i->i.getName().equals(name)).findFirst();
    }

    public Optional<Category> findById(Long id) {
        return DataHolder.categories.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public List<Category> search(String text) {
        return DataHolder.categories.stream().filter(i->i.getName().contains(text) || i.getDescription().contains(text)).collect(Collectors.toList());
    }

    public void delete(String name) {
        DataHolder.categories.removeIf(i->i.getName().equals(name));
    }

}
