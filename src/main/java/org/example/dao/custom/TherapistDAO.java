package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Therapists;

import java.io.IOException;
import java.util.List;

public interface TherapistDAO extends SuperDAO {
    boolean saveTherapist(Therapists therapists) throws IOException;

    boolean updateTherapist(Therapists therapists) throws IOException;

    List<Therapists> getAll() throws IOException;

    int getLastId() throws IOException;

    boolean delete(String id) throws IOException;

    Therapists getbyId(int therapy) throws IOException;

    List getAllId() throws IOException;

}
