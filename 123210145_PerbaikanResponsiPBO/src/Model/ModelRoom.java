/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Koneksi.koneksi;
import java.util.List;
import DAO.DAORoom;
import java.sql.SQLException;
/**
 *
 * @author Reva
 */
public class ModelRoom {
    private String name;
    private String size;
    private int price;
    private String status;
    DAORoom DAOR;
    
    public ModelRoom() {
        DAOR = new DAORoom(new koneksi());
    }

    public void closeConnection() throws SQLException{
        DAOR.closeConnection();
    }
    
    public String getNama() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<ModelRoom> getAllRooms(){
       List<ModelRoom> room = DAOR.getAllRooms();
        return room;
    }
}
