package BLL;

import java.util.ArrayList;

import DAL.DALSanPhamBaoHanh;
import DTO.DTOSanPhamBaoHanh;

public class BLLSanPhamBaoHanh {
    private DALSanPhamBaoHanh DALSPBH = new DALSanPhamBaoHanh();
    
    public ArrayList<DTOSanPhamBaoHanh> BLLgetDL(){
        return DALSPBH.getallspbhlist();
    }
    
    public String BLLthem(DTOSanPhamBaoHanh spbh){
        if(DALSPBH.hasSPBH(spbh.getMaSPBH())){
            return "Mã nhân viên đã tồn tài!";
        }
        if(DALSPBH.themSPBH(spbh)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(String spbh){
        if(DALSPBH.xoaSPBH(spbh)){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOSanPhamBaoHanh spbh){
        if(DALSPBH.suaSPBH(spbh)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOSanPhamBaoHanh BLLtim(String spbh){
        if(DALSPBH.hasSPBH(spbh)){
            return DALSPBH.timtheomspbh(spbh);
        }
        return null;
    }
    
    public ArrayList<DTOSanPhamBaoHanh> BLLtimBH(String spbh){
        if(DALSPBH.hasSPBH(spbh)){
            return DALSPBH.timtheombh(spbh);
        }
        return null;
    }
}
