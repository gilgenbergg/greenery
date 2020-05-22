package com.example.greenery.service;

import com.example.greenery.model.Plant;
import com.example.greenery.model.PurchaseRequest;
import com.example.greenery.repo.PlantRepo;
import com.example.greenery.repo.PreqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private PreqRepo preqRepo;

    public PurchaseService(PreqRepo preqRepo) {
        this.preqRepo = preqRepo;
    }

    public List<PurchaseRequest> findAll() {
        return preqRepo.findAll();
    }

    public PreqRepo getPreqRepo() {
        return preqRepo;
    }
    @Autowired
    public void setPreqRepo(PreqRepo preqRepo) {
        this.preqRepo = preqRepo;
    }
}
