package com.gofit.service;

import com.gofit.entity.Roles;

import java.util.List;

public interface RolesService {

    List<Roles> findAll();

    Roles findById(int id);

    Roles save(Roles roles);

    Roles update(Roles roles);

    void delete(Roles roles);
}
