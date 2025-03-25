package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.TherapyPrograms;

import java.io.IOException;
import java.util.List;

public interface TherapyProgramsDAO extends SuperDAO {
    boolean save(TherapyPrograms therapyPrograms) throws IOException;

    boolean delete(String id) throws IOException;

    boolean update(TherapyPrograms therapyPrograms) throws IOException;

    List<TherapyPrograms> getAll() throws IOException;

    int getLastId() throws IOException;

    TherapyPrograms getbyId(int program) throws IOException;

    List getAllId() throws IOException;
}
