package com.example.ewd.service;

import com.example.ewd.entity.Plant;
import com.example.ewd.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {
    private final PlantRepository repository;

    public PlantService(PlantRepository repository) {
        this.repository = repository;
    }

    public List<Plant> searchPlants(String query) {
        if (query == null || query.trim().isEmpty()) {
            return List.of();
        }
        return repository.searchPlants(query.trim());
    }

    public List<Plant> findAll() {
        return repository.findAll();
    }
}