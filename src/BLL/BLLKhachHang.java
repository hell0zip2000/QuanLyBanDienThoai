/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DAL.DALKhachHang;
import DTO.DTOKhachHang;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class BLLKhachHang {
    private DALKhachHang DALKH = new DALKhachHang();
    
    public ArrayList<DTOKhachHang> BLLgetDL(){
        return DALKH.getallKHlist();
    }
    
    public String BLLthem(DTOKhachHang kh){
        if(DALKH.hasKH(kh.getMaKH())){
            return "Mã khách hàng đã tồn tài!";
        }
        if(DALKH.themKH(kh)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(String kh){
        if(DALKH.xoaKH(kh)){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOKhachHang kh){
        if(DALKH.suaKH(kh)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOKhachHang BLLtim(String mc){
        if(DALKH.hasKH(mc)){
            return DALKH.timtheomakh(mc);
        }
        return null;
    }
    
    public ArrayList<DTOKhachHang> BLLtimtheoten(String mc){
        return DALKH.timtheoten(mc);
    }
}