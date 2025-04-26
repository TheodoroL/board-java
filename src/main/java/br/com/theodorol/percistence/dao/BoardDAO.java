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
        final String sql = "INSERT INTO board (name) VALUES (?) RETURNING id_board;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    entity.setBoardId(rs.getLong("id_board"));
                }
            }
        }
        return entity;
    }

    public void delete(final Long id) throws SQLException {
        final String sql = "DELETE FROM board WHERE id_board = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        final String sql = "SELECT id_board, name FROM board WHERE id_board = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    BoardEntity entity = new BoardEntity();
                    entity.setBoardId(rs.getLong("id_board"));
                    entity.setName(rs.getString("name"));
                    return Optional.of(entity);
                }
            }
        }
        return Optional.empty();
    }

    public boolean exists(final Long id) throws SQLException {
        final String sql = "SELECT EXISTS (SELECT 1 FROM board WHERE id_board = ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        }
        return false;
    }
}
