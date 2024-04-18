package DAL;

import DTO.DTONhaCungCap;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
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

    public boolean openConnection() {
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
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
     }

    public void closeConnection() {
        try {
            if (c!=null)
            c.close();
        }catch (SQLException ex) {
            System.out.println(ex); 
        }
    }
     
    public ArrayList<DTONhaCungCap> getAllNCC(){
        if(openConnection()){
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
                closeConnection();
            } 
        }
        return nccList;
    }
    
    public boolean AddNCC(DTONhaCungCap NCC)
    {
        boolean result = false;
        if (openConnection()) {
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
            closeConnection();
            } 
        }
        return result;
    }
    
    public boolean xoaNCC(DTONhaCungCap NCC){
        boolean result = false;
        if(openConnection()){
            try{
                PreparedStatement stmt  = c.prepareStatement("delete from NHA_CUNG_CAP where MA_NHA_CUNG_CAP = (?)");
               stmt.setString(1, NCC.getMaNCC());
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

    public boolean hasNCC(String MaNCC){
        boolean result = false;
        if(openConnection()){
            try{
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("Select *from NHA_CUNG_CAP where MA_NHA_CUNG_CAP = '"+MaNCC+"'");
                result = rs.next();
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                closeConnection();
            }
        }
        return result;
    }
    
    public DTONhaCungCap timtheomaNCC(String MaNCC){
        try{
            if(openConnection()){
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
            closeConnection();
        }
            return null;
        }
        
    public boolean suaNCC(DTONhaCungCap NCC){
        boolean result = false;
        if(openConnection()){
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
                closeConnection();
            }
        }
        return result;
    }
}
