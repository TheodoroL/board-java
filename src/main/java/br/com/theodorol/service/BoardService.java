package br.com.theodorol.service;

import br.com.theodorol.percistence.dao.BoardColumnDAO;
import br.com.theodorol.percistence.dao.BoardDAO;
import br.com.theodorol.percistence.entity.BoardEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class BoardService {
    private final Connection connection;

    public BoardService(Connection connection) {
        this.connection = connection;
    }
    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        try{
            dao.insert(entity);
            var columns = entity.getBoardColumns().stream().map(c -> {
                c.setBoard(entity);
                return c;
            }).toList();
            for (var column :  columns){
                boardColumnDAO.insert(column);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return entity;
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
