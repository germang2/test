/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author german
 */
public class Author{
    
    private int id;
    private String name;
    private String lastname;
    private String identification;
    private Date bithdate;
    PreparedStatement pst;
    
    public void createTable(Connection conn) throws SQLException {
        try {
            String query = "CREATE TABLE IF NOT EXISTS authors ("
                    + "id serial primary key,"
                    + "name VARCHAR(50) NOT NULL,"
                    + "lastname VARCHAR(50)," 
                    + "identification VARCHAR(20) UNIQUE NOT NULL,"
                    + "birthdate DATE NOT NULL"
                    + ")";
            // Object fo execute query
            this.pst = conn.prepareStatement(query);
            // execute query
            pst.executeUpdate();
            System.out.println("Create table authors successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean store(Connection conn, HashMap<String, String> fields) throws SQLException {
        try {
            // create query for method prepareStatement
            String query = "INSERT INTO authors (name, lastname, identification, birthdate) VALUES (?, ?, ?, ?)";
            String name = fields.get("name");
            String lastname = fields.get("lastname");
            String identification = fields.get("identification");
            String birthdate = fields.get("bithdate");
            // Object for execute query
            this.pst = conn.prepareStatement(query);
            // Set values to preparedStatement, first parameter is 1, second parameter is 2 ...
            pst.setString(1, name);
            pst.setString(2, lastname);
            pst.setString(3, identification);
            utils utility = new utils();
            Date date = utils.getDate(birthdate);
            pst.setDate(4, date);
            // execute the sql, NOTE: executeUpdate() method is only for INSERT, UPDATE and DELETE
            pst.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void get(Connection conn) throws SQLException {
        try {
            // Create query
            String query = "SELECT * FROM authors";
            // Object for execute query
            this.pst = conn.prepareStatement(query);
            // ResultSet object allows allocate the result of SELECT query 
            ResultSet rs = pst.executeQuery();
            // Iterate each data of query result
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ": " + rs.getString(2) + " " +  rs.getString(3) + " identification: " + rs.getString(4) + " birthday:" + rs.getDate(5));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void find(Connection conn, int id) throws SQLException{
        try {
            // create query
            String query = "SELECT * FROM  authors WHERE id = ?";
            // create object for execute query
            this.pst = conn.prepareStatement(query);
            // set id to query
            pst.setInt(1, id);
            // ResultSet object allows allocate the result of SELECT query 
            ResultSet rs = pst.executeQuery();
            // Iterate each data of query result
            while (rs.next()) {
                this.id = rs.getInt(1);
                this.name = rs.getString(2);
                this.lastname = rs.getString(3);
                this.identification = rs.getString(4);
                this.bithdate = rs.getDate(5);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getIdentification() {
        return identification;
    }

    public Date getBithdate() {
        return bithdate;
    }
}
