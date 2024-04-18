/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.BLLSanPham;
import DTO.ChiTietSanPham;
import DTO.DTOSanPham;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon; 
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QLSP_GUI extends javax.swing.JFrame {
    String maSP,maNCC;
    int soLuong;
    float gia;
    DefaultTableModel model;
    BLLSanPham BLLsp = new BLLSanPham();
    String duongdananh = "";
    public String anhlocal = "C:\\Users\\HP\\Desktop\\QuanLyBanDienThoai\\src\\IMG\\Sản phẩm";

    /**
     * Creates new form QLSP_GUI
     */
    public QLSP_GUI() {
        initComponents();
        setLocationRelativeTo(null);
        loadSP();
        maTuDong();
        trangThaiBanDau();
        ArrayList<String> danhSachMaNCC = dsmncc();
        cbbMaNCC.setModel(new DefaultComboBoxModel<>(danhSachMaNCC.toArray(new String[0])));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT); // Align values to the right

        // Set the renderer for the column containing double values
        tbSanPham.getColumnModel().getColumn(3).setCellRenderer(renderer);
    }
    
    public ArrayList<String> dsmncc(){
        ArrayList<String> arr = new ArrayList<String>();
        for(DTOSanPham sp : BLLsp.BLLgetDL()){
            if(arr.contains(sp.getMaNCC())){
                continue;
            }
            arr.add(sp.getMaNCC());
        }
        sortArrayList(arr);
        return arr;
    }
    
    public static void sortArrayList(ArrayList<String> arrayList) {
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Kiểm tra xem chuỗi có đúng định dạng không
                if (!s1.matches("NCC\\d{3}") || !s2.matches("NCC\\d{3}")) {
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
    
    public void loadSP(){
        model = new DefaultTableModel();
        model.addColumn("Mã Sản Phẩm");
        model.addColumn("Tên Sản Phẩm");
        model.addColumn("Giá Bán");
        model.addColumn("Giá Nhập");
        model.addColumn("Số lượng");
        model.addColumn("Mã Nhà Cung Cấp");
        model.addColumn("Bảo Hành");
        model.addColumn("Ảnh");
        tbSanPham.setModel(model);
        ArrayList<DTOSanPham> arr = new ArrayList<DTOSanPham>();
        arr = BLLsp.BLLgetDL();
        for (int i = 0; i < arr.size(); i++){
            DTOSanPham sp = arr.get(i);
            String MaSP = sp.getMaSanPham();
            String TenSP = sp.getTenSanPham();
            int GiaBan = sp.getGiaBan();
            int GiaNhap = sp.getGiaNhap();
            int SoLuong = sp.getSoLuong();
            String MaNCC = sp.getMaNCC();
            int BH = sp.getBaoHanh();
            String anh = sp.getImg();
            Object[] row = {MaSP, TenSP, GiaBan, GiaNhap, SoLuong, MaNCC, BH, anh};
            model.addRow(row);
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
            return "SP001"; // Giả sử mã đầu tiên là "SP001"
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
        for(DTOSanPham sp : BLLsp.BLLgetDL()){
            dsma.add(sp.getMaSanPham());
        }
        return dsma;
    }
    
    public void maTuDong(){
        String newMaSP = tangMaSP(laydsma());
        txtMaSP.setText(newMaSP);
    }
    
    public void trangThaiBanDau(){
        txtAnh.setText("");
        txtTenSP.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("0");
        cbbMaNCC.setSelectedItem(null);
        txtBaoHanh.setText("");
        jlbAnh.setIcon(null);
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
        jpCTSP = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtTenSP2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtManHinh = new javax.swing.JTextField();
        txtCamera = new javax.swing.JTextField();
        txtROM = new javax.swing.JTextField();
        txtRAM = new javax.swing.JTextField();
        txtPin = new javax.swing.JTextField();
        txtThietKe = new javax.swing.JTextField();
        txtTrongLuong = new javax.swing.JTextField();
        txtMau = new javax.swing.JTextField();
        txtIMEI = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnSua2 = new javax.swing.JButton();
        jlbAnh2 = new javax.swing.JLabel();
        txtMaSP2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jpQLSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        jlbAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbbMaNCC = new javax.swing.JComboBox<>();
        txtBaoHanh = new javax.swing.JTextField();
        txtAnh = new javax.swing.JTextField();
        btnAnh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        btnCapNhat = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        cbbSapXep = new javax.swing.JComboBox<>();
        txtTim = new javax.swing.JTextField();
        cbbTim = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtTenSP2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTenSP2.setText("Tên sản phẩm");
        txtTenSP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSP2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Màn hình");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Camera");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Rom");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Ram");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Pin");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Thiết kế");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Trọng lượng");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Màu");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("IMEI");

        txtPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPinActionPerformed(evt);
            }
        });

        txtTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrongLuongActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(0, 102, 102));
        btnLuu.setText("LƯU");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua2.setForeground(new java.awt.Color(0, 102, 102));
        btnSua2.setText("SỬA");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        jlbAnh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iphone-15-pro-max-natural-titanium-pure-back-iphone-15-pro-max-natural-titanium-pure-front-2up-scree.jpg"))); // NOI18N

        txtMaSP2.setEditable(false);
        txtMaSP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSP2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Mã sản phẩm");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(293, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIMEI, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThietKe, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtROM, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(314, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(747, 747, 747)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(btnSua2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txtROM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtThietKe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jlbAnh2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIMEI, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165))
        );

        javax.swing.GroupLayout jpCTSPLayout = new javax.swing.GroupLayout(jpCTSP);
        jpCTSP.setLayout(jpCTSPLayout);
        jpCTSPLayout.setHorizontalGroup(
            jpCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpCTSPLayout.setVerticalGroup(
            jpCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Chi tiết sản phẩm", jpCTSP);

        jpQLSP.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Mã sản phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Tên sản phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Giá bán");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Giá nhập");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Số lượng");

        txtSoLuong.setEditable(false);

        txtMaSP.setEditable(false);

        jlbAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iphone-15-pro-max-natural-titanium-pure-back-iphone-15-pro-max-natural-titanium-pure-front-2up-scree.jpg"))); // NOI18N

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 102, 102));
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 102, 102));
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 102, 102));
        btnXoa.setText("XOÁ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChiTiet.setForeground(new java.awt.Color(0, 102, 102));
        btnChiTiet.setText("CHI TIẾT");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Bảo hành");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Mã NCC");

        cbbMaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaNCCActionPerformed(evt);
            }
        });

        txtAnh.setText("Link ảnh");

        btnAnh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAnh.setForeground(new java.awt.Color(0, 102, 102));
        btnAnh.setText("Thêm hình");
        btnAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhActionPerformed(evt);
            }
        });

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSanPham);

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(0, 102, 102));
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim.setForeground(new java.awt.Color(0, 102, 102));
        btnTim.setText("Tìm sản phẩm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        cbbSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá bán", "Giá Nhập", "Bảo Hành" }));
        cbbSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSapXepActionPerformed(evt);
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

        cbbTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã sản phẩm", "Tên sản phẩm ", "Mã nhà cung cấp" }));

        javax.swing.GroupLayout jpQLSPLayout = new javax.swing.GroupLayout(jpQLSP);
        jpQLSP.setLayout(jpQLSPLayout);
        jpQLSPLayout.setHorizontalGroup(
            jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQLSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpQLSPLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpQLSPLayout.createSequentialGroup()
                        .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpQLSPLayout.createSequentialGroup()
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMaNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jpQLSPLayout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpQLSPLayout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpQLSPLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(44, 84, Short.MAX_VALUE)
                        .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpQLSPLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhat)
                .addGap(324, 324, 324))
        );
        jpQLSPLayout.setVerticalGroup(
            jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpQLSPLayout.createSequentialGroup()
                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpQLSPLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpQLSPLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpQLSPLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)))))
                .addGap(48, 48, 48)
                .addGroup(jpQLSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(233, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách sản phẩm", jpQLSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1745, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenSP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSP2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSP2ActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
        txtMaSP2.setText(txtMaSP.getText());
        txtTenSP2.setText(txtTenSP.getText());
        String fileimg = BLLsp.BLLtimtheomasp(txtMaSP.getText()).getImg();
        ImageIcon imageIcon = new ImageIcon(fileimg);
        Image scaledImage = imageIcon.getImage().getScaledInstance(jlbAnh.getWidth(), jlbAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        jlbAnh2.setIcon(scaledImageIcon);
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try{
            if(txtMaSP.getText().trim().equals("") ||
                txtTenSP.getText().trim().equals("") ||
                txtGiaNhap.getText().trim().equals("") ||
                txtGiaBan.getText().trim().equals("")||
                cbbMaNCC.getSelectedItem().equals("")||
                txtBaoHanh.getText().equals("")||
                txtAnh.getText().trim().equals(""))
                           
                JOptionPane.showMessageDialog(null,"Vui lòng nhập lại thông tin");
            else if(txtGiaNhap.getText().equals(txtGiaBan.getText()) || 
                    Integer.parseInt(txtGiaBan.getText()) > Integer.parseInt(txtGiaBan.getText())){
                JOptionPane.showMessageDialog(null,"Giá bán phải lớn hơn giá nhập!");
            }
            else if(Integer.parseInt(txtBaoHanh.getText()) < 0){
                JOptionPane.showMessageDialog(null,"Bảo hành không được âm!");
            }
            else if(Integer.parseInt(txtBaoHanh.getText()) < 2000000){
                JOptionPane.showMessageDialog(null,"Giá nhập không được dưới 2 triệu!");
            }
            else{
                DTOSanPham sp = new DTOSanPham();
                sp.setMaSanPham(txtMaSP.getText());
                sp.setTenSanPham(txtTenSP.getText());
                sp.setGiaNhap(Integer.parseInt(txtGiaNhap.getText()));
                sp.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
                sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                sp.setBaoHanh(Integer.parseInt(txtBaoHanh.getText()));
                sp.setMaNCC(cbbMaNCC.getSelectedItem().toString());
                sp.setImg(txtAnh.getText());
                JOptionPane.showMessageDialog(null,BLLsp.BLLthem(sp));
                loadSP();
                trangThaiBanDau();
            }
        }
        catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Thông tin không hợp lệ");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void cbbMaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMaNCCActionPerformed

    private void txtPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPinActionPerformed

    private void txtTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrongLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrongLuongActionPerformed

    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon myImage = new ImageIcon(ImagePath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(jlbAnh.getWidth(), jlbAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    private void btnAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhActionPerformed
        try{
            JFileChooser f = new JFileChooser(anhlocal);
            f.setDialogTitle("Mở file");
            f.showOpenDialog(null);
            File ftenanh = f.getSelectedFile();
            duongdananh = ftenanh.getAbsolutePath();
            if(duongdananh != null){
                jlbAnh.setIcon(ResizeImage(String.valueOf(duongdananh)));
                txtAnh.setText(duongdananh);
            }
            else{
                JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
            } 
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnhActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        try{
            int i = tbSanPham.getSelectedRow();
            if(i >= 0){
                txtMaSP.setText(model.getValueAt(i, 0).toString());
                txtTenSP.setText(model.getValueAt(i, 1).toString());
                txtGiaBan.setText(model.getValueAt(i, 2).toString());
                txtGiaNhap.setText(model.getValueAt(i, 3).toString());
                txtSoLuong.setText(model.getValueAt(i, 4).toString());
                txtBaoHanh.setText(model.getValueAt(i, 6).toString());
                cbbMaNCC.setSelectedItem(model.getValueAt(i, 5).toString());
                String fileimg = (String) model.getValueAt(i, 7).toString().trim();
                ImageIcon imageIcon = new ImageIcon(fileimg);
                Image scaledImage = imageIcon.getImage().getScaledInstance(jlbAnh.getWidth(), jlbAnh.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                txtAnh.setText((String) model.getValueAt(i, 7).toString().trim());
                jlbAnh.setIcon(scaledImageIcon);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            int i = tbSanPham.getSelectedRow(); 
            DTOSanPham sp = new DTOSanPham();
            if (i>=0){
                if(txtMaSP.getText().trim().equals("") ||
                    txtTenSP.getText().trim().equals("") ||
                    txtGiaNhap.getText().trim().equals("") ||
                    txtGiaBan.getText().trim().equals("")||
                    cbbMaNCC.getSelectedItem().equals("")||
                    txtBaoHanh.getText().equals("")||
                    txtAnh.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(this, "Không được để trống thông tin sản phẩm!");
                }
                else if(txtGiaNhap.getText().equals(txtGiaBan.getText()) || 
                        Integer.parseInt(txtGiaBan.getText()) < Integer.parseInt(txtGiaNhap.getText())){
                    JOptionPane.showMessageDialog(null,"Giá bán phải lớn hơn giá nhập!");
                }
                else if((Integer.parseInt(txtBaoHanh.getText())) <= 0){
                    JOptionPane.showMessageDialog(null,"Bảo hành không được bé hơn hoặc bằng 0!");
                }
                else if((Integer.parseInt(txtSoLuong.getText())) <= 0){
                    JOptionPane.showMessageDialog(null,"Số lượng không được bé hơn hoặc bằng 0!");
                }
                else{
                    sp.setMaSanPham(txtMaSP.getText()); 
                    sp.setTenSanPham(txtTenSP.getText());
                    sp.setGiaNhap(Integer.parseInt(txtGiaNhap.getText()));
                    sp.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
                    sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                    sp.setMaNCC((cbbMaNCC.getSelectedItem()).toString());
                    sp.setBaoHanh(Integer.parseInt(txtBaoHanh.getText()));
                    sp.setImg(txtAnh.getText());
                    JOptionPane.showMessageDialog(this, BLLsp.BLLsua(sp));
                    loadSP();
                    trangThaiBanDau();
                }
            }else{
                JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để sửa");
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Lỗi");
            JOptionPane.showMessageDialog(this, e);
            System.out.println(e);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int i = tbSanPham.getSelectedRow();
        String masp;
        if(i >= 0){
            masp = model.getValueAt(i, 0).toString();
            JOptionPane.showMessageDialog(this,BLLsp.BLLxoa(masp));
            loadSP();
            trangThaiBanDau();
        }else{
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng để xóa");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        loadSP();
        trangThaiBanDau();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if(txtTim.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu muốn tìm kiếm");
        }else if(cbbTim.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kiểu tìm kiếm");
        }else{
            String selectedValue = cbbTim.getSelectedItem().toString();
            switch (selectedValue) {
                case "Mã sản phẩm":
                    String maSPCanTim = txtTim.getText();
                    DTOSanPham ketQuaMaSP = BLLsp.BLLtimtheomasp(maSPCanTim);
                    if (ketQuaMaSP != null){
                        model = (DefaultTableModel) tbSanPham.getModel();
                        model.setRowCount(0);
                        Object[] row = {ketQuaMaSP.getMaSanPham(),ketQuaMaSP.getTenSanPham(),ketQuaMaSP.getGiaBan(),ketQuaMaSP.getGiaNhap(),ketQuaMaSP.getSoLuong(),ketQuaMaSP.getMaNCC(),ketQuaMaSP.getBaoHanh(),ketQuaMaSP.getImg()};
                        model.addRow(row);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm");
                    }
                    break;
                case "Tên sản phẩm":
                    String tenSachCanTim = txtTim.getText();
                    ArrayList<DTOSanPham> ketQuaTenSach = BLLsp.BLLtimtheoten(tenSachCanTim);
                    if (ketQuaTenSach.size() > 0){
                        model = (DefaultTableModel) tbSanPham.getModel();
                        model.setRowCount(0);
                        for(DTOSanPham s : ketQuaTenSach){
                            Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                            model.addRow(row);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy tên sản phẩm");
                    }
                    break;
                case "Mã nhà cung cấp":
                    String theLoaiCanTim = txtTim.getText();
                    ArrayList<DTOSanPham> ketQuaTL = BLLsp.BLLtimtheomancc(theLoaiCanTim);
                    if (ketQuaTL.size() > 0){
                        model = (DefaultTableModel) tbSanPham.getModel();
                        model.setRowCount(0);
                        for(DTOSanPham s : ketQuaTL){
                            Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getImg()};
                            model.addRow(row);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhà cung cấp");
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
                DTOSanPham ketQuaMaSach = BLLsp.BLLtimtheomasp(maSachCanTim);
                if (ketQuaMaSach != null){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQuaMaSach.getMaSanPham(),ketQuaMaSach.getTenSanPham(),ketQuaMaSach.getGiaBan(),ketQuaMaSach.getGiaNhap(),ketQuaMaSach.getSoLuong(),ketQuaMaSach.getMaNCC(),ketQuaMaSach.getBaoHanh(),ketQuaMaSach.getImg()};
                    model.addRow(row);
                }else{
                    loadSP();
                }
                break;
            case 1:
                String tenSachCanTim = txtTim.getText().toString();
                ArrayList<DTOSanPham> ketQuaTenSach = BLLsp.BLLtimtheoten(tenSachCanTim);
                for(DTOSanPham sp : ketQuaTenSach){
                    System.out.println(sp.getMaSanPham());
                }
                if (!ketQuaTenSach.isEmpty()){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    for(DTOSanPham s : ketQuaTenSach){
                        Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                        model.addRow(row);
                    }
                }
                else{
                    loadSP();
                }
                break;
            case 2:
                String theLoaiCanTim = txtTim.getText();
                ArrayList<DTOSanPham> ketQuaTL = BLLsp.BLLtimtheomancc(theLoaiCanTim);
                if (!ketQuaTL.isEmpty()){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    for(DTOSanPham s : ketQuaTL){
                        Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                        model.addRow(row);
                    }
                }
                else{
                    loadSP();
                }
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_txtTimKeyReleased

    private void cbbSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSapXepActionPerformed
        // TODO add your handling code here:
        if(cbbSapXep.getSelectedItem() == ""){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu muốn sắp xếp");
        }else{
            int selectedValue = cbbSapXep.getSelectedIndex();
            ArrayList<DTOSanPham> ketQuaMaSP = BLLsp.BLLgetDL();
            switch (selectedValue) {
                case 0:
                    for(int j = 0; j < ketQuaMaSP.size() - 1; j ++){
                        for(int i = 0; i < ketQuaMaSP.size() - j - 1; i ++){
                            if(ketQuaMaSP.get(i).getGiaBan() > ketQuaMaSP.get(i  + 1).getGiaBan()){
                                DTOSanPham temp = ketQuaMaSP.get(i);
                                ketQuaMaSP.set(i, ketQuaMaSP.get(i + 1));
                                ketQuaMaSP.set(i + 1, temp);
                            }
                        }
                    }
                    if (ketQuaMaSP != null){
                        model = (DefaultTableModel) tbSanPham.getModel();
                        model.setRowCount(0);
                        for(DTOSanPham s : ketQuaMaSP){
                            Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                            model.addRow(row);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Lỗiiii");
                    }
                    break;
                case 1:
                    for(int j = 0; j < ketQuaMaSP.size() - 1; j ++){
                        for(int i = 0; i < ketQuaMaSP.size() - j - 1; i ++){
                            if(ketQuaMaSP.get(i).getGiaNhap() > ketQuaMaSP.get(i  + 1).getGiaNhap()){
                                DTOSanPham temp = ketQuaMaSP.get(i);
                                ketQuaMaSP.set(i, ketQuaMaSP.get(i + 1));
                                ketQuaMaSP.set(i + 1, temp);
                            }
                        }
                    }
                    if (ketQuaMaSP != null){
                        model = (DefaultTableModel) tbSanPham.getModel();
                        model.setRowCount(0);
                        for(DTOSanPham s : ketQuaMaSP){
                            Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                            model.addRow(row);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Lỗiiii");
                    }
                    break;
                case 2:
                    for(int j = 0; j < ketQuaMaSP.size() - 1; j ++){
                        for(int i = 0; i < ketQuaMaSP.size() - j - 1; i ++){
                            if(ketQuaMaSP.get(i).getBaoHanh() > ketQuaMaSP.get(i  + 1).getBaoHanh()){
                                DTOSanPham temp = ketQuaMaSP.get(i);
                                ketQuaMaSP.set(i, ketQuaMaSP.get(i + 1));
                                ketQuaMaSP.set(i + 1, temp);
                            }
                        }
                    }
                    if (ketQuaMaSP != null){
                        model = (DefaultTableModel) tbSanPham.getModel();
                        model.setRowCount(0);
                        for(DTOSanPham s : ketQuaMaSP){
                            Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                            model.addRow(row);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Lỗiiii");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }//GEN-LAST:event_cbbSapXepActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try{
            if(txtManHinh.getText().trim().equals("") ||
                txtMaSP2.getText().trim().equals("") ||
                txtCamera.getText().trim().equals("") ||
                txtROM.getText().trim().equals("") ||
                txtRAM.getText().trim().equals("")||
                txtPin.getText().equals("")||
                txtThietKe.getText().equals("")||
                txtTrongLuong.getText().equals("")||
                txtMau.getText().equals("")||
                txtIMEI.getText().equals("")){
                           
                JOptionPane.showMessageDialog(null,"Vui lòng nhập lại thông tin");
            }else if(BLLsp.BLLtimtheomasp(txtMaSP2.getText()) == null){
                JOptionPane.showMessageDialog(null,"Mã sản phẩm không tồn tại");
            }else if(BLLsp.BLLtimctsp(txtMaSP2.getText()) == null){
                JOptionPane.showMessageDialog(null,"Chi tiết sản phẩm đã tồn tại");
            }
            else{
                ChiTietSanPham sp = new ChiTietSanPham();
                sp.setManHinh(txtManHinh.getText());
                sp.setCamera(txtCamera.getText());
                sp.setRom(txtROM.getText());
                sp.setRam(txtRAM.getText());
                sp.setPin(txtPin.getText());
                sp.setThietKe(txtThietKe.getText());
                sp.setKhoiLuong(Float.parseFloat(txtTrongLuong.getText()));
                sp.setMauSac(txtTrongLuong.getText());
                sp.setIMEI(txtIMEI.getText());
                JOptionPane.showMessageDialog(null,BLLsp.BLLthemct(sp));
                loadSP();
                trangThaiBanDau();
            }
        }
        catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Thông tin không hợp lệ");
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
        try {
            int i = tbSanPham.getSelectedRow(); 
            ChiTietSanPham sp = new ChiTietSanPham();
            if (i>=0){
                if(txtManHinh.getText().trim().equals("") ||
                    txtCamera.getText().trim().equals("") ||
                    txtROM.getText().trim().equals("") ||
                    txtRAM.getText().trim().equals("")||
                    txtPin.getText().equals("")||
                    txtThietKe.getText().equals("")||
                    txtTrongLuong.getText().equals("")||
                    txtMau.getText().equals("")||
                    txtIMEI.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(this, "Không được để trống thông tin sản phẩm!");
                }
                else{
                    sp.setManHinh(txtMaSP.getText()); 
                    sp.setCamera(txtTenSP.getText());
                    sp.setRom(txtGiaNhap.getText());
                    sp.setRam(txtGiaBan.getText());
                    sp.setPin(txtSoLuong.getText());
                    sp.setThietKe((cbbMaNCC.getSelectedItem()).toString());
                    sp.setKhoiLuong(Integer.parseInt(txtBaoHanh.getText()));
                    sp.setMauSac(txtAnh.getText());
                    JOptionPane.showMessageDialog(this, BLLsp.BLLsuactsp(sp, sp.getMaSP()));
                    loadSP();
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
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void txtMaSP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSP2ActionPerformed
        // TODO add your handling code here:
        txtMaSP2.setText(txtMaSP.getText());
    }//GEN-LAST:event_txtMaSP2ActionPerformed

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
            java.util.logging.Logger.getLogger(QLSP_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSP_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSP_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSP_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLSP_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnh;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbMaNCC;
    private javax.swing.JComboBox<String> cbbSapXep;
    private javax.swing.JComboBox<String> cbbTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jlbAnh;
    private javax.swing.JLabel jlbAnh2;
    private javax.swing.JPanel jpCTSP;
    private javax.swing.JPanel jpQLSP;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtAnh;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtCamera;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtIMEI;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaSP2;
    private javax.swing.JTextField txtManHinh;
    private javax.swing.JTextField txtMau;
    private javax.swing.JTextField txtPin;
    private javax.swing.JTextField txtRAM;
    private javax.swing.JTextField txtROM;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenSP2;
    private javax.swing.JTextField txtThietKe;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTrongLuong;
    // End of variables declaration//GEN-END:variables
}
