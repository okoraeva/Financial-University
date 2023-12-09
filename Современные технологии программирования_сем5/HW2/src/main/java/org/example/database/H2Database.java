package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database implements DatabaseInterface {
    private final String url;
    private final String username;
    private final String password;

    public H2Database() {
        this.url = "jdbc:h2:~/treeDB";
        this.username = "userTree";
        this.password = "pass";
    }

    public H2Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
