/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Koneksi.koneksi;
import DAO.DAORenter;
import Model.ModelRenter;
import View.renterview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class ControllerEdit implements ActionListener{
    private renterview view;
    private ModelRenter mr;
    private DAORenter dr;
    
    public ControllerEdit(ModelRenter mr) {
        this.mr = mr;
        dr = new DAORenter(new koneksi());
        view = new renterview();
        view.getbtnsubmit().addActionListener(this);
        view.getbtnlogout().addActionListener(this);
        view.settxtdurasi(mr.getDurasi());
        view.settxtkontak(mr.getKontak());
        view.settxtid(mr.getId());
        view.settxtnama(mr.getNama());
    }
    
    void showPage() {
    view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getbtnsubmit()){
            try {
                mr.setNama(view.gettxtnama());
                mr.setKontak(view.gettxtkontak());
                System.out.println("Nama new = "+mr.getNama());
                System.out.println("ID new = "+mr.getId()); 
                dr.updateRenter(mr);
                view.setVisible(false);
                ControllerAdmin apc = new ControllerAdmin();
                apc.showPage();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if(e.getSource()==view.getbtnlogout()){
            System.exit(0);
        }
    }
    
}
