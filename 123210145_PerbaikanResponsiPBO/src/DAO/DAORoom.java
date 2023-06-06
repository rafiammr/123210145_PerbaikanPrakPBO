/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ModelRoom;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Koneksi.koneksi;
/**
 *
 * @author Reva
 */
public class DAORoom {
    koneksi konek;
    private String name;
    private String room;
    
    public DAORoom(koneksi db) {
        this.konek = db;
    }
    
    public List<ModelRoom> getAllRooms(){
        String query="SELECT * FROM ROOMS";
        List<ModelRoom> room = new ArrayList<>();
        try {
            ResultSet rs = this.konek.getStatement().executeQuery(query);
            while (rs.next()) {                
                ModelRoom mr = new ModelRoom();
                mr.setName(rs.getString("name"));
                mr.setPrice(rs.getInt("price"));
                mr.setSize(rs.getString("size"));
                mr.setStatus(rs.getString("status"));
                room.add(mr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        }
        return room;
    }
    public void closeConnection() throws SQLException{
        konek.konek.close();
    }

    public void setNama(String room,String nama) throws SQLException {
        this.name = nama;
        this.room = room;
        String query = "UPDATE `rooms` SET `status`='"+name+"' WHERE name = '"+room+"'";
        konek.getStatement().executeUpdate(query);
    }

    public void setRoomKosong(String room) throws SQLException {
        this.room = room;
        String query = "UPDATE `rooms` SET `status`= 'empty' WHERE name = '"+room+"'";
        konek.getStatement().executeUpdate(query);
    }
}
