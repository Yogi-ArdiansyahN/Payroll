/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Database.conn;
import java.awt.HeadlessException;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MrY
 */
public class laporan extends javax.swing.JInternalFrame {

    /**
     * Creates new form laporan
     */
    
    conn n = new conn();
    Connection con = n.connection;
    Statement st;
    ResultSet rs;
    String query;
    
    public laporan() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        show_laporan_inJTable();
        show_laporan_inJTable1();
    }
    
    public ArrayList<laporanClass> getLaporanList(){
        ArrayList<laporanClass> laporanList;
        laporanList = new ArrayList<laporanClass>();
        String query = "SELECT "
                + "laporan.kode_laporan,"
                + "data_karyawan.nik,"
                + "data_karyawan.nama_karyawan,"
                + "jabatan.nama_jabatan,"
                + "data_gaji_karyawan.gaji_pokok,"
                + "data_rekap_absensi.sakit,"
                + "data_rekap_absensi.izin,"
                + "data_rekap_absensi.bolos,"
                + "laporan.total_gaji,"
                + "data_rekap_absensi.bulan "
                + "FROM laporan "
                + "INNER JOIN data_karyawan ON laporan.id_karyawan = data_karyawan.id_karyawan "
                + "INNER JOIN jabatan ON laporan.id_jabatan = jabatan.id_jabatan "
                + "INNER JOIN data_rekap_absensi ON laporan.id_rekap_absensi = data_rekap_absensi.id_rekap_absensi "
                + "INNER JOIN data_gaji_karyawan ON laporan.id_gaji = data_gaji_karyawan.id_gaji WHERE total_gaji = 0";
       
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            laporanClass LaporanClass;
            
            while(rs.next()){
                LaporanClass = new laporanClass(
                        rs.getInt("kode_laporan"),
                        rs.getInt("nik"),
                        rs.getString("nama_karyawan"),
                        rs.getString("nama_jabatan"),
//                        rs.getInt("id_karyawan"),
//                        rs.getInt("id_jabatan"),
                        rs.getInt("gaji_pokok"),
                        rs.getInt("sakit"),
                        rs.getInt("izin"),
                        rs.getInt("bolos"),
                        rs.getInt("total_gaji"),
                        rs.getString("bulan"));
                laporanList.add(LaporanClass);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return laporanList;
    }
    
    public void show_laporan_inJTable(){
        ArrayList<laporanClass> list = getLaporanList();
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        
        Object[] row = new Object[10];
        
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getKodeLaporan();
            row[1] = list.get(i).getNIKaryawan();
            row[2] = list.get(i).getNamaKaryawan();
            row[3] = list.get(i).getNamaJabatan();
            row[4] = list.get(i).getGajiPokok();
            row[5] = list.get(i).getSakit();
            row[6] = list.get(i).getIzin();
            row[7] = list.get(i).getBolos();
            row[8] = list.get(i).getTotalGaji();
            row[9] = list.get(i).getBulan();
            model.addRow(row);
        }
    }
    
    public ArrayList<laporanClass> getLaporanList1(){
        ArrayList<laporanClass> laporanList;
        laporanList = new ArrayList<laporanClass>();
        String query = "SELECT "
                + "laporan.kode_laporan,"
                + "data_karyawan.nik,"
                + "data_karyawan.nama_karyawan,"
                + "jabatan.nama_jabatan,"
                + "data_gaji_karyawan.gaji_pokok,"
                + "data_rekap_absensi.sakit,"
                + "data_rekap_absensi.izin,"
                + "data_rekap_absensi.bolos,"
                + "laporan.total_gaji,"
                + "data_rekap_absensi.bulan "
                + "FROM laporan "
                + "INNER JOIN data_karyawan ON laporan.id_karyawan = data_karyawan.id_karyawan "
                + "INNER JOIN jabatan ON laporan.id_jabatan = jabatan.id_jabatan "
                + "INNER JOIN data_rekap_absensi ON laporan.id_rekap_absensi = data_rekap_absensi.id_rekap_absensi "
                + "INNER JOIN data_gaji_karyawan ON laporan.id_gaji = data_gaji_karyawan.id_gaji WHERE total_gaji >  0";
       
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            laporanClass LaporanClass;
            
            while(rs.next()){
                LaporanClass = new laporanClass(
                        rs.getInt("kode_laporan"),
                        rs.getInt("nik"),
                        rs.getString("nama_karyawan"),
                        rs.getString("nama_jabatan"),
//                        rs.getInt("id_karyawan"),
//                        rs.getInt("id_jabatan"),
                        rs.getInt("gaji_pokok"),
                        rs.getInt("sakit"),
                        rs.getInt("izin"),
                        rs.getInt("bolos"),
                        rs.getInt("total_gaji"),
                        rs.getString("bulan"));
                laporanList.add(LaporanClass);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return laporanList;
    }
    
    public void show_laporan_inJTable1(){
        ArrayList<laporanClass> list = getLaporanList1();
        DefaultTableModel model = (DefaultTableModel)jTable3.getModel();
        
        Object[] row = new Object[10];
        
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getKodeLaporan();
            row[1] = list.get(i).getNIKaryawan();
            row[2] = list.get(i).getNamaKaryawan();
            row[3] = list.get(i).getNamaJabatan();
            row[4] = list.get(i).getGajiPokok();
            row[5] = list.get(i).getSakit();
            row[6] = list.get(i).getIzin();
            row[7] = list.get(i).getBolos();
            row[8] = list.get(i).getTotalGaji();
            row[9] = list.get(i).getBulan();
            model.addRow(row);
        }
    }
    
    public void executeSQLQuery(String query, String message) throws SQLException{
        try{
            st = con.createStatement();
            if((st.executeUpdate(query)) == 1){
                JOptionPane.showMessageDialog(null, "Data "+message+" Succesfully");
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                DefaultTableModel model1 = (DefaultTableModel) jTable3.getModel();
                model.setRowCount(0);
                model1.setRowCount(0);
                show_laporan_inJTable();
                show_laporan_inJTable1();
            }else{
                JOptionPane.showMessageDialog(this, "Data Not "+message);
            }
        }catch(HeadlessException | SQLException ex){
            ex.printStackTrace();
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
        jTFkode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFnik = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFnama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTFjabatan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFgaji = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTFsakit = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTFizin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFbolos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTFbulan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnInput = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTFtotalGaji = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(1020, 460));
        jPanel1.setMinimumSize(new java.awt.Dimension(1020, 460));

        jTFkode.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFkode.setEnabled(false);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode Laporan");

        jTFnik.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFnik.setEnabled(false);

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("NIK");

        jTFnama.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFnama.setEnabled(false);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama");

        jTFjabatan.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFjabatan.setEnabled(false);

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jabatan");

        jTFgaji.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFgaji.setEnabled(false);

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gaji Pokok");

        jTFsakit.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFsakit.setEnabled(false);

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sakit");

        jTFizin.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFizin.setEnabled(false);

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Izin");

        jTFbolos.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFbolos.setEnabled(false);

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Bolos");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Bulan");

        jTFbulan.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jTFbulan.setEnabled(false);
        jTFbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFbulanActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(13, 36, 51));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Laporan ", "NIK", "Nama", "Jabatan", "Gaji Pokok", "Sakit", "Izin", "Bolos", "Total Gaji", "Bulan"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Table Data Gaji");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Laporan ", "NIK", "Nama", "Jabatan", "Gaji Pokok", "Sakit", "Izin", "Bolos", "Total Gaji", "Bulan"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Keterangan : Sakit = -25.000, Izin = -30.000, Bolos = -50.000");

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font(".SF NS Text", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Table Laporan ");

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-making_notes.png"))); // NOI18N
        btnUpdate.setText("Cetak PDF");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnUpdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reset.png"))); // NOI18N
        btnUpdate1.setText("Refresh Data Laporan");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdate1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(111, 111, 111)
                                        .addComponent(jLabel1))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnUpdate1))
                .addContainerGap())
        );

        btnInput.setBackground(new java.awt.Color(204, 204, 204));
        btnInput.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-making_notes.png"))); // NOI18N
        btnInput.setText("Input Laporan");
        btnInput.setBorder(null);
        btnInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(240, 84, 84));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font(".SF NS Text", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Total Gaji");

        jTFtotalGaji.setFont(new java.awt.Font(".SF NS Text", 1, 18)); // NOI18N
        jTFtotalGaji.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFtotalGaji.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTFtotalGaji)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFtotalGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFkode))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFnik)
                            .addComponent(jTFnama)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFbulan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jTFgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTFsakit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTFizin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTFbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnInput, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFkode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jTFnik, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFnama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTFjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTFsakit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jTFizin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jTFbolos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTFbulan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFbulanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFbulanActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int i = jTable2.getSelectedRow();
        TableModel model = jTable2.getModel();
        int sakit = 25000;
        int izin = 30000;
        int bolos = 50000;
       
        jTFkode.setText(model.getValueAt(i,0).toString());
        jTFnik.setText(model.getValueAt(i,1).toString());
        jTFnama.setText(model.getValueAt(i,2).toString());
        jTFjabatan.setText(model.getValueAt(i,3).toString());
        jTFgaji.setText(model.getValueAt(i,4).toString());
        jTFsakit.setText(model.getValueAt(i,5).toString());
        jTFizin.setText(model.getValueAt(i,6).toString());
        jTFbolos.setText(model.getValueAt(i, 7).toString());
        jTFtotalGaji.setText(model.getValueAt(i, 8).toString());
        jTFbulan.setText(model.getValueAt(i, 9).toString());
        
        int sakitt = Integer.parseInt(jTFsakit.getText());
        int izinn = Integer.parseInt(jTFizin.getText());
        int boloss = Integer.parseInt(jTFbolos.getText());
        int totall = Integer.parseInt(jTFgaji.getText());
        
        
        int total = (sakitt * sakit) + (izinn * izin) + (boloss * bolos);
        int total_gaji = totall - total;
        String totals = Integer.toString(total_gaji);
        jTFtotalGaji.setText(totals);
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputActionPerformed
        // TODO add your handling code here:
        query = "UPDATE laporan SET "
                + "total_gaji = '"+jTFtotalGaji.getText()+"'"
                + "WHERE kode_laporan = '"+ jTFkode.getText() +"'";

        try {
            executeSQLQuery(query, "inserted");
        } catch (SQLException ex) {
//            Logger.getLogger(menuBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInputActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int i = jTable3.getSelectedRow();
        TableModel model = jTable3.getModel();
        
        int sakit = 25000;
        int izin = 30000;
        int bolos = 50000;
        
        jTFkode.setText(model.getValueAt(i,0).toString());
        jTFnik.setText(model.getValueAt(i,1).toString());
        jTFnama.setText(model.getValueAt(i,2).toString());
        jTFjabatan.setText(model.getValueAt(i,3).toString());
        jTFgaji.setText(model.getValueAt(i,4).toString());
        jTFsakit.setText(model.getValueAt(i,5).toString());
        jTFizin.setText(model.getValueAt(i,6).toString());
        jTFbolos.setText(model.getValueAt(i, 7).toString());
        jTFtotalGaji.setText(model.getValueAt(i, 8).toString());
        jTFbulan.setText(model.getValueAt(i, 9).toString());
        
        int sakitt = Integer.parseInt(jTFsakit.getText());
        int izinn = Integer.parseInt(jTFizin.getText());
        int boloss = Integer.parseInt(jTFbolos.getText());
        int totall = Integer.parseInt(jTFgaji.getText());
        
        int total = (sakitt * sakit) + (izinn * izin) + (boloss * bolos);
        int total_gaji = totall - total;
        String totals = Integer.toString(total_gaji);
        jTFtotalGaji.setText(totals);
        
    }//GEN-LAST:event_jTable3MouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
