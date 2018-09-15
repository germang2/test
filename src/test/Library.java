/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author german
 */
public class Library {
    public void createTable(Connection conn) throws SQLException {
        try {
            // create query
            String query = "CREATE TABLE IF NOT EXISTS library ("
                    + "id serial primary key,"
                    + "book_id integer REFERENCES books(id) ON DELETE RESTRICT NOT NULL,"
                    + "amount numeric NOT NULL check (amount > 0)"
                    + ")";
            // create object for execute query
            PreparedStatement pst = conn.prepareStatement(query);
            // execute query
            pst.executeUpdate();
            System.out.println("Create table library successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
