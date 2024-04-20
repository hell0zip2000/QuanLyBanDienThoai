/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DAL.DALTaiKhoan;
import DTO.DTOTaiKhoan;
import java.util.ArrayList;
/**
 *
 * @author HP
 */
public class BLLTaiKhoan {
    private DALTaiKhoan DALTK = new DALTaiKhoan();
    
    public ArrayList<DTOTaiKhoan> BLLgetDL(){
        return DALTK.getallTKlist();
    }       
    
    public ArrayList<String> BLLgetDSNVchuacoTK(){
        return DALTK.laynhanvienchuacoTK();
    } 
    
    public DTOTaiKhoan BLLtim(String mc){
        if(DALTK.hasTK(mc)){
            return DALTK.timtheotk(mc);
        }
        return null;
    }
    
    public String BLLthem(DTOTaiKhoan tk){
        if(DALTK.hasTK(tk.getTaikhoan())){
            return "Tài khoản đã tồn tại!";
        }
        if(DALTK.themTK(tk)){
            return "Tạo tài khoản thành công!";
        }
        return "Tạo tài khoản thất bại!";
    }
    
    
    public String BLLxoa(String tk){
        if(!DALTK.hasTK(tk)){
            return "Tài khoản không tồn tại!";
        }
        if(DALTK.xoaTK(tk)){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOTaiKhoan tk){
        if(!DALTK.hasTK(tk.getTaikhoan())){
            return "Tài khoản không tồn tại!";
        }
        if(DALTK.suaTK(tk)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }

    public boolean DangNhap(String TaiKhoan, String MatKhau){
        if(DALTK.dangnhap(TaiKhoan, MatKhau) == true){
            return true;
        }
        return false;
    }

}