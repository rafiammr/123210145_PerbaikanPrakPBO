/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Koneksi.koneksi;
import Model.ModelRenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Model.ModelRoom;
import View.roomview;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class ControllerRoom implements ActionListener{
    private static ControllerRoom instance;
    ModelRoom room;
    private roomview view;
    private ControllerRenter cr;
    private ModelRenter mr;
    
    public ControllerRoom() {
        room = new ModelRoom();
        view = new roomview();
        loadAllRoom();
        view.getjButton1().addActionListener(this);
    }
    public static ControllerRoom getInstance(){
        if(instance==null){
            instance = new ControllerRoom();
        }
        return instance;
    }
public void loadAllRoom() {
    List<ModelRoom> rooms = room.getAllRooms();
    DefaultTableModel dtm = new DefaultTableModel();
    dtm.addColumn("Name");
    dtm.addColumn("Size");
    dtm.addColumn("Price");
    dtm.addColumn("Status");
    for (ModelRoom r : rooms) {
        Object[] row = { r.getNama(), r.getSize(), r.getPrice(), r.getStatus() };
        dtm.addRow(row);
    }
    view.setTableModel(dtm);
    
    view.getTable().addMouseListener(new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            mr = new ModelRenter();
            try {
                mr.closeConnection();
                int row = view.getTable().getSelectedRow();
                mr.setRoom((String) view.getTable().getValueAt(row, 0));
                mr.setBill((int) view.getTable().getValueAt(row, 2));
                if("empty".equals((String) view.getTable().getValueAt(row, 3))){
                    ControllerRenter rdc = new ControllerRenter(mr);
                    view.setVisible(false);
                    rdc = new ControllerRenter(mr);
                    rdc.showPage();    
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());//Logger.getLogger(roomcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getjButton1()){
            System.exit(0);
        }
    }

    void showPage() {
    view.setVisible(true);
    }
    
}
