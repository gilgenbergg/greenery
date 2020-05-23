package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.Plant;
import com.example.greenery.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepo extends JpaRepository<Plant, Long> {
    @Query(value = "select * from plant where plant_id = :plantId")
    Optional<Plant> getPlantByPlantId(Integer plantId);

    @Query(value = "select * from plant where client_id = :userId")
    Optional<List<Plant>> getPlantsByClientId(Integer uid);

    //TODO
//    @Query(value = "delete * from resource where plant_id = :plantId; " +
//            "delete * from plant where plant_id = :plantId;")
//    @Transactional
//    void deleteByPlantId(Integer plantId);
}
