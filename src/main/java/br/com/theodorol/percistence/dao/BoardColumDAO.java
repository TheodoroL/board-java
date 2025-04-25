package br.com.theodorol.percistence.dao;

import br.com.theodorol.percistence.entity.BoardColumnsEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BoardColumDAO {
    private final Connection connection;

    public BoardColumDAO(Connection connection) {
        this.connection = connection;
    }

    public BoardColumnsEntity insert(BoardColumnsEntity entity) throws SQLException {
        String sql = "INSERT INTO BOARD_COLUMNS (name, `order`, kind, board_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setString(i++, entity.getName());
            statement.setInt(i++, entity.getOrder());
            statement.setString(i++, entity.getKind().name());
            statement.setLong(i, entity.getBoard().getBoardId());

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    entity.setIdBoardColum(resultSet.getLong(1));
                }
            }
        }

        return entity;
    }
    public List<BoardColumnsEntity> findByBoardId(final Long id) throws SQLException{
        return null;
    }
}
