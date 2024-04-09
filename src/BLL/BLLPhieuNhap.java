package BLL;

import DAL.DALPhieuNhap;
import DTO.ChiTietPhieuNhap;
import DTO.DTOPhieuNhap;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class BLLPhieuNhap {
    private DALPhieuNhap DALPN = new DALPhieuNhap();
    
    public ArrayList<DTOPhieuNhap> BLLgetDL(){
        return DALPN.getallPNlist();
    }
    
    public String BLLthem(DTOPhieuNhap pn){
        if(DALPN.hasPN(pn.getMaPhieuNhap())){
            return "Mã phiếu nhập đã tồn tài!";
        }
        if(DALPN.themPN(pn)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(DTOPhieuNhap pn){
        if(DALPN.xoaPN(pn)){
            DALPN.xoaCTPN(pn.getMaPhieuNhap());
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOPhieuNhap pn){
        if(DALPN.suaPN(pn)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOPhieuNhap BLLtim(String mc){
        if(DALPN.hasPN(mc)){
            return DALPN.timtheomapn(mc);
        }
        return null;
    }
    
    public String BLLthemct(ChiTietPhieuNhap ctpn){
        if(!DALPN.hasPN(ctpn.getMaPN())){
        } else {
            return "Mã phiếu nhập không tồn tài!";
        }
        if(DALPN.themctpn(ctpn)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLsuactpn(ChiTietPhieuNhap ctpn, String mc){
        if(!DALPN.hasPN(ctpn.getMaPN())){
            return "Mã phiếu nhập không tồn tại!";
        }
        if(DALPN.suaCTPN(ctpn, mc)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public ChiTietPhieuNhap BLLtimctpn(String mc){
        if(DALPN.hasPN(mc)){
            return DALPN.timctpntheomapn(mc);
        }
        return null;
    }
}
