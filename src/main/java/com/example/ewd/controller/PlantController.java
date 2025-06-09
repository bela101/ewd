package com.example.ewd.controller;

import com.example.ewd.entity.Plant;
import com.example.ewd.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService service;

    public PlantController(PlantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        return ResponseEntity.ok(service.findAll());
    }


    @RequestMapping("/search")
    public ResponseEntity<List<Plant>> searchPlants(@RequestParam String query) {
        return ResponseEntity.ok(service.searchPlants(query));
    }
}
