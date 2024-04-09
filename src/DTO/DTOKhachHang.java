package DTO;

import java.sql.Date;


public class DTOKhachHang extends ConNguoi{
    String MaKH;

    public DTOKhachHang() {
        super();
    }

    public DTOKhachHang(String MaKH, String Ten, String DiaChi, String SDT, String sex, Date ngaySinh) {
        super(Ten, DiaChi, SDT, sex, ngaySinh);
        this.MaKH = MaKH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }
}
