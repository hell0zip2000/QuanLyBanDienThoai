package BLL;

import java.util.ArrayList;
import DAL.DALPhanQuyen;
import DTO.DTOPhanQuyen;

public class BLLPhanQuyen {
    private DALPhanQuyen DALPQ = new DALPhanQuyen();
    
    public ArrayList<DTOPhanQuyen> BLLgetDL(){
        return DALPQ.getallPQlist();
    }
    
    public String BLLthem(DTOPhanQuyen pq){
        if(DALPQ.hasQ(pq.getMaQuyen())){
            return "Mã nhân viên đã tồn tài!";
        }
        if(DALPQ.themQ(pq)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(DTOPhanQuyen pq){
        if(DALPQ.xoaPQ(pq.getMaQuyen())){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOPhanQuyen pq){
        if(DALPQ.suaQ(pq)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOPhanQuyen BLLtim(String pq){
        if(DALPQ.hasQ(pq)){
            return DALPQ.timtheomq(pq);
        }
        return null;
    }
}
