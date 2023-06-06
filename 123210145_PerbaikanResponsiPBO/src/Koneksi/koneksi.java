/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Koneksi;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Reva
 */
public class koneksi {
    String dbUrl = "jdbc:mysql://localhost/almaul_db";
    String username = "root";
    String password = "";
    public Connection konek;
    Statement statement;
    
    public koneksi(){
        try{
            konek = DriverManager.getConnection(dbUrl, username, password);
            createStatement();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    public void createStatement(){
        try {
            this.statement = konek.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
      Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Berhasil terhubung ke database");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return conn;
    }

}
