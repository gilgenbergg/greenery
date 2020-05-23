package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.ClientRequest;
import com.example.greenery.model.PurchaseRequest;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreqRepo extends JpaRepository<PurchaseRequest, Long> {

    @Query(value = "select * from preq where preq_id = :preqId")
    Optional<PurchaseRequest> getPurchaseRequestByPreqId(Integer preqId);

    @Query(value = "select * from preq where status = :status")
    Optional<List<PurchaseRequest>> getPurchaseRequestByStatus(String status);

    @Query(value = "select * from preq where client_id = :clientId")
    Optional<List<PurchaseRequest>> getPurchaseRequestByClientId(Integer clientId);

    @Query(value = "select * from preq where admin_id = :adminId")
    Optional<List<PurchaseRequest>> getPurchasetRequestByAdminId(Integer adminId);

    @Query(value = "select * from preq where landscaper_id = :landscaperId")
    Optional<List<PurchaseRequest>> getPurchaseRequestByLandscaperId(Integer landscaperId);
}
