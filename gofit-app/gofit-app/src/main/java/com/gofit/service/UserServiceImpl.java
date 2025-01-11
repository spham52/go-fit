package com.gofit.service;

import com.gofit.entity.User;
import com.gofit.dao.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDAO userDAO) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        User newUser = userDAO.update(user);
        return newUser;
    }

    @Override
    public User findByID(int id) {
        return userDAO.findByID(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public void deleteByID(int id) {
        userDAO.delete(id);
    }
}
