/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.DTOChiNhanh;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author HP
 */
public class DALChiNhanh {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ArrayList<DTOChiNhanh> cnList = new ArrayList<DTOChiNhanh>();
    
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
            if(c!=null){
                c.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public ArrayList<DTOChiNhanh> getallCNlist(){
        if(open()){
            try{
                cnList.clear();
                String sql = "SELECT * FROM CHI_NHANH";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOChiNhanh cn = new DTOChiNhanh();
                    cn.setMaChiNhanh(rs.getString("MA_CHI_NHANH"));
                    cn.setTenChiNhanh(rs.getString("TEN_CHI_NHANH"));
                    cnList.add(cn);
                }
                return cnList;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return null;
    }
    
    public boolean hasCN(String MaCN){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM CHI_NHANH WHERE MA_CHI_NHANH = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaCN);
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
    
    public DTOChiNhanh timtheomacn(String MaCN){
        try{
            if(open()){
                String sql = "SELECT * FROM CHI_NHANH WHERE MA_CHI_NHANH=?";
                p = c.prepareStatement(sql);
                p.setString(1, MaCN);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String ten = rs.getString("TEN_CHI_NHANH");
                    DTOChiNhanh cn = new DTOChiNhanh(MaCN, ten);
                    return cn;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themCN(DTOChiNhanh cn){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO CHI_NHANH VALUES(?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, cn.getMaChiNhanh());
                p.setString(2,cn.getTenChiNhanh());
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
    
public boolean xoaCN(DTOChiNhanh cn){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM CHI_NHANH WHERE MA_CHI_NHANH = ?";
                p = c.prepareStatement(sql);
                p.setString(1, cn.getMaChiNhanh());
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
    
    public boolean suaCN(DTOChiNhanh cn){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE HOA_DON SET MA_CHI_NHANH = ?, TEN_CHI_NHANH = ? WHERE MA_CHI_NHANH = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, cn.getMaChiNhanh());
                p.setString(2, cn.getTenChiNhanh());
                p.setString(3, cn.getMaChiNhanh());
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
