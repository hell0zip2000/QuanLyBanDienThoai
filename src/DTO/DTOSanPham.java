package DTO;

public class DTOSanPham{

    protected String maSanPham;
    protected String tenSanPham;
    protected int soLuong;
    protected int giaNhap;
    protected int giaBan;
    protected String img;
    protected String maNCC;
    protected int baoHanh;

    public DTOSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public DTOSanPham(String maSanPham, String tenSanPham, int giaNhap, int giaBan, int soLuong, String img, String maNCC, int baoHanh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.img = img;
        this.maNCC = maNCC;
        this.baoHanh = baoHanh;
    }
    
    public DTOSanPham() {
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
            this.maSanPham=maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public String getImg(){
        return img;
    }
    public void setImg(String img){
        this.img = img;
    }
}