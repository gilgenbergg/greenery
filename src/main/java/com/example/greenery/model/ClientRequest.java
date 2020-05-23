package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "creq")
public class ClientRequest {

    public ClientRequest() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creq_id")
    public Integer creqId;

    @Column(name = "plant_id")
    public Integer plantId;

    @Column(name = "client_id")
    public Integer clientId;

    @Column(name = "landscaper_id")
    public Integer landscaperId;

    @Column(name = "admin_id")
    public Integer adminId;

    @Column(name = "status")
    public String status;

    @Column(name = "type")
    public String type;

    public Integer getcReqId() {
        return creqId;
    }

    public void setcReqId(Integer cReqId) {
        this.creqId = cReqId;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getLandscaperId() {
        return landscaperId;
    }

    public void setLandscaperId(Integer landscaperId) {
        this.landscaperId = landscaperId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreqType() {
        return type;
    }

    public void setCreqType(String creqType) {
        this.type = creqType;
    }
}
