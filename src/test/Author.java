/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author german
 */
public class Author {
    public void store(int id, String name, Connection conn) throws SQLException {
        try {
            // create query for method prepareStatement
            String query = "INSERT INTO authors (id, name) VALUES (?, ?)";
            // Object for execute query
            PreparedStatement pst = conn.prepareStatement(query);
            // Set values to preparedStatement, first parameter is 1, second parameter is 2 ...
            pst.setInt(1, id);
            pst.setString(2, name);
            // execute the sql, NOTE: executeUpdate() method is only for INSERT, UPDATE and DELETE
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void get(Connection conn) throws SQLException {
        try {
            // Create query
            String query = "SELECT * FROM authors";
            // Object for execute query
            PreparedStatement pst = conn.prepareStatement(query);
            // ResultSet object allows alocate the result of SELECT query 
            ResultSet rs = pst.executeQuery();
            // Iterate each data of query result
            while (rs.next()) {

                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
}
