package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "instruction")
public class Instruction {

    public Instruction() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instruction_id")
    public Integer instructionId;

    public Integer getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Integer instructionId) {
        this.instructionId = instructionId;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Column(name = "instruction")
    public String instruction;

}
