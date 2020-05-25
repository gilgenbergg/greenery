package com.example.greenery.repo;

import com.example.greenery.model.AuthData;
import com.example.greenery.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "select * from user_info  where first_name = :firstName")
    Optional<List<User>> getUserByFirstName(String firstName);

    @Query(value = "select * from user_info  where second_name = :secondName")
    Optional<List<User>> getUserBySecondName(String secondName);

    @Query(value = "select * from user_info  where role = :role")
    List<User> getUserByRole(String role);

    @Query(value = "select * from user_info  where user_id = :uid")
    Optional<User> getUserByUserId(Integer uid);

    @Query(value = "select * from user_info  where auth_data_id = :authDataId")
    Optional<User> getUserByAuthDataId(Integer authDataId);
}
