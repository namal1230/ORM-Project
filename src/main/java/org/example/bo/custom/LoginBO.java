package org.example.bo.custom;

import org.example.bo.SuperBO;

import java.io.IOException;

public interface LoginBO extends SuperBO {
    boolean checkUser(String name, String password,String jobRole) throws IOException;
}
