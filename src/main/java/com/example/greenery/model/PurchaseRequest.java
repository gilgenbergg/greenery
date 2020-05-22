package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "preq")
public class PurchaseRequest {
    
    public PurchaseRequest() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preq_id")
    public Integer pReqID;

    @Column(name = "creq_id")
    public Integer cReqID;

    @Column(name = "plant_id")
    public Integer plantID;

    @Column(name = "admin_id")
    public Integer adminID;

    @Column(name = "landscaperq_id")
    public Integer landscaperID;

    @Column(name = "status")
    public String status;

    public Integer getpReqID() {
        return pReqID;
    }

    public void setpReqID(Integer pReqID) {
        this.pReqID = pReqID;
    }

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

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public Integer getLandscaperID() {
        return landscaperID;
    }

    public void setLandscaperID(Integer landscaperID) {
        this.landscaperID = landscaperID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
