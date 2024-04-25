/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.BLLHoaDon;
import BLL.BLLKhachHang;
import BLL.BLLNhanVien;
import DAL.DALKhuyenMai;
import DTO.DTOKhuyenMai;
import DAL.DALHoaDon;
import DTO.ChiTietHoaDon;
import DTO.DTOHoaDon;
import DTO.DTOKhachHang;
import DTO.DTONhanVien;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class QLHD_GUI extends javax.swing.JFrame {
    private ArrayList<DTOKhuyenMai> listKM;
    private ArrayList<DTOHoaDon> listHD;
    private ArrayList<ChiTietHoaDon> listCTHD, listCTHD_Ma;
    private ArrayList<String> danhSachMaKhuyenMai;
    DefaultTableModel model, model2;
    BLLHoaDon bllHoaDon = new BLLHoaDon();
    String maHD;
    BLLNhanVien BLLnv = new BLLNhanVien();
    BLLKhachHang BLLkh = new BLLKhachHang();
    
    /**
     * Creates new form QLHD_GUI
     */
    public QLHD_GUI() {
        initComponents();
        listKM = new DALKhuyenMai().getallkmlist();
        listHD = new DALHoaDon().getallHDlist();
        danhSachMaKhuyenMai = new ArrayList<>();
        for (DTOKhuyenMai km : listKM) {
            danhSachMaKhuyenMai.add(km.getMaKhuyenMai());
        }
        capNhatComboBoxMaKhuyenMai();
        listCTHD = new DALHoaDon().getallcthdlist();
        model = (DefaultTableModel) tableQLHD.getModel();
        model2 = (DefaultTableModel) tableCTHD.getModel();
        showTable1();
        txtGia_CT.enable(false);
        txtMaHD_CT.enable(false);
        txtMaSP_CT.enable(false);
        txtTien_CT.enable(false);
        trangThaiBanDau();
        maTuDong();
        ArrayList<String> danhSachMaNV = dsmnv();
        cbbMaNV.setModel(new DefaultComboBoxModel<>(danhSachMaNV.toArray(new String[0])));
        ArrayList<String> danhSachMaKH = dsmkh();
        cbbMaKH.setModel(new DefaultComboBoxModel<>(danhSachMaKH.toArray(new String[0])));
    }
    
    public ArrayList<String> dsmnv(){
        ArrayList<String> arr = new ArrayList<String>();
        for(DTONhanVien sp : BLLnv.BLLgetDL()){
            if(arr.contains(sp.getMaNV())){
                continue;
            }
            arr.add(sp.getMaNV());
        }
        sortArrayList1(arr);
        return arr;
    }
    
    public ArrayList<String> dsmkh(){
        ArrayList<String> arr = new ArrayList<String>();
        for(DTOKhachHang sp : BLLkh.BLLgetDL()){
            if(arr.contains(sp.getMaKH())){
                continue;
            }
            arr.add(sp.getMaKH());
        }
        sortArrayList2(arr);
        return arr;
    }
    
    public static void sortArrayList1(ArrayList<String> arrayList) {
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
    
    public static void sortArrayList2(ArrayList<String> arrayList) {
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Kiểm tra xem chuỗi có đúng định dạng không
                if (!s1.matches("KH\\d{3}") || !s2.matches("KH\\d{3}")) {
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
    
    public void capNhatComboBoxMaKhuyenMai() {
        // Xóa tất cả các mục cũ trong JComboBox
        jComboBox1.removeAllItems();
        // Thêm tất cả các mã khuyến mãi vào JComboBox
        for (String maKhuyenMai : danhSachMaKhuyenMai) {
            jComboBox1.addItem(maKhuyenMai);
        }
    }
    int i = 1;
    public void showTable1(){
        for(DTOHoaDon hd : listHD){
            model.addRow(new Object[]{
                i++, hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getMaNhanVien(), hd.getThoiGianTao(), hd.getTongSoLuong(),hd.getTongGia(),hd.getMaKhuyenMai(),hd.getThanhTien()});
        }
    }
    int j = 1;
    public void showTable2(){
        for(ChiTietHoaDon cthd : listCTHD){
            model2.addRow(new Object[]{
                j++, cthd.getMaHD(), cthd.getMaSP(), cthd.getGia(),cthd.getSoLuong(),cthd.getThanhTien()});
        }
    }
    
    public static String tangMaHD(ArrayList<String> danhSachMaSP) {
        String maxMaSP = ""; 
        for (String maSP : danhSachMaSP) {
            if (maSP.compareTo(maxMaSP) > 0) {
                maxMaSP = maSP;
            }
        }
        if (maxMaSP == null || maxMaSP.isEmpty()) {
            return "HD001"; // Giả sử mã đầu tiên là "SP001"
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
        for(DTOHoaDon sp : bllHoaDon.BLLgetDL()){
            dsma.add(sp.getMaHoaDon());
        }
        return dsma;
    }
    
    public void maTuDong(){
        String newMaSP = tangMaHD(laydsma());
        txtMaHD.setText(newMaSP);
    }
    
    public void trangThaiBanDau(){
        cbbMaKH.setSelectedItem(null);
        cbbMaNV.setSelectedItem(null);
        jDNgayTao.setDate(null);
        txtTongSL.setText("");
        txtTongGia.setText("0");
        jComboBox1.setSelectedItem(null);
        txtThanhTien.setText("");
        maTuDong();
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
        jpQLHD = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableQLHD = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        txtTongGia = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtTongSL = new javax.swing.JTextField();
        btnChiTiet = new javax.swing.JButton();
        btnSuaHD = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jDNgayTao = new com.toedter.calendar.JDateChooser();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        cbbTim = new javax.swing.JComboBox<>();
        btnCapNhat = new javax.swing.JButton();
        cbbMaNV = new javax.swing.JComboBox<>();
        cbbMaKH = new javax.swing.JComboBox<>();
        jpCTHD = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtMaHD_CT = new javax.swing.JTextField();
        txtMaSP_CT = new javax.swing.JTextField();
        txtGia_CT = new javax.swing.JTextField();
        txtSL_CT = new javax.swing.JTextField();
        txtTien_CT = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý hoá đơn");
        setPreferredSize(new java.awt.Dimension(1400, 770));

        jpQLHD.setBackground(new java.awt.Color(255, 255, 255));
        jpQLHD.setPreferredSize(new java.awt.Dimension(1400, 770));
        jpQLHD.setRequestFocusEnabled(false);

        tableQLHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hoá đơn", "Mã khách hàng", "Mã nhân viên", "Ngày tạo", "Tổng số lượng", "Tổng giá", "Mã khuyến mãi", "Thành tiền"
            }
        ));
        tableQLHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableQLHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableQLHD);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Mã hoá đơn");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Mã khách hàng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Mã nhân viên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Ngày tạo");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Tổng số lượng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Tổng giá");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Mã khuyến mãi");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Thành tiền");

        txtMaHD.setEditable(false);

        txtTongGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongGiaActionPerformed(evt);
            }
        });

        jComboBox1.setName(""); // NOI18N

        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChiTiet.setForeground(new java.awt.Color(0, 102, 102));
        btnChiTiet.setText("CHI TIẾT");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        btnSuaHD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSuaHD.setForeground(new java.awt.Color(0, 102, 102));
        btnSuaHD.setText("SỬA");
        btnSuaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaHDActionPerformed(evt);
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

        cbbTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hóa đơn", "Mã khách hàng ", "Mã nhân viên" }));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        cbbMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMaKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpQLHDLayout = new javax.swing.GroupLayout(jpQLHD);
        jpQLHD.setLayout(jpQLHDLayout);
        jpQLHDLayout.setHorizontalGroup(
            jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jpQLHDLayout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpQLHDLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpQLHDLayout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpQLHDLayout.createSequentialGroup()
                            .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpQLHDLayout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jDNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpQLHDLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jpQLHDLayout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTongSL))
                                .addGroup(jpQLHDLayout.createSequentialGroup()
                                    .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtThanhTien)
                                        .addComponent(jComboBox1, 0, 480, Short.MAX_VALUE)
                                        .addComponent(txtTongGia)))))
                        .addGroup(jpQLHDLayout.createSequentialGroup()
                            .addComponent(btnTim)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(72, 72, 72)
                            .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnSuaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );
        jpQLHDLayout.setVerticalGroup(
            jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLHDLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongSL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpQLHDLayout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                    .addGroup(jpQLHDLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpQLHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Quản lí hoá đơn", jpQLHD);

        jpCTHD.setBackground(new java.awt.Color(255, 255, 255));

        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hoá đơn", "Mã sản phẩm", "Giá", "Số lượng", "Thành tiền"
            }
        ));
        tableCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCTHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCTHD);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Mã hoá đơn");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Mã sản phẩm");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Giá thành");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Số lượng");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Thành tiền");

        txtSL_CT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSL_CTActionPerformed(evt);
            }
        });

        txtTien_CT.setText("jTextField5");

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 102, 102));
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpCTHDLayout = new javax.swing.GroupLayout(jpCTHD);
        jpCTHD.setLayout(jpCTHDLayout);
        jpCTHDLayout.setHorizontalGroup(
            jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCTHDLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addGroup(jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpCTHDLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaHD_CT, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpCTHDLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSP_CT))
                    .addGroup(jpCTHDLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGia_CT))
                    .addGroup(jpCTHDLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSL_CT))
                    .addGroup(jpCTHDLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTien_CT))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCTHDLayout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(228, 228, 228)))
                .addGap(45, 45, 45))
        );
        jpCTHDLayout.setVerticalGroup(
            jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jpCTHDLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHD_CT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSP_CT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGia_CT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSL_CT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jpCTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTien_CT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        jTabbedPane1.addTab("Chi tiết hoá đơn", jpCTHD);

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

    private void txtTongGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongGiaActionPerformed
int current = 0;
    private void tableQLHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableQLHDMouseClicked
        // TODO add your handling code here:
        current = tableQLHD.getSelectedRow();
        displayQLHD(current);
    }//GEN-LAST:event_tableQLHDMouseClicked

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        String maHD = txtMaHD.getText();
        BLLHoaDon bllHoaDon = new BLLHoaDon();
        ArrayList<ChiTietHoaDon> rs = bllHoaDon.BLLgetCTHD(maHD);
        if (rs != null) {
            model2.setRowCount(0);
            j=1;
            model = (DefaultTableModel) tableCTHD.getModel();
            model.setRowCount(0);
            for(ChiTietHoaDon cthd : rs){
                System.out.println(cthd.getMaHD());
                model2.addRow(new Object[]{
                    j++, cthd.getMaHD(), cthd.getMaSP(), cthd.getGia(), cthd.getSoLuong(), cthd.getThanhTien()}); 
            }
        }
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnSuaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaHDActionPerformed
        // TODO add your handling code here:
         DTOHoaDon hd = new DTOHoaDon();
         int selectedRow = tableQLHD.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
        hd.setMaHoaDon(txtMaHD.getText());
        String maHD = txtMaHD.getText();
        hd.setMaKhachHang((String) cbbMaKH.getSelectedItem());
        hd.setMaNhanVien(cbbMaNV.getSelectedItem().toString());

        java.util.Date tgkt = jDNgayTao.getDate();
        if (tgkt == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày bắt đầu và ngày kết thúc hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.sql.Date sql_tgkt = new java.sql.Date(tgkt.getTime());
        hd.setThoiGianTao(sql_tgkt);

        int tongSL = 0;
        try {
            tongSL = Integer.parseInt(txtTongSL.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        hd.setTongSoLuong(tongSL);

        double tongGia = 0.0;
            try {
                tongGia = Double.parseDouble(txtTongGia.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng tổng giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; 
            }
               
                hd.setTongGia(tongGia);
                
        String maKhuyenMai = jComboBox1.getSelectedItem().toString();
        if (maKhuyenMai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        hd.setMaKhuyenMai(maKhuyenMai);
        double thanhTien = 0.0;
            try {
                thanhTien = Double.parseDouble(txtThanhTien.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng tổng giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; 
            }
               
                hd.setThanhTien(thanhTien);
        double thanhtien = 0.0;
        float giatri = 0.0f;
        for (DTOKhuyenMai km : listKM) {
            String makm = km.getMaKhuyenMai();
            if (maKhuyenMai.equals(makm)) {
            giatri = km.getGiaTri();
                System.out.print(giatri);
            }
        }
      thanhtien = tongGia - ((giatri * tongGia)/100);
      hd.setThanhTien(thanhtien);

        String result = bllHoaDon.BLLsua(hd, maHD);
        if (result.equals("Sửa thành công!")) {
            DefaultTableModel model = (DefaultTableModel) tableQLHD.getModel();
            model.removeRow(selectedRow); 
            model.addRow(new Object[] { i++, hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getMaNhanVien(), hd.getThoiGianTao(), hd.getTongSoLuong(), hd.getTongGia(), hd.getMaKhuyenMai(), hd.getThanhTien()});
            tableQLHD.setModel(model);
            JOptionPane.showMessageDialog(null, result, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, result, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSuaHDActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        DTOHoaDon hd = new DTOHoaDon();
    int selectedRow = tableCTHD.getSelectedRow();
    hd.setMaHoaDon(txtMaHD.getText());
    int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa hoá đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.YES_OPTION) {
        BLLHoaDon bllHoaDon = new BLLHoaDon();
        String result = bllHoaDon.BLLxoa(hd);
        tableQLHD.getModel();
        model.removeRow(selectedRow);
        tableQLHD.setModel(model);
        JOptionPane.showMessageDialog(null, result, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    } else {
        // Hiển thị thông báo nếu người dùng chọn "No"
        JOptionPane.showMessageDialog(null, "Đã hủy xóa hoá đơn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSL_CTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSL_CTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSL_CTActionPerformed
    int current1 = 0;
    private void tableCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCTHDMouseClicked
        // TODO add your handling code here:
        try{
            int i = tableCTHD.getSelectedRow();
            if(i >= 0){
                txtMaHD_CT.setText(model.getValueAt(i, 1).toString());
                txtMaSP_CT.setText(model.getValueAt(i, 2).toString());
                txtSL_CT.setText(model.getValueAt(i, 4).toString());
                txtGia_CT.setText(model.getValueAt(i, 3).toString());
                txtTien_CT.setText(model.getValueAt(i, 5).toString());
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }
//        current1 = tableCTHD.getSelectedRow();
//        displayCTHD(current1);
    }//GEN-LAST:event_tableCTHDMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        ChiTietHoaDon cthd = new ChiTietHoaDon();
        cthd.setMaHD(txtMaHD_CT.getText());
        String maHD = txtMaHD_CT.getText();
        int selectedRow = tableCTHD.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int SL = 0;
        try {
            SL = Integer.parseInt(txtTongSL.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        cthd.setSoLuong(SL);
        BLLHoaDon bllHoaDon = new BLLHoaDon();
        String result = bllHoaDon.BLLsuacthd(cthd, maHD);
        if (result.equals("Sửa thành công!")) {
            DefaultTableModel model = (DefaultTableModel) tableCTHD.getModel();
            model.removeRow(selectedRow); 
            model.addRow(new Object[] { i++, cthd.getMaHD(),  cthd.getSoLuong(), cthd.getGia(),cthd.getThanhTien()});
            tableCTHD.setModel(model);
            JOptionPane.showMessageDialog(null, result, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, result, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if(txtTim.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu muốn tìm kiếm");
        }else if(cbbTim.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kiểu tìm kiếm");
        }else{
            String selectedValue = cbbTim.getSelectedItem().toString();
            switch (selectedValue) {
                case "Mã hóa đơn":
                    String maSPCanTim = txtTim.getText();
                    DTOHoaDon ketQuaMaSP = bllHoaDon.BLLtim(maSPCanTim);
                    if (ketQuaMaSP != null){
                        model = (DefaultTableModel) tableQLHD.getModel();
                        model.setRowCount(0);
                        Object[] row = {ketQuaMaSP.getMaHoaDon(),ketQuaMaSP.getMaKhachHang(),ketQuaMaSP.getMaNhanVien(),ketQuaMaSP.getThoiGianTao(),ketQuaMaSP.getTongSoLuong(),ketQuaMaSP.getTongGia(),ketQuaMaSP.getMaKhuyenMai(),ketQuaMaSP.getThanhTien()};
                        model.addRow(row);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã hóa đơn");
                    }
                    break;
                case "Mã khách hàng":
                    String tenSachCanTim = txtTim.getText();
                    DTOHoaDon ketQuaTenSach = bllHoaDon.BLLtimtheomakh(tenSachCanTim);
                    if (ketQuaTenSach != null){
                        model = (DefaultTableModel) tableQLHD.getModel();
                        model.setRowCount(0);
                        Object[] row = {ketQuaTenSach.getMaHoaDon(),ketQuaTenSach.getMaKhachHang(),ketQuaTenSach.getMaNhanVien(),ketQuaTenSach.getThoiGianTao(),ketQuaTenSach.getTongSoLuong(),ketQuaTenSach.getTongGia(),ketQuaTenSach.getMaKhuyenMai(),ketQuaTenSach.getThanhTien()};
                        model.addRow(row);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã khách hàng");
                    }
                    break;
                case "Mã nhân viên":
                    String theLoaiCanTim = txtTim.getText();
                    DTOHoaDon ketQuaTL = bllHoaDon.BLLtimtheomanv(theLoaiCanTim);
                    if (ketQuaTL != null){
                        model = (DefaultTableModel) tableQLHD.getModel();
                        model.setRowCount(0);
                        Object[] row = {ketQuaTL.getMaHoaDon(),ketQuaTL.getMaKhachHang(),ketQuaTL.getMaNhanVien(),ketQuaTL.getThoiGianTao(),ketQuaTL.getTongSoLuong(),ketQuaTL.getTongGia(),ketQuaTL.getMaKhuyenMai(),ketQuaTL.getThanhTien()};
                        model.addRow(row);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã khách hàng");
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
                DTOHoaDon ketQuaTL = bllHoaDon.BLLtim(maSachCanTim);
                if (ketQuaTL != null){
                    model = (DefaultTableModel) tableQLHD.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQuaTL.getMaHoaDon(),ketQuaTL.getMaKhachHang(),ketQuaTL.getMaNhanVien(),ketQuaTL.getThoiGianTao(),ketQuaTL.getTongSoLuong(),ketQuaTL.getTongGia(),ketQuaTL.getMaKhuyenMai(),ketQuaTL.getThanhTien()};
                    model.addRow(row);
                }else{
                    showTable1();
                }
                break;
            case 1:
                String tenSachCanTim = txtTim.getText().toString();
                DTOHoaDon ketQuaTenSach = bllHoaDon.BLLtimtheomakh(tenSachCanTim);
                if (ketQuaTenSach != null){
                    model = (DefaultTableModel) tableQLHD.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQuaTenSach.getMaHoaDon(),ketQuaTenSach.getMaKhachHang(),ketQuaTenSach.getMaNhanVien(),ketQuaTenSach.getThoiGianTao(),ketQuaTenSach.getTongSoLuong(),ketQuaTenSach.getTongGia(),ketQuaTenSach.getMaKhuyenMai(),ketQuaTenSach.getThanhTien()};
                    model.addRow(row);
                }else{
                    showTable1();
                }
                break;
            case 2:
                String theLoaiCanTim = txtTim.getText();
                DTOHoaDon ketQua = bllHoaDon.BLLtimtheomanv(theLoaiCanTim);
                if (ketQua != null){
                    model = (DefaultTableModel) tableQLHD.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQua.getMaHoaDon(),ketQua.getMaKhachHang(),ketQua.getMaNhanVien(),ketQua.getThoiGianTao(),ketQua.getTongSoLuong(),ketQua.getTongGia(),ketQua.getMaKhuyenMai(),ketQua.getThanhTien()};
                    model.addRow(row);
                }else{
                    showTable1();
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
    public void displayQLHD(int i){
        DTOHoaDon hd = listHD.get(i);    
        txtMaHD.setText(hd.maHoaDon);
        cbbMaKH.setSelectedItem(hd.maKhachHang);
        cbbMaNV.setSelectedItem(hd.maNhanVien);
        jDNgayTao.setDate(hd.thoiGianTao);
        txtTongSL.setText(String.valueOf(hd.tongSoLuong));
        txtTongGia.setText(String.valueOf(hd.tongGia));
        jComboBox1.setSelectedItem(hd.maKhuyenMai);
        txtThanhTien.setText(String.valueOf(hd.thanhTien));
    }
    public void displayCTHD(int i){
        ChiTietHoaDon hd = listCTHD.get(i);    
        txtMaHD_CT.setText(hd.MaHD);
        txtMaSP_CT.setText(hd.MaSP);
        txtSL_CT.setText(String.valueOf(hd.soLuong));
        txtGia_CT.setText(String.valueOf(hd.Gia));
        txtTien_CT.setText(String.valueOf(hd.ThanhTien));
    }
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
            java.util.logging.Logger.getLogger(QLHD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLHD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLHD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLHD_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLHD_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaHD;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbMaKH;
    private javax.swing.JComboBox<String> cbbMaNV;
    private javax.swing.JComboBox<String> cbbTim;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDNgayTao;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpCTHD;
    private javax.swing.JPanel jpQLHD;
    private javax.swing.JTable tableCTHD;
    private javax.swing.JTable tableQLHD;
    private javax.swing.JTextField txtGia_CT;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHD_CT;
    private javax.swing.JTextField txtMaSP_CT;
    private javax.swing.JTextField txtSL_CT;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTien_CT;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongGia;
    private javax.swing.JTextField txtTongSL;
    // End of variables declaration//GEN-END:variables
}
