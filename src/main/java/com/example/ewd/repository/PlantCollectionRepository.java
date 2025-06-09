package com.example.ewd.repository;

import com.example.ewd.entity.PlantCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantCollectionRepository extends JpaRepository<PlantCollection, Long> {
    List<PlantCollection> findByIsPublicTrue();
    List<PlantCollection> findByUserId(Long userId);
}
