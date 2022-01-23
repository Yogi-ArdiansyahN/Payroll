/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

/**
 *
 * @author MrY
 */
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import Database.conn;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class rekapAbsensi extends javax.swing.JInternalFrame {

    /**
     * Creates new form rekapAbsensi
     */
    
    conn n = new conn();
    Connection con = n.connection;
    Statement st;
    ResultSet rs;
    String query;
    
    public rekapAbsensi() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        connectJComboBox_From_Database();
        show_absensi_inJTable();
    }
    
    public ArrayList<absensi> getAbsensiList(){
        ArrayList<absensi> absensiList;
        absensiList = new ArrayList<absensi>();
        String query = "SELECT "
                + "data_rekap_absensi.kode_absensi, "
                + "data_karyawan.nik,"
                + "data_karyawan.nama_karyawan,"
                + "jabatan.nama_jabatan, "
                + "data_rekap_absensi.bolos, "
                + "data_rekap_absensi.sakit, "
                + "data_rekap_absensi.izin, "
                + "data_rekap_absensi.bulan "
                + "FROM ((data_rekap_absensi "
                + "INNER JOIN data_karyawan ON data_rekap_absensi.id_karyawan = data_karyawan.id_karyawan) "
                + "INNER JOIN jabatan ON data_rekap_absensi.id_jabatan = jabatan.id_jabatan)";
       
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            absensi Absensi;
            
            while(rs.next()){
                Absensi = new absensi(
                        rs.getInt("kode_absensi"),
                        rs.getInt("nik"),
                        rs.getString("nama_karyawan"),
                        rs.getString("nama_jabatan"),
//                        rs.getInt("id_karyawan"),
//                        rs.getInt("id_jabatan"),
                        rs.getInt("bolos"),
                        rs.getInt("sakit"),
                        rs.getInt("Izin"),
                        rs.getString("bulan"));
                absensiList.add(Absensi);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return absensiList;
    }
    
    public void show_absensi_inJTable(){
        ArrayList<absensi> list = getAbsensiList();
        
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        
        Object[] row = new Object[8];
        
        
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getKodeAbsensi();
            row[1] = list.get(i).getidKaryawanNIK();
            row[2] = list.get(i).getidKaryawanNama();
            row[3] = list.get(i).getidJabatan();
            row[4] = list.get(i).getSakit();
            row[5] = list.get(i).getIzin();
            row[6] = list.get(i).getBolos();
            row[7] = list.get(i).getBulan();
            model.addRow(row);
        }
        
    }
    
    public void connectJComboBox_From_Database(){
        
        try{
            jCBbulan.addItem("January");
            jCBbulan.addItem("Februari");
            jCBbulan.addItem("Maret");
            jCBbulan.addItem("April");
            jCBbulan.addItem("Mei");
            jCBbulan.addItem("Juni");
            jCBbulan.addItem("Juli");
            jCBbulan.addItem("Agustus");
            jCBbulan.addItem("September");
            jCBbulan.addItem("Oktober");
            jCBbulan.addItem("November");
            jCBbulan.addItem("Desember");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void executeSQLQuery(String query, String message) throws SQLException{
        try{
            st = con.createStatement();
            if((st.executeUpdate(query)) == 1){
                JOptionPane.showMessageDialog(null, "Data "+message+" Succesfully");
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);
                show_absensi_inJTable();
            }else{
                JOptionPane.showMessageDialog(this, "Data Not "+message);
            }
        }catch(HeadlessException | SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void cari(String key){
        
//        query = "SELECT "
//                + "data_karyawan.nik,"
//                + "data_karyawan.nama_karyawan,"
//                + "jabatan.nama_jabatan, "
//                + "data_rekap_absensi.bolos, "
//                + "data_rekap_absensi.sakit, "
//                + "data_rekap_absensi.izin, "
//                + "data_rekap_absensi.bulan "
//                + "FROM ((data_rekap_absensi "
//                + "INNER JOIN data_karyawan ON data_rekap_absensi.id_karyawan = data_karyawan.id_karyawan) "
//                + "INNER JOIN jabatan ON data_rekap_absensi.id_jabatan = jabatan.id_jabatan) "
//                + "WHERE "
//                + "data_karyawan.nik LIKE '%"+key+"%' "
//                + "OR data_karyawan.nama_karyawan LIKE '%"+key+"%' "
//                + "OR jabatan.nama_jabatan LIKE '%"+key+"%'";
//        
//        try{
//            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
//            model.getDataVector().removeAllElements();
//            jTable2.setModel(model);
//            Object[] row = new Object[7];
//            
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            
//            while(rs.next()){
//                row[0] = rs.getString("nik");
//                row[1] = rs.getString("nama_karyawan");
//                row[2] = rs.getString("nama_jabatan");
//                row[3] = rs.getString("bolos");
//                row[4] = rs.getString("izin");
//                row[5] = rs.getString("sakit");
//                row[6] = rs.getString("bulan");
//                model.addRow(row);
//            }
//            
//        } catch(Exception e){
//            e.printStackTrace();
//        }
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
        jTFnikaryawan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTFnama = new javax.swing.JTextField();
        jTFjabatan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTFbolos = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTFsakit = new javax.swing.JTextField();
        jTFizin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jCBbulan = new javax.swing.JComboBox<>();
        btnInsert = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTFkodeAbsensi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBatal1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(733, 579));
        setMinimumSize(new java.awt.Dimension(733, 579));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(1020, 460));
        jPanel1.setMinimumSize(new java.awt.Dimension(1020, 460));
        jPanel1.setPreferredSize(new java.awt.Dimension(1020, 460));

        jTFnikaryawan.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFnikaryawan.setEnabled(false);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NIKaryawan");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama");

        jTFnama.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFnama.setEnabled(false);

        jTFjabatan.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFjabatan.setEnabled(false);

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jabatan");

        jTFbolos.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Bolos");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Sakit");

        jTFsakit.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N

        jTFizin.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Izin");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Bulan");

        jCBbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBbulanActionPerformed(evt);
            }
        });

        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-add.png"))); // NOI18N
        btnInsert.setText("Tambah");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reset.png"))); // NOI18N
        btnBatal.setText("ReStart Data Absensi");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Absensi", "NIKaryawan", "Nama", "Jabatan", "Sakit", "Izin", "Bolos", "Bulan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTFkodeAbsensi.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFkodeAbsensi.setEnabled(false);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Absen");

        btnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reset.png"))); // NOI18N
        btnBatal1.setText("ReStart Absensi");
        btnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFnikaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFkodeAbsensi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFnama, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jTFjabatan))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFsakit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFizin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCBbulan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 167, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBatal)
                        .addContainerGap())))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jTFnama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jTFkodeAbsensi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jTFsakit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jTFizin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jTFbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addComponent(jCBbulan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTFnikaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBatal1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBbulanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBbulanActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:

        query = "UPDATE data_rekap_absensi SET "
                + "sakit = '"+jTFsakit.getText()+"',"
                + "izin = '"+jTFizin.getText()+"',"
                + "bolos = '"+jTFbolos.getText()+"',"
                + "bulan = '"+jCBbulan.getSelectedItem()+"'"
                + "WHERE kode_absensi = '"+ jTFkodeAbsensi.getText() +"'";

        try {
            executeSQLQuery(query, "inserted");
        } catch (SQLException ex) {
//            Logger.getLogger(menuBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int i = jTable2.getSelectedRow();
        TableModel model = jTable2.getModel();
        
        jTFkodeAbsensi.setText(model.getValueAt(i,0).toString());
        jTFnikaryawan.setText(model.getValueAt(i,1).toString());
        jTFnama.setText(model.getValueAt(i,2).toString());
        jTFjabatan.setText(model.getValueAt(i,3).toString());
        jTFsakit.setText(model.getValueAt(i,4).toString());
        jTFizin.setText(model.getValueAt(i,5).toString());
        jTFbolos.setText(model.getValueAt(i,6).toString());
        jCBbulan.setSelectedItem(model.getValueAt(i, 7).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        query = "UPDATE data_rekap_absensi SET "
                + "bolos = '0',"
                + "sakit = '0',"
                + "izin =   '0'";

        try{
            st = con.createStatement();
            if((st.executeUpdate(query)) == 1){
//                JOptionPane.showMessageDialog(null, "Data "+message+" Succesfully");
//                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
//                model.setRowCount(0);
//                show_absensi_inJTable();
            }else{
                String message = "Inserted";
                JOptionPane.showMessageDialog(this, "Data Succes "+message);
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);
                show_absensi_inJTable();
            }
        }catch(HeadlessException | SQLException ex){
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatal1ActionPerformed
        // TODO add your handling code here:
        jTFkodeAbsensi.setText("");
        jTFnikaryawan.setText("");
        jTFnama.setText("");
        jTFjabatan.setText("");
        jTFsakit.setText("");
        jTFizin.setText("");
        jTFbolos.setText("");
        jCBbulan.setSelectedItem("");
    }//GEN-LAST:event_btnBatal1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBatal1;
    private javax.swing.JButton btnInsert;
    private javax.swing.JComboBox<String> jCBbulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFbolos;
    private javax.swing.JTextField jTFizin;
    private javax.swing.JTextField jTFjabatan;
    private javax.swing.JTextField jTFkodeAbsensi;
    private javax.swing.JTextField jTFnama;
    private javax.swing.JTextField jTFnikaryawan;
    private javax.swing.JTextField jTFsakit;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
