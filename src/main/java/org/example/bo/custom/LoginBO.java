package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.UsersDTO;

import java.io.IOException;

public interface LoginBO extends SuperBO {
    boolean checkUser(UsersDTO usersDTO) throws IOException;
}
