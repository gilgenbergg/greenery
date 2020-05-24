package com.example.greenery.service;

import com.example.greenery.exceptions.ItemNotFoundException;
import com.example.greenery.exceptions.LoginNotFoundException;
import com.example.greenery.model.AuthData;
import com.example.greenery.repo.AuthDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthDataService {

    private AuthDataRepo authDataRepo;

    public AuthDataService(AuthDataRepo authDataRepo) {
        this.authDataRepo = authDataRepo;
    }

    public List<AuthData> findAll() {
        return authDataRepo.findAll();
    }

    public AuthData findAuthDataByLogin(String login) {
        return authDataRepo.getAuthDataByLogin(login)
                .orElseThrow(() -> new LoginNotFoundException(login));
    }

    public AuthData addItem(AuthData newAuthData) {
        return authDataRepo.save(newAuthData);
    }

    public AuthDataRepo getAuthDataRepo() {
        return authDataRepo;
    }
    @Autowired
    public void setAuthDataRepo(AuthDataRepo authDataRepo) {
        this.authDataRepo = authDataRepo;
    }

}
