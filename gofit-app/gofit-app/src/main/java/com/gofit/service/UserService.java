package com.gofit.service;

import com.gofit.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    User update(User user);

    void deleteByID(int id);

    Optional<User> findByID(int id);

    List<User> findAllUsers();

    Optional<User> findByUsername(String username);

    void addRoleToUser(int userID, int roleID);

    void deleteRoleFromUser(int userID, int roleID);
}
