package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.Instruction;
import com.example.greenery.model.Plant;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructionRepo extends JpaRepository<Instruction, Long> {

    @Query(value = "select * from instruction where instruction_id = :instructionId")
    Optional<Instruction> getInstructionByInstructionId(Integer instructionId);
}
