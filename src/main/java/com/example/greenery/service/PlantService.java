package com.example.greenery.service;

import com.example.greenery.exceptions.ItemNotFoundException;
import com.example.greenery.model.Plant;
import com.example.greenery.repo.PlantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlantService {

    private PlantRepo plantRepo;

    public PlantService(PlantRepo plantRepo) {
        this.plantRepo = plantRepo;
    }

    public List<Plant> findAll() {
        return plantRepo.findAll();
    }

    public PlantRepo getPlantRepo() {
        return plantRepo;
    }

    public List<Plant> plantsByUID(Integer uid) {
        return plantRepo.getPlantsByClientId(uid).orElseThrow(() -> new ItemNotFoundException(uid));
    }

    public Plant findPlantByPlantId(Integer plantId) {
        return plantRepo.getPlantByPlantId(plantId).orElseThrow(() -> new ItemNotFoundException(plantId));
    }

    public void addPlant(Plant newPlant) {
        plantRepo.save(newPlant);
    }

    public void setLastInspection(Integer plantId, String newDate) {
        plantRepo.getPlantByPlantId(plantId)
                .map(plant -> {
                    plant.setLastInspection(newDate);
                    return plantRepo.save(plant);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(plantId);
                });
    }

    public void setNextInspection(Integer plantId, String newDate) {
        plantRepo.getPlantByPlantId(plantId)
                .map(plant -> {
                    plant.setNextInspection(newDate);
                    return plantRepo.save(plant);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(plantId);
                });
    }

    //TODO
//    @Transactional
//    public void deletePlantById(Integer plantId) {
//        plantRepo.deleteByPlantId(plantId);
//    }

    @Autowired
    public void setPlantRepo(PlantRepo plantRepo) {
        this.plantRepo = plantRepo;
    }
}
