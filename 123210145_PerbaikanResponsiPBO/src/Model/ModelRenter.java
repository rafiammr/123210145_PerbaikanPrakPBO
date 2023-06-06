/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Koneksi.koneksi;
import DAO.DAORenter;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Reva
*/
public class ModelRenter {
    private String nama;
    private String id;
    private String contact;
    private String status;
    private String room;
    private int durasi;
    private int bill;
    
    DAORenter DAOR;
    
    public ModelRenter() {
        DAOR = new DAORenter(new koneksi());
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKontak() {
        return contact;
    }

    public void setKontak(String contact) {
        this.contact = contact;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public List<ModelRenter> getAllRents(){
       List<ModelRenter> rent = DAOR.getAllRent();
       return rent;
    }
    public void closeConnection() throws SQLException{
        DAOR.closeConnection();
    }
}
