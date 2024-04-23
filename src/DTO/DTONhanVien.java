package DTO;

import java.sql.Date;

public class DTONhanVien extends ConNguoi{

    String MaNV, vitri, hinhanh, MaCN, trangThai;
    float luong;
    
    public DTONhanVien() {
        super();
    }
    
    public DTONhanVien(String MaNV, String Ten, String sex, Date ngaySinh, String DiaChi, String SDT, float luong, String vitri, String trangThai, String hinhanh, String MaCN) {
        super(Ten, DiaChi, SDT, sex, ngaySinh);
        this.MaNV = MaNV;
        this.luong = luong;
        this.trangThai = trangThai;
        this.vitri = vitri;
        this.hinhanh = hinhanh;
        this.MaCN = MaCN;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getVitri() {
        return vitri;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getHinhanh() {
        return hinhanh;
    }
    
    public String getMaCN() {
        return MaCN;
    }

    public void setMaCN(String MaCN) {
        this.MaCN = MaCN;
    }
}
