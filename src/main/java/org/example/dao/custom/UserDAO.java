package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Users;

import java.io.IOException;

public interface UserDAO extends SuperDAO {
    boolean checkUser(Users users) throws IOException;
}
