package br.com.theodorol.percistence.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionConfig {
    public static Connection getConnection() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:postgresql://localhost/board", "postgres", "12345678");
        connection.setAutoCommit(false);
        return connection;
    }
}
