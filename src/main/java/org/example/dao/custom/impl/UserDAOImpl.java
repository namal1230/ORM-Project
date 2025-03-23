package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    public boolean checkUser(String name, String password,String jobRole) throws IOException {
        Session session = FactoryConfiguration.getInstance().openSession();
        Query query = session.
                createQuery("from Users u where u.name = :name AND u.password = :password AND u.jobRole = :role").
                setParameter("name", name).
                setParameter("password",password).
                setParameter("role",jobRole);
        List list = query.list();
        if (list.isEmpty()){
            return false;
        }
        return true;
    }
}
