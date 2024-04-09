package DTO;

import java.sql.Date;

public class DTOSanPhamBaoHanh {
    private String maSPBH, tenSP;
    private boolean trangThai;
    private Date ngayNhan, ngayTra;

    public DTOSanPhamBaoHanh(){}

    public DTOSanPhamBaoHanh(String maSPBH, String tenSP, boolean trangThai, Date ngayNhan, Date ngayTra)
    {
        this.maSPBH = maSPBH;
        this.tenSP = tenSP;
        this.trangThai = trangThai;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
    }

    public void setMaSPBH(String maSPBH) {
        this.maSPBH = maSPBH;
    }

    public String getMaSPBH() {
        return maSPBH;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public boolean getTrangThai(){
        return trangThai;
    }
}
