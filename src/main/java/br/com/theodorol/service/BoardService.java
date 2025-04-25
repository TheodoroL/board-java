package br.com.theodorol.service;

import br.com.theodorol.percistence.dao.BoardDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class BoardService {
    private final Connection connection;

    public BoardService(Connection connection) {
        this.connection = connection;
    }
    public boolean delete(Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        try {
            if(!dao.exists(id)) return false;
            dao.delete(id);
            connection.commit();
            return true;
        }catch (SQLException ex){
            connection.rollback();
            throw ex;
        }
    }
}
