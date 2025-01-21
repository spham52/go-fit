package com.gofit.service;

import com.gofit.entity.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {

    List<Roles> findAll();

    Optional<Roles> findById(int id);

    Roles save(Roles roles);

    Roles update(Roles roles);

    Roles findByName(String name);

    void delete(int id);
}
