package com.example.greenery.repo;
import com.example.greenery.model.AuthData;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthDataRepo extends JpaRepository<AuthData, Long> {

    @Query(value = "select * from auth_data  where login = :login")
    public Optional<AuthData> getAuthDataByLogin(String login);
}