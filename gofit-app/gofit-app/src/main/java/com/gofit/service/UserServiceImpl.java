package com.gofit.service;

import com.gofit.entity.CustomUserDetails;
import com.gofit.entity.Roles;
import com.gofit.entity.User;
import com.gofit.dao.UserDAO;
import com.gofit.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Roles role = rolesService.findByName("ROLE_USER");
        if (role != null) {
            user.getRoles().add(role);
        } else {
            role = new Roles("ROLE_USER");
            user.getRoles().add(role);
        }
        userDAO.save(user);
        return user;
    }

    @Override
    @Transactional
    public User update(@Valid User user) {
        Optional<User> optionalUser = Optional.ofNullable(userDAO.findByID(user.getId()));
        optionalUser.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        user.setPassword(passwordEncoder.encode(user.getPlainPassword()));
        return userDAO.update(user);
    }

    @Override
    @Transactional
    public User findByID(int id) {
        User user = userDAO.findByID(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
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
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User not authenticated");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            return findByUsername(userDetails.getUsername());
        } else {
            throw new IllegalStateException("Unexpected principal: " + principal);
        }
    }

    @Override
    @Transactional
    public void deleteByID(int id) {
        if (userDAO.findByID(id) == null) {
            throw new ResourceNotFoundException("User not found");
        }
        userDAO.delete(id);
    }

}
