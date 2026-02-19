package com.weg.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "${postgres_url}";
    private static final String USER = "${postgres_user}";
    private static final String PASSWORD = "${postgres_password}";

    public static Connection toInstance() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
