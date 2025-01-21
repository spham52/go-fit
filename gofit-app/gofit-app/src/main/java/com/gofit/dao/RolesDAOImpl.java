package com.gofit.dao;

import com.gofit.entity.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolesDAOImpl implements RolesDAO {
    private EntityManager em;

    @Autowired
    public RolesDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Roles> findAll() {
        TypedQuery<Roles> query = em.createQuery("select r from Roles r", Roles.class);
        return query.getResultList();
    }

    @Override
    public Roles findById(int id) {
        return em.find(Roles.class, id);
    }

    @Override
    @Transactional
    public Roles save(Roles roles) {
        em.persist(roles);
        return roles;
    }

    @Override
    @Transactional
    public Roles update(Roles roles) {
        Roles updatedRole = em.merge(roles);
        return updatedRole;
    }

    @Override
    @Transactional
    public void delete(Roles roles) {
        em.remove(roles);
    }

    @Override
    public Roles findByName(String name) {
        TypedQuery<Roles> query = em.createQuery("select r from Roles r where r.name = :name", Roles.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
