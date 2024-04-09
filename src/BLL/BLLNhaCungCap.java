/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALNhaCungCap;
import DTO.DTONhaCungCap;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class BLLNhaCungCap {
    private DALNhaCungCap DALNCC = new DALNhaCungCap();
    
    public ArrayList<DTONhaCungCap> BLLgetDL(){
        return DALNCC.getAllNCC();
    }
    
    public String BLLthem(DTONhaCungCap ncc){
        if(DALNCC.hasNCC(ncc.getMaNCC())){
            return "Mã nhà cung cấp đã tồn tài!";
        }
        if(DALNCC.AddNCC(ncc)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(DTONhaCungCap ncc){
        if(!DALNCC.hasNCC(ncc.getMaNCC())){
            return "Mã nhà cung cấp không tồn tại!";
        }
        if(DALNCC.xoaNCC(ncc)){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTONhaCungCap ncc){
        if(DALNCC.suaNCC(ncc)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTONhaCungCap BLLtim(String mc){
        if(DALNCC.hasNCC(mc)){
            return DALNCC.timtheomaNCC(mc);
        }
        return null;
    }
}