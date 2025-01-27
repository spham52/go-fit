package com.gofit.service;

import com.gofit.dao.RolesDAO;
import com.gofit.entity.Roles;
import com.gofit.exception.ResourceNotFound;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    private RolesDAO rolesDAO;

    @Autowired
    public RolesServiceImpl(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    @Override
    public List<Roles> findAll() {
        return rolesDAO.findAll();
    }

    @Override
    public Roles findById(int id) {
        Roles role = rolesDAO.findById(id);
        if (role == null) {
            throw new ResourceNotFound("Role not found");
        }
        return role;
    }

    @Override
    @Transactional
    public Roles save(@Valid Roles roles) {
        return rolesDAO.save(roles);
    }

    @Override
    @Transactional
    public Roles update(@Valid Roles roles) {
        Roles findRole = rolesDAO.findById(roles.getId());
        if (findRole == null) {
            throw new ResourceNotFound("Role not found");
        }
        return rolesDAO.update(roles);
    }

    @Override
    public Roles findByName(String name) {
        return rolesDAO.findByName(name);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Roles role = findById(id);
        rolesDAO.delete(role);
    }
}
