package com.example.demo.service.Impl;

import com.example.demo.model.Category;
import com.example.demo.model.Exceptions.CategoryNotFoundException;
import com.example.demo.model.Exceptions.ManufacturerNotFoundException;
import com.example.demo.model.Exceptions.ProductNotFoundException;
import com.example.demo.model.Manufacturer;
import com.example.demo.model.Product;
import com.example.demo.repository.InMemoryCategoryRepository;
import com.example.demo.repository.InMemoryManufacturerRepository;
import com.example.demo.repository.InMemoryProductRepository;
import com.example.demo.repository.jpa.CategoryRepository;
import com.example.demo.repository.jpa.ManufacturerRepository;
import com.example.demo.repository.jpa.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
        this.manufacturerRepository=manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long category, Long manufacturer) {
        Category category1 = categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category));
        Manufacturer manufacturer1 = manufacturerRepository.findById(manufacturer).orElseThrow(()-> new ManufacturerNotFoundException(manufacturer));
        productRepository.deleteByName(name);
        return Optional.of(productRepository.save(new Product(name,price,quantity,category1,manufacturer1)));
    }

    @Override
    public Optional<Product> save(Product productDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer) {
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        Category category1 = categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category));
        product.setCategory(category1);
        Manufacturer manufacturer1 = manufacturerRepository.findById(manufacturer).orElseThrow(()-> new ManufacturerNotFoundException(manufacturer));
        product.setManufacturer(manufacturer1);
        productRepository.deleteByName(name);
        return Optional.of(productRepository.save(product));
    }

    @Override
    public Optional<Product> edit(Long id, Product productDto) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
