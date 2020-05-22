package com.example.greenery.service;

import com.example.greenery.exceptions.ItemNotFoundException;
import com.example.greenery.model.AuthData;
import com.example.greenery.model.Instruction;
import com.example.greenery.repo.InstructionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructionService {

    private InstructionRepo instructionRepo;

    public InstructionService(InstructionRepo instructionRepo) {
        this.instructionRepo = instructionRepo;
    }

    public List<Instruction> findAll() {
        return instructionRepo.findAll();
    }

    public Instruction getInstructionByInstructionId(Integer instructionId) {
        return instructionRepo.getInstructionByInstructionId(instructionId)
                .orElseThrow(() -> new ItemNotFoundException(instructionId));
    }

    public void addInstruction(Instruction newInstruction) {
        instructionRepo.save(newInstruction);
    }

    public InstructionRepo getInstructionRepo() {
        return instructionRepo;
    }
    @Autowired
    public void setInstructionRepo(InstructionRepo instructionRepo) {
        this.instructionRepo = instructionRepo;
    }

}
