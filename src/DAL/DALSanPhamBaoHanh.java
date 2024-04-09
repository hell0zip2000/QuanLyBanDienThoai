package DAL;

import DTO.DTOSanPhamBaoHanh;
import java.util.ArrayList;
import java.sql.*;

public class DALSanPhamBaoHanh {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    public ArrayList <DTOSanPhamBaoHanh> spbhList = new ArrayList<DTOSanPhamBaoHanh>();
    
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
    
    public ArrayList<DTOSanPhamBaoHanh> getallspbhlist(){
        if(open()){
            try{
                spbhList.clear();
                String sql = "SELECT * FROM SAN_PHAM_BAO_HANH";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOSanPhamBaoHanh spbh = new DTOSanPhamBaoHanh();
                    spbh.setMaSPBH(rs.getString("MA_SAN_PHAM"));
                    spbh.setTenSP(rs.getString("TEN_SAN_PHAM"));
                    spbh.setNgayNhan(rs.getDate("NGAY_NHAN"));
                    spbh.setNgayTra(rs.getDate("NGAY_TRA"));
                    spbh.setTrangThai(rs.getBoolean("TRANG_THAI"));
                    spbhList.add(spbh);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return spbhList;
    }
    
    public boolean hasSPBH(String MaSPBH){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM SAN_PHAM_BAO_HANH WHERE MA_SAN_PHAM = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaSPBH);
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
    
    public DTOSanPhamBaoHanh timtheomspbh(String MaSPBH){
        try{
            if(open()){
                String sql = "SELECT * FROM SAN_PHAM_BAO_HANH WHERE MA_SAN_PHAM = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaSPBH);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    Date ngayNhan = rs.getDate("NGAY_NHAN");
                    Date ngayTra = rs.getDate("NGAY_TRA");
                    String TenSP = rs.getString("TEN_SAN_PHAM");
                    Boolean TrangThai = rs.getBoolean("TRANG_THAI");
                    DTOSanPhamBaoHanh spbh = new DTOSanPhamBaoHanh(MaSPBH,TenSP,TrangThai,ngayNhan,ngayTra);
                    return spbh;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themSPBH(DTOSanPhamBaoHanh spbh){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO SAN_PHAM-BAO_HANH VALUES(?, ?, ? ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1,spbh.getMaSPBH());
                p.setString(2, spbh.getTenSP());
                p.setDate(3, spbh.getNgayNhan());
                p.setDate(4, spbh.getNgayTra());
                p.setBoolean(5, spbh.getTrangThai());
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
    
    public boolean xoaSPBH(String MaSPBH){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETA FROM SAN_PHAM_BAO_HANH WHERE MA_SAN_PHAM = ?";
                p = c.prepareStatement(sql);
                p.executeUpdate();
                p.setString(1, MaSPBH);
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
    
    public boolean suaSPBH(DTOSanPhamBaoHanh spbh){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE SAN_PHAM_BAO_HANH SET  MA_SAN_PHAM = ?, TEN_SAN_PHAM = ?, NGAY_NHAN = ?, NGAY_TRA = ?, TRANG_THAI = ? WHERE MA_SAN_PHAM = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, spbh.getMaSPBH());
                p.setString(2, spbh.getTenSP());
                p.setDate(3, spbh.getNgayNhan());
                p.setDate(4, spbh.getNgayTra());
                p.setBoolean(5, spbh.getTrangThai());
                p.setString(6, spbh.getMaSPBH());
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
