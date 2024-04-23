package DTO;

public class ChiTietHoaDon {

    public String MaHD, MaSP;
    public int soLuong; 
    public float Gia,ThanhTien;
    public ChiTietHoaDon(){
        this.MaHD = "";
        this.MaSP = "";
        this.soLuong = 0;
        this.Gia = 0;
        this.ThanhTien = 0;
    }
    
    public ChiTietHoaDon(String MaHD, String MaSP, int soLuong, float Gia, float ThanhTien){
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.soLuong = soLuong;
        this.ThanhTien = ThanhTien;
    }
    
    public void setMaHD(String MaHD){
        this.MaHD = MaHD;
    }
    
    public String getMaHD(){
        return MaHD;
    }
    
    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public float getThanhTien() {
        return Gia * soLuong;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    } 
}
