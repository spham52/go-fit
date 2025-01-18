package com.gofit.service;

import com.gofit.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User update(User user);

    void deleteByID(int id);

    User findByID(int id);

    List<User> findAllUsers();

    User findByUsername(String username);

    void addRoleToUser(int userID, int roleID);

    void deleteRoleFromUser(int userID, int roleID);
}
