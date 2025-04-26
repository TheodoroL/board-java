package br.com.theodorol.service;

import br.com.theodorol.dto.CardDetailsDTO;
import br.com.theodorol.percistence.dao.CardDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class CardQueryService {
    private final Connection connection;

    public CardQueryService(Connection connection) {
        this.connection = connection;
    }

    public Optional<CardDetailsDTO> findById(final Long id) throws SQLException, SQLException {
        var dao = new CardDAO(connection);
        return dao.findById(id);
    }

}
