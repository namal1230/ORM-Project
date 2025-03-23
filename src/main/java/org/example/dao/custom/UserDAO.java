package org.example.dao.custom;

import org.example.dao.SuperDAO;

import java.io.IOException;

public interface UserDAO extends SuperDAO {
    boolean checkUser(String name, String password,String jobRole) throws IOException;
}
