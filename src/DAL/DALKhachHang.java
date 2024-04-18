package DAL;
import DTO.DTOKhachHang;
import java.util.ArrayList;
import java.sql.*;

public class DALKhachHang {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    public static ArrayList <DTOKhachHang> khList = new ArrayList<DTOKhachHang>();
    
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
    
    public ArrayList<DTOKhachHang> getallKHlist(){
        if(open()){
            try{
                khList.clear();
                String sql = "SELECT * FROM KHACH_HANG";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOKhachHang kh = new DTOKhachHang();
                    kh.setMaKH(rs.getString("MA_KHACH_HANG"));
                    kh.setTen(rs.getString("TEN"));
                    kh.setDiaChi(rs.getString("DIA_CHI"));
                    kh.setSDT(rs.getString("SO_DIEN_THOAI"));
                    kh.setNgaySinh(rs.getDate("NAM_SINH"));
                    kh.setSex(rs.getString("GIOI_TINH"));
                    khList.add(kh);
                    //System.out.println(khList);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return khList;
    }
    
    public boolean hasKH(String MaKH){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM KHACH_HANG WHERE MA_KHACH_HANG = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaKH);
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
    
    public DTOKhachHang timtheomakh(String MaKH){
        try{
            if(open()){
                String sql = "SELECT * FROM KHACH_HANG WHERE MA_KHACH_HANG = ?";
                p = c.prepareStatement(sql);
                p.setString(1, MaKH);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String ten = rs.getString("TEN");
                    String diachi = rs.getString("DIA_CHI");
                    String sdt = rs.getString("SO_DIEN_THOAI");
                    Date NgaySinh = rs.getDate("NAM_SINH");
                    String sex = rs.getString("GIOI_TINH");
                    DTOKhachHang kh = new DTOKhachHang(MaKH,ten,diachi,sdt,sex,NgaySinh);
                    return kh;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themKH(DTOKhachHang kh){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO KHACH_HANG VALUES(?, ?, ?, ?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1, kh.getMaKH());
                p.setString(6, kh.getTen());
                p.setString(3, kh.getDiaChi());
                p.setString(2, kh.getSDT());
                p.setString(5, kh.getSex());
                p.setDate(4, kh.getNgaySinh());
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
    
    public boolean xoaKH(DTOKhachHang kh){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM KHACH_HANG WHERE MA_KHACH_HANG = ?";
                p = c.prepareStatement(sql);
                p.setString(1, kh.getMaKH());
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
    
    public boolean suaKH(DTOKhachHang kh){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE KHACH_HANG SET MA_KHACH_HANG = ?, TEN = ?, DIA_CHI = ?, SO_DIEN_THOAI = ?, GIOI_TINH = ?, NAM_SINH = ? WHERE MA_KHACH_HANG = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, kh.getMaKH());
                p.setString(2, kh.getTen());
                p.setString(3, kh.getDiaChi());
                p.setString(4, kh.getSDT());
                p.setString(5, kh.getSex());
                p.setDate(6, kh.getNgaySinh());
                p.setString(7, kh.getMaKH());
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
    
    public ArrayList<DTOKhachHang> timtheoten(String tenkh){
        try{
            if(open()){
                khList.clear();
                String sql = "SELECT * FROM KHACH_HANG WHERE LOWER(TEN) LIKE LOWER(?)";
                p = c.prepareStatement(sql);
                p.setString(1, "%" + tenkh + "%");
                ResultSet rs = p.executeQuery();
                while(rs.next()){
                    String makh = rs.getString("MA_KHACH_HANG");
                    String ten = rs.getString("TEN");
                    Date ngaysinh = rs.getDate("NAM_SINH");
                    String diachi = rs.getString("DIA_CHI");
                    String sdt = rs.getString("SO_DIEN_THOAI");
                    String sex = rs.getString("GIOI_TINH");
                    DTOKhachHang kh = new DTOKhachHang(makh,ten,diachi,sdt,sex,ngaysinh);
                    khList.add(kh);
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return khList;
    }
}  
