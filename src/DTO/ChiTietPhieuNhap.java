package DTO;

public class ChiTietPhieuNhap{
    private String MaPN;
    private String MaSP;
    private Integer soLuong;
    private Float donGia;
    private String tenSP;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(String MaPN, String MaSP, Integer soLuong, Float donGia, String tenSP) {
        this.MaPN = MaPN;
        this.MaSP = MaSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tenSP = tenSP;
    }
    
    public void setMaPN(String MaPN){
        this.MaPN = MaPN;
    }
    
    public String getMaPN(){
        return MaPN;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }
    
    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
