package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {

    private static final String URLCON = "jdbc:mariadb://localhost:3306/lojadb?allowMultiQueries=true";
    private static final String USER = "root";
    private static final String PASS = "";

    public DatabaseController() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(URLCON, USER, PASS);
            System.out.println("Acesso ao banco de dados realizado com SUCESSO.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
