/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.DAOAkun;
import Koneksi.koneksi;
import java.sql.*;
/**
 *
 * @author Reva
 */
public class ModelAkun {
    private String username;
    private String password;
    private String role;
    
    public ModelAkun(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String GetAkun() {
        koneksi conn = new koneksi();

        DAOAkun ma = new DAOAkun(conn);

        try {
            ResultSet rs = ma.getAkun(username, password);
            if (rs != null && rs.next()) {
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.role = rs.getString("role");
                return role;
            } else {
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
 
    return null;
}
}
