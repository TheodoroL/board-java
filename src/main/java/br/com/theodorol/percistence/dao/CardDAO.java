package br.com.theodorol.percistence.dao;

import br.com.theodorol.dto.CardDetailsDTO;
import br.com.theodorol.percistence.entity.CardEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static br.com.theodorol.percistence.converter.OffsetDateTimeConverter.toOffsetDateTime;
import static java.util.Objects.nonNull;

public class CardDAO {
    private final Connection connection;

    public CardDAO(Connection connection) {
        this.connection = connection;
    }

    public CardEntity insert(final CardEntity entity) throws SQLException {
        var sql = "INSERT INTO cards (title, description, board__column_id) VALUES (?, ?, ?) RETURNING id_card;";
        try (var statement = connection.prepareStatement(sql)) {
            var i = 1;
            statement.setString(i++, entity.getTitle());
            statement.setString(i++, entity.getDescription());
            statement.setLong(i, entity.getBoardColumn().getIdBoardColum());

            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    entity.setIdCard(resultSet.getLong("id_card"));
                }
            }
        }
        return entity;
    }

    public void moveToColumn(final Long columnId, final Long cardId) throws SQLException{
        var sql = "UPDATE cards SET board__column_id = ? WHERE id_card = ?;";
        try(var statement = connection.prepareStatement(sql)){
            var i = 1;
            statement.setLong(i ++, columnId);
            statement.setLong(i, cardId);
            statement.executeUpdate();
        }
    }

    public Optional<CardDetailsDTO> findById(final Long id) throws SQLException {
        var sql = """
        SELECT c.id_card,
               c.title,
               c.description,
               b.blocked_at,
               b.block_reason,
               c.board__column_id,
               bc.name,
               (
                 SELECT COUNT(sub_b.id_blocks)
                   FROM BLOCKS sub_b
                  WHERE sub_b.card_id = c.id_card
               ) AS blocks_amount
          FROM CARDS c
          LEFT JOIN BLOCKS b
            ON c.id_card = b.card_id
           AND b.unblocked_at IS NULL
          INNER JOIN BOARD_COLUMNS bc
            ON bc.id_board_column = c.board__column_id
         WHERE c.id_card = ?;
    """;

        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var dto = new CardDetailsDTO(
                        resultSet.getLong("id_card"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        nonNull(resultSet.getString("block_reason")),
                        toOffsetDateTime(resultSet.getTimestamp("blocked_at")),
                        resultSet.getString("block_reason"),
                        resultSet.getInt("blocks_amount"),
                        resultSet.getLong("board__column_id"),
                        resultSet.getString("name")
                );
                return Optional.of(dto);
            }
        }
        return Optional.empty();
    }
}
