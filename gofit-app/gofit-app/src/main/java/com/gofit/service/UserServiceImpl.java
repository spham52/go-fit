package com.gofit.service;

import com.gofit.dao.RolesDAO;
import com.gofit.entity.Roles;
import com.gofit.entity.User;
import com.gofit.dao.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;
    private final RolesService rolesService;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDAO userDAO, RolesService rolesService) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
        this.rolesService = rolesService;
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
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void addRoleToUser(int userID, int roleID) {
        User user = userDAO.findByID(userID);
        List<Roles> roles = user.getRoles();
        if (roles == null) {
            roles = new ArrayList<Roles>();
        }
        Roles role = rolesService.findById(roleID);
        roles.add(role);
        user.setRoles(roles);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void deleteRoleFromUser(int userID, int roleID) {
        User user = userDAO.findByID(userID);
        List<Roles> roles = user.getRoles();
        if (roles == null) {
            return;
        }
        Roles role = rolesService.findById(roleID);
        roles.remove(role);
        user.setRoles(roles);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void deleteByID(int id) {
        userDAO.delete(id);
    }
}
