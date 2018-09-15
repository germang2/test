/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author german
 */
public class Book {
    private int id;
    private String name;
    private Date release;
    private int user_id;
    
    public void createTable(Connection conn) throws SQLException {
        try {
            // create query
            String query = "CREATE TABLE IF NOT EXISTS books ("
                   + "id serial primary key,"
                   + "name VARCHAR(50) NOT NULL,"
                   + "release DATE NOT NULL,"
                   + "user_id integer REFERENCES authors(id) ON DELETE RESTRICT"
                   + ")";   
            // object for execute query
            PreparedStatement pst = conn.prepareStatement(query);
            // execute query
            pst.executeUpdate();
            System.out.println("Create table books successfully");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
