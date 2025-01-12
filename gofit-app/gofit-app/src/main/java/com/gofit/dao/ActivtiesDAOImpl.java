package com.gofit.dao;

import com.gofit.entity.Activities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivtiesDAOImpl implements ActivitiesDAO {

    private EntityManager em;

    @Autowired
    public ActivtiesDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Activities> getAll() {
        TypedQuery<Activities> query = em.createQuery("select a from Activities a", Activities.class);
        return query.getResultList();
    }

    @Override
    public Activities get(int id) {
        Activities activities = em.find(Activities.class, id);
        return activities;
    }

    @Override
    public Activities save(Activities activities) {
        em.persist(activities);
        return activities;
    }

    @Override
    public Activities update(Activities activities) {
        Activities newActivities = em.merge(activities);
        return newActivities;
    }

    @Override
    public void delete(int id) {
        em.remove(get(id));
    }

    @Override
    public List<Activities> getAllFromUser(int userID) {
        TypedQuery<Activities> query = em.createQuery("select a from Activities a where a.user.id = :userID",
                                                          Activities.class);
        query.setParameter("userID", userID);
        return query.getResultList();
    }

    @Override
    public void deleteAllActivitiesFromUser(int userID) {
        TypedQuery<Activities> query =  em.createQuery("delete from Activities a where a.user.id = :userID",
                                                           Activities.class);
        query.setParameter("userID", userID);
        query.executeUpdate();
    }
}
