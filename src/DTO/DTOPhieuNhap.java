package DTO;

import java.util.Date;

public class DTOPhieuNhap{
    String maPhieuNhap;
    Date ngayNhap;
    public String mancc;
    public String manv;
    
    public DTOPhieuNhap() {
    }

    public DTOPhieuNhap(String maPhieuNhap, Date ngayNhap, String mancc, String manv) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.mancc = mancc;
        this.manv = manv;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPN) {
        this.maPhieuNhap = maPN;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMaNCC() {
        return mancc;
    }

    public void setMaNCC(String mancc) {
        this.mancc = mancc;
    }

    public String getMaNV() {
        return manv;
    }

    public void setMaNV(String manv) {
        this.manv = manv;
    }
}
 
