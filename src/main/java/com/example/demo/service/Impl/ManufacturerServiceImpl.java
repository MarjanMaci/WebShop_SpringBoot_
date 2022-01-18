package com.example.demo.service.Impl;

import com.example.demo.model.Manufacturer;
import com.example.demo.repository.InMemoryManufacturerRepository;
import com.example.demo.repository.jpa.ManufacturerRepository;
import com.example.demo.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository=manufacturerRepository;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        Manufacturer novo=new Manufacturer(name,address);
        return Optional.of(manufacturerRepository.save(novo));
    }

    @Override
    public void deleteById(Long id) {
        manufacturerRepository.deleteById(id);
    }

    @Override
    public Manufacturer findByName(String text){
        return manufacturerRepository.findByName(text);
    }
}
