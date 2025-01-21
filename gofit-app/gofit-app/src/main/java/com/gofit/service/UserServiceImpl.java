package com.gofit.service;

import com.gofit.dao.RolesDAO;
import com.gofit.entity.Roles;
import com.gofit.entity.User;
import com.gofit.dao.UserDAO;
import com.gofit.exception.ResourceNotFound;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<User> findByID(int id) {
        User user = userDAO.findByID(id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = userDAO.findByUsername(username);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public void addRoleToUser(int userID, int roleID) {
        User user = findByID(userID).orElseThrow(() -> new ResourceNotFound("User not found with id " + userID));
        List<Roles> roles = user.getRoles();
        if (roles == null) {
            roles = new ArrayList<Roles>();
        }
        Roles role = rolesService.findById(roleID).orElseThrow(()
                -> new ResourceNotFound("Role not found with id " + roleID));
        roles.add(role);
        user.setRoles(roles);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void deleteRoleFromUser(int userID, int roleID) {
        User user = findByID(userID).orElseThrow(() -> new ResourceNotFound("User not found with id " + userID));
        List<Roles> roles = user.getRoles();
        if (roles == null) {
            return;
        }
        Roles role = rolesService.findById(roleID).orElseThrow(()
                -> new ResourceNotFound("Role not found with id " + roleID));
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
