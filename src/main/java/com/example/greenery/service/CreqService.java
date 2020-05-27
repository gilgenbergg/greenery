package com.example.greenery.service;

import com.example.greenery.exceptions.ItemNotFoundException;
import com.example.greenery.exceptions.StatusNotFoundException;
import com.example.greenery.exceptions.TypeNotFoundException;
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

    public ClientRequest getCreqByCreqId(Integer creqId) {
        return creqRepo.getClientRequestByCreqId(creqId).orElseThrow(() -> new ItemNotFoundException(creqId));
    }

    public void addCreq(ClientRequest newCreq) {
        creqRepo.save(newCreq);
    }

    public List<ClientRequest> getCreqByStatus(String status) {
        return creqRepo.getClientRequestByStatus(status).orElseThrow(() -> new StatusNotFoundException(status));
    }

    public List<ClientRequest> getCreqByType(String type) {
        return creqRepo.getClientRequestByType(type).orElseThrow(() -> new TypeNotFoundException(type));
    }

    public ClientRequest getClientRequestByCreqId(Integer creqId) {
        return creqRepo.getClientRequestByCreqId(creqId).orElseThrow(() -> new ItemNotFoundException(creqId));
    }

    public List<ClientRequest> getCreqByClientId(Integer clientId) {
        return creqRepo.getClientRequestByClientId(clientId).orElseThrow(() -> new ItemNotFoundException(clientId));
    }

    public void assignAdmin(Integer creqId, Integer adminId) {
        creqRepo.getClientRequestByCreqId(creqId)
                .map(creq -> {
                    creq.setAdminId(adminId);
                    return creqRepo.save(creq);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(creqId);
                });
    }

    public void assignLandscaper(Integer creqId, Integer landscaperId) {
        creqRepo.getClientRequestByCreqId(creqId)
                .map(creq -> {
                    creq.setLandscaperId(landscaperId);
                    return creqRepo.save(creq);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(creqId);
                });
    }

    public void setCreqStatus(Integer creqId, String status) {
        creqRepo.getClientRequestByCreqId(creqId)
                .map(creq -> {
                    creq.setStatus(status);
                    return creqRepo.save(creq);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(creqId);
                });
    }

    public void setCreqType(Integer creqId, String type) {
        creqRepo.getClientRequestByCreqId(creqId)
                .map(creq -> {
                    creq.setCreqType(type);
                    return creqRepo.save(creq);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(creqId);
                });
    }

    public List<ClientRequest> getCreqByAdminId(Integer adminId) {
        return creqRepo.getClientRequestByAdminId(adminId).orElseThrow(() -> new ItemNotFoundException(adminId));
    }

    public List<ClientRequest> getCreqByLandscaperId(Integer landscaperId) {
        return creqRepo.getClientRequestByLandscaperId(landscaperId).orElseThrow(() -> new ItemNotFoundException(landscaperId));
    }

    public CreqRepo getCreqRepo() {
        return creqRepo;
    }
    @Autowired
    public void setCreqRepo(CreqRepo creqRepo) {
        this.creqRepo = creqRepo;
    }

}