package DTO;

import java.io.Serializable;
import java.util.Scanner;

public class DTONhaCungCap implements Serializable{
    private String maNCC;
    private String tenNCC;
    private String diaChi;
    private String SDT;
    transient Scanner sc = new Scanner(System.in);
    public DTONhaCungCap(){
        
    }

    public DTONhaCungCap(String maNCC) {
        this.maNCC = maNCC;
    }
    
    public DTONhaCungCap(String maNCC,String tenNCC, String diaChi, String SDT)
    {
        this.maNCC=maNCC;
        this.tenNCC=tenNCC;
        this.diaChi=diaChi;
        this.SDT=SDT;
    }
    public DTONhaCungCap(DTONhaCungCap n)
    {
        this.maNCC=n.maNCC;
        this.tenNCC=n.tenNCC;
        this.diaChi=n.diaChi;
        this.SDT=n.SDT;

    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    } 
}
