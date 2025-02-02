package com.gofit.service;

import com.gofit.entity.User;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(@Valid User user);

    User update(@Valid User user);

    void deleteByID(int id);

    User findByID(int id);

    List<User> findAllUsers();

    User findByUsername(String username);

    void addRoleToUser(int userID, int roleID);

    void deleteRoleFromUser(int userID, int roleID);

    public User getCurrentUser();
}
