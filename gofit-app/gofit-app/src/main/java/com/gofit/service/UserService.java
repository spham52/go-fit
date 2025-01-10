package com.gofit.service;

import com.gofit.entity.User;

import java.util.List;

public interface UserService {

    public User save(User user);

    public User update(User user);

    public void deleteByID(int id);

    public User findByID(int id);

    public List<User> findAllUsers();


}
