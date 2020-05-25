package com.example.greenery.service;

import com.example.greenery.exceptions.ItemNotFoundException;
import com.example.greenery.exceptions.LoginNotFoundException;
import com.example.greenery.model.User;
import com.example.greenery.repo.AuthDataRepo;
import com.example.greenery.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }
    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findUserByFirstName(String firstName) throws NameNotFoundException {
        return userRepo.getUserByFirstName(firstName).orElseThrow(() -> new NameNotFoundException(firstName));
    }

    public List<User> findUserBySecondName(String secondName) throws NameNotFoundException {
        return userRepo.getUserBySecondName(secondName).orElseThrow(() -> new NameNotFoundException(secondName));
    }

    public List<User> filterUsersByRole(String role) {
        return userRepo.getUserByRole(role);
    }

    public User findUserByUID(Integer uid) {
        return userRepo.getUserByUserId(uid).orElseThrow(() -> new ItemNotFoundException(uid));
    }

    public User findUserByAuthDataId(Integer authDataId) {
        return userRepo.getUserByAuthDataId(authDataId).orElseThrow(() -> new ItemNotFoundException(authDataId));
    }

    public User addUser(User newUser) {
        return userRepo.save(newUser);
    }

}
