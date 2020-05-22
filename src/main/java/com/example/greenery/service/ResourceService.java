package com.example.greenery.service;

import com.example.greenery.exceptions.ItemNotFoundException;
import com.example.greenery.model.Instruction;
import com.example.greenery.model.PurchaseRequest;
import com.example.greenery.model.Resource;
import com.example.greenery.repo.PreqRepo;
import com.example.greenery.repo.ResourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private ResourceRepo resourceRepo;

    public ResourceService(ResourceRepo resourceRepo) {
        this.resourceRepo = resourceRepo;
    }

    public List<Resource> findAll() {
        return resourceRepo.findAll();
    }

    public List<Resource> getResourcesByPlantId(Integer plantId) {
        return resourceRepo.getResourcesByPlantId(plantId).orElseThrow(() -> new ItemNotFoundException(plantId));
    }

    public Resource getResourceByResourceId(Integer resourceId) {
        return resourceRepo.getResourceByResourceId(resourceId)
                .orElseThrow(() -> new ItemNotFoundException(resourceId));
    }

    public void addResource(Resource newResource) {
        resourceRepo.save(newResource);
    }

    public ResourceRepo getResourceRepo() {
        return resourceRepo;
    }
    @Autowired
    public void setResourceRepo(ResourceRepo resourceRepo) {
        this.resourceRepo = resourceRepo;
    }


}
