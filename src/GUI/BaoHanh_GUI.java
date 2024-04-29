/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package GUI;

import BLL.BLLHoaDon;
import BLL.BLLPhieuBaoHanh;
import BLL.BLLSanPham;
import BLL.BLLSanPhamBaoHanh;
import DTO.DTOHoaDon;
import DTO.DTOPhieuBaoHanh;
import DTO.DTOSanPham;
import DTO.DTOSanPhamBaoHanh;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class BaoHanh_GUI extends java.awt.Frame {

    BLLHoaDon BLLhd = new BLLHoaDon();
    BLLPhieuBaoHanh BLLpbh = new BLLPhieuBaoHanh();
    BLLSanPhamBaoHanh BLLspbh = new BLLSanPhamBaoHanh();
    BLLSanPham BLLsp = new BLLSanPham();
    DefaultTableModel model, model2;
    /**
     * Creates new form BaoHanh_GUI
     */
    public BaoHanh_GUI() {
        initComponents();
        loadPBH();
        maTuDong();
        ArrayList<String> danhSachMaSP = dsmsp();
        cbbMaSP.setModel(new DefaultComboBoxModel<>(danhSachMaSP.toArray(new String[0])));
        ArrayList<String> danhSachMa = dsmhd();
        cbbMaHD.setModel(new DefaultComboBoxModel<>(danhSachMaSP.toArray(new String[0])));
        model2 = new DefaultTableModel();
        model2.addColumn("Mã phiếu nhập");
        model2.addColumn("Mã sản phẩm");
        model2.addColumn("Tên");
        model2.addColumn("NCC");
        model2.addColumn("Số lượng");
        model2.addColumn("Đơn giá");
        tbSPBH.setModel(model2);
    }
    
    public  int TongGia(String ma){
        ArrayList<DTOSanPhamBaoHanh> ds = BLLspbh.BLLtimBH(ma);
        int Gia = 0;
        if(ds.size() > 0){
            for(DTOSanPhamBaoHanh ct : ds){
                Gia += ct.getGia();
            }
        }
        return Gia;
    }
    
    public ArrayList<String> dsmhd(){
        ArrayList<String> arr = new ArrayList<String>();
        for(DTOHoaDon sp : BLLhd.BLLgetDL()){
            if(arr.contains(sp.getMaHoaDon())){
                continue;
            }
        }
        sortArrayList(arr);
        return arr;
    }
    
    public static void sortArrayList(ArrayList<String> arrayList) {
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Kiểm tra xem chuỗi có đúng định dạng không
                if (!s1.matches("BH\\d{3}") || !s2.matches("BH\\d{3}")) {
                    // Trả về kết quả không ổn định nếu một trong hai chuỗi không tuân thủ đúng định dạng
                    return 0;
                }
                
                // Lấy chỉ số số từ chuỗi, chuyển đổi sang số và so sánh
                int num1 = Integer.parseInt(s1.substring(3));
                int num2 = Integer.parseInt(s2.substring(3));
                return Integer.compare(num1, num2);
            }
        });
    }
    
    public ArrayList<String> dsmsp(){
        ArrayList<String> arr = new ArrayList<String>();
        for(DTOSanPham sp : BLLsp.BLLgetDL()){
            if(arr.contains(sp.getMaSanPham())){
                continue;
            }
            arr.add(sp.getMaSanPham());
        }
        sortArrayList1(arr);
        return arr;
    }
    
    public static void sortArrayList1(ArrayList<String> arrayList) {
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Kiểm tra xem chuỗi có đúng định dạng không
                if (!s1.matches("SP\\d{3}") || !s2.matches("SP\\d{3}")) {
                    // Trả về kết quả không ổn định nếu một trong hai chuỗi không tuân thủ đúng định dạng
                    return 0;
                }
                
                // Lấy chỉ số số từ chuỗi, chuyển đổi sang số và so sánh
                int num1 = Integer.parseInt(s1.substring(3));
                int num2 = Integer.parseInt(s2.substring(3));
                return Integer.compare(num1, num2);
            }
        });
    }
    
    public static Date addMonths(Date date, int months) {
        // Chuyển Date thành Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Thêm số tháng vào ngày
        calendar.add(Calendar.MONTH, months);

        // Lấy ngày mới
        return calendar.getTime();
    }
    
    public boolean kiemtraBH(Date a, int b){
        Boolean rs = false;
        Date d = new Date();
        Date bh = addMonths(a, b);
        if(d.after(bh)){
            rs = true;
        }
        return rs;
    }
    
    public void loadPBH(){
        model = new DefaultTableModel();
        model.addColumn("Mã phiếu bảo hành");
        model.addColumn("Chi phí");
        model.addColumn("Ngày Lập");
        model.addColumn("Nội dung");
        model.addColumn("Mã hóa đơn");
        tbBH.setModel(model);
        ArrayList<DTOPhieuBaoHanh> arr = BLLpbh.BLLgetDL();
        for (int i = 0; i < arr.size(); i++){
            DTOPhieuBaoHanh sp = arr.get(i);
            String Ma = sp.getMaBaoHanh();
            float chiphi = sp.getChiPhi();
            Date ngay = sp.getNgayLap();
            String ND = sp.getNoiDung();
            String MaHD = sp.getMaHoaDon();
            Object[] row = {Ma, chiphi, ngay, ND, MaHD};
            model.addRow(row);
        }
    }

    public void loadSPBH(String ma){
        ArrayList<DTOSanPhamBaoHanh> rs = BLLspbh.BLLtimBH(ma);
        model2.setRowCount(0);
        if (rs != null) { 
            for(DTOSanPhamBaoHanh cthd : rs){
                model2.addRow(new Object[]{
                     cthd.getMaBH(), cthd.getMaSPBH(), cthd.getTenSP(), cthd.getGia()}); 
            }
        }
    }
    
    public static String tangMaSP(ArrayList<String> danhSachMaSP) {
        String maxMaSP = ""; 
        for (String maSP : danhSachMaSP) {
            if (maSP.compareTo(maxMaSP) > 0) {
                maxMaSP = maSP;
            }
        }
        if (maxMaSP == null || maxMaSP.isEmpty()) {
            return "PN001"; // Giả sử mã đầu tiên là "SP001"
        }
        // Tăng mã 
        String prefix = maxMaSP.substring(0, 4); // Giả sử mã có dạng "TGxxx"
        int suffix = Integer.parseInt(maxMaSP.substring(4));
        suffix++;
        // Trả về mã mới
        return prefix + String.format("%d", suffix);
    }
    
    public ArrayList<String> laydsma(){
        ArrayList<String> dsma = new ArrayList<String>();
        for(DTOPhieuBaoHanh sp : BLLpbh.BLLgetDL()){
            dsma.add(sp.getMaBaoHanh());
        }
        return dsma;
    }
    
    public void maTuDong(){
        String newMaSP = tangMaSP(laydsma());
        txtMaBH.setText(newMaSP);
    }
    
    public void trangThaiBanDau(){
        txtChiPhi.setText("");
        jDateNgayNhap.setDate(null);
        txtNoiDung.setText("");
        cbbMaHD.setSelectedItem(null);
        maTuDong();
    }
    
    public void trangThaiBanDau2(){
        cbbMaSP.setSelectedItem("");
        txtTenSP.setText("");
        txtGia.setText("");
        txtMaBH2.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpQLPN = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBH = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaBH = new javax.swing.JTextField();
        txtNoiDung = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        cbbTim = new javax.swing.JComboBox<>();
        btnCapNhat = new javax.swing.JButton();
        txtChiPhi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jDateNgayNhap = new com.toedter.calendar.JDateChooser();
        cbbMaHD = new javax.swing.JComboBox<>();
        jpCTPN = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtMaBH2 = new javax.swing.JTextField();
        btnSuaCT = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        cbbMaSP = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSPBH = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 1064));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1280, 104));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jpQLPN.setBackground(new java.awt.Color(255, 255, 255));

        tbBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBH);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Mã bảo hành");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Chi phí");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Ngày lập");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Nội dung");

        txtMaBH.setEditable(false);
        txtMaBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaBHActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 102, 102));
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 102, 102));
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 102, 102));
        btnXoa.setText("XOÁ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChiTiet.setForeground(new java.awt.Color(0, 102, 102));
        btnChiTiet.setText("CHI TIẾT");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim.setForeground(new java.awt.Color(0, 102, 102));
        btnTim.setText("TÌM KIẾM");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        cbbTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã bảo hành", "Mã hóa đơn" }));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        txtChiPhi.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Mã hóa đơn");

        cbbMaHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpQLPNLayout = new javax.swing.GroupLayout(jpQLPN);
        jpQLPN.setLayout(jpQLPNLayout);
        jpQLPNLayout.setHorizontalGroup(
            jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQLPNLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLPNLayout.createSequentialGroup()
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpQLPNLayout.createSequentialGroup()
                                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26))
                            .addGroup(jpQLPNLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)))
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(txtNoiDung)
                            .addComponent(txtTim)
                            .addComponent(cbbMaHD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLPNLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLPNLayout.createSequentialGroup()
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(64, 64, 64)
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaBH, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtChiPhi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnTim, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18))
        );
        jpQLPNLayout.setVerticalGroup(
            jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLPNLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaBH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(jpQLPNLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu bảo hành", jpQLPN);

        jpCTPN.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Mã sản phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tên sản phẩm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Giá");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Mã bảo hành");

        txtTenSP.setEditable(false);

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        txtMaBH2.setEditable(false);
        txtMaBH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaBH2ActionPerformed(evt);
            }
        });

        btnSuaCT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSuaCT.setForeground(new java.awt.Color(0, 102, 102));
        btnSuaCT.setText("SỬA");
        btnSuaCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(0, 102, 102));
        btnLuu.setText("THÊM");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        cbbMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaSPActionPerformed(evt);
            }
        });

        tbSPBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbSPBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPBHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSPBH);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("XOÁ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnSuaCT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(201, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                                    .addComponent(txtMaBH2)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaBH2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaCT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
            .addComponent(jScrollPane2)
        );

        javax.swing.GroupLayout jpCTPNLayout = new javax.swing.GroupLayout(jpCTPN);
        jpCTPN.setLayout(jpCTPNLayout);
        jpCTPNLayout.setHorizontalGroup(
            jpCTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpCTPNLayout.setVerticalGroup(
            jpCTPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sản phẩm bảo hành", jpCTPN);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void tbBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBHMouseClicked
        // TODO add your handling code here:
        try{
            int i = tbBH.getSelectedRow();
            if(i >= 0){
                txtMaBH.setText(model.getValueAt(i, 0).toString());
                txtChiPhi.setText(model.getValueAt(i, 1).toString());
                String dateString = model.getValueAt(i, 2).toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                jDateNgayNhap.setDate(date);
                txtNoiDung.setText(model.getValueAt(i, 3).toString());
                cbbMaHD.setSelectedItem(model.getValueAt(i, 4).toString());
            }
        }
        catch(Exception ex){
            //JOptionPane.showMessageDialog(this, ex);
            System.out.println(ex);
        }
    }//GEN-LAST:event_tbBHMouseClicked

    private void txtMaBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaBHActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            int i = tbBH.getSelectedRow();
            DTOPhieuBaoHanh sp = new DTOPhieuBaoHanh();
            if (i>=0){
                if(txtMaBH.getText().trim().equals("") ||
                    jDateNgayNhap.getDate() == null ||
                    txtNoiDung.getText().trim().equals("") ||
                    cbbMaHD.getSelectedItem().equals("")){
                    JOptionPane.showMessageDialog(this, "Không được để trống thông tin!");
                }
                else{
                    sp.setMaBaoHanh(txtMaBH.getText());
                    sp.setChiPhi(TongGia(sp.getMaBaoHanh()));
                    Date d = jDateNgayNhap.getDate();
                    sp.setNgayLap((java.sql.Date) d);
                    sp.setNoiDung(txtNoiDung.getText());
                    sp.setMaHoaDon(cbbMaHD.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(this, BLLpbh.BLLsua(sp));
                    loadPBH();
                    trangThaiBanDau();
                }
            }else{
                JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để sửa");
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Lỗi");
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try{
            if(jDateNgayNhap.getDate() == null ||
                txtNoiDung.getText().trim().equals(null) ||
                cbbMaHD.getSelectedItem() == null)

            JOptionPane.showMessageDialog(null,"Vui lòng nhập lại thông tin");
            else{
                DTOPhieuBaoHanh sp = new DTOPhieuBaoHanh();
                sp.setMaBaoHanh(txtMaBH.getText());
                sp.setMaHoaDon(cbbMaHD.getSelectedItem().toString());
                Date d = jDateNgayNhap.getDate();
                java.sql.Date sqlDate = new java.sql.Date(d.getTime());
                sp.setNgayLap(sqlDate);
                sp.setChiPhi(TongGia(sp.getMaBaoHanh()));
                sp.setNoiDung(txtNoiDung.getText());
                JOptionPane.showMessageDialog(null,BLLpbh.BLLthem(sp));
                loadPBH();
                trangThaiBanDau();
            }
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Thông tin không hợp lệ");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int i = tbBH.getSelectedRow();
        String masp;
        if(i >= 0){
            masp = model.getValueAt(i, 0).toString();
            JOptionPane.showMessageDialog(this,BLLpbh.BLLxoa(masp));
            loadSPBH(masp);
            trangThaiBanDau();
        }else{
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        model2.setRowCount(0);
        jTabbedPane1.setSelectedIndex(1);
        String a = txtMaBH.getText();
        loadSPBH(a);
        trangThaiBanDau2();
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if(txtTim.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu muốn tìm kiếm");
        }else if(cbbTim.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kiểu tìm kiếm");
        }else{
            String selectedValue = cbbTim.getSelectedItem().toString();
            switch (selectedValue) {
                case "Mã bảo hành":
                    String maSPCanTim = txtTim.getText();
                    DTOPhieuBaoHanh ketQuaMaSP = BLLpbh.BLLtim(maSPCanTim);
                    if (ketQuaMaSP != null){
                        model = (DefaultTableModel) tbBH.getModel();
                        model.setRowCount(0);
                        Object[] row = {ketQuaMaSP.getMaBaoHanh(),ketQuaMaSP.getChiPhi(),ketQuaMaSP.getNgayLap(), ketQuaMaSP.getNoiDung(), ketQuaMaSP.getMaHoaDon()};
                        model.addRow(row);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu bảo hành");
                    }
                    break;
                case "Mã hóa đơn":
                    String tenSachCanTim = txtTim.getText();
                    ArrayList<DTOPhieuBaoHanh> ketQuaTenSach = BLLpbh.BLLtimmhd(tenSachCanTim);
                    if (ketQuaTenSach.size() > 0){
                        model = (DefaultTableModel) tbBH.getModel();
                        model.setRowCount(0);
                        for(DTOPhieuBaoHanh s : ketQuaTenSach){
                            Object[] row = {s.getMaBaoHanh(),s.getChiPhi(),s.getNgayLap(),s.getNoiDung(),s.getMaHoaDon()};
                            model.addRow(row);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu bảo hành");
                    }
                    break;
                default:
                throw new AssertionError();
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        int selectedValue = cbbTim.getSelectedIndex();
        switch (selectedValue) {
            case 0:
                String maSachCanTim = txtTim.getText();
                DTOPhieuBaoHanh ketQuaMaSach = BLLpbh.BLLtim(maSachCanTim);
                if (ketQuaMaSach != null){
                    model = (DefaultTableModel) tbBH.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQuaMaSach.getMaBaoHanh(),ketQuaMaSach.getChiPhi(),ketQuaMaSach.getNgayLap(), ketQuaMaSach.getNoiDung(), ketQuaMaSach.getMaHoaDon()};
                    model.addRow(row);
                }else{
                    loadPBH();
                }
                break;
            case 1:
                String tenSachCanTim = txtTim.getText().toString();
                ArrayList<DTOPhieuBaoHanh> ketQuaTenSach = BLLpbh.BLLtimmhd(tenSachCanTim);
                if (!ketQuaTenSach.isEmpty()){
                    model = (DefaultTableModel) tbBH.getModel();
                    model.setRowCount(0);
                    for(DTOPhieuBaoHanh s : ketQuaTenSach){
                        Object[] row = {s.getMaBaoHanh(),s.getChiPhi(),s.getNgayLap(),s.getNoiDung(),s.getMaHoaDon()};
                        model.addRow(row);
                    }
                }
                else{
                    loadPBH();
                }
                break;
            default:
            throw new AssertionError();
        }
    }//GEN-LAST:event_txtTimKeyReleased

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        trangThaiBanDau();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void txtMaBH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaBH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaBH2ActionPerformed

    private void btnSuaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTActionPerformed
        // TODO add your handling code here:
        try {
            int i = tbSPBH.getSelectedRow();
            DTOSanPhamBaoHanh sp = new DTOSanPhamBaoHanh();
            if (i>=0){
                if(txtTenSP.getText().trim().equals("") ||
                    cbbMaSP.getSelectedItem().equals("") ||
                    txtGia.getText().trim().equals("") ||
                    txtMaBH2.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Không được để trống thông tin!");
                }else if(kiemtraBH(jDateNgayNhap.getDate(), BLLsp.BLLtimtheomasp(cbbMaSP.getSelectedItem().toString()).getBaoHanh()) == false){
                    JOptionPane.showMessageDialog(null,"Đã hết thời hạn bảo hành");
                }else
                    sp.setMaBH(txtMaBH2.getText());
                    sp.setMaSPBH(cbbMaSP.getSelectedItem().toString());
                    sp.setTenSP(txtTenSP.getText());
                    sp.setGia(Float.parseFloat(txtGia.getText()));
                    JOptionPane.showMessageDialog(this, BLLspbh.BLLsua(sp));
                    loadSPBH(sp.getMaSPBH());
                    trangThaiBanDau();
            }else{
                JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để sửa");
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Lỗi");
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSuaCTActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try{
            if(cbbMaSP.getSelectedItem() == null ||
                txtTenSP.getText().trim().equals("") ||
                txtGia.getText().trim().equals("") ||
                txtMaBH2.getText().trim().equals("")){

            JOptionPane.showMessageDialog(null,"Vui lòng nhập lại thông tin");
            }else if(kiemtraBH(jDateNgayNhap.getDate(), BLLsp.BLLtimtheomasp(cbbMaSP.getSelectedItem().toString()).getBaoHanh()) == false){
                    JOptionPane.showMessageDialog(null,"Đã hết thời hạn bảo hành");   
            }else{
                DTOSanPhamBaoHanh sp = new DTOSanPhamBaoHanh();
                sp.setMaSPBH(cbbMaSP.getSelectedItem().toString());
                sp.setMaBH(txtMaBH2.getText());
                sp.setGia(Float.parseFloat(txtGia.getText()));
                sp.setTenSP(txtTenSP.getText());
                System.out.println(sp.getTenSP());
                JOptionPane.showMessageDialog(null,BLLspbh.BLLthem(sp));
                loadSPBH(sp.getMaBH());
                trangThaiBanDau();
            }
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Thông tin không hợp lệ");
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void cbbMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaSPActionPerformed
        // TODO add your handling code here:
        String a = cbbMaSP.getSelectedItem().toString();
        txtTenSP.setText(BLLsp.BLLtimtheomasp(a).getTenSanPham());
    }//GEN-LAST:event_cbbMaSPActionPerformed

    private void tbSPBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPBHMouseClicked
        // TODO add your handling code here:
        try{
            int i = tbSPBH.getSelectedRow();
            if(i >= 0){
                txtMaBH2.setText(model2.getValueAt(i, 0).toString());
                cbbMaSP.setSelectedItem(model2.getValueAt(i, 1).toString());
                txtTenSP.setText(model2.getValueAt(i, 2).toString());
                txtGia.setText(model2.getValueAt(i, 3).toString());
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_tbSPBHMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i = tbSPBH.getSelectedRow();
        String masp;
        if(i >= 0){
            masp = model2.getValueAt(i, 0).toString();
            JOptionPane.showMessageDialog(this,BLLspbh.BLLxoa(masp));
            loadSPBH(masp);
            trangThaiBanDau();
        }else{
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để xóa");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaoHanh_GUI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaCT;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbMaHD;
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JComboBox<String> cbbTim;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateNgayNhap;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpCTPN;
    private javax.swing.JPanel jpQLPN;
    private javax.swing.JTable tbBH;
    private javax.swing.JTable tbSPBH;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaBH;
    private javax.swing.JTextField txtMaBH2;
    private javax.swing.JTextField txtNoiDung;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
