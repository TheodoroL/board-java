package br.com.theodorol;

import br.com.theodorol.percistence.migration.MigrationStrategy;
import br.com.theodorol.ui.MainMenu;

import java.sql.SQLException;

import static br.com.theodorol.percistence.config.ConnectionConfig.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
    try (var connection = getConnection()){
        new MigrationStrategy(connection).executeMigration();

    }
    new MainMenu().execute();
    }
}