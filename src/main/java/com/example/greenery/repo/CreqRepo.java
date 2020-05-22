package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.ClientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreqRepo extends JpaRepository<ClientRequest, Long> {
}
