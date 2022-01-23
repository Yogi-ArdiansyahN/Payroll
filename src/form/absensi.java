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



public class absensi {
//    pricate int idRekapAbsensi;
//    private int idKaryawan;
//    private int idJabatan;
    private int kodeAbsensi;
    private int idKaryawanNIK;
    private String idKaryawanNama;
    private String idJabatan;
    
    private int bolos;
    private int sakit;
    private int izin;
    private String bulan;
    
    String query;
    
    public absensi(
            int kodeAbsensi,
            int idKaryawanNIK,
            String idKaryawanNama, 
            String idJabatan,
//            int idKaryawan,
//            int idJabatan,
            int bolos, 
            int sakit, 
            int izin, 
            String bulan){
//        this.idRekapAbsensi = idRekapAbsensi;
        this.kodeAbsensi = kodeAbsensi;
        this.idKaryawanNIK = idKaryawanNIK;
        this.idKaryawanNama = idKaryawanNama;
        this.idJabatan = idJabatan;
        this.bolos = bolos;
        this.sakit = sakit;
        this.izin = izin;
        this.bulan = bulan;
    }

    
//    public int getidRekapAbsensi(){
//        return idRekapAbsensi;
//    }
    
    public int getKodeAbsensi(){
        return kodeAbsensi;
    }
    
    public int getidKaryawanNIK(){
        return idKaryawanNIK;
    }
    
    public String getidKaryawanNama(){
        return idKaryawanNama;
    }
    
    public String getidJabatan(){
//        query = "SELECT * FROM jabatan where = " + getidJabatan();
//        query = "SELECT nama_jabatan FROM jabatan where = " + idJabatan;
        return idJabatan;
    }
   
    
    public int getBolos(){
        return bolos;
    }
    
    public int getSakit(){
        return sakit;
    }
    
    public int getIzin(){
        return izin;
    }
    
    public String getBulan(){
        return bulan;
    }
}
