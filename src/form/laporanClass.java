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
public class laporanClass {
    
    private int KodeLaporan;
    private int NIKaryawan;
    private String NamaKaryawan;
    private String NamaJabatan;
    private int GajiPokok;
    private int Sakit;
    private int Izin;
    private int Bolos;
    private int TotalGaji;
    private String Bulan;
    
    public laporanClass(
        int KodeLaporan,
        int NIKaryawan,
        String NamaKaryawan,
        String NamaJabatan,
        int GajiPokok,
        int Sakit,
        int Izin,
        int Bolos,
        int TotalGaji,
        String Bulan){
        this.KodeLaporan = KodeLaporan;
        this.NIKaryawan = NIKaryawan;
        this.NamaKaryawan = NamaKaryawan;
        this.NamaJabatan = NamaJabatan;
        this.GajiPokok = GajiPokok;
        this.Sakit = Sakit;
        this.Izin = Izin;
        this.Bolos = Bolos;
        this.TotalGaji = TotalGaji;
        this.Bulan = Bulan;
}
    
    public int getKodeLaporan(){
        return KodeLaporan;
    }
    
    public int getNIKaryawan(){
        return NIKaryawan;
    }
    
    public String getNamaKaryawan(){
        return NamaKaryawan;
    }
    
    public String getNamaJabatan(){
        return NamaJabatan;
    }
    
    public int getGajiPokok(){
        return GajiPokok;
    }
    
    public int getSakit(){
        return Sakit;
    }
    
    public int getIzin(){
        return Izin;
    }
    
    public int getBolos(){
        return Bolos;
    }
    
    public int getTotalGaji(){
        return TotalGaji;
    }
    
    public String getBulan(){
        return Bulan;
    }
    
}
