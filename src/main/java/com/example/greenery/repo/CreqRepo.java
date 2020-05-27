package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.ClientRequest;
import com.example.greenery.model.Plant;
import com.example.greenery.model.PurchaseRequest;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreqRepo extends JpaRepository<ClientRequest, Long> {

    @Query(value = "select * from creq where creq_id = :creqId")
    Optional<ClientRequest> getClientRequestByCreqId(Integer creqId);

    @Query(value = "select * from creq where admin_id = :adminId")
    Optional<List<ClientRequest>> getClientRequestByAdminId(Integer adminId);

    @Query(value = "select * from creq where landscaper_id = :landscaperId")
    Optional<List<ClientRequest>> getClientRequestByLandscaperId(Integer landscaperId);

    @Query(value = "select * from creq where status = :status")
    Optional<List<ClientRequest>> getClientRequestByStatus(String status);

    @Query(value = "select * from creq where type = :type")
    Optional<List<ClientRequest>> getClientRequestByType(String type);

    @Query(value = "select * from creq where client_id = :clientId")
    Optional<List<ClientRequest>> getClientRequestByClientId(Integer clientId);
}
