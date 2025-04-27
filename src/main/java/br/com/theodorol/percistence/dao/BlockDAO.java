package br.com.theodorol.percistence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import static br.com.theodorol.percistence.converter.OffsetDateTimeConverter.toTimestamp;

public class BlockDAO {
    private final Connection connection;

    public BlockDAO(Connection connection) {
        this.connection = connection;
    }
    public void block(final String reason, final Long cardId) throws SQLException, SQLException {
        var sql = "INSERT INTO BLOCKS (blocked_at, block_reason, card_id) VALUES (?, ?, ?);";
        try(var statement = connection.prepareStatement(sql)){
            var i = 1;
            statement.setTimestamp(i ++, toTimestamp(OffsetDateTime.now()));
            statement.setString(i ++, reason);
            statement.setLong(i, cardId);
            statement.executeUpdate();
        }
    }
}
