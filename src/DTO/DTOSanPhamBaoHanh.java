package DTO;

import java.sql.Date;

public class DTOSanPhamBaoHanh {
    private String maSPBH, tenSP, maBH;
    private float gia;

    public DTOSanPhamBaoHanh(){}

    public DTOSanPhamBaoHanh(String maSPBH, String tenSP, float gia, String maBH)
    {
        this.maSPBH = maSPBH;
        this.tenSP = tenSP;
        this.gia = gia;
        this.maBH = maBH;
    }

    public void setMaSPBH(String maSPBH) {
        this.maSPBH = maSPBH;
    }

    public String getMaSPBH() {
        return maSPBH;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenSP() {
        return tenSP;
    }
    
    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public String getMaBH() {
        return maBH;
    }
    
    public void setGia(float gia) {
        this.gia = gia;
    }

    public Float getGia() {
        return gia;
    }
}
