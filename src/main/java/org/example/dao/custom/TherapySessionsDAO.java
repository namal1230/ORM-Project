package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.TherapySessions;

import java.io.IOException;
import java.util.List;

public interface TherapySessionsDAO extends SuperDAO {
    boolean save(TherapySessions therapySessions) throws IOException;

    boolean update(TherapySessions therapySessions) throws IOException;

    int getLastId() throws IOException;

    boolean delete(String lid) throws IOException;

    List<TherapySessions> getAll() throws IOException;
}
