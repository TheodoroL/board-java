package br.com.theodorol.service;

import br.com.theodorol.percistence.dao.BoardColumnDAO;
import br.com.theodorol.percistence.entity.BoardColumnsEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class BoardColumnQueryService {
    private final Connection connection;

    public BoardColumnQueryService(Connection connection) {
        this.connection = connection;
    }
    public Optional<BoardColumnsEntity> findById(final Long id) throws SQLException {
        var dao = new BoardColumnDAO(connection);
        return dao.findById(id);
    }

}
