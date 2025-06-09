package com.example.ewd.service;

import com.example.ewd.ResourceNotFoundException;
import com.example.ewd.entity.PlantCollection;
import com.example.ewd.entity.User;
import com.example.ewd.repository.PlantCollectionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<PlantCollection> findPublicCollections() {
        return repo.findByIsPublicTrue();
    }

    public List<PlantCollection> findUserCollections() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        return repo.findByUserId(userId);
    }

    public PlantCollection save(PlantCollection c) {
        return repo.save(c);
    }

    public PlantCollection findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return repo.findById(id)
                .filter(collection -> collection.getIsPublic() ||
                        collection.getUser().getId().equals(currentUser.getId()))
                .orElseThrow( () -> new ResourceNotFoundException("Collection not found or access denied"));

    }
}
