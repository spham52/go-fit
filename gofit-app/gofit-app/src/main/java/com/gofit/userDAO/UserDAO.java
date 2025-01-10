package com.gofit.userDAO;

import com.gofit.entity.User;

import java.util.List;

public interface UserDAO {

    User findByID(int id);

    List<User> findAll();

    User save(User user);

    User update(User user);

    void delete(int id);
}
