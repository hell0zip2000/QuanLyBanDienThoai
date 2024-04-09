package DAL;

import DTO.ChiTietPhieuNhap;
import DTO.DTOPhieuNhap;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;

public class DALPhieuNhap {
    private Connection c;
    private PreparedStatement p = null;
    private java.sql.Statement stm = null;
    public ArrayList<DTOPhieuNhap> pnList = new ArrayList<DTOPhieuNhap>();
    public ArrayList<ChiTietPhieuNhap> ctpnList = new ArrayList<ChiTietPhieuNhap>();

    public DALPhieuNhap() {
        this.p = null;
    }
    
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
    
    public ArrayList<DTOPhieuNhap> getallPNlist(){
        if(open()){
            try{
                pnList.clear();
                String sql = "SELECT * FROM PHIEU_NHAP";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOPhieuNhap pn = new DTOPhieuNhap();
                    pn.setMaPhieuNhap(rs.getString("MA_PHIEU_NHAP"));
                    pn.setNgayNhap(rs.getDate("NGAY_NHAP"));
                    pn.setMaNCC(rs.getString("MA_NHA_CUNG_CAP"));
                    pn.setMaNV(rs.getString("MA_NHAN_VIEN"));
                    pn.setTongGia(rs.getFloat("TONG_GIA"));
                    pnList.add(pn);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return pnList;
    }
    
    public boolean hasPN(String MaPN){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM PHIEU_NHAP WHERE MA_PHIEU_NHAP = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaPN);
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
    
    public DTOPhieuNhap timtheomapn(String MaPN){
        try{
            if(open()){
                String sql = "SELECT * FROM PHIEU_NHAP WHERE MA_PHIEU_NHAP = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaPN);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    Date ngaynhap = rs.getDate("NGAY_NHAP");
                    String mncc = rs.getString("MA_NHA_CUNG_CAP");
                    String mnv = rs.getString("MA_NHAN_VIEN");
                    float gia = rs.getFloat("TONG_GIA");
                    DTOPhieuNhap pn = new DTOPhieuNhap(MaPN,ngaynhap,mncc,mnv,gia);
                    return pn;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themPN(DTOPhieuNhap pn){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO PHIEU_NHAP VALUES(?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, pn.getMaPhieuNhap());
                p.setDate(2, (Date) pn.getNgayNhap());
                p.setString(3, pn.getMaNCC());
                p.setString(4, pn.getMaNV());
                p.setFloat(5, pn.getTongGia());
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
    
    public boolean xoaPN(DTOPhieuNhap pn){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM PHIEU_NHAP WHERE MA_PHIEU_NHAP = ?";
                p = c.prepareStatement(sql);
                p.setString(1, pn.getMaPhieuNhap());
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
    
    public boolean suaPN(DTOPhieuNhap pn){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE PHIEU_NHAP SET MA_PHIEU_NHAP = ?, NGAY_NHAP = ?, MA_NHA_CUNG_CAP = ?, MA_NHAN_VIEN = ?, TONG_GIA = ? WHERE MA_PHIEU_NHAP = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, pn.getMaPhieuNhap());
                p.setDate(2, (Date)pn.getNgayNhap());
                p.setString(3, pn.getMaNCC());
                p.setString(4, pn.getMaNV());
                p.setFloat(5, pn.getTongGia());
                p.setString(6, pn.getMaPhieuNhap());
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
    
     public boolean themctpn(ChiTietPhieuNhap pn){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO CHI_TIET_PHIEU_NHAP VALUE(?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, pn.getMaPN());
                p.setString(2, pn.getMaSP());
                p.setInt(3, pn.getSoLuong());
                p.setFloat(4,pn.getDonGia());
                p.setString(5, pn.getTenSP());
                if(p.executeUpdate() >= 0){
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
    
    public ArrayList<ChiTietPhieuNhap> getallctpnlist(){
        if(open()){
            try{
                ctpnList.clear();
                String sql = "SELECT * FROM CHI_TIET_PHIEU_NHAP";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    ChiTietPhieuNhap cthd = new ChiTietPhieuNhap();
                    cthd.setMaPN(rs.getString("MA_PHIEU_NHAP"));
                    cthd.setMaSP(rs.getString("MA_SAN_PHAM"));
                    cthd.setSoLuong(rs.getInt("SO_LUONG"));
                    cthd.setDonGia(rs.getFloat("DON_GIA"));
                    cthd.setTenSP(rs.getString("TEN_SAN_PHAM"));
                    ctpnList.add(cthd);
                }
                return ctpnList;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return null;
    }

    public boolean suaCTPN(ChiTietPhieuNhap ctpn, String MaPN){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE CHI_TIET_PHIEU_NHAP SET MA_PHIEU_NHAP = ?, MA_SAN_PHAM = ?, SO_LUONG = ?, DON_GIA = ?, TEN_SAN_PHAM = ? WHERE MA_PHIEU_NHAP = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, ctpn.getMaPN());
                p.setString(2, ctpn.getMaSP());
                p.setInt(3, ctpn.getSoLuong());
                p.setFloat(4, ctpn.getDonGia());
                p.setString(5, ctpn.getTenSP());
                p.setString(6, MaPN);
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
    
    public ChiTietPhieuNhap timctpntheomapn(String MaPN){
        if(open()){
            try{
                    String sql = "SELECT * FROM CHI_TIET_PHIEU_NHAP WHERE MA_PHIEU_NHAP = ?";
                    p = c.prepareStatement(sql);
                    p.setString(1, MaPN);
                    ResultSet rs = p.executeQuery();
                    if(rs.next()){
                        String MaSP = rs.getString("MA_SAN_PHAM");
                        int SoLuong = rs.getInt("SO_LUONG");
                        float DonGia = rs.getFloat("DON_GIA");
                        String TenSP = rs.getString("TEN_SAN_PHAM");
                        ChiTietPhieuNhap cthd = new ChiTietPhieuNhap(MaPN,MaSP,SoLuong,DonGia,TenSP);
                        return cthd;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return null;
    }
    
    public boolean xoaCTPN(String mapn){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM CHI_TIET_PHIEU_NHAP WHERE MA_PHIEU_NHAP = ?";
                p = c.prepareStatement(sql);
                p.setString(1, mapn);
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

