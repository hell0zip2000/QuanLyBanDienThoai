package DTO;

import java.sql.Date;

public abstract class ConNguoi {
    public String Ten, DiaChi, SDT, sex;
    public Date ngaySinh; 

    public ConNguoi() {}

    public ConNguoi(String Ten, String DiaChi, String SDT, String sex, Date ngaySinh)
    {
        this.Ten = Ten;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.sex = sex;
        this.ngaySinh = ngaySinh;
    }
    
    public void setTen(String ten) {
        Ten = ten;
    }

    public String getTen() {
        return Ten;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setSDT(String sDT) {
        SDT = sDT;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
