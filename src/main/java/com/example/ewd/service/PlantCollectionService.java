package com.example.ewd.service;

import com.example.ewd.entity.PlantCollection;
import com.example.ewd.repository.PlantCollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantCollectionService {

    private final PlantCollectionRepository repo;

    public PlantCollectionService(PlantCollectionRepository repo) {
        this.repo = repo;
    }

    public List<PlantCollection> findAll() {
        return repo.findAll();
    }

    public PlantCollection save(PlantCollection c) {
        return (PlantCollection) repo.save(c);
    }
}
