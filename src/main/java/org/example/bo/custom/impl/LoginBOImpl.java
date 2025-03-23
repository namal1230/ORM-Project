package org.example.bo.custom.impl;

import org.example.bo.custom.LoginBO;
import org.example.dao.DAOFactory;
import org.example.dao.SuperDAO;
import org.example.dao.custom.UserDAO;

import java.io.IOException;

public class LoginBOImpl implements LoginBO {

    UserDAO Userdao = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean checkUser(String name, String password,String jobRole) throws IOException {
        return Userdao.checkUser(name,password,jobRole);
    }
}
