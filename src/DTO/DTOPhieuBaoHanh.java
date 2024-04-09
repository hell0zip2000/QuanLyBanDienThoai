package DTO;

import java.sql.Date;

public class DTOPhieuBaoHanh {
    private String maBaoHanh, noiDung;
    private Date ngayLap;
    private float chiphi;

    public DTOPhieuBaoHanh(){}

    public DTOPhieuBaoHanh(String maBaoHanh, String noiDung, Date ngayLap, float chiphi)
    {
        this.maBaoHanh = maBaoHanh;
        this.noiDung = noiDung;
        this.ngayLap = ngayLap;
        this.chiphi = chiphi;
    }

    public void setMaBaoHanh(String maBaoHanh) {
        this.maBaoHanh = maBaoHanh;
    }

    public String getMaBaoHanh() {
        return maBaoHanh;
    }

    public void setChiPhi(float chiphi) {
        this.chiphi = chiphi;
    }

    public float getChiPhi() {
        return chiphi;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDung() {
        return noiDung;
    }
}
