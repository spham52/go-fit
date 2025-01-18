package com.gofit.service;

import com.gofit.dao.RolesDAO;
import com.gofit.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return rolesDAO.findById(id);
    }

    @Override
    public Roles save(Roles roles) {
        return rolesDAO.save(roles);
    }

    @Override
    public Roles update(Roles roles) {
        return rolesDAO.update(roles);
    }

    @Override
    public void delete(Roles roles) {
        rolesDAO.delete(roles);
    }
}
