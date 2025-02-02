package com.gofit.dao;

import com.gofit.entity.Session;

import java.util.List;

public interface SessionDAO {

    Session get(int id);

    List<Session> getAll();

    Session save(Session session);

    Session update(Session session);

    void delete(int id);

    Session getSessionAndUser(int id);

    List<Session> getSessionsByUser(int id);

}
