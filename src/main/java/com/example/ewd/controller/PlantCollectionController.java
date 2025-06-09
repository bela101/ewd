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

    @GetMapping("/all")
    public ResponseEntity<List<PlantCollection>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping
    public ResponseEntity<List<PlantCollection>> publicCollections() {
        return ResponseEntity.ok(service.findPublicCollections());
    }

    @GetMapping("/user")
    public ResponseEntity<List<PlantCollection>> userCollections() {
        return ResponseEntity.ok(service.findUserCollections());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantCollection> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PlantCollection> create(@RequestBody PlantCollection c) {
        return ResponseEntity.ok(service.save(c));
    }
}
