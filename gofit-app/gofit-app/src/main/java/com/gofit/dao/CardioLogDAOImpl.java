package com.gofit.dao;

import com.gofit.entity.CardioLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.smartcardio.Card;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CardioLogDAOImpl implements CardioLogDAO {

    private final EntityManager em;

    @Override
    public CardioLog get(int id) {
        return em.find(CardioLog.class, id);
    }

    @Override
    public CardioLog save(CardioLog cardioLog) {
        em.persist(cardioLog);
        return cardioLog;
    }

    @Override
    public void delete(int id) {
        CardioLog cardioLog = em.find(CardioLog.class, id);
        em.remove(cardioLog);
    }

    @Override
    public CardioLog update(CardioLog cardioLog) {
       return em.merge(cardioLog);
    }

    @Override
    public Optional<CardioLog> getCardioLogByActivityID(int activityID) {
        TypedQuery<CardioLog> query = em.createQuery("select c from CardioLog c where " +
                "c.activityLog.activityLogID = :activityID", CardioLog.class);
        query.setParameter("activityID", activityID);
        return query.getResultStream().findFirst();
    }
}
