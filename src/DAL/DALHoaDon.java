package DAL;

import DTO.ChiTietHoaDon;
import DTO.DTOHoaDon;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.*;

public class DALHoaDon {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ArrayList<DTOHoaDon> hdList = new ArrayList<DTOHoaDon>();
    public ArrayList<ChiTietHoaDon> cthdList = new ArrayList<ChiTietHoaDon>();
    
    public boolean open(){
        try {
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
            System.out.println("Kết nối thành công");
            System.out.println(c.getCatalog());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
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
    
  public ArrayList<DTOHoaDon> getallHDlist() {
    if (open()) {
        try {
            hdList.clear();
            String sql = "SELECT HOA_DON.MA_HOA_DON, HOA_DON.MA_NHAN_VIEN, HOA_DON.MA_KHACH_HANG, " +
             "HOA_DON.MA_KHUYEN_MAI, HOA_DON.THOI_GIAN_TAO, SUM(CHI_TIET_HOA_DON.SO_LUONG) AS TongSoLuong, " +
             "SUM(CHI_TIET_HOA_DON.GIA) AS TONG_GIA, " +
             "HOA_DON.THANH_TIEN " +
             "FROM HOA_DON " +
             "INNER JOIN CHI_TIET_HOA_DON ON HOA_DON.MA_HOA_DON = CHI_TIET_HOA_DON.MA_HOA_DON " +
             "GROUP BY HOA_DON.MA_HOA_DON, HOA_DON.MA_NHAN_VIEN, HOA_DON.MA_KHACH_HANG, " +
             "HOA_DON.MA_KHUYEN_MAI, HOA_DON.THOI_GIAN_TAO, HOA_DON.TONG_GIA, HOA_DON.THANH_TIEN";
            stm = c.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()) {
                DTOHoaDon hd = new DTOHoaDon();
                hd.setMaHoaDon(rs.getString("MA_HOA_DON"));
                hd.setMaNhanVien(rs.getString("MA_NHAN_VIEN"));
                hd.setMaKhachHang(rs.getString("MA_KHACH_HANG"));
                hd.setMaKhuyenMai(rs.getString("MA_KHUYEN_MAI"));
                hd.setThoiGianTao(rs.getDate("THOI_GIAN_TAO"));
                hd.setTongSoLuong(rs.getInt("TongSoLuong")); // Set tổng số lượng từ trường tính tổng
                hd.setTongGia(rs.getDouble("TONG_GIA"));
                hd.setThanhTien(rs.getDouble("THANH_TIEN"));
                hdList.add(hd);
            }
            return hdList;
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            close();
        }
    }
    return null;
}

    
    public boolean hasHD(String MaHD){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM HOA_DON WHERE MA_HOA_DON=?";
                p = c.prepareStatement(sql);
                p.setString(1, MaHD);
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
    
    public DTOHoaDon timtheomahd(String MaHD){
        try{
            if(open()){
                String sql = "SELECT * FROM HOA_DON WHERE MA_HOA_DON=?";
                p = c.prepareStatement(sql);
                p.setString(1, MaHD);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String mnv = rs.getString("MA_NHAN_VIEN");
                    String mkh = rs.getString("MA_KHACH_HANG");
                    String mkm = rs.getString("MA_KHUYEN_MAI");
                    Date tgtao = rs.getDate("THOI_GIAN_TAO");
                    int soluong = rs.getInt("TONG_SO_LUONG");
                    Double gia = rs.getDouble("TONG_GIA"); 
                    Double tien = rs.getDouble("THANH_TIEN"); 
                    DTOHoaDon hd = new DTOHoaDon(MaHD,mnv,mkh,mkm,tgtao,soluong,gia,tien);
                    return hd;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themHD(DTOHoaDon hd){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO HOA_DON VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, hd.getMaHoaDon());
                p.setString(8,hd.getMaNhanVien());
                p.setString(6, hd.getMaKhachHang());
                p.setString(7, hd.getMaKhuyenMai());
                p.setDate(2, hd.getThoiGianTao());
                p.setInt(3, hd.getTongSoLuong());
                p.setDouble(4, hd.getTongGia());
                p.setDouble(5, hd.getThanhTien());
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
    
public boolean xoaHD(DTOHoaDon hd){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM HOA_DON WHERE MA_HOA_DON = ?";
                p = c.prepareStatement(sql);
                p.setString(1, hd.getMaHoaDon());
                xoaCTHD(hd.getMaHoaDon());
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
    
    public boolean suaHD(DTOHoaDon hd, String MaHD){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE HOA_DON SET MA_HOA_DON = ?, MA_NHAN_VIEN = ?, MA_KHACH_HANG = ?, MA_KHUYEN_MAI = ?, THOI_GIAN_TAO = ?, TONG_SO_LUONG = ?, TONG_GIA = ?, THANH_TIEN = ? WHERE MA_HOA_DON = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, hd.getMaHoaDon());
                p.setString(2, hd.getMaNhanVien());
                p.setString(3, hd.getMaKhachHang());
                p.setString(4, hd.getMaKhuyenMai());
                p.setDate(5, hd.getThoiGianTao());
                p.setInt(6, hd.getTongSoLuong());
                p.setDouble(7, hd.getTongGia());
                p.setDouble(8, hd.getThanhTien());
                p.setString(9, MaHD);
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
    
     public boolean themcthd(ChiTietHoaDon hd){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO CHI_TIET_HOA_DON VALUE(?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(2, hd.getMaHD());
                p.setString(1, hd.getMaSP());
                p.setInt(3, hd.getSoLuong());
                p.setFloat(4,hd.getGia());
                p.setFloat(5, hd.getThanhTien());
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
    
    public ArrayList<ChiTietHoaDon> getallcthdlist(){
        if(open()){
            try{
                cthdList.clear();
                String sql = "SELECT * FROM CHI_TIET_HOA_DON";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    ChiTietHoaDon cthd = new ChiTietHoaDon();
                    cthd.setMaHD(rs.getString("MA_HOA_DON"));
                    cthd.setMaSP(rs.getString("MA_SAN_PHAM"));
                    cthd.setSoLuong(rs.getInt("SO_LUONG"));
                    cthd.setGia(rs.getFloat("GIA"));
                    cthd.setThanhTien(rs.getFloat("THANH_TIEN"));
                    cthdList.add(cthd);
                }
                return cthdList;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return null;
    }

    public boolean suaCTHD(ChiTietHoaDon cthd, String MaHD){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE CHI_TIET_HOA_DON SET MA_HOA_DON = ?, MA_SAN_PHAM = ?, SO_LUONG = ?, GIA = ?, THANH_TIEN = ? WHERE MA_HOA_DON = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, cthd.getMaHD());
                p.setString(2, cthd.getMaSP());
                p.setInt(3, cthd.getSoLuong());
                p.setFloat(4, cthd.getGia());
                p.setFloat(5, cthd.getThanhTien());
                p.setString(6, MaHD);
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
    
    public ChiTietHoaDon timcthdtheomahd(String MaHD){
        if(open()){
            try{
                    String sql = "SELECT * FROM CHI_TIET_HOA_DON WHERE MA_HOA_DON=?";
                    p = c.prepareStatement(sql);
                    p.setString(1, MaHD);
                    ResultSet rs = p.executeQuery();
                    if(rs.next()){
                        String MaSP = rs.getString("MA_SAN_PHAM");
                        int SoLuong = rs.getInt("SO_LUONG");
                        float Gia = rs.getFloat("GIA");
                        float ThanhTien = rs.getFloat("THANH_TIEN");
                        ChiTietHoaDon cthd = new ChiTietHoaDon(MaHD,MaSP,SoLuong,Gia,ThanhTien);
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
    
    public boolean xoaCTHD(String mahd){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM CHI_TIET_HOA_DON WHERE MA_HOA_DON = ?";
                p = c.prepareStatement(sql);
                p.setString(1, mahd);
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
    
    public int soLuongSP(String mahd){
        int total = 0;
        if(open()){
            try{
                String sql = "SELECT SUM(SO_LUONG) AS TongSoLuong FROM CHI_TIET_HOA_DON WHERE MA_HOA_DON = ?";
                p = c.prepareStatement(sql);
                p.setString(1, mahd);
                ResultSet rs = p.executeQuery(); // Thực thi truy vấn SELECT và lấy kết quả
                if(rs.next()){ // Kiểm tra xem có dòng kết quả nào không
                    total = rs.getInt("TongSoLuong"); // Lấy giá trị tổng từ cột "TongSoLuong"
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return total;
    }
}