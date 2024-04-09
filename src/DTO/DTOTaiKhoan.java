package DTO;

public class DTOTaiKhoan {
    private String taikhoan, matkhau, maQuyen;

    public DTOTaiKhoan(){
    }

    public DTOTaiKhoan(String taikhoan, String matkhau, String maQuyen){
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.maQuyen = maQuyen;
    }
   
    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTaikhoan() {
        return taikhoan;
    }
    
    public String getMatkhau() {
        return matkhau;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getMaQuyen() {
        return maQuyen;
    }
}
