package com.example.mywork.DAO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> {

    boolean save(T DTO) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    ArrayList<String> getAllIds() throws SQLException;

    boolean update(T DTO) throws SQLException;

    boolean delete(String Id) throws SQLException;


}
