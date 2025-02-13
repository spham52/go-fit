package com.gofit.dao;

import com.gofit.entity.GymLog;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GymLogDAOImpl implements GymLogDAO {

    private final EntityManager em;

    @Override
    public GymLog get(int id) {
        return em.find(GymLog.class, id);
    }

    @Override
    public GymLog save(GymLog gymLog) {
        em.persist(gymLog);
        return gymLog;
    }

    @Override
    public GymLog update(GymLog gymLog) {
        return em.merge(gymLog);
    }

    @Override
    public void delete(int id) {
        em.remove(em.find(GymLog.class, id));
    }
}
