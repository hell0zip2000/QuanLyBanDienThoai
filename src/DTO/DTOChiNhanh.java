/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author HP
 */
public class DTOChiNhanh {
    public String maChiNhanh;
    public String tenChiNhanh;
    
    public DTOChiNhanh(){}
    
    public DTOChiNhanh(String maChiNhanh, String tenChiNhanh){
        this.maChiNhanh = maChiNhanh;
        this.tenChiNhanh = tenChiNhanh;
    }
    
    public void setMaChiNhanh(String maChiNhanh){
        this.maChiNhanh = maChiNhanh;
    }
    
    public String getMaChiNhanh(){
        return maChiNhanh;
    }
    
    public void setTenChiNhanh(String tenChiNhanh){
        this.tenChiNhanh = tenChiNhanh;
    }
    
    public String getTenChiNhanh(){
        return tenChiNhanh;
    }
}
