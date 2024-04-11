package DTO;

import java.sql.Date;

public class DTOHoaDon {

    public String maHoaDon, maNhanVien, maKhachHang, maKhuyenMai;
    public Date thoiGianTao;
    public int tongSoLuong;
    public Double tongGia, thanhTien;
    public ChiTietHoaDon cthd;

    public DTOHoaDon() {
    }

    public DTOHoaDon(String maHoaDon, String maNhanVien, String maKhachHang, String maKhuyenMai, Date thoiGianTao, int tongSoLuong, Double tongGia, Double thanhTien) 
    {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.maKhuyenMai = maKhuyenMai;
        this.thoiGianTao = thoiGianTao;
        this.tongSoLuong = tongSoLuong;
        this.tongGia = tongGia;
        this.thanhTien = thanhTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongGia(Double tongGia) {
        this.tongGia = tongGia;
    }

    public Double getTongGia() {
        return tongGia;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Double getThanhTien() {
        return thanhTien;
    }
}
