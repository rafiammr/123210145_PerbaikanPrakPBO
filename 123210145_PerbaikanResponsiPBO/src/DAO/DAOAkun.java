/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Koneksi.koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAkun {
    private koneksi dbConnection;

    public DAOAkun(koneksi dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ResultSet getAkun(String username, String password) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }
}


