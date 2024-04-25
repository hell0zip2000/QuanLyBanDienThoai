/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALNhanVien;
import DTO.DTONhanVien;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class BLLNhanVien {
    private DALNhanVien DALNV = new DALNhanVien();
    
    public ArrayList<DTONhanVien> BLLgetDL(){
        return DALNV.getAllNV();
    }
    
    public String BLLthem(DTONhanVien nv){
        if(DALNV.hasNV(nv.getMaNV())){
            return "Mã nhân viên đã tồn tài!";
        }
        if(DALNV.addNV(nv)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(String nv){
        if(DALNV.xoaNV(nv)){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTONhanVien nv){
        if(DALNV.suaNV(nv)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTONhanVien BLLtim(String mc){
        if(DALNV.hasNV(mc)){
            return DALNV.timtheomaNV(mc);
        }
        return null;
    }
}
