package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDatabase implements DatabaseInterface {
    private final String url;
    private final String username;
    private final String password;

    public PostgresDatabase() {
        this.url = "jdbc:postgresql://localhost/treeDB";
        this.username = "userTree";
        this.password = "pass";
    }

    public PostgresDatabase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
