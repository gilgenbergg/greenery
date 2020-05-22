package com.example.greenery.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plant")
public class Plant {

    public Plant() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    public Integer plantId;

    @Column(name = "type")
    public String type;

    @Column(name = "last_inspection")
    public String lastInspection;

    @Column(name = "next_inspection")
    public String nextInspection;

    @Column(name = "instruction_id")
    public Integer instructionId;

    @Column(name = "client_id")
    public Integer clientId;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastInspection() {
        return lastInspection;
    }

    public void setLastInspection(String lastInspection) {
        this.lastInspection = lastInspection;
    }

    public String getNextInspection() {
        return nextInspection;
    }

    public void setNextInspection(String nextInspection) {
        this.nextInspection = nextInspection;
    }

    public Integer getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Integer instructionId) {
        this.instructionId = instructionId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
