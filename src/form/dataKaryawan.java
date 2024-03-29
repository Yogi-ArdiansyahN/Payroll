/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Database.conn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MrY
 */

public class dataKaryawan extends javax.swing.JInternalFrame {

    /**
     * Creates new form dataKaryawan
     */
    
    conn n = new conn();
    Connection con = n.connection;
    Statement st;
    ResultSet rs;
    String query;
    
    public dataKaryawan() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        show_karyawan_inJTable();
    }
    
    public ArrayList<karyawan> getKaryawanList(){
        ArrayList<karyawan> karyawanList = new ArrayList<karyawan>();
        karyawanList = new ArrayList<karyawan>();
        
        String query = "SELECT "
                + "data_karyawan.nik,"
                + "data_karyawan.nama_karyawan,"
                + "jabatan.nama_jabatan, "
                + "data_karyawan.no_hp, "
                + "data_karyawan.alamat "
                + "FROM data_karyawan "
                + "INNER JOIN jabatan ON data_karyawan.id_jabatan = jabatan.id_jabatan";
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            karyawan Karyawan;
            
            while(rs.next()){
                Karyawan = new karyawan(
                        rs.getInt("nik"),
                        rs.getString("nama_karyawan"),
                        rs.getString("nama_jabatan"),
                        rs.getInt("no_hp"),
                        rs.getString("alamat"));
                karyawanList.add(Karyawan);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return karyawanList;
    }
    
    public void show_karyawan_inJTable(){
        ArrayList<karyawan> list = getKaryawanList();
        
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        
        Object[] row = new Object[5];
        
        
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getnikaryawan();
            row[1] = list.get(i).getNama();
            row[2] = list.get(i).getidJabatan();
            row[3] = list.get(i).getnoHp();
            row[4] = list.get(i).getAlamat();
            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(1066, 518));
        setMinimumSize(new java.awt.Dimension(1066, 518));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(1020, 460));
        jPanel1.setMinimumSize(new java.awt.Dimension(1020, 460));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIKaryawan", "Nama", "Jabatan", "No Hp", "Alamat"
            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
