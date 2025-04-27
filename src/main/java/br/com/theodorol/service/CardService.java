package br.com.theodorol.service;

import br.com.theodorol.percistence.dao.CardDAO;
import br.com.theodorol.percistence.entity.CardEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class CardService {
    private  final Connection connection;

    public CardService(Connection connection) {
        this.connection = connection;
    }
    public CardEntity create(CardEntity entity) throws SQLException {
        try {
            var dao = new CardDAO(connection);
            dao.insert(entity);
            connection.commit();
            return entity;
        } catch (SQLException ex){
            connection.rollback();
            throw ex;
        }
    }
}
