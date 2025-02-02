package com.gofit.dao;

import com.gofit.entity.ActivityLog;
import com.gofit.entity.Roles;
import com.gofit.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ActivityLogDAOImpl implements ActivityLogDAO {

    private final EntityManager em;

    @Override
    public List<ActivityLog> findAll() {
    TypedQuery<ActivityLog> query = em.createQuery("select a from ActivityLog a", ActivityLog.class);
    return query.getResultList();
    }

    @Override
    public ActivityLog findById(int id) {
        return em.find(ActivityLog.class, id);
    }

    @Override
    public ActivityLog save(ActivityLog log) {
        em.persist(log);
        return log;
    }

    @Override
    public ActivityLog update(ActivityLog log) {
        return em.merge(log);
    }

    @Override
    public void delete(int id) {
        ActivityLog log = findById(id);
        em.remove(log);
    }

    public ActivityLog getActivityLogAndUser(int activityLogID) {
        TypedQuery<ActivityLog> query = em.createQuery("select a from ActivityLog a join fetch " +
                "a.session.user where a.id = :id", ActivityLog.class);
        query.setParameter("id", activityLogID);
        return query.getSingleResult();
    }
}
