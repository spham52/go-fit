package com.gofit.service;

import com.gofit.dao.RolesDAO;
import com.gofit.entity.Roles;
import com.gofit.exception.ResourceNotFound;
import jakarta.transaction.Transactional;
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
    public Optional<Roles> findById(int id) {
        Roles role = rolesDAO.findById(id);
        return Optional.ofNullable(role);
    }

    @Override
    @Transactional
    public Roles save(Roles roles) {
        return rolesDAO.save(roles);
    }

    @Override
    @Transactional
    public Roles update(Roles roles) {
        return rolesDAO.update(roles);
    }

    @Override
    public Roles findByName(String name) {
        return rolesDAO.findByName(name);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Roles role = findById(id).orElseThrow(() -> new ResourceNotFound("Role not found: " + id ));
        rolesDAO.delete(role);
    }
}
