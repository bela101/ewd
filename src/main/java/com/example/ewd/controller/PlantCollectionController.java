package com.example.ewd.controller;

import com.example.ewd.entity.PlantCollection;
import com.example.ewd.service.PlantCollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class PlantCollectionController {

    private final PlantCollectionService service;

    public PlantCollectionController(PlantCollectionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PlantCollection>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<PlantCollection> create(@RequestBody PlantCollection c) {
        return ResponseEntity.ok(service.save(c));
    }
}
