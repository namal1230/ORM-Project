package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Users;

import java.io.IOException;
import java.util.List;

public interface UserDAO extends SuperDAO {
    List<Users> checkUser(Users users) throws IOException;
}
