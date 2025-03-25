package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.entity.Payments;

import java.io.IOException;
import java.util.List;

public interface PaymentsDAO extends SuperDAO {
    boolean save(Payments payments) throws IOException;

    boolean update(Payments payments) throws IOException;

    int getLastId() throws IOException;

    boolean delete(String text) throws IOException;

    List<Payments> getAll() throws IOException;
}
