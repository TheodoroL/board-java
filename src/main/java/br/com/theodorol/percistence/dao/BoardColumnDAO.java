package br.com.theodorol.percistence.dao;

import br.com.theodorol.dto.BoardColumDTO;
import br.com.theodorol.percistence.entity.BoardColumnKindEnum;
import br.com.theodorol.percistence.entity.BoardColumnsEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardColumnDAO {
    private final Connection connection;

    public BoardColumnDAO(Connection connection) {
        this.connection = connection;
    }

    public BoardColumnsEntity insert(BoardColumnsEntity entity) throws SQLException {
        String sql = "INSERT INTO BOARD_COLUMNS (name, \"order\", kind, board_id) VALUES (?, ?, ?, ?)";
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
        List<BoardColumnsEntity> entities = new ArrayList<>();
        String sql = "ELECT id, name, \"order\", kind FROM BOARDS_COLUMNS WHERE board_id = ? ORDER BY \"order\";";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            var result =statement.getResultSet();
            while (result.next()){
                BoardColumnsEntity boardColumns = new BoardColumnsEntity();
                boardColumns.setIdBoardColum(result.getLong("id_board_column"));
                boardColumns.setName(result.getString("name"));
                boardColumns.setOrder(result.getInt("order"));
                boardColumns.setKind(BoardColumnKindEnum.findByName(result.getString("kind")));
                entities.add(boardColumns);
            }
        }
        return entities;
    }
    public List<BoardColumDTO> findByBoardIdWithDetails( Long id) throws SQLException{
        List<BoardColumDTO> dtos = new ArrayList<>();
        var sql =
                """
                SELECT 
                    bc.id,
                    bc.name,
                    bc.kind,
                    (
                        SELECT COUNT(c.id)
                        FROM CARDS c
                        WHERE c.board_column_id = bc.id
                    ) AS cards_amount
                FROM 
                    BOARDS_COLUMNS bc
                WHERE 
                    board_id = ?
                ORDER BY 
                    "order";
                """;


        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            var result =statement.getResultSet();
            while (result.next()){
                var boardColumns = new BoardColumDTO(
                        result.getLong("id_board_column"),
                        result.getString("name"),
                        BoardColumnKindEnum.findByName(result.getString("kind")
                        ));
                dtos.add(boardColumns);
            }
        }
        return dtos;
    }
}