//        
            String query = "SELECT "
                + "laporan.kode_laporan,"
                + "data_karyawan.nik,"
                + "data_karyawan.nama_karyawan,"
                + "jabatan.nama_jabatan,"
                + "data_gaji_karyawan.gaji_pokok,"
                + "data_rekap_absensi.sakit,"
                + "data_rekap_absensi.izin,"
                + "data_rekap_absensi.bolos,"
                + "laporan.total_gaji,"
                + "data_rekap_absensi.bulan "
                + "FROM laporan "
                + "INNER JOIN data_karyawan ON laporan.id_karyawan = data_karyawan.id_karyawan "
                + "INNER JOIN jabatan ON laporan.id_jabatan = jabatan.id_jabatan "
                + "INNER JOIN data_rekap_absensi ON laporan.id_rekap_absensi = data_rekap_absensi.id_rekap_absensi "
                + "INNER JOIN data_gaji_karyawan ON laporan.id_gaji = data_gaji_karyawan.id_gaji WHERE total_gaji >  0";
            
            File reportFile = new File(".");
            String dirr = "";
            
            try {            
                
              st = con.createStatement();
              rs = st.executeQuery(query);
              dirr = reportFile.getCanonicalPath() + "/src/form/ReportData/";
              JasperDesign design = JRXmlLoader.load(dirr + "report.jrxml");
              JasperReport jr = JasperCompileManager.compileReport(design);
              JRResultSetDataSource rsDataSource = new JRResultSetDataSource(rs);
              JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), rsDataSource);
              JasperViewer.viewReport(jp);
              
            }catch(JRException | SQLException | IOException ex){
             JOptionPane.showMessageDialog(null, "Gagal Mencetak" + ex, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:
        
        query = "UPDATE laporan SET "
                + "total_gaji = '0'";
        
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
                DefaultTableModel modell = (DefaultTableModel) jTable3.getModel();
                modell.setRowCount(0);
                show_laporan_inJTable();
                show_laporan_inJTable1();
            }
        }catch(HeadlessException | SQLException ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInput;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFbolos;
    private javax.swing.JTextField jTFbulan;
    private javax.swing.JTextField jTFgaji;
    private javax.swing.JTextField jTFizin;
    private javax.swing.JTextField jTFjabatan;
    private javax.swing.JTextField jTFkode;
    private javax.swing.JTextField jTFnama;
    private javax.swing.JTextField jTFnik;
    private javax.swing.JTextField jTFsakit;
    private javax.swing.JTextField jTFtotalGaji;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
