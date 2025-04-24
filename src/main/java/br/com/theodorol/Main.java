package br.com.theodorol;

import br.com.theodorol.percistence.migration.MigrationStrategy;

import java.sql.SQLException;

import static br.com.theodorol.percistence.config.ConnectionConfig.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
    try (var connection = getConnection()){
        new MigrationStrategy(connection).executeMigration();

    }
    }
}