package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.Resource;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Long> {

    @Query(value = "select * from resource where plant_id = :plantId")
    Optional<List<Resource>> getResourcesByPlantId(Integer plantId);

    @Query(value = "select * from resource where resource_id = :resourceId")
    Optional<Resource> getResourceByResourceId(Integer resourceId);
}
