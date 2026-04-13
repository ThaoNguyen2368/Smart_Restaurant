// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package gui;

import dao.KhachHang_DAO;
import dao.TheThanhVien_DAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.TheThanhVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class QuanLyKhachHang_GUI extends JPanel {
   private JTextField txtMaKH;
   private JTextField txtHoTen;
   private JTextField txtSDT;
   private JTextField txtEmail;
   private JTextField txtTimKiem;
   private JRadioButton radNam;
   private JRadioButton radNu;
   private ButtonGroup nhomGioiTinh;
   private JTextField txtDiem;
   private JTable bangKH;
   private DefaultTableModel modelBangKH;
   private KhachHang_DAO khDAO = new KhachHang_DAO();
   private TheThanhVien_DAO ttvDAO = new TheThanhVien_DAO();
   private NhanVien nvDangNhap = new NhanVien("NV001");

   public QuanLyKhachHang_GUI() {
      this.setLayout(new BorderLayout(15, 15));
      this.setBackground(new Color(255, 218, 170));
      this.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
      JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG", 0);
      lblTieuDe.setFont(new Font("Arial", 1, 36));
      lblTieuDe.setForeground(Color.BLACK);
      lblTieuDe.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
      JPanel pnlGiua = new JPanel(new BorderLayout(10, 10));
      pnlGiua.setBackground(new Color(255, 218, 170));
      JPanel pnlForm = this.taoPanelForm();
      pnlGiua.add(pnlForm, "Center");
      JPanel pnlNutChucNang = this.taoPanelNut();
      pnlGiua.add(pnlNutChucNang, "South");
      JPanel pnlDuoi = new JPanel(new BorderLayout(15, 15));
      pnlDuoi.setBackground(new Color(255, 218, 170));
      JPanel pnlTimKiem = this.taoPanelTimKiem();
      pnlDuoi.add(pnlTimKiem, "North");
      JScrollPane cuonBangKH = this.taoBang();
      pnlDuoi.add(cuonBangKH, "Center");
      JPanel pnlBocTren = new JPanel(new BorderLayout());
      pnlBocTren.setBackground(new Color(255, 218, 170));
      pnlBocTren.add(lblTieuDe, "North");
      pnlBocTren.add(pnlGiua, "Center");
      this.add(pnlBocTren, "North");
      this.add(pnlDuoi, "Center");
      this.taiDuLieuVaoBang();
      this.xoaTrangForm();
   }

   private void taiDuLieuVaoBang() {
      try {
         this.modelBangKH.setRowCount(0);

         for(Object[] dong : this.khDAO.layTatCaKhachHangChoBang()) {
            this.modelBangKH.addRow(dong);
         }
      } catch (SQLException e) {
         e.printStackTrace();
         JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu khách hàng!", "Lỗi Cơ Sở Dữ Liệu", 0);
      }

   }

   private JPanel taoPanelForm() {
      JPanel pnl = new JPanel(new GridBagLayout());
      pnl.setBackground(new Color(220, 210, 210));
      pnl.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1), BorderFactory.createEmptyBorder(25, 30, 25, 30)));
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 15, 10, 15);
      gbc.fill = 2;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 0.2;
      gbc.anchor = 17;
      pnl.add(this.taoNhan("Mã khách hàng"), gbc);
      gbc.gridx = 1;
      gbc.weightx = 0.8;
      this.txtMaKH = this.taoTextFieldDinhDang();
      this.txtMaKH.setEditable(false);
      this.txtMaKH.setBackground(new Color(230, 230, 230));
      pnl.add(this.txtMaKH, gbc);
      gbc.gridx = 2;
      gbc.weightx = 0.2;
      pnl.add(this.taoNhan("Họ tên"), gbc);
      gbc.gridx = 3;
      gbc.weightx = 0.8;
      this.txtHoTen = this.taoTextFieldDinhDang();
      pnl.add(this.txtHoTen, gbc);
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.weightx = 0.2;
      pnl.add(this.taoNhan("Số điện thoại"), gbc);
      gbc.gridx = 1;
      gbc.weightx = 0.8;
      this.txtSDT = this.taoTextFieldDinhDang();
      pnl.add(this.txtSDT, gbc);
      gbc.gridx = 2;
      gbc.weightx = 0.2;
      pnl.add(this.taoNhan("Email"), gbc);
      gbc.gridx = 3;
      gbc.weightx = 0.8;
      this.txtEmail = this.taoTextFieldDinhDang();
      pnl.add(this.txtEmail, gbc);
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.weightx = 0.2;
      pnl.add(this.taoNhan("Giới tính"), gbc);
      gbc.gridx = 1;
      gbc.weightx = 0.8;
      JPanel pnlGioiTinh = new JPanel(new FlowLayout(0, 20, 0));
      pnlGioiTinh.setBackground(new Color(220, 210, 210));
      this.radNam = new JRadioButton("Nam");
      this.radNu = new JRadioButton("Nữ");
      this.radNam.setBackground(new Color(220, 210, 210));
      this.radNu.setBackground(new Color(220, 210, 210));
      this.radNam.setFont(new Font("Arial", 0, 14));
      this.radNu.setFont(new Font("Arial", 0, 14));
      this.nhomGioiTinh = new ButtonGroup();
      this.nhomGioiTinh.add(this.radNam);
      this.nhomGioiTinh.add(this.radNu);
      pnlGioiTinh.add(this.radNam);
      pnlGioiTinh.add(this.radNu);
      pnl.add(pnlGioiTinh, gbc);
      gbc.gridx = 2;
      gbc.weightx = 0.2;
      pnl.add(this.taoNhan("Điểm tích lũy"), gbc);
      gbc.gridx = 3;
      gbc.weightx = 0.8;
      this.txtDiem = this.taoTextFieldDinhDang();
      this.txtDiem.setText("0");
      pnl.add(this.txtDiem, gbc);
      return pnl;
   }

   private JPanel taoPanelNut() {
      JPanel pnl = new JPanel(new FlowLayout(1, 20, 15));
      pnl.setBackground(new Color(255, 218, 170));
      JButton btnThem = new JButton("Thêm khách hàng");
      this.dinhDangNut(btnThem, new Color(76, 175, 80), Color.WHITE);
      btnThem.addActionListener((e) -> this.themKhachHang());
      JButton btnXoa = new JButton("Xóa khách hàng");
      this.dinhDangNut(btnXoa, new Color(244, 67, 54), Color.WHITE);
      btnXoa.addActionListener((e) -> this.xoaKhachHang());
      JButton btnSua = new JButton("Chỉnh sửa thông tin");
      this.dinhDangNut(btnSua, new Color(234, 196, 28), Color.WHITE);
      btnSua.addActionListener((e) -> this.suaKhachHang());
      JButton btnLamMoi = new JButton("Làm mới");
      this.dinhDangNut(btnLamMoi, new Color(158, 158, 158), Color.WHITE);
      btnLamMoi.addActionListener((e) -> this.xoaTrangForm());
      pnl.add(btnThem);
      pnl.add(btnXoa);
      pnl.add(btnSua);
      pnl.add(btnLamMoi);
      return pnl;
   }

   private void dinhDangNut(JButton nut, Color mauNen, Color mauChu) {
      nut.setBackground(mauNen);
      nut.setForeground(mauChu);
      nut.setFocusPainted(false);
      nut.setFont(new Font("Arial", 1, 14));
      nut.setPreferredSize(new Dimension(230, 45));
      nut.setCursor(new Cursor(12));
      nut.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(mauNen.darker(), 2), BorderFactory.createEmptyBorder(8, 20, 8, 20)));
   }

   private JLabel taoNhan(String chuỗiNhan) {
      JLabel nhan = new JLabel(chuỗiNhan);
      nhan.setFont(new Font("Arial", 1, 14));
      nhan.setForeground(Color.BLACK);
      return nhan;
   }

   private JTextField taoTextFieldDinhDang() {
      JTextField textField = new JTextField();
      textField.setFont(new Font("Arial", 0, 14));
      textField.setBackground(Color.WHITE);
      textField.setPreferredSize(new Dimension(0, 35));
      textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
      return textField;
   }

   private JPanel taoPanelTimKiem() {
      JPanel pnl = new JPanel(new FlowLayout(0, 15, 10));
      pnl.setBackground(new Color(255, 218, 170));
      this.txtTimKiem = new JTextField(35);
      this.txtTimKiem.setFont(new Font("Arial", 0, 14));
      this.txtTimKiem.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(8, 12, 8, 12)));
      JButton btnTim = new JButton("Tìm Kiếm");
      btnTim.setFocusPainted(false);
      btnTim.setFont(new Font("Arial", 1, 16));
      btnTim.setCursor(new Cursor(12));
      btnTim.setPreferredSize(new Dimension(150, 35));
      btnTim.addActionListener((e) -> this.timKiemKhachHang());
      btnTim.setBackground(new Color(33, 150, 243));
      btnTim.setForeground(Color.WHITE);
      JLabel lblTim = new JLabel("Tìm khách hàng:");
      lblTim.setFont(new Font("Arial", 1, 13));
      lblTim.setForeground(new Color(100, 100, 100));
      pnl.add(lblTim);
      pnl.add(this.txtTimKiem);
      pnl.add(btnTim);
      return pnl;
   }

   private JScrollPane taoBang() {
      String[] cot = new String[]{"Mã", "Tên khách hàng", "Thẻ thành viên", "Số điện thoại", "Giới tính", "Email"};
      this.modelBangKH = new DefaultTableModel(cot, 0) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      this.bangKH = new JTable(this.modelBangKH);
      this.bangKH.setRowHeight(40);
      this.bangKH.setFont(new Font("Arial", 0, 13));
      this.bangKH.setSelectionMode(0);
      this.bangKH.setSelectionBackground(new Color(255, 200, 150));
      this.bangKH.setSelectionForeground(Color.BLACK);
      this.bangKH.setGridColor(new Color(200, 200, 200));
      this.bangKH.setShowGrid(true);
      this.bangKH.setIntercellSpacing(new Dimension(1, 1));
      this.bangKH.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
               QuanLyKhachHang_GUI.this.hienThiDongDaChon();
            }

         }
      });
      JTableHeader tieuDeBang = this.bangKH.getTableHeader();
      tieuDeBang.setBackground(new Color(255, 178, 102));
      tieuDeBang.setForeground(Color.BLACK);
      tieuDeBang.setFont(new Font("Arial", 1, 14));
      tieuDeBang.setPreferredSize(new Dimension(tieuDeBang.getWidth(), 45));
      tieuDeBang.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 100), 2));
      JScrollPane cuon = new JScrollPane(this.bangKH);
      cuon.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 100), 2));
      return cuon;
   }

   private void themKhachHang() {
      if (this.kiemTraDuLieuNhap()) {
         String maKH = this.txtMaKH.getText().trim();
         String sdt = this.txtSDT.getText().trim();

         try {
            String hoTen = this.txtHoTen.getText().trim();
            String email = this.txtEmail.getText().trim();
            boolean gioiTinh = this.radNam.isSelected();
            KhachHang kh = new KhachHang(maKH, hoTen, sdt, email, gioiTinh);
            if (this.khDAO.themKhachHang(kh)) {
               int diem = this.chuyenDoiDiem();
               String maTheMoi = this.ttvDAO.phatSinhMaThe();
               TheThanhVien ttv = new TheThanhVien(maTheMoi, kh, diem);
               this.ttvDAO.themTheThanhVien(ttv);
               this.taiDuLieuVaoBang();
               this.xoaTrangForm();
               JOptionPane.showMessageDialog(this, "Đã thêm khách hàng và thẻ thành viên thành công!", "Thông báo", 1);
            } else {
               JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại (DAO trả về false)!", "Lỗi", 0);
            }
         } catch (SQLException e) {
            String thongBaoLoi = e.getMessage();
            if (thongBaoLoi.contains("PRIMARY KEY") && thongBaoLoi.contains(maKH)) {
               JOptionPane.showMessageDialog(this, "Lỗi: Mã khách hàng '" + maKH + "' đã tồn tại.\nVui lòng nhấn 'Làm mới' để lấy mã mới.", "Lỗi Trùng Mã Khách Hàng", 0);
            } else if (thongBaoLoi.contains("UNIQUE") && thongBaoLoi.contains(sdt)) {
               JOptionPane.showMessageDialog(this, "Lỗi: Số điện thoại '" + sdt + "' đã được đăng ký cho khách hàng khác.", "Lỗi Trùng Số Điện Thoại", 0);
            } else if (thongBaoLoi.contains("PRIMARY KEY") && thongBaoLoi.contains("THETHANHVIEN")) {
               JOptionPane.showMessageDialog(this, "Lỗi: Không thể tạo thẻ thành viên, mã thẻ tự động phát sinh bị trùng.\nVui lòng thử lại.", "Lỗi Tạo Mã Thẻ", 0);
            } else {
               e.printStackTrace();
               JOptionPane.showMessageDialog(this, "Lỗi Cơ sở dữ liệu khi thêm khách hàng hoặc thẻ thành viên:\n" + thongBaoLoi, "Lỗi Cơ Sở Dữ Liệu", 0);
            }
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi Dữ Liệu Nhập", 2);
         } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không mong muốn: " + ex.getMessage(), "Lỗi Hệ Thống", 0);
         }

      }
   }

   private void xoaKhachHang() {
      int hangDaChon = this.bangKH.getSelectedRow();
      if (hangDaChon == -1) {
         JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!", "Chưa chọn khách hàng", 2);
      } else {
         String maKH = this.modelBangKH.getValueAt(hangDaChon, 0).toString();
         int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng '" + maKH + "'?\n(Thao tác này cũng sẽ xóa thẻ thành viên nếu có)", "Xác nhận xóa", 0);
         if (xacNhan == 0) {
            try {
               this.ttvDAO.xoaTheThanhVienTheoMaKH(maKH);
               if (this.khDAO.xoaKhachHang(maKH)) {
                  this.taiDuLieuVaoBang();
                  this.xoaTrangForm();
                  JOptionPane.showMessageDialog(this, "Đã xóa khách hàng thành công!", "Thông báo", 1);
               } else {
                  JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại (DAO trả về false)!", "Lỗi", 0);
               }
            } catch (SQLException e) {
               e.printStackTrace();
               JOptionPane.showMessageDialog(this, "Lỗi khi xóa khách hàng hoặc thẻ thành viên:\n" + e.getMessage(), "Lỗi Cơ Sở Dữ Liệu", 0);
            }
         }

      }
   }

   private void suaKhachHang() {
      int hangDaChon = this.bangKH.getSelectedRow();
      if (hangDaChon == -1) {
         JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa thông tin!", "Chưa chọn khách hàng", 2);
      } else {
         if (this.kiemTraDuLieuNhap()) {
            try {
               String maKH = this.txtMaKH.getText().trim();
               String hoTen = this.txtHoTen.getText().trim();
               String sdt = this.txtSDT.getText().trim();
               String email = this.txtEmail.getText().trim();
               boolean gioiTinh = this.radNam.isSelected();
               int diemMoi = this.chuyenDoiDiem();
               KhachHang khMoi = new KhachHang(maKH, hoTen, sdt, email, gioiTinh);
               boolean capNhatThanhCong = this.khDAO.capNhatKhachHang(khMoi);
               if (capNhatThanhCong) {
                  TheThanhVien ttv = this.ttvDAO.layTheTheoMaKH(maKH);
                  if (ttv == null) {
                     String maTheMoi = this.ttvDAO.phatSinhMaThe();
                     TheThanhVien ttvMoi = new TheThanhVien(maTheMoi, khMoi, diemMoi);
                     this.ttvDAO.themTheThanhVien(ttvMoi);
                  } else {
                     ttv.setDiemTichLuy(diemMoi);
                     this.ttvDAO.capNhatTheThanhVien(ttv);
                  }

                  this.taiDuLieuVaoBang();
                  JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin khách hàng thành công!", "Thông báo", 1);
               } else {
                  JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thất bại!", "Lỗi", 0);
               }
            } catch (IllegalArgumentException | SQLException e) {
               ((Exception)e).printStackTrace();
               JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật khách hàng hoặc thẻ thành viên:\n" + ((Exception)e).getMessage(), "Lỗi", 0);
            } catch (Exception ex) {
               ex.printStackTrace();
               JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không mong muốn: " + ex.getMessage(), "Lỗi Hệ Thống", 0);
            }
         }

      }
   }

   private void hienThiDongDaChon() {
      int hangDaChon = this.bangKH.getSelectedRow();
      if (hangDaChon != -1) {
         try {
            String maKH = this.modelBangKH.getValueAt(hangDaChon, 0).toString();
            KhachHang kh = this.khDAO.layKhachHangTheoMa(maKH);
            if (kh == null) {
               JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin chi tiết của khách hàng này trong Cơ sở dữ liệu!", "Lỗi Dữ Liệu", 0);
               return;
            }

            this.txtMaKH.setText(kh.getMaKH());
            this.txtHoTen.setText(kh.getHoTenKH());
            this.txtSDT.setText(kh.getSoDienThoai());
            this.txtEmail.setText(kh.getEmail());
            if (kh.isGioiTinh()) {
               this.radNam.setSelected(true);
            } else {
               this.radNu.setSelected(true);
            }

            TheThanhVien ttv = this.ttvDAO.layTheTheoMaKH(maKH);
            if (ttv != null) {
               this.txtDiem.setText(String.valueOf(ttv.getDiemTichLuy()));
            } else {
               this.txtDiem.setText("0");
            }
         } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải chi tiết khách hàng từ Cơ sở dữ liệu!", "Lỗi Cơ Sở Dữ Liệu", 0);
         }
      }

   }

   private void timKiemKhachHang() {
      String tuKhoa = this.txtTimKiem.getText().trim();
      if (tuKhoa.isEmpty()) {
         this.taiDuLieuVaoBang();
      } else {
         ArrayList<Object[]> dsKetQua = this.khDAO.timKhachHangTongHop(tuKhoa);
         this.modelBangKH.setRowCount(0);
         if (!dsKetQua.isEmpty()) {
            for(Object[] row : dsKetQua) {
               this.modelBangKH.addRow(row);
            }

            this.bangKH.setRowSelectionInterval(0, 0);
            this.hienThiDongDaChon();
            JOptionPane.showMessageDialog(this, "Tìm thấy " + dsKetQua.size() + " kết quả phù hợp.", "Kết quả tìm kiếm", 1);
         } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng nào với từ khóa: '" + tuKhoa + "'.", "Không tìm thấy", 2);
            this.taiDuLieuVaoBang();
            this.xoaTrangForm();
         }

      }
   }

   private boolean kiemTraDuLieuNhap() {
      try {
         new KhachHang(this.txtMaKH.getText().trim(), this.txtHoTen.getText().trim(), this.txtSDT.getText().trim(), this.txtEmail.getText().trim(), this.radNam.isSelected());
         if (!this.radNam.isSelected() && !this.radNu.isSelected()) {
            throw new IllegalArgumentException("Vui lòng chọn giới tính (Nam hoặc Nữ)!");
         } else {
            this.chuyenDoiDiem();
            return true;
         }
      } catch (IllegalArgumentException e) {
         JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi Dữ Liệu Nhập", 2);
         return false;
      }
   }

   private int chuyenDoiDiem() throws IllegalArgumentException {
      try {
         int diem = Integer.parseInt(this.txtDiem.getText().trim());
         if (diem < 0) {
            throw new IllegalArgumentException("Điểm tích lũy không được là số âm.");
         } else {
            return diem;
         }
      } catch (NumberFormatException var2) {
         throw new IllegalArgumentException("Điểm tích lũy phải là một con số nguyên hợp lệ.");
      }
   }

   private void xoaTrangForm() {
      try {
         this.txtMaKH.setText(this.khDAO.phatSinhMaKH());
      } catch (Exception e) {
         this.txtMaKH.setText("LoiMaKH");
         JOptionPane.showMessageDialog(this, "Lỗi phát sinh mã khách hàng mới: " + e.getMessage(), "Lỗi Cơ Sở Dữ Liệu", 0);
      }

      this.txtHoTen.setText("");
      this.txtSDT.setText("");
      this.txtEmail.setText("");
      this.nhomGioiTinh.clearSelection();
      this.txtDiem.setText("0");
      this.bangKH.clearSelection();
   }
}

