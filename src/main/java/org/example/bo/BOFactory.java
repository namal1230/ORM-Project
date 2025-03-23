package org.example.bo;

import org.example.bo.custom.impl.LoginBOImpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes {
        USER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER -> {
                return new LoginBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
