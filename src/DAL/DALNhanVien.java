package DAL;

import DTO.DTONhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;


public class DALNhanVien{
    private Connection c;
    private PreparedStatement p = null;
    private ArrayList<DTONhanVien> nvList = new ArrayList<>();
    public DALNhanVien() {
    }

    transient Scanner sc = new Scanner(System.in);
     public boolean openConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBDT;encrypt=false";
            String username = "sa"; String password= "123456";
            c = DriverManager.getConnection(dbUrl, username, password);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
     
    public void closeConnection() {
        try {
            if (c!=null)
            c.close();
        } catch (SQLException ex) {
            System.out.println(ex); 
        }
    }
    
    public ArrayList<DTONhanVien> getAllNV(){
     if(openConnection()){
        try{
            nvList.clear();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from NHAN_VIEN");
            while(rs.next()){
                DTONhanVien nv = new DTONhanVien();
                nv.setMaNV(rs.getString("MA_NHAN_VIEN"));
                nv.setTen(rs.getString("TEN"));
                nv.setSex(rs.getString("GIOI_TINH"));
                nv.setNgaySinh(rs.getDate("NAM_SINH"));
                nv.setDiaChi(rs.getString("DIA_CHI"));
                nv.setSDT(rs.getString("SO_DIEN_THOAI"));
                nv.setLuong(rs.getFloat("LUONG"));
                nv.setTrangThai(rs.getBoolean("TRANG_THAI"));
                nv.setVitri(rs.getString("VI_TRI"));
                nv.setHinhanh(rs.getString("HINH_ANH"));
                nv.setMaCN(rs.getString("MA_CHI_NHANH"));
                nvList.add(nv);
        }
        }catch (SQLException ex) {
          ex.printStackTrace();
         }finally {
           closeConnection();
          } 
        }
     return nvList;
     }
    
    public boolean addNV(DTONhanVien nv){
    boolean result = false;
    if (openConnection()) {
        try{
        PreparedStatement stmt  = c.prepareStatement("insert into NHAN_VIEN values(?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, nv.getMaNV());
        stmt.setString(3, nv.getTen());
        stmt.setString(8, nv.getSDT());
        stmt.setString(2, nv.getDiaChi());
        stmt.setString(4, nv.getSex());
        stmt.setFloat(9, nv.getLuong());
        stmt.setDate(5, nv.getNgaySinh());
        stmt.setString(7, nv.getVitri());
        stmt.setBoolean(10, nv.getTrangThai());
        stmt.setString(6, nv.getHinhanh());
        stmt.setString(11, nv.getMaCN());
        if (stmt.executeUpdate()>=1)
          result = true;
        }catch (SQLException ex) {
          ex.printStackTrace();
         }finally {
           closeConnection();
          } 
    }
    return result;
    }
    public boolean xoaNV(DTONhanVien nv){
        boolean result = false;
        if(openConnection()){
            try{
                PreparedStatement stmt  = c.prepareStatement("delete from NHAN_VIEN where MA_NHAN_VIEN = (?)");
                stmt.setString(1, nv.getMaNV());
                if(stmt.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                closeConnection();
            }
        }
        return result;
    }

    public boolean hasNV(String MaNV){
        boolean result = false;
        if(openConnection()){
            try{
                String sql = "Select * from NHAN_VIEN where MA_NHAN_VIEN =?";
                p = c.prepareStatement(sql);
                p.setString(1, MaNV);
                ResultSet rs = p.executeQuery();
                result = rs.next();
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                closeConnection();
            }
        }
        return result;
    }
        
    public DTONhanVien timtheomaNV(String MaNV){
        try{
            if(openConnection()){
                String sql = "Select *from NHAN_VIEN where MA_NHAN_VIEN =? ";
                p = c.prepareStatement(sql);
                p.setString(1, MaNV);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String ten = rs.getString("TEN");
                    String diachi = rs.getString("DIA_cHI");
                    String sdt = rs.getString("SO_DIEN_THOAI");
                    String sex = rs.getString("GIOI_TINH");
                    float luong = rs.getFloat("LUONG");
                    Date NgaySinh = rs.getDate("NAM_SINH");
                    Boolean trangthai = rs.getBoolean("TRANG_THAI");
                    String vitri = rs.getString("VI_TRI");
                    String hinhanh = rs.getString("HINH_ANH");
                    String macn = rs.getString("MA_CHI_NHANH");
                    DTONhanVien nv = new DTONhanVien(MaNV, ten, sex, NgaySinh, diachi, sdt, luong, vitri, trangthai, hinhanh, macn);
                    return nv;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            closeConnection();
        }
        return null;
    }
    
    public boolean suaNV(DTONhanVien nv){
        boolean result = false;
        if(openConnection()){
            try{
                String SQL = "Update NHAN_VIEN set MA_NHAN_VIEN = ?, TEN = ?, SO_DIEN_THOAI = ?, DIA_CHI = ?, GIOI_TINH = ?, LUONG = ?, NAM_SINH = ?, VI_TRI = ?, TRANG_THAI = ?, HINH_ANH = ?, MA_CHI_NHANH = ? where MA_NHAN_VIEN = ?";
                p = c.prepareStatement(SQL);
                p.setString(1, nv.getMaNV());
                p.setString(2, nv.getTen());
                p.setString(3, nv.getSDT());
                p.setString(4, nv.getDiaChi());
                p.setString(5, nv.getSex());
                p.setFloat(6, nv.getLuong());
                p.setDate(7, nv.getNgaySinh());
                p.setString(8, nv.getVitri());
                p.setBoolean(9, nv.getTrangThai());
                p.setString(10, nv.getHinhanh());
                p.setString(11, nv.getMaCN());
                p.setString(12, nv.getMaNV());
                if(p.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                closeConnection();
            }
        }
        return result;
    }
}

