package com.gofit.userDAO;

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
    public User findUserByID(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        TypedQuery<User> list = em.createQuery("select u FROM User u", User.class);
        return list.getResultList();
    }

    @Override
    public User saveUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        User newUser = em.merge(user);
        return newUser;
    }

    @Override
    public void deleteUser(int id) {
        em.remove(findUserByID(id));
    }
}
