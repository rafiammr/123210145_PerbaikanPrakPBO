/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Koneksi.koneksi;
import DAO.DAORenter;
import DAO.DAORoom;
import Model.ModelRenter;
import View.adminview;
import View.renterview;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class ControllerAdmin implements ActionListener{
    private static ControllerAdmin instance;
    //private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(koneksi.class.getName());
    ModelRenter mr;
    private adminview view;
    private renterview rv;
    
    public ControllerAdmin(){
        mr = new ModelRenter();
        view = new adminview();
        view.getbtnlogout().addActionListener(this);
        loadAllRenter();
    }
    
    public static ControllerAdmin getInstance(){
        if(instance==null){
            instance = new ControllerAdmin();
        }
        return instance;
    }
    
    public void loadAllRenter(){
        List<ModelRenter> rents = mr.getAllRents();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Name");
        dtm.addColumn("ID");
        dtm.addColumn("Contact");
        dtm.addColumn("Duration");
        dtm.addColumn("Bill");
        dtm.addColumn("Status");
        dtm.addColumn("Room");
        for(ModelRenter r:rents){
            Object[] row = {
            r.getNama(), r.getId(), r.getKontak(), r.getDurasi(),r.getBill(),r.getStatus(),r.getRoom()
            };
            dtm.addRow(row);
        }
        view.setTableModel(dtm);
        view.getTable().addMouseListener(new MouseAdapter() {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            mr.setNama((String) view.getTable().getValueAt(row, 0));
            mr.setId((String) view.getTable().getValueAt(row, 1));
            mr.setKontak((String) view.getTable().getValueAt(row, 2));
            mr.setDurasi((int) view.getTable().getValueAt(row, 3));
            mr.setStatus((String) view.getTable().getValueAt(row, 5));
            mr.setRoom((String) view.getTable().getValueAt(row, 6));
            DAORenter rh = new DAORenter(new koneksi());
            DAORoom roh = new DAORoom(new koneksi());
            if("notPaid".equals(mr.getStatus())){
                int jawab = JOptionPane.showOptionDialog(null, 
                    "ubah ke paid?", 
                    "Option", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(jawab == JOptionPane.YES_OPTION){
                    try {
                        rh.SetPaid(mr.getId());
                        loadAllRenter();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    }
            }else{
                Object[] options = { "ubah", "hapus" };
                int jawab = JOptionPane.showOptionDialog(null, "silahkan pilih", "Option",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (jawab == JOptionPane.YES_OPTION) {
                    System.out.println("ID = "+mr.getId()); 
                    ControllerEdit erdc = new ControllerEdit(mr);
                    view.setVisible(false);
                    erdc.showPage();
                } else if (jawab == JOptionPane.NO_OPTION) {
                    try {
                        rh.hapusID(mr.getId());
                        roh.setRoomKosong(mr.getRoom());
                        loadAllRenter();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }});
    }
                 
    void showPage() {
    view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getbtnlogout()){
         System.exit(0);
        }
    }

}  
