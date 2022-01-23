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
public class karyawan {
    
//    private int idKaryawan;
    private int nikaryawan;
    private String nama;
    private String idJabatan;
    private int noHp;
    private String alamat;
    
    public karyawan (
//            int idKaryawan, 
            int nikaryawan, 
            String nama, 
            String idJabatan, 
            int noHp, 
            String alamat){
//        this.idKaryawan = idKaryawan;
        this.nikaryawan  = nikaryawan;
        this.nama = nama;
        this.idJabatan = idJabatan;
        this.noHp = noHp;
        this.alamat = alamat;
    }
    
//    public int getidKaryawan(){
//        return idKaryawan;
//    }
    
    public int getnikaryawan(){
        return nikaryawan;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getidJabatan(){
        return idJabatan;
    }
    
    public int getnoHp(){
        return noHp;
    }
    
    public String getAlamat(){
        return alamat;
    }
    
}
