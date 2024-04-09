package DAL;

import DTO.DTOPhanQuyen;
import java.util.ArrayList;
import java.sql.*;

public class DALPhanQuyen {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    public ArrayList <DTOPhanQuyen> pqList = new ArrayList<DTOPhanQuyen>();
    
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
    
        public ArrayList<DTOPhanQuyen> getallPQlist(){
        if(open()){
            try{
                pqList.clear();
                String sql = "SELECT * FROM PHAN_QUYEN";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOPhanQuyen pq = new DTOPhanQuyen();
                    pq.setMaQuyen(rs.getString("MA_QUYEN"));
                    pq.setTenQuyen(rs.getString("TEN_QUYEN"));
                    pq.setQuyen(rs.getString("QUYEN"));
                    pqList.add(pq);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return pqList;
    }
    
    public boolean hasQ(String MaQuyen){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM PHAN_QUYEN WHERE MA_QUYEN=?";
                p = c.prepareStatement(sql);
                p.setString(1, MaQuyen);
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
    
    public DTOPhanQuyen timtheomq(String MaQuyen){
        try{
            if(open()){
                String sql = "SELECT * FROM PHAN_QUYEN WHERE MA_QUYEN = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaQuyen);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String TenQuyen = rs.getString("TEN_QUYEN");
                    String Quyen = rs.getString("QUYEN");
                    DTOPhanQuyen pq = new DTOPhanQuyen(MaQuyen,TenQuyen,Quyen);
                    return pq;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themQ(DTOPhanQuyen pq){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO PHAN_QUYEN VALUES(?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1,pq.getMaQuyen());
                p.setString(2, pq.getTenQuyen());
                p.setString(3, pq.getQuyen());
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
    
    public boolean xoaPQ(String MaQuyen){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETA FROM PHAN_QUYEN WHERE MA_QUYEN = ?";
                p = c.prepareStatement(sql);
                p.executeUpdate();
                p.setString(1, MaQuyen);
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
    
    public boolean suaQ(DTOPhanQuyen pq){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE PHAN_QUYEN SET  MA_QUYEN = ?, TEN_QUYEN = ?, QUYEN = ? WHERE MA_QUYEN = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, pq.getMaQuyen());
                p.setString(2, pq.getTenQuyen());
                p.setString(3, pq.getQuyen());
                p.setString(4, pq.getMaQuyen());
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