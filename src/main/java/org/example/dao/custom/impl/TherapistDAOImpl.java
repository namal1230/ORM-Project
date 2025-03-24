package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.TherapistDAO;
import org.example.entity.Therapists;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    @Override
    public boolean saveTherapist(Therapists therapists) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(therapists);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateTherapist(Therapists therapists) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(therapists);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Therapists> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();

        return session.createQuery("FROM Therapists").list();


    }

    @Override
    public int getLastId() throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        return  (int) session.createQuery(" SELECT t.id from Therapists t ORDER BY t.id desc").setMaxResults(1).uniqueResult();
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Transaction transaction = session.beginTransaction();

        Therapists therapists = session.get(Therapists.class, id);
        session.remove(therapists);

        transaction.commit();

        session.close();
        return true;
    }
}
