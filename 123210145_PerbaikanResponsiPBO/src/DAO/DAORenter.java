/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ModelRenter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Koneksi.koneksi;
import DAOImplements.implementsRenter;

/**
 *
 * @author Reva
 */
public class DAORenter implements implementsRenter{
    koneksi konek;
    ModelRenter mr;
    private String id;
    
    public DAORenter(koneksi db) {
        this.konek = db;
    }
    
    @Override
    public int hitungTotalHarga(int durasi, int bill) {
        return durasi * bill;
    }
    
    public void inputrenter(ModelRenter mr) throws SQLException{
        this.mr = mr;
        int totalHarga = hitungTotalHarga(mr.getDurasi(), mr.getBill());
        String query="INSERT INTO `renter`(`name`, `id`, `contact`, `duration`, `bill`, `room`) VALUES ('"+mr.getNama()+"','"+mr.getId()+"','"+mr.getKontak()+"','"+mr.getDurasi()+"','"+ totalHarga+"','"+mr.getRoom()+"')";
        konek.getStatement().executeUpdate(query);
    } 
    
    public List <ModelRenter> getAllRent(){
        String query="SELECT * FROM RENTER";
        List<ModelRenter> rent = new ArrayList<>();
        try {
            ResultSet rs = this.konek.getStatement().executeQuery(query);
            while (rs.next()) {                
                ModelRenter mr = new ModelRenter();
                mr.setNama(rs.getString("name"));
                mr.setId(rs.getString("id"));
                mr.setKontak(rs.getString("contact"));
                mr.setDurasi(rs.getInt("duration"));
                mr.setBill(rs.getInt("bill"));
                mr.setStatus(rs.getString("status"));
                mr.setRoom(rs.getString("room"));
                rent.add(mr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rent;
    }
    public void closeConnection() throws SQLException{
        konek.konek.close();
    }

    public void SetPaid(String id) throws SQLException {
        this.id = id;
        String query="UPDATE `renter` SET `status`='Paid' WHERE id='"+id+"'";
        konek.getStatement().executeUpdate(query);
    }

    public void hapusID(String id) throws SQLException {
        this.id = id;
        String query="DELETE FROM `renter` WHERE id='"+id+"'";
        konek.getStatement().executeUpdate(query);
    }

    public void updateRenter(ModelRenter mr) throws SQLException {
        this.mr = mr;
        System.out.println("ID = "+mr.getId());
        
        String query="UPDATE `renter` SET `name`='"+mr.getNama()+"',`contact`='"+mr.getKontak()+"' 'duration'='"+mr.getDurasi()+"' WHERE id='"+mr.getId()+"'";
        konek.getStatement().executeUpdate(query);
    }

}
