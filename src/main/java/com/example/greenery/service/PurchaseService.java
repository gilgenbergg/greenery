package com.example.greenery.service;

import com.example.greenery.exceptions.ItemNotFoundException;
import com.example.greenery.exceptions.StatusNotFoundException;
import com.example.greenery.exceptions.TypeNotFoundException;
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

    public PurchaseRequest getPreqByPreqId(Integer preqId) {
        return preqRepo.getPurchaseRequestByPreqId(preqId).orElseThrow(() -> new ItemNotFoundException(preqId));
    }

    public void addPreq(PurchaseRequest newPreq) {
        preqRepo.save(newPreq);
    }

    public List<PurchaseRequest> getPreqByStatus(String status) {
        return preqRepo.getPurchaseRequestByStatus(status).orElseThrow(() -> new StatusNotFoundException(status));
    }

    public List<PurchaseRequest> getPreqByClientId(Integer clientId) {
        return preqRepo.getPurchaseRequestByClientId(clientId).orElseThrow(() -> new ItemNotFoundException(clientId));
    }

    public void assignAdmin(Integer preqId, Integer adminId) {
        preqRepo.getPurchaseRequestByPreqId(preqId)
                .map(preq -> {
                    preq.setAdminId(adminId);
                    return preqRepo.save(preq);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(preqId);
                });
    }

    public void assignLandscaper(Integer preqId, Integer landscaperId) {
        preqRepo.getPurchaseRequestByPreqId(preqId)
                .map(preq -> {
                    preq.setLandscaperId(landscaperId);
                    return preqRepo.save(preq);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(preqId);
                });
    }

    public void setPreqStatus(Integer preqId, String status) {
        preqRepo.getPurchaseRequestByPreqId(preqId)
                .map(preq -> {
                    preq.setStatus(status);
                    return preqRepo.save(preq);
                })
                .orElseGet(() -> {
                    throw new ItemNotFoundException(preqId);
                });
    }

    public List<PurchaseRequest> getPreqByAdminId(Integer adminId) {
        return preqRepo.getPurchasetRequestByAdminId(adminId).orElseThrow(() -> new ItemNotFoundException(adminId));
    }

    public List<PurchaseRequest> getPreqByLandscaperId(Integer landscaperId) {
        return preqRepo.getPurchaseRequestByLandscaperId(landscaperId).orElseThrow(() -> new ItemNotFoundException(landscaperId));
    }

    public PreqRepo getPreqRepo() {
        return preqRepo;
    }
    @Autowired
    public void setPreqRepo(PreqRepo preqRepo) {
        this.preqRepo = preqRepo;
    }

}
