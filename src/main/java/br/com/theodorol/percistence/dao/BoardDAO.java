package br.com.theodorol.percistence.dao;

import br.com.theodorol.percistence.entity.BoardEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BoardDAO {
    private final Connection connection;

    public BoardDAO(Connection connection) {
        this.connection = connection;
    }

    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        String sql = "INSERT INTO BOARDS (name) VALUES (?) RETURNING id;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity.setBoardId(resultSet.getLong("id"));
            }
            return entity;
        }
    }

    // Deletar Board pelo ID
    public void delete(final Long id) throws SQLException {
        String sql = "DELETE FROM BOARDS WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Buscar Board por ID
    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        String sql = "SELECT id, name FROM BOARDS WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BoardEntity entity = new BoardEntity();
                entity.setBoardId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                return Optional.of(entity);
            }
            return Optional.empty();
        }
    }

    // Verificar se Board existe pelo ID
    public boolean exists(final Long id) throws SQLException {
        String sql = "SELECT 1 FROM BOARDS WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }
}
