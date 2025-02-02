package com.gofit.dao;

import com.gofit.entity.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SessionDAOImpl implements SessionDAO {

    private final EntityManager em;

    @Override
    public Session get(int id) {
        return em.find(Session.class, id);
    }

    @Override
    public List<Session> getAll() {
        TypedQuery<Session> query = em.createQuery("select s from Session s", Session.class);
        return query.getResultList();
    }

    @Override
    public Session save(Session session) {
        em.persist(session);
        return session;
    }

    @Override
    public Session update(Session session) {
        return em.merge(session);
    }

    @Override
    public void delete(int id) {
        em.remove(get(id));
    }

    @Override
    public Session getSessionAndUser(int id) {
        TypedQuery<Session> query = em.createQuery("select s from Session s JOIN FETCH s.user WHERE " +
                "s.id = :id", Session.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Session> getSessionsByUser(int id) {
        TypedQuery<Session> query = em.createQuery("select s from Session s WHERE s.user.id = :id", Session.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
