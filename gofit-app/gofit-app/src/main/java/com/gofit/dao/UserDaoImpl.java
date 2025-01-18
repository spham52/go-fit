package com.gofit.dao;

import com.gofit.entity.Roles;
import com.gofit.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    private EntityManager em;

    @Autowired
    public void userDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public User findByID(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> list = em.createQuery("select u FROM User u", User.class);
        return list.getResultList();
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        User newUser = em.merge(user);
        return newUser;
    }

    @Override
    public void delete(int id) {
        em.remove(findByID(id));
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
