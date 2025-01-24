package com.gofit.service;

import com.gofit.dao.RolesDAO;
import com.gofit.entity.Roles;
import com.gofit.entity.User;
import com.gofit.dao.UserDAO;
import com.gofit.exception.ResourceNotFound;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
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
    public User save(@Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPlainPassword()));
        userDAO.save(user);
        return user;
    }

    @Override
    @Transactional
    public User update(@Valid User user) {
        Optional<User> optionalUser = Optional.ofNullable(userDAO.findByID(user.getId()));
        optionalUser.orElseThrow(() -> new ResourceNotFound("User Not Found"));
        user.setPassword(passwordEncoder.encode(user.getPlainPassword()));
        return userDAO.update(user);
    }

    @Override
    public User findByID(int id) {
        User user = userDAO.findByID(id);
        if (user == null) {
            throw new ResourceNotFound("User Not Found");
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public User findByUsername(String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFound("User Not Found");
        }
        return user;
    }

    @Override
    @Transactional
    public void addRoleToUser(int userID, int roleID) {
        User user = findByID(userID);
        List<Roles> roles = user.getRoles();
        if (roles == null) {
            roles = new ArrayList<Roles>();
        }
        Roles role = rolesService.findById(roleID);
        roles.add(role);
        user.setRoles(roles);
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void deleteRoleFromUser(int userID, int roleID) {
        User user = findByID(userID);
        List<Roles> roles = user.getRoles();
        Roles role = rolesService.findById(roleID);
        if (roles == null || !roles.contains(role)) {
            return;
        }
        roles.remove(role);
        user.setRoles(roles);
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void deleteByID(int id) {
        if (userDAO.findByID(id) == null) {
            throw new ResourceNotFound("User not found");
        }
        userDAO.delete(id);
    }

}
