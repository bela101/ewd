
package com.example.ewd.repository;

import com.example.ewd.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    @Query("SELECT p FROM Plant p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.latinName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.color) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.sunlightRequirements) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.soilRequirements) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Plant> searchPlants(@Param("query") String query);
}