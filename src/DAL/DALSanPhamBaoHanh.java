package DAL;

import DTO.DTOSanPhamBaoHanh;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.*;

public class DALSanPhamBaoHanh {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ArrayList <DTOSanPhamBaoHanh> spbhList = new ArrayList<DTOSanPhamBaoHanh>();
    
    public boolean open(){
        try{
            String server = "DESKTOP-IHH7KJB\\HUY180903";
            String user = "sa";
            String pass = "123456";
            String db = "QLBDT";
            int port = 1433;
            ds.setUser(user);
            ds.setPassword(pass);
            ds.setDatabaseName(db);
            ds.setServerName(server);
            ds.setPortNumber(port);
            ds.setEncrypt(false);
            ds.setIntegratedSecurity(false);
            ds.setTrustServerCertificate(false);
            c = ds.getConnection();
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
                    spbh.setGia(rs.getFloat("GIA_BAN"));
                    spbh.setMaBH(rs.getString("MA_BAO_HANH"));
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
                String sql = "SELECT * FROM SAN_PHAM_BAO_HANH WHERE MA_BAO_HANH = ?";
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
                    String TenSP = rs.getString("TEN_SAN_PHAM");
                    float gia = rs.getFloat("GIA_BAN");
                    String MaBH = rs.getString("MA_BAO_HANH");
                    DTOSanPhamBaoHanh spbh = new DTOSanPhamBaoHanh(MaSPBH,TenSP,gia,MaBH);
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
    
    public ArrayList<DTOSanPhamBaoHanh> timtheombh(String MaSPBH){
        try{
            if(open()){
                spbhList.clear();
                String sql = "SELECT * FROM SAN_PHAM_BAO_HANH WHERE MA_BAO_HANH = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaSPBH);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String TenSP = rs.getString("TEN_SAN_PHAM");
                    float gia = rs.getFloat("GIA_BAN");
                    String MaBH = rs.getString("MA_SAN_PHAM");
                    DTOSanPhamBaoHanh spbh = new DTOSanPhamBaoHanh(MaSPBH,TenSP,gia,MaBH);
                    spbhList.add(spbh);
                    return spbhList;
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
                String sql = "INSERT INTO SAN_PHAM-BAO_HANH VALUES(?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1,spbh.getMaSPBH());
                p.setString(2, spbh.getTenSP());
                p.setFloat(3, spbh.getGia());
                p.setString(4,spbh.getMaBH());
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
                String sql = "DELETA FROM SAN_PHAM_BAO_HANH WHERE MA_BAO_HANH = ?";
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
                String SQL = "UPDATE SAN_PHAM_BAO_HANH SET  MA_SAN_PHAM = ?, TEN_SAN_PHAM = ?, GIA = ?, MA_BAO_HANH = ? WHERE MA_BAO_HANH = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, spbh.getMaSPBH());
                p.setString(2, spbh.getTenSP());
                p.setFloat(3, spbh.getGia());
                p.setString(4, spbh.getMaBH());
                p.setString(6, spbh.getMaBH());
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
