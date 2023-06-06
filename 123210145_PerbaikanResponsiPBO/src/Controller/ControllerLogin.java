/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Koneksi.koneksi;
import Model.ModelAkun;
import View.adminview;
import View.loginview;
import View.roomview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
/**
 *
 * @author USER
 */
public class ControllerLogin implements ActionListener{
    loginview view;
    ControllerRoom roomListController;
    ControllerAdmin ca;

    public ControllerLogin(loginview view) {
        this.view = view;
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        ModelAkun akun = new ModelAkun(view.getjTextField1(),view.getjPasswordField1());
        if(akun.GetAkun().equalsIgnoreCase("ADMIN")){
            view.setVisible(false);
            ca = ControllerAdmin.getInstance();
            ca.showPage();
        }  else if(akun.GetAkun().equalsIgnoreCase("RENTER")){
            view.setVisible(false);
            roomListController = ControllerRoom.getInstance();
            roomListController.showPage();
        }else{
            view.showMessage("login gagal");
        }
    }
}
