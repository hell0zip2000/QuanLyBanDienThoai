package DAL;

import DTO.DTOPhieuBaoHanh;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.*;

public class DALPhieuBaoHanh {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ArrayList <DTOPhieuBaoHanh> pbhList = new ArrayList<DTOPhieuBaoHanh>();
    
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
    
    public ArrayList<DTOPhieuBaoHanh> getallpbhlist(){
        if(open()){
            try{
                pbhList.clear();
                String sql = "SELECT * FROM PHIEU_BAO_HANH";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOPhieuBaoHanh pbh = new DTOPhieuBaoHanh();
                    pbh.setMaBaoHanh(rs.getString("MA_BAO_HANH"));
                    pbh.setNgayLap(rs.getDate("NGAY_LAP"));
                    pbh.setNoiDung(rs.getString("NOI_DUNG"));
                    pbh.setChiPhi(rs.getFloat("CHI_PHI"));
                    pbh.setMaBaoHanh(rs.getString("MA_HOA_DON"));
                    pbhList.add(pbh);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return pbhList;
    }
    
    public boolean hasPBH(String MaBaoHanh){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM PHIEU_BAO_HANH WHERE MA_BAO_HANH = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaBaoHanh);
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
    
    public DTOPhieuBaoHanh timtheompbh(String MaBaoHanh){
        try{
            if(open()){
                String sql = "SELECT * FROM PHIEU_BAO_HANH WHERE MA_BAO_HANH = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaBaoHanh);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    Date ngayLap = rs.getDate("NGAY_LAP");
                    String NoiDung = rs.getString("NOI_DUNG");
                    float Gia = rs.getFloat("CHI_PHI");
                    String HoaDon = rs.getString("MA_HOA_DON");
                    DTOPhieuBaoHanh pbh = new DTOPhieuBaoHanh(MaBaoHanh,NoiDung,ngayLap,Gia,HoaDon);
                    return pbh;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public ArrayList<DTOPhieuBaoHanh> timtheomhd(String MaBaoHanh){
        try{
            if(open()){
                pbhList.clear();
                String sql = "SELECT * FROM PHIEU_BAO_HANH WHERE MA_HOA_DON = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaBaoHanh);
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    Date ngayLap = rs.getDate("NGAY_LAP");
                    String NoiDung = rs.getString("NOI_DUNG");
                    float Gia = rs.getFloat("CHI_PHI");
                    String HoaDon = rs.getString("MA_HOA_DON");
                    DTOPhieuBaoHanh pbh = new DTOPhieuBaoHanh(MaBaoHanh,NoiDung,ngayLap,Gia,HoaDon);
                    pbhList.add(pbh);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return pbhList;
    }
    
    public boolean themPBH(DTOPhieuBaoHanh pbh){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO PHIEU_BAO_HANH VALUES(?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1,pbh.getMaBaoHanh());
                p.setDate(3, pbh.getNgayLap());
                p.setString(4, pbh.getNoiDung());
                p.setFloat(2, pbh.getChiPhi());
                p.setString(5,pbh.getMaHoaDon());
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
    
    public boolean xoaPBH(String MaBaoHanh){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETA FROM PHIEU_BAO_HANH WHERE MA_BAO_HANH = ?";
                p = c.prepareStatement(sql);
                p.executeUpdate();
                p.setString(1, MaBaoHanh);
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
    
    public boolean suaPBH(DTOPhieuBaoHanh pbh){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE PHIEU_BAO_HANH SET  MA_BAO_HANH = ?, NGAY_LAP = ?, NOI_DUNG = ?, CHI_PHI = ?, MA_HOA_DON = ? WHERE MA_BAO_HANH = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, pbh.getMaBaoHanh());
                p.setDate(2, pbh.getNgayLap());
                p.setString(3, pbh.getNoiDung());
                p.setFloat(4, pbh.getChiPhi());
                p.setString(5, pbh.getMaHoaDon());
                p.setString(6, pbh.getMaBaoHanh());
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
