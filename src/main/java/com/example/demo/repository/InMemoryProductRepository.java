package com.example.demo.repository;

import com.example.demo.bootstrap.DataHolder;
import com.example.demo.model.Category;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {
    public List<Product> findAll() {
        return DataHolder.products;
    }

    public Optional<Product> findById(Long id) {
        return DataHolder.products.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Product> findByName(String name) {
        return DataHolder.products.stream().filter(i -> i.getName().equals(name)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.products.removeIf(i -> i.getId().equals(id));
    }

    public void deleteByName(String name) { DataHolder.products.removeIf(i->i.getName().equals(name)); }

    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Category category, Manufacturer manufacturer) {
        DataHolder.products.removeIf(i -> i.getName().equals(name));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.add(product);
        return Optional.of(product);
    }

    public Optional<Product> save (Product product){
        DataHolder.products.removeIf(p->p.getName().equals(product.getName()));
        DataHolder.products.add(product);
        return Optional.of(product);
    }

}
