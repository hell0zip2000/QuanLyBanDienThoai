package BLL;

import DAL.DALSanPham;
import DTO.ChiTietSanPham;
import DTO.DTOSanPham;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class BLLSanPham {
    private DALSanPham DALSP = new DALSanPham();
    
    public ArrayList<DTOSanPham> BLLgetDL(){
        return DALSP.getallSPlist();
    }
    
    public String BLLthem(DTOSanPham sp){
        if(DALSP.hasSP(sp.getMaSanPham())){
            return "Mã sản phẩm đã tồn tài!";
        }
        if(DALSP.themSP((DTOSanPham)sp)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(String sp){
        if(DALSP.xoaSP(sp)){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOSanPham sp){
        if(DALSP.suaSP(sp)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOSanPham BLLtimtheomasp(String sp){
        if(DALSP.hasSP(sp)){
            return DALSP.timtheomasp(sp);
        }
        return null;
    }
    
    public String BLLthemct(ChiTietSanPham ctsp){
        if(!DALSP.hasSP(ctsp.getMaSP())){
        } else {
            return "Mã sản phẩm không tồn tài!";
        }
        if(DALSP.themctsp(ctsp)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLsuactsp(ChiTietSanPham ctsp, String mc){
        if(!DALSP.hasSP(ctsp.getMaSP())){
            return "Mã hóa đơn không tồn tại!";
        }
        if(DALSP.suaCTSP(ctsp, mc)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public ChiTietSanPham BLLtimctsp(String mc){
        if(DALSP.hasSP(mc)){
            return DALSP.timctsptheomasp(mc);
        }
        return null;
    }
    
    public ArrayList<DTOSanPham> BLLtimtheoten(String mc){
        return DALSP.timtheoten(mc);
    }
        
    public ArrayList<DTOSanPham> BLLtimtheomancc(String mc){
        return DALSP.timtheomancc(mc);
    }
}
