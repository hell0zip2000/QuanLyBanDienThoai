package DAL;

import DTO.DTOKhuyenMai;
import java.util.ArrayList;
import java.sql.*;

public class DALKhuyenMai {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    public ArrayList <DTOKhuyenMai> kmList = new ArrayList<DTOKhuyenMai>();
    
    public boolean open(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName = QLBDT;encrypt=false";
            String usename = "sa";
            String password = "123456";
            c = DriverManager.getConnection(dbUrl,usename,password);
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    
    public void close(){
        try{
            if(c!=null){
                c.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public ArrayList<DTOKhuyenMai> getallkmlist(){
        if(open()){
            try{
                kmList.clear();
                String sql = "SELECT * FROM KHUYEN_MAI";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOKhuyenMai km = new DTOKhuyenMai();
                    km.setMaKhuyenMai(rs.getString("MA_KHUYEN_MAI"));
                    km.setNgayBD(rs.getDate("NGAY_BAT_DAU"));
                    km.setNgayKT(rs.getDate("NGAY_KET_THUC"));
                    km.setLoai(rs.getBoolean("LOAI"));
                    km.setGiaTri(rs.getFloat("GIA_TRI"));
                    km.setTen(rs.getString("TEN"));
                    kmList.add(km);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return kmList;
    }
    
    public boolean hasKM(String MaKhuyenMai){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM KHUYEN_MAI WHERE MA_KHUYEN_MAI = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaKhuyenMai);
                ResultSet rs = p.executeQuery();
                result = rs.next();
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public DTOKhuyenMai timtheomkm(String MaKhuyenMai){
        try{
            if(open()){
                String sql = "SELECT * FROM KHUYEN_MAI WHERE MA_KHUYEN_MAI = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaKhuyenMai);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    Date ngayBD = rs.getDate("NGAY_BAT_DAU");
                    Date ngayKT = rs.getDate("NGAY_KET_THUC");
                    Boolean Loai = rs.getBoolean("LOAI");
                    float GiaTri = rs.getFloat("GIA_TRI");
                    String ten = rs.getString("TEN");
                    DTOKhuyenMai km = new DTOKhuyenMai(MaKhuyenMai,ngayBD,ngayKT,Loai,GiaTri,ten);
                    return km;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themKM(DTOKhuyenMai km){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO KhuyenMai VALUES(?, ?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1,km.getMaKhuyenMai());
                p.setDate(2, km.getNgayBD());
                p.setDate(3, km.getNgayKT());
                p.setBoolean(4, km.getLoai());
                p.setString(5, km.getTen());
                p.setFloat(6, km.getGiaTri());
                if(p.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public boolean xoaKM(String MaKhuyenMai){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETA FROM KHUYEN_MAI WHERE MA_KHUYEN_MAI = ?";
                p = c.prepareStatement(sql);
                p.executeUpdate();
                p.setString(1, MaKhuyenMai);
                if(p.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public boolean suaKM(DTOKhuyenMai km){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE KHUYEN_MAI SET  MA_KHUYEN_MAI = ?, NGAY_BAT_DAU = ?, NGAY_KET_THUC = ?, LOAI = ?, GIA_TRI = ?, TEN = ? WHERE MA_KHUYEN_MAI = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, km.getMaKhuyenMai());
                p.setDate(2, km.getNgayBD());
                p.setDate(3, km.getNgayKT());
                p.setBoolean(4, km.getLoai());
                p.setFloat(5, km.getGiaTri());
                p.setString(6, km.getTen());
                p.setString(7, km.getMaKhuyenMai());
                if(p.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
}
