package BLL;

import java.util.ArrayList;
import DAL.DALPhieuBaoHanh;
import DTO.DTOPhieuBaoHanh;

public class BLLPhieuBaoHanh {
    private DALPhieuBaoHanh DALPBH = new DALPhieuBaoHanh();
    
    public ArrayList<DTOPhieuBaoHanh> BLLgetDL(){
        return DALPBH.getallpbhlist();
    }
    
    public String BLLthem(DTOPhieuBaoHanh pbh){
        if(DALPBH.hasPBH(pbh.getMaBaoHanh())){
            return "Mã nhân viên đã tồn tài!";
        }
        if(DALPBH.themPBH(pbh)){
            return "Thêm thành công!";
        }
        return "Thêm thất bại!";
    }
    
    public String BLLxoa(DTOPhieuBaoHanh pbh){
        if(DALPBH.xoaPBH(pbh.getMaBaoHanh())){
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }
    
    public String BLLsua(DTOPhieuBaoHanh pbh){
        if(DALPBH.suaPBH(pbh)){
            return "Sửa thành công!";
        }
        return "Sửa thất bại!";
    }
    
    public DTOPhieuBaoHanh BLLtim(String pbh){
        if(DALPBH.hasPBH(pbh)){
            return DALPBH.timtheompbh(pbh);
        }
        return null;
    }
}
