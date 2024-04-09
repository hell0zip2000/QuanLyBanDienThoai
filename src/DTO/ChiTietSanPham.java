package DTO;
import java.io.Serializable;
public class ChiTietSanPham implements Serializable{
    private String MaSP;
    private String mauSac;
    private String IMEI;
    private String manHinh;
    private String ram;
    private String rom;
    private String pin;
    private String thietKe;
    private String camera;
    private float khoiLuong;
    public ChiTietSanPham() {
    }

    public ChiTietSanPham(String MaSP, String mauSac, String IMEI, String manHinh,String ram,String rom, String pin,String thietKe,String camera,float khoiLuong) {
        this.MaSP = MaSP;
        this.mauSac=mauSac;
        this.IMEI=IMEI;
        this.manHinh=manHinh;
        this.ram=ram;
        this.rom=rom;
        this.pin=pin;
        this.thietKe=thietKe;
        this.camera=camera;
        this.khoiLuong=khoiLuong;
    }
    
    public void setMaSP(String MaSP){
        this.MaSP = MaSP;
    }
    
    public String getMaSP(){
        return MaSP;
    }

    public String getMauSac() {
        return mauSac;
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getManHinh(){
        return manHinh;
    }

    public String getRam(){
        return ram;
    }

    public String getRom(){
        return rom;
    }

    public String getPin(){
        return pin;
    }
    
    public String getThietKe(){
        return thietKe;
    }

    public String getCamera(){
        return camera;
    }
    
    public float getKhoiLuong(){
        return khoiLuong;
    }

    public void setMauSac(String mauSac) {
        this.mauSac =mauSac;
    }

    public void setIMEI(String IMEI) {
        this.IMEI= IMEI;
    }

    public void setManHinh(String manHinh) {
        this.manHinh= manHinh;
    }

    public void setRam(String ram) {
        this.ram= ram;
    }

    public void setRom(String rom) {
        this.rom= rom;
    }

    public void setPin(String pin) {
        this.pin= pin;
    }

    public void setThietKe(String thietKe) {
        this.thietKe= thietKe;
    }
    public void setCamera(String camera) {
        this.camera= camera;
    }

    public void setKhoiLuong(float khoiLuong) {
        this.khoiLuong= khoiLuong;
    }

}