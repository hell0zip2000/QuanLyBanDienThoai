/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALHoaDon;
import DTO.DTOHoaDon;
import DTO.ChiTietHoaDon;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class BLLHoaDon {
    private DALHoaDon DALHD = new DALHoaDon();
    
    public ArrayList<DTOHoaDon> BLLgetDL(){
        return DALHD.getallHDlist();
    }
    
    public String BLLthem(DTOHoaDon hd){
        if(DALHD.hasHD(hd.getMaHoaDon())){
            return "Mã hóa đơn đã tồn tài!";
        }
        if(DALHD.themHD(hd)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(DTOHoaDon hd){
        if(DALHD.xoaHD(hd)){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOHoaDon hd, String mc){
        if(!DALHD.hasHD(hd.getMaHoaDon())){
            return "Mã hóa đơn không tồn tại!";
        }
        if(DALHD.suaHD(hd, mc)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOHoaDon BLLtim(String mc){
        if(DALHD.hasHD(mc)){
            return DALHD.timtheomahd(mc);
        }
        return null;
    }
    
    public String BLLthemct(ChiTietHoaDon cthd){
        if(!DALHD.hasHD(cthd.getMaHD())){
        } else {
            return "Mã hóa đơn không tồn tài!";
        }
        if(DALHD.themcthd(cthd)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLsuacthd(ChiTietHoaDon cthd, String mc){
        if(!DALHD.hasHD(cthd.getMaHD())){
            return "Mã hóa đơn không tồn tại!";
        }
        if(DALHD.suaCTHD(cthd, mc)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public ChiTietHoaDon BLLtimcthd(String mc){
        if(DALHD.hasHD(mc)){
            return DALHD.timcthdtheomahd(mc);
        }
        return null;
    }
}
