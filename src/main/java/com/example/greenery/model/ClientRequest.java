package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "creq")
public class ClientRequest {

    public ClientRequest() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creq_id")
    public Integer cReqID;

    @Column(name = "plant_id")
    public Integer plantID;

    @Column(name = "client_id")
    public Integer clientID;

    @Column(name = "landscaper_id")
    public Integer landscaperID;

    @Column(name = "admin_id")
    public Integer adminID;

    @Column(name = "status")
    public String status;

    @Column(name = "type")
    public String creqType;

    public Integer getcReqID() {
        return cReqID;
    }

    public void setcReqID(Integer cReqID) {
        this.cReqID = cReqID;
    }

    public Integer getPlantID() {
        return plantID;
    }

    public void setPlantID(Integer plantID) {
        this.plantID = plantID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getLandscaperID() {
        return landscaperID;
    }

    public void setLandscaperID(Integer landscaperID) {
        this.landscaperID = landscaperID;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreqType() {
        return creqType;
    }

    public void setCreqType(String creqType) {
        this.creqType = creqType;
    }
}
