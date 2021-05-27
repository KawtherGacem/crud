package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    public static Connection connect() {
        Connection con = null;
        try {
            String jdbcurl= "jdbc:sqlite:C:\\Users\\Dell\\IdeaProjects\\test\\clients.db";
            con = DriverManager.getConnection(jdbcurl); // connecting to our database
            System.out.println("Connected!");
        } catch (SQLException e ) {
            // TODO Auto-generated catch block
            System.out.println(e+"");
        }

        return con;
    }
}
