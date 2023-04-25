package com.example.restaurante.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost/restaurante";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}


