package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "resource")
public class Resource {

    public Resource() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    public Integer resourceId;

    @Column(name = "type")
    public String type;

    @Column(name = "plant_id")
    public Integer plantId;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantID(Integer plantId) {
        this.plantId = plantId;
    }

}
