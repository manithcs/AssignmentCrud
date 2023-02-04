package com.hello.configuration;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class WebConfiguration {
    public static Connection initDB() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/assignment?useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root","" );

        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  connection;




    }
}
