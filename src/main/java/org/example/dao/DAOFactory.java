package org.example.dao;

import org.example.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        USER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case USER -> {
                return new UserDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
