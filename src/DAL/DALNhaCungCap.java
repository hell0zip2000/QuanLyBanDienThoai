package DAL;

import DTO.DTONhaCungCap;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;;

public class DALNhaCungCap {
    public  ArrayList <DTONhaCungCap> nccList = new ArrayList<>();
    private Connection c;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    private PreparedStatement p = null;
    private Statement stm = null;

    public boolean open() {
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
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
     }

    public void close() {
        try {
            if (c!=null)
            c.close();
        }catch (SQLException ex) {
            System.out.println(ex); 
        }
    }
     
    public ArrayList<DTONhaCungCap> getAllNCC(){
        if(open()){
            try{
                nccList.clear();
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery("select *from NHA_CUNG_CAP");
                while(rs.next()){
                    DTONhaCungCap ncc = new DTONhaCungCap();
                    ncc.setMaNCC(rs.getString("MA_NHA_CUNG_CAP"));
                    ncc.setTenNCC(rs.getString("TEN"));
                    ncc.setDiaChi(rs.getString("DIA_CHI"));
                    ncc.setSDT(rs.getString("SDT"));
                    nccList.add(ncc);
                }
                return nccList;
            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                close();
            } 
        }
        return nccList;
    }
    
    public boolean AddNCC(DTONhaCungCap NCC)
    {
        boolean result = false;
        if (open()) {
            try{
                PreparedStatement stmt  = c.prepareStatement("insert into NHA_CUNG_CAP values(?,?,?,?)");
                stmt.setString(1, NCC.getMaNCC());
                stmt.setString(2, NCC.getTenNCC());
                stmt.setString(3, NCC.getDiaChi());
                stmt.setString(4, NCC.getSDT());
                if (stmt.executeUpdate()>=1)
                result = true;
            }catch (SQLException ex) {
            ex.printStackTrace();
            }finally {
            close();
            } 
        }
        return result;
    }
    
    public boolean xoaNCC(String NCC){
        boolean result = false;
        if(open()){
            try{
                PreparedStatement stmt  = c.prepareStatement("delete from NHA_CUNG_CAP where MA_NHA_CUNG_CAP = (?)");
               stmt.setString(1, NCC);
                if(stmt.executeUpdate() >= 1){
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

    public boolean hasNCC(String MaNCC){
        boolean result = false;
        if(open()){
            try{
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("Select *from NHA_CUNG_CAP where MA_NHA_CUNG_CAP = '"+MaNCC+"'");
                result = rs.next();
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public DTONhaCungCap timtheomaNCC(String MaNCC){
        try{
            if(open()){
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("Select *from NHA_CUNG_CAP where MA_NHA_CUNG_CAP = '"+MaNCC+"'");
                if(rs.next()){
                    String ten = rs.getString(2);
                    String diachi = rs.getString(3);
                    String sdt = rs.getString(4);
                    DTONhaCungCap NCC = new DTONhaCungCap(MaNCC, ten, diachi, sdt);
                    return NCC;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
            return null;
        }
        
    public boolean suaNCC(DTONhaCungCap NCC){
        boolean result = false;
        if(open()){
            try{
                String SQL = "Update NHA_CUNG_CAP set MA_NHA_CUNG_CAP = ?, TEN = ?, DIA_CHI = ?, SDT = ? where MA_NHA_CUNG_CAP = ?";
                p = c.prepareStatement(SQL);
                p.setString(1, NCC.getMaNCC());
                p.setString(2, NCC.getTenNCC());
                p.setString(3, NCC.getDiaChi());
                p.setString(4, NCC.getSDT());
                p.setString(5, NCC.getMaNCC());
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
    
    public ArrayList<DTONhaCungCap> timtheoten(String ten){
        try{
            if(open()){
                nccList.clear();
                String sql = "SELECT * FROM NHA_CUNG_CAP WHERE LOWER(TEN) LIKE LOWER(?)";
                p = c.prepareStatement(sql);
                p.setString(1, "%" + ten + "%");
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    String ma = rs.getString("MA_NHA_CUNG_CAP");
                    String tensach = rs.getString("TEN");
                    String soluong = rs.getString("DIA_CHI");
                    String img = rs.getString("SDT");
                    DTONhaCungCap sp = new DTONhaCungCap(ma,tensach,soluong,img);
                    nccList.add(sp);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return nccList;
    }
}
