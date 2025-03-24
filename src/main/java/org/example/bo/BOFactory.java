package org.example.bo;

import org.example.bo.custom.impl.LoginBOImpl;
import org.example.bo.custom.impl.TherapistManagementBOImpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public enum BOTypes {
        USER,THERAPIST
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER -> {
                return new LoginBOImpl();
            }
            case THERAPIST -> {
                return new TherapistManagementBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
