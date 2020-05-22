package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.PurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreqRepo extends JpaRepository<PurchaseRequest, Long> {
}
