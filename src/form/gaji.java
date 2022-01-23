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
public class gaji {
    
    private int idGaji;
//    private int idKaryawan;
    
    private int NIKaryawan;
    private String NamaKaryawan;
    private String NamaJabatan;
//    private int idJabatan;
    private int gajiPokok;
    
    public gaji(
            int idGaji, 
            int NIKaryawan, 
            String NamaKaryawan,
            String NamaJabatan,
            int gajiPokok){
        this.idGaji = idGaji;
        this.NIKaryawan = NIKaryawan;
        this.NamaKaryawan = NamaKaryawan;
        this.NamaJabatan = NamaJabatan;
        this.gajiPokok = gajiPokok;
    }
    
    public int getidGaji(){
        return idGaji;
    }
    
    public String getNamaKaryawan(){
        return NamaKaryawan;
    }
    
    public int getNIKaryawan(){
        return NIKaryawan;
    }
    
    public String getNamaJabatan(){
        return NamaJabatan;
    }
    
    public int getGajiPokok(){
        return gajiPokok;
    }
}
