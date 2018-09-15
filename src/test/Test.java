/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author german
 */
public class Test {

    private final String url = "jdbc:postgresql://localhost/test";
    private final String user = "postgres";
    private final String password = "postgres";
    
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
        
        // Create tables if not exists
        Author author = new Author();
        author.createTable(conn);
        
        Book book = new Book();
        book.createTable(conn);
        
        Library library = new Library();
        library.createTable(conn);
        
        //------------------------------
        
        // Create author
        HashMap<String, String> author_fields = new HashMap<String, String>();
        author_fields.put("name", "German");
        author_fields.put("lastname", "Gomez");
        author_fields.put("identification", "123456789");
        author_fields.put("bithdate", "1993-03-08");
        
        boolean flag = author.store(conn, author_fields);
        if (flag) System.out.println("Successfull");
        else System.out.println("Failed");
        
        author.get(conn);
        author.find(conn, 2);
        System.out.println("Id: " + author.getId());
        System.out.println("Name: " + author.getName());
        System.out.println("Last name: " + author.getLastname());
        System.out.println("Identification: " + author.getIdentification());
        System.out.println("Birthdate: " + author.getBithdate());
        
    }
    
}
