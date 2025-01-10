package com.gofit.userDAO;

import com.gofit.entity.User;

import java.util.List;

public interface UserDAO {

    User findUserByID(int id);

    List<User> findAllUsers();

    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(int id);
}
