/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Koneksi.koneksi;
import DAO.DAORoom;
import Model.ModelRenter;
import DAO.DAORenter;
import View.renterview;
import View.roomview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.event.AncestorListener;

/**
 *
 * @author USER
 */
public class ControllerRenter implements ActionListener{
    private renterview view;
    private ModelRenter mr;
    private ControllerRoom romListController;
    
    public ControllerRenter(ModelRenter mr) {
    this.mr = mr;
    view = new renterview();
    view.getbtnsubmit().addActionListener(this);
    view.getbtnlogout().addActionListener(this);
    }
    
    void showPage() {
    view.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getbtnsubmit()){
            mr.setNama(view.gettxtnama());
            mr.setId(view.gettxtid());
            mr.setKontak(view.gettxtkontak());
            mr.setDurasi(Integer.parseInt(view.gettxtdurasi()));
            DAORenter reDAO = new DAORenter(new koneksi());
            DAORoom roDAO = new DAORoom(new koneksi());
            try {
                reDAO.inputrenter(mr);
                roDAO.setNama(mr.getRoom(),mr.getNama());
            } catch (SQLException p) {
                System.out.println(p.getMessage());//Logger.getLogger(rentercontroller.class.getName()).log(Level.SEVERE, null, p);
            }
            ControllerRoom rlc = new ControllerRoom();
            view.setVisible(false);
            rlc.showPage();
        }
        if(e.getSource()==view.getbtnlogout()){
            System.exit(0);
        }
    }
}
