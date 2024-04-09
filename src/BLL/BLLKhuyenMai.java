package BLL;

import java.util.ArrayList;

import DAL.DALKhuyenMai;
import DTO.DTOKhuyenMai;

public class BLLKhuyenMai {
    private DALKhuyenMai DALKM = new DALKhuyenMai();
    
    public ArrayList<DTOKhuyenMai> BLLgetDL(){
        return DALKM.getallkmlist();
    }
    
    public String BLLthem(DTOKhuyenMai km){
        if(DALKM.hasKM(km.getMaKhuyenMai())){
            return "Mã nhân viên đã tồn tài!";
        }
        if(DALKM.themKM(km)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(DTOKhuyenMai km){
        if(DALKM.xoaKM(km.getMaKhuyenMai())){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOKhuyenMai km){
        if(DALKM.suaKM(km)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOKhuyenMai BLLtim(String km){
        if(DALKM.hasKM(km)){
            return DALKM.timtheomkm(km);
        }
        return null;
    }
}
