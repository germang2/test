/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author german
 */
public class Test {

    private final String url = "jdbc:postgresql://localhost/test";
    private final String user = "german";
    private final String password = "german";
    
    /**
     * Connect to the PostgreSQL database
     * 
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successfull connection");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException{
        Test test = new Test();
        //create connection to db
        Connection conn = test.connect();
        
        Author author = new Author();
        //author.store(7, "Juan Jose", conn);
        author.get(conn);
    }
    
}
