package DAL;

import DTO.ChiTietSanPham;
import DTO.DTOSanPham;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.*;

public class DALSanPham {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ArrayList <DTOSanPham> spList = new ArrayList<>();
    public ArrayList <ChiTietSanPham> ctspList = new ArrayList<>();

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
            System.out.println("Kết nối thành công");
            System.out.println(c.getCatalog());
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    
    public void close(){
        try{
            if(c != null){
                c.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public ArrayList<DTOSanPham > getallSPlist(){
        if(open()){
            try{
                spList.clear();
                String sql = "SELECT * FROM SAN_PHAM";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOSanPham sp = new DTOSanPham();
                    sp.setMaSanPham(rs.getString("MA_SAN_PHAM"));
                    sp.setTenSanPham(rs.getString("TEN"));
                    sp.setSoLuong(rs.getInt("SO_LUONG"));
                    sp.setBaoHanh(rs.getInt("BAO_HANH"));
                    sp.setGiaNhap(rs.getInt("GIA_NHAP"));
                    sp.setGiaBan(rs.getInt("GIA_BAN"));
                    sp.setImg(rs.getString("HINH_ANH"));
                    sp.setMaNCC(rs.getString("MA_NHA_CUNG_CAP"));
                    spList.add(sp);
                }
                return spList;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return null;
    }
    
    public boolean hasSP(String MaSP){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM SAN_PHAM WHERE MA_SAN_PHAM = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaSP);
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
    
    public DTOSanPham timtheomasp(String MaSP){
        try{
            if(open()){
                String sql = "SELECT * FROM SAN_PHAM WHERE MA_SAN_PHAM = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaSP);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String ten = rs.getString("TEN");
                    int SoLuong = rs.getInt("SO_LUONG");
                    int gianhap = rs.getInt("GIA_NHAP");
                    int giaban = rs.getInt("GIA_BAN");
                    String nsx = rs.getString("MA_NHA_CUNG_CAP");
                    String img = rs.getString("HINH_ANH");
                    int baohanh = rs.getInt("BAO_HANH");
                    DTOSanPham ad = new DTOSanPham(MaSP,ten,gianhap,giaban,SoLuong,img,nsx,baohanh);
                    return ad;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themSP(DTOSanPham sp){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO SAN_PHAM VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, sp.getMaSanPham());
                p.setString(2,sp.getTenSanPham());
                p.setInt(3, sp.getSoLuong());
                p.setString(4, sp.getImg());
                p.setInt(5, sp.getGiaNhap());
                p.setInt(6, sp.getGiaBan());
                p.setInt(7, sp.getBaoHanh());
                p.setString(8,sp.getMaNCC());
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
    
    public boolean xoaSP(String ma){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM SAN_PHAM WHERE MA_SAN_PHAM = ?";
                p = c.prepareStatement(sql);
                p.setString(1, ma);
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
    
    public boolean suaSP(DTOSanPham sp){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE SAN_PHAM SET MA_SAN_PHAM = ?, TEN = ?, GIA_NHAP = ?, GIA_BAN = ?, SO_LUONG = ?, HINH_ANH = ?, MA_NHA_CUNG_CAP = ?, BAO_HANH = ? WHERE MA_SAN_PHAM = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, sp.getMaSanPham());
                p.setString(2, sp.getTenSanPham());
                p.setInt(3, sp.getGiaNhap());
                p.setInt(4, sp.getGiaBan());
                p.setInt(5, sp.getSoLuong());
                p.setString(6, sp.getImg());
                p.setString(7, sp.getMaNCC());
                p.setInt(8, sp.getBaoHanh());
                p.setString(9, sp.getMaSanPham());
                p.executeUpdate();
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
        
    public boolean themctsp(ChiTietSanPham sp){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO CHI_TIET_SAN_PHAM VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, sp.getMaSP());
                p.setString(5, sp.getMauSac());
                p.setString(6,sp.getIMEI());
                p.setString(7, sp.getManHinh());
                p.setString(8, sp.getRam());
                p.setString(9, sp.getRom());
                p.setString(10, sp.getPin());
                p.setString(2, sp.getThietKe());
                p.setString(3, sp.getCamera());
                p.setFloat(4, sp.getKhoiLuong());
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
    
    public ArrayList<ChiTietSanPham> getallctsplist(){
        if(open()){
            try{
                
                ctspList.clear();
                String sql = "SELECT * FROM CHI_TIET_SAN_PHAM";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    ChiTietSanPham ctsp = new ChiTietSanPham();
                    ctsp.setMaSP(rs.getString("MA_SAN_PHAM"));
                    ctsp.setMauSac(rs.getString("MAU"));
                    ctsp.setIMEI(rs.getString("IMEI"));
                    ctsp.setManHinh(rs.getString("MAN_HINH"));
                    ctsp.setRam(rs.getString("RAM"));
                    ctsp.setRom(rs.getString("ROM"));
                    ctsp.setPin(rs.getString("PIN"));
                    ctsp.setThietKe(rs.getString("THIET_KE"));
                    ctsp.setCamera(rs.getString("CAMERA"));
                    ctsp.setKhoiLuong(rs.getFloat("KHOI_LUONG"));
                    ctspList.add(ctsp);
                }
                return ctspList;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return null;
    }

    public boolean suaCTSP(ChiTietSanPham ctsp, String MaSP){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE CHI_TIET_SAN_PHAM SET MA_SAN_PHAM = ?, MAU = ?, IMEI = ?, MAN_HINH = ?, RAM = ?, ROM = ?, PIN = ?, THIET_KE = ?, CAMERA = ?, KHOI_LUONG = ? WHERE MA_SAN_PHAM = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, ctsp.getMaSP());
                p.setString(2, ctsp.getMauSac());
                p.setString(3, ctsp.getIMEI());
                p.setString(4, ctsp.getManHinh());
                p.setString(5, ctsp.getRam());
                p.setString(6, ctsp.getRom());
                p.setString(7, ctsp.getPin());
                p.setString(8, ctsp.getThietKe());
                p.setString(9, ctsp.getCamera());
                p.setFloat(10, ctsp.getKhoiLuong());
                p.setString(11, MaSP);
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
    
    public ChiTietSanPham timctsptheomasp(String MaSP){
        if(open()){
            try{
                    String sql = "SELECT * FROM CHI_TIET_SAN_PHAM WHERE MA_SAN_PHAM = ?";
                    p = c.prepareStatement(sql);
                    p.setString(1, MaSP);
                    ResultSet rs = p.executeQuery();
                    if(rs.next()){
                        String MauSac = rs.getString("MAU");
                        String IMEI = rs.getString("IMEI");
                        String ManHinh = rs.getString("MAN_HINH");
                        String RAM = rs.getString("RAM");
                        String ROM = rs.getString("ROM");
                        String Pin = rs.getString("PIN");
                        String ThietKe = rs.getString("THIET_KE");
                        String Camera = rs.getString("CAMERA");
                        Float KhoiLuong = rs.getFloat("KHOI_LUONG");
                        ChiTietSanPham ctsp = new ChiTietSanPham(MaSP,MauSac,IMEI,ManHinh,RAM,ROM,Pin,ThietKe,Camera,KhoiLuong);
                        return ctsp;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return null;
    }
    
    public boolean xoaCTSP(String masp){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM CHI_TIET_SAN_PHAM WHERE MA_SAN_PHAM = ?";
                p = c.prepareStatement(sql);
                p.setString(1, masp);
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

    public ArrayList<DTOSanPham> timtheoten(String ten){
        try{
            if(open()){
                spList.clear();
                String sql = "SELECT * FROM SAN_PHAM WHERE LOWER(TEN) LIKE LOWER(?)";
                p = c.prepareStatement(sql);
                p.setString(1, "%" + ten + "%");
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    String ma = rs.getString("MA_SAN_PHAM");
                    String tensach = rs.getString("TEN");
                    int soluong = rs.getInt("SO_LUONG");
                    String img = rs.getString("HINH_ANH");
                    int giaban = rs.getInt("GIA_BAN");
                    int gianhap = rs.getInt("GIA_NHAP");
                    int baohanh = rs.getInt("BAO_HANH");
                    String mancc = rs.getString("MA_NHA_CUNG_CAP");
                    DTOSanPham sp = new DTOSanPham(ma,tensach,gianhap,giaban,soluong,img,mancc,baohanh);
                    spList.add(sp);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return spList;
    }
    
    public ArrayList<DTOSanPham> timtheomancc(String Ma){
        try{
            if(open()){
                spList.clear();
                String sql = "SELECT * FROM SAN_PHAM WHERE LOWER(MA_NHA_CUNG_CAP) LIKE LOWER(?)";
                p = c.prepareStatement(sql);
                p.setString(1, "%" + Ma + "%");
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    String ten = rs.getString("TEN");
                    int SoLuong = rs.getInt("SO_LUONG");
                    int gianhap = rs.getInt("GIA_NHAP");
                    int giaban = rs.getInt("GIA_BAN");
                    String masp = rs.getString("MA_SAN_PHAM");
                    String img = rs.getString("HINH_ANH");
                    int baohanh = rs.getInt("BAO_HANH");
                    String mancc = rs.getString("MA_NHA_CUNG_CAP");
                    DTOSanPham ad = new DTOSanPham(masp,ten,gianhap,giaban,SoLuong,img,mancc,baohanh);
                    spList.add(ad);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return spList;
    }
}
