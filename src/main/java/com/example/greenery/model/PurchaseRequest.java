package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "preq")
public class PurchaseRequest {
    
    public PurchaseRequest() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preq_id")
    public Integer preqId;

    @Column(name = "creq_id")
    public Integer creqId;

    @Column(name = "plant_id")
    public Integer plantId;

    @Column(name = "admin_id")
    public Integer adminId;

    @Column(name = "client_id")
    public Integer clientId;

    @Column(name = "landscaperq_id")
    public Integer landscaperId;

    @Column(name = "status")
    public String status;

    public Integer getPreqId() {
        return preqId;
    }

    public void setPreqId(Integer pReqId) {
        this.preqId = pReqId;
    }

    public Integer getCreqId() {
        return creqId;
    }

    public void setCreqId(Integer cReqId) {
        this.creqId = cReqId;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getLandscaperId() {
        return landscaperId;
    }

    public void setLandscaperId(Integer landscaperId) {
        this.landscaperId = landscaperId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
