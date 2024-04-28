/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.BLLNhanVien;
import BLL.BLLPhieuNhap;
import BLL.BLLSanPham;
import BLL.BLLTaiKhoan;
import DTO.ChiTietPhieuNhap;
import DTO.DTOPhieuNhap;
import DTO.DTOSanPham;
import DTO.DTOTaiKhoan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QLPN_GUI extends javax.swing.JFrame {

    BLLPhieuNhap BLLpn = new BLLPhieuNhap();
    BLLSanPham BLLsp = new BLLSanPham();
    BLLNhanVien BLLnv = new BLLNhanVien();
    BLLTaiKhoan BLLtk = new BLLTaiKhoan();
    DefaultTableModel model, model2;
    String MaNV;
    /**
     * Creates new form PhieuNhap_GUI
     */
    public QLPN_GUI() {
        initComponents();
        loadPN();
        maTuDong();
        ArrayList<String> danhSachMaSP = dsmsp();
        cbbMaSP.setModel(new DefaultComboBoxModel<>(danhSachMaSP.toArray(new String[0])));
        model2 = new DefaultTableModel();
        model2.addColumn("Mã phiếu nhập");
        model2.addColumn("Mã sản phẩm");
        model2.addColumn("Tên");
        model2.addColumn("NCC");
        model2.addColumn("Số lượng");
        model2.addColumn("Đơn giá");
        tbCTPN.setModel(model2);
    }
    
    public QLPN_GUI(String manv) {
        initComponents();
        loadPN();
        maTuDong();
        this.MaNV = manv;
        txtMaNV.setText(manv);
        ArrayList<String> danhSachMaSP = dsmsp();
        cbbMaSP.setModel(new DefaultComboBoxModel<>(danhSachMaSP.toArray(new String[0])));
        model2 = new DefaultTableModel();
        model2.addColumn("Mã phiếu nhập");
        model2.addColumn("Mã sản phẩm");
        model2.addColumn("Tên");
        model2.addColumn("NCC");
        model2.addColumn("Số lượng");
        model2.addColumn("Đơn giá");
        tbCTPN.setModel(model2);
    }
    
    public  int TongGia(String ma){
        ArrayList<ChiTietPhieuNhap> ds = BLLpn.BLLtimctpn(ma);
        int Gia = 0;
        if(ds.size() > 0){
            for(ChiTietPhieuNhap ct : ds){
                Gia += ct.getDonGia();
            }
        }
        return Gia;
    }
    
    public ArrayList<String> dsmnvk(){
        ArrayList<String> arr = new ArrayList<String>();
        for(DTOTaiKhoan sp : BLLtk.BLLgetDL()){
            if(arr.contains(sp.getTaikhoan())){
                continue;
            }
            if(sp.getMaQuyen().equals("QK001")){
                arr.add(sp.getTaikhoan());
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
                if (!s1.matches("NV\\d{3}") || !s2.matches("NV\\d{3}")) {
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
    
    public void loadPN(){
        model = new DefaultTableModel();
        model.addColumn("Mã phiếu nhập");
        model.addColumn("Mã nhân viên");
        model.addColumn("Ngày Nhập");
        model.addColumn("Tổng giá");
        tbPN.setModel(model);
        ArrayList<DTOPhieuNhap> arr = new ArrayList<DTOPhieuNhap>();
        arr = BLLpn.BLLgetDL();
        for (int i = 0; i < arr.size(); i++){
            DTOPhieuNhap sp = arr.get(i);
            String MaSP = sp.getMaPhieuNhap();
            String TenSP = sp.getMaNV();
            Date GiaBan = sp.getNgayNhap();;
            Object[] row = {MaSP, TenSP, GiaBan, TongGia(MaSP)};
            model.addRow(row);
        }
    }

    public void loadCTPN(String ma){
        ArrayList<ChiTietPhieuNhap> rs = BLLpn.BLLtimctpn(ma);
        model2.setRowCount(0);
        if (rs != null) { 
            for(ChiTietPhieuNhap cthd : rs){
                model2.addRow(new Object[]{
                     cthd.getMaPN(), cthd.getMaSP(), BLLsp.BLLtimtheomasp(cthd.getMaSP()).getTenSanPham(), BLLsp.BLLtimtheomasp(cthd.getMaSP()).getMaNCC(), cthd.getSoLuong(), cthd.getDonGia()}); 
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
        for(DTOPhieuNhap sp : BLLpn.BLLgetDL()){
            dsma.add(sp.getMaPhieuNhap());
        }
        return dsma;
    }
    
    public void maTuDong(){
        String newMaSP = tangMaSP(laydsma());
        txtMaPN.setText(newMaSP);
    }
    
    public void trangThaiBanDau(String manv){
        txtMaNV.setText(manv);
        jDateNgayNhap.setDate(null);
        txtTongGia.setText("");
        maTuDong();
    }
    
    public void trangThaiBanDau2(){
        txtPhieuNhap.setText(txtMaPN.getText().toString());
        cbbMaSP.setSelectedItem("");
        txtTenSP.setText("");
        txtNCC.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpQLPN = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPN = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaPN = new javax.swing.JTextField();
        txtTongGia = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        cbbTim = new javax.swing.JComboBox<>();
        btnCapNhat = new javax.swing.JButton();
        jDateNgayNhap = new com.toedter.calendar.JDateChooser();
        txtMaNV = new javax.swing.JTextField();
        jpCTPN = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPhieuNhap = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        btnSuaCT = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        txtNCC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbbMaSP = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTPN = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 1064));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jpQLPN.setBackground(new java.awt.Color(255, 255, 255));

        tbPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã phiếu nhập", "Mã nhà cung cấp", "Mã nhân viên", "Ngày nhập", "Tổng giá"
            }
        ));
        tbPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPN);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Mã phiếu nhập");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Mã nhân viên");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Ngày nhập");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Tổng giá");

        txtMaPN.setEditable(false);
        txtMaPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPNActionPerformed(evt);
            }
        });

        txtTongGia.setEditable(false);

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

        cbbTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã phiếu nhập", "Mã nhân viên" }));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpQLPNLayout = new javax.swing.GroupLayout(jpQLPN);
        jpQLPN.setLayout(jpQLPNLayout);
        jpQLPNLayout.setHorizontalGroup(
            jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQLPNLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpQLPNLayout.createSequentialGroup()
                            .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLPNLayout.createSequentialGroup()
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLPNLayout.createSequentialGroup()
                                    .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(27, 27, 27)))
                            .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpQLPNLayout.createSequentialGroup()
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jpQLPNLayout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMaPN)
                                        .addComponent(txtTongGia, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                                        .addComponent(jDateNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMaNV)))))
                        .addComponent(txtTim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTim, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jpQLPNLayout.setVerticalGroup(
            jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLPNLayout.createSequentialGroup()
                .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpQLPNLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jDateNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jpQLPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
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
                            .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(52, 52, 52))
        );

        jTabbedPane1.addTab("Quản lí phiếu nhập", jpQLPN);

        jpCTPN.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Mã phiếu nhập");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Mã sản phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tên sản phẩm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Số lượng ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Giá");

        txtTenSP.setEditable(false);

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
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

        txtNCC.setEditable(false);
        txtNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNCCActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Mã nhà cung cấp");

        cbbMaSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaSPActionPerformed(evt);
            }
        });

        tbCTPN.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCTPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTPNMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCTPN);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbMaSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNCC)
                            .addComponent(txtTenSP)
                            .addComponent(txtSoLuong)
                            .addComponent(txtGia)
                            .addComponent(txtPhieuNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnSuaCT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
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

        jTabbedPane1.addTab("Chi tiết phiếu nhập", jpCTPN);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTActionPerformed
        // TODO add your handling code here:
        try {
            int i = tbCTPN.getSelectedRow(); 
            ChiTietPhieuNhap sp = new ChiTietPhieuNhap();
            if (i>=0){
                if(txtMaPN.getText().trim().equals("") ||
                    cbbMaSP.getSelectedItem().equals("") ||
                    txtSoLuong.getText().trim().equals("") ||
                    txtGia.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Không được để trống thông tin!");
                }else if(Integer.parseInt(txtSoLuong.getText()) <= 0){
                    JOptionPane.showMessageDialog(null,"Số lượng phải lớn hơn 0!");
                }else if(Integer.parseInt(txtGia.getText()) <= 2000000){
                    JOptionPane.showMessageDialog(null,"Đơn giá phải trên 2.000.000!");
                }
                else{
                    sp.setMaPN(txtMaPN.getText()); 
                    sp.setMaSP(cbbMaSP.getSelectedItem().toString()); 
                    sp.setTenSP(txtTenSP.getText());
                    sp.setSoLuong(Integer.valueOf(txtSoLuong.getText())); 
                    sp.setDonGia(Float.valueOf(txtSoLuong.getText())); 
                    JOptionPane.showMessageDialog(this, BLLpn.BLLsuactpn(sp, sp.getMaPN()));
                    loadCTPN(sp.getMaPN());
                    trangThaiBanDau(MaNV);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để sửa");
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Lỗi");
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSuaCTActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        model2.setRowCount(0);
        jTabbedPane1.setSelectedIndex(1);
        String a = txtMaPN.getText();
        loadCTPN(a);
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
                case "Mã phiếu nhập":
                    String maSPCanTim = txtTim.getText();
                    DTOPhieuNhap ketQuaMaSP = BLLpn.BLLtim(maSPCanTim);
                    if (ketQuaMaSP != null){
                        model = (DefaultTableModel) tbPN.getModel();
                        model.setRowCount(0);
                        Object[] row = {ketQuaMaSP.getMaPhieuNhap(),ketQuaMaSP.getMaNV(),ketQuaMaSP.getNgayNhap(), TongGia(ketQuaMaSP.getMaPhieuNhap())};
                        model.addRow(row);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu nhập");
                    }
                    break;
                case "Mã nhân viên":
                    String ma = txtTim.getText();
                    DTOPhieuNhap ketQua = BLLpn.BLLtimtheomanv(ma);
                    if (ketQua != null){
                        model = (DefaultTableModel) tbPN.getModel();
                        model.setRowCount(0);
                        Object[] row = {ketQua.getMaPhieuNhap(),ketQua.getMaNV(),ketQua.getNgayNhap(), TongGia(ketQua.getMaPhieuNhap())};
                        model.addRow(row);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu nhập");
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
                DTOPhieuNhap ketQuaMaSach = BLLpn.BLLtim(maSachCanTim);
                if (ketQuaMaSach != null){
                    model = (DefaultTableModel) tbPN.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQuaMaSach.getMaPhieuNhap(),ketQuaMaSach.getMaNV(),ketQuaMaSach.getNgayNhap(),TongGia(ketQuaMaSach.getMaPhieuNhap())};
                    model.addRow(row);
                }else{
                    loadPN();
                }
                break;
            case 1:
                String ma = txtTim.getText();
                DTOPhieuNhap ketQua= BLLpn.BLLtimtheomanv(ma);
                if (ketQua != null){
                    model = (DefaultTableModel) tbPN.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQua.getMaPhieuNhap(),ketQua.getMaNV(),ketQua.getNgayNhap(),TongGia(ketQua.getMaPhieuNhap())};
                    model.addRow(row);
                }else{
                    loadPN();
                }
                break;
            default:
            throw new AssertionError();
        }
    }//GEN-LAST:event_txtTimKeyReleased

    private void txtNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCCActionPerformed

    private void txtMaPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPNActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        trangThaiBanDau(MaNV);
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void tbPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPNMouseClicked
        // TODO add your handling code here:
        try{
            int i = tbPN.getSelectedRow();
            if(i >= 0){
                txtMaPN.setText(model.getValueAt(i, 0).toString());
                txtMaNV.setText(model.getValueAt(i, 1).toString());
                String dateString = model.getValueAt(i, 2).toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateString);
                jDateNgayNhap.setDate(date);
                txtTongGia.setText(model.getValueAt(i, 3).toString());
            }
        }
        catch(Exception ex){
            //JOptionPane.showMessageDialog(this, ex);
            System.out.println(ex);
        }
    }//GEN-LAST:event_tbPNMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try{
            if(jDateNgayNhap.getDate() == null)
                           
                JOptionPane.showMessageDialog(null,"Vui lòng nhập lại thông tin");
            else{
                DTOPhieuNhap sp = new DTOPhieuNhap();
                sp.setMaPhieuNhap(txtMaPN.getText());
                sp.setMaNV(txtMaNV.getText().toString());
                Date d = jDateNgayNhap.getDate();
                java.sql.Date sqlDate = new java.sql.Date(d.getTime());
                sp.setNgayNhap(sqlDate);
                JOptionPane.showMessageDialog(null,BLLpn.BLLthem(sp));
                loadPN();
                trangThaiBanDau(MaNV);
            }
        }
        catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Thông tin không hợp lệ");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tbCTPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTPNMouseClicked
        // TODO add your handling code here:
        try{
            int i = tbCTPN.getSelectedRow();
            if(i >= 0){
                txtPhieuNhap.setText(model2.getValueAt(i, 0).toString());
                cbbMaSP.setSelectedItem(model2.getValueAt(i, 1).toString());
                txtNCC.setText(model2.getValueAt(i, 3).toString());
                txtTenSP.setText(model2.getValueAt(i, 2).toString());
                txtSoLuong.setText(model2.getValueAt(i, 4).toString());
                txtGia.setText(model2.getValueAt(i, 5).toString());
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_tbCTPNMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try{
            if(cbbMaSP.getSelectedItem() == null ||
                txtTenSP.getText().trim().equals("") ||
                txtSoLuong.getText().trim().equals("") ||
                txtGia.getText().trim().equals(""))
                           
                JOptionPane.showMessageDialog(null,"Vui lòng nhập lại thông tin");
            else if(Integer.parseInt(txtSoLuong.getText()) < 0){
                JOptionPane.showMessageDialog(null,"Số lượng không được âm!");
            }
            else if(Integer.parseInt(txtGia.getText()) < 2000000){
                JOptionPane.showMessageDialog(null,"Giá nhập không được dưới 2 triệu!");
            }
            else{
                ChiTietPhieuNhap sp = new ChiTietPhieuNhap();
                sp.setMaSP(cbbMaSP.getSelectedItem().toString());
                sp.setMaPN(txtPhieuNhap.getText());
                sp.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
                sp.setDonGia(Float.valueOf(txtGia.getText()));
                sp.setTenSP(txtTenSP.getText());
                System.out.println(sp.getTenSP());
                JOptionPane.showMessageDialog(null,BLLpn.BLLthemct(sp));
                loadCTPN(sp.getMaPN());
                trangThaiBanDau(MaNV);
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
        txtNCC.setText(BLLsp.BLLtimtheomasp(a).getMaNCC());
    }//GEN-LAST:event_cbbMaSPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i = tbCTPN.getSelectedRow();
        String masp;
        if(i >= 0){
            masp = model2.getValueAt(i, 0).toString();
            JOptionPane.showMessageDialog(this,BLLpn.BLLxoaCT(masp));
            loadCTPN(masp);
            trangThaiBanDau(MaNV);
        }else{
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để xóa");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            int i = tbPN.getSelectedRow(); 
            DTOPhieuNhap sp = new DTOPhieuNhap();
            if (i>=0){
                if(txtMaPN.getText().trim().equals("") ||
                    txtMaNV.getText().equals("") ||
                    jDateNgayNhap.getDate() == null){
                    JOptionPane.showMessageDialog(this, "Không được để trống thông tin khách hàng!");
                }
                else{
                    sp.setMaPhieuNhap(txtMaPN.getText()); 
                    sp.setMaNV(txtMaNV.getText());
                    Date d = jDateNgayNhap.getDate();
                    sp.setNgayNhap((java.sql.Date) d);
                    JOptionPane.showMessageDialog(this, BLLpn.BLLsua(sp));
                    loadPN();
                    trangThaiBanDau(MaNV);
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

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int i = tbPN.getSelectedRow();
        String masp;
        if(i >= 0){
            masp = model.getValueAt(i, 0).toString();
            JOptionPane.showMessageDialog(this,BLLpn.BLLxoa(masp));
            loadCTPN(masp);
            trangThaiBanDau(MaNV);
        }else{
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLPN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLPN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLPN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLPN_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLPN_GUI().setVisible(true);
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
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JComboBox<String> cbbTim;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpCTPN;
    private javax.swing.JPanel jpQLPN;
    private javax.swing.JTable tbCTPN;
    private javax.swing.JTable tbPN;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPN;
    private javax.swing.JTextField txtNCC;
    private javax.swing.JTextField txtPhieuNhap;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongGia;
    // End of variables declaration//GEN-END:variables
}
