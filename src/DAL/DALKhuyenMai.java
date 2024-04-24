package DAL;

import DTO.DTOKhuyenMai;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.*;

public class DALKhuyenMai {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ArrayList <DTOKhuyenMai> kmList = new ArrayList<DTOKhuyenMai>();
    
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
                    km.setTen(rs.getString("TEN"));
                    km.setNgayBD(rs.getDate("THOI_GIAN_BAT_DAU"));
                    km.setNgayKT(rs.getDate("THOI_GIAN_KET_THUC"));
                    km.setLoai(rs.getString("LOAI"));
                    km.setGiaTri(rs.getFloat("GIA_TRI"));
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
                    Date ngayBD = rs.getDate("THOI_GIAN_BAT_DAU");
                    Date ngayKT = rs.getDate("THOI_GIAN_KET_THUC");
                    String Loai = rs.getString("LOAI");
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
                p.setDate(3, km.getNgayBD());
                p.setDate(4, km.getNgayKT());
                p.setString(5, km.getLoai());
                p.setString(2, km.getTen());
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
                String SQL = "UPDATE KHUYEN_MAI SET  MA_KHUYEN_MAI = ?, THOI_GIAN_BAT_DAU = ?, tHOI_GIAN_KET_THUC = ?, LOAI = ?, GIA_TRI = ?, TEN = ? WHERE MA_KHUYEN_MAI = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, km.getMaKhuyenMai());
                p.setDate(2, km.getNgayBD());
                p.setDate(3, km.getNgayKT());
                p.setString(4, km.getLoai());
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
    
    public ArrayList<DTOKhuyenMai> timtheoten(String ten){
        try{
            if(open()){
                kmList.clear();
                String sql = "SELECT * FROM KHUYEN_MAI WHERE LOWER(TEN) LIKE LOWER(?)";
                p = c.prepareStatement(sql);
                p.setString(1, "%" + ten + "%");
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    String ma = rs.getString("MA_KHUYEN_MAI");
                    Date soluong = rs.getDate("THOI_GIAN_BAT_DAU");
                    Date img = rs.getDate("THOI_GIAN_KET_THUC");
                    String giaban = rs.getString("LOAI");
                    float gianhap = rs.getFloat("GIA_TRI");
                    String tenKM = rs.getString("TEN");
                    DTOKhuyenMai sp = new DTOKhuyenMai(ma,soluong,img,giaban,gianhap,tenKM);
                    kmList.add(sp);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return kmList;
    }
}
