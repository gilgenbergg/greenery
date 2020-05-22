package com.example.greenery.service;

import com.example.greenery.model.ClientRequest;
import com.example.greenery.repo.CreqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreqService {

    private CreqRepo creqRepo;

    public CreqService(CreqRepo creqRepo) {
        this.creqRepo = creqRepo;
    }

    public List<ClientRequest> findAll() {
        return creqRepo.findAll();
    }

    public CreqRepo getAdminRepo() {
        return creqRepo;
    }
    @Autowired
    public void setCreqRepo(CreqRepo creqRepo) {
        this.creqRepo = creqRepo;
    }

}
