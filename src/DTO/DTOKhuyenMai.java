package DTO;

import java.sql.Date;

public class DTOKhuyenMai {
    public String maKhuyenMai, ten, loai;
    Date ngayBD, ngayKT;
    float giaTri;

    public DTOKhuyenMai()
    {

    }

    public DTOKhuyenMai(String maKhuyenMai, Date ngayBD, Date ngayKT, String loai, float giaTri, String ten){
        this.maKhuyenMai = maKhuyenMai;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.loai = loai;
        this.giaTri = giaTri;
        this.ten = ten;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public float getGiaTri() {
        return giaTri;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getLoai(){
        return loai;
    }

    public void setGiaTri(float giaTri) {
        this.giaTri = giaTri;
    }

    public Date getNgayKT() {
        return ngayKT;
    }
    
    public String getTen() {
        return ten;
    }

    public void setTen(String Ten) {
        this.ten = ten;
    }
}
