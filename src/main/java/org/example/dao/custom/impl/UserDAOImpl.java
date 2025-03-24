package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.UserDAO;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    public boolean checkUser(Users users) throws IOException {
        System.out.println(users.getName());
        System.out.println(users.getPassword());
        System.out.println(users.getJobRole());
        Session session = FactoryConfiguration.getInstance().openSession();
        Query query = session.
                createQuery("from Users u where u.name = :name AND u.password = :password AND u.jobRole = :role").
                setParameter("name", users.getName()).
                setParameter("password",users.getPassword()).
                setParameter("role",users.getJobRole());
        List list = query.list();
        if (list.isEmpty()){
            return false;
        }
        return true;
    }
}
