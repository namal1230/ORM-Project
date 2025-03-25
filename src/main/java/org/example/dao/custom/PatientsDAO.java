package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Patients;

import java.io.IOException;
import java.util.List;

public interface PatientsDAO extends SuperDAO {
    boolean save(Patients patients) throws IOException;

    boolean update(Patients patients) throws IOException;

    List<Patients> getAll() throws IOException;

    int getLastId() throws IOException;

    boolean delete(String id) throws IOException;

    List<Patients> search(String text) throws IOException;

    Patients getbyId(int patient) throws IOException;

    List getAllId() throws IOException;
}
