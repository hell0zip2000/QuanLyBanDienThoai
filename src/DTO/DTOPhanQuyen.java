package DTO;

public class DTOPhanQuyen {
    private String maQuyen, tenQuyen, quyen;

    public DTOPhanQuyen(){}

    public DTOPhanQuyen(String maQuyen, String tenQuyen, String quyen)
    {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
        this.quyen = quyen; 
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getQuyen() {
        return quyen;
    }
}
