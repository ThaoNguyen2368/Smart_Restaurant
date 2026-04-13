// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class TrangChinh_Form extends JFrame implements ActionListener {
   private static final long serialVersionUID = 1L;
   private CardLayout cardLayout;
   private JPanel pnlContent;
   private JButton btndangxuat;
   private JButton currentBtn = null;
   private Color colorNen = new Color(30, 41, 59);
   private Color colorNhat = new Color(30, 41, 59);
   private Color colorDam = new Color(65, 137, 230);
   private Font fontMenu = new Font("Segoe UI", 1, 14);

   public TrangChinh_Form() {
      this.setTitle("Smart Restaurant - Hệ thống quản lý");
      this.setDefaultCloseOperation(3);
      this.setSize(1200, 800);
      this.setExtendedState(6);
      this.setLocationRelativeTo((Component)null);
      this.getContentPane().setBackground(new Color(247, 247, 247));
      String userRole = "Quản lý";
      String userName = "Admin";
      if (DangNhap_GUI.taiKhoanDangNhap != null) {
         userRole = DangNhap_GUI.taiKhoanDangNhap.getVaiTro();
         if (DangNhap_GUI.taiKhoanDangNhap.getNhanVien() != null) {
            userName = DangNhap_GUI.taiKhoanDangNhap.getNhanVien().getHoTen();
         }
      }

      JPanel pTrai = new JPanel();
      pTrai.setBackground(this.colorNen);
      pTrai.setPreferredSize(new Dimension(220, this.getHeight()));
      pTrai.setLayout(new BoxLayout(pTrai, 1));
      pTrai.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
      JLabel lblHello = new JLabel("Xin chào " + userRole);
      lblHello.setFont(new Font("Segoe UI", 1, 16));
      lblHello.setForeground(Color.WHITE);
      lblHello.setAlignmentX(0.5F);
      lblHello.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
      pTrai.add(lblHello);
      this.cardLayout = new CardLayout();
      this.pnlContent = new JPanel(this.cardLayout);

      try {
         this.pnlContent.add(new Dashboard_GUI(), "Dashboard");
      } catch (Exception var16) {
      }

      try {
         BanDat_GUI pnlDatBan = new BanDat_GUI();
         DanhSachBanDat_GUI pnlDanhSach = new DanhSachBanDat_GUI();
         pnlDatBan.setDataRefreshListener(() -> pnlDanhSach.refreshData());
         pnlDanhSach.setDataRefreshListener(() -> pnlDatBan.refreshData());
         this.pnlContent.add(pnlDatBan, "PANEL_DAT_BAN");
         this.pnlContent.add(pnlDanhSach, "PANEL_DS_DAT_BAN");
      } catch (Exception var15) {
      }

      this.addModule(new MonAn_GUI(), "Quản lý món ăn");
      this.addModule(new QuanLyKhachHang_GUI(), "Quản lý KH");
      this.addModule(new NhanVien_GUI(), "Quản lý nhân viên");
      this.addModule(new BaoCao_GUI(), "Báo cáo");
      this.addModule(new KhuyenMai_GUI(), "Khuyến mãi");
      this.addModule(new HoaDon_GUI(), "Quản lý hóa đơn");
      String[] btnChucnang = new String[]{"Dashboard", "Quản lý bàn đặt", "Quản lý món ăn", "Quản lý KH", "Quản lý nhân viên", "Quản lý hóa đơn", "Báo cáo", "Khuyến mãi"};
      String[] imgPaths = new String[]{"image/dashboard.png", "image/ban.png", "image/monan.png", "image/khachhang.png", "image/nhanvien.png", "image/hoadon.png", "image/baocao.png", "image/khuyenmai.png"};
      List<String> chucNangCamNhanVien = Arrays.asList("Quản lý món ăn", "Quản lý nhân viên", "Báo cáo", "Khuyến mãi");

      for(int i = 0; i < btnChucnang.length; ++i) {
         String label = btnChucnang[i];
         if (!userRole.equalsIgnoreCase("Nhân viên") || !chucNangCamNhanVien.contains(label)) {
            JButton btn = this.createMenuButton(label, imgPaths[i], this.colorNhat, this.fontMenu);
            if (label.equals("Quản lý bàn đặt")) {
               btn.setText(label + "   ▼");
               JPopupMenu popupMenu = this.createBanDatPopupMenu(btn);
               btn.addActionListener((e) -> {
                  this.setButtonActive(btn);
                  popupMenu.show(btn, 0, btn.getHeight());
               });
            } else {
               btn.addActionListener((e) -> {
                  this.cardLayout.show(this.pnlContent, label);
                  this.setButtonActive(btn);
               });
            }

            if (label.equals("Dashboard")) {
               this.setButtonActive(btn);
            }

            pTrai.add(btn);
            pTrai.add(Box.createRigidArea(new Dimension(0, 15)));
         }
      }

      this.btndangxuat = this.createMenuButton("Đăng xuất", "image/dangxuat.png", this.colorNhat, this.fontMenu);
      this.btndangxuat.addActionListener(this);
      pTrai.add(this.btndangxuat);
      this.add(pTrai, "West");
      this.add(this.pnlContent, "Center");
      this.setVisible(true);
   }

   private String getTenRutGon(String fullName) {
      if (fullName == null) {
         return "";
      } else {
         String[] parts = fullName.split("\\s+");
         return parts[parts.length - 1];
      }
   }

   private ImageIcon getIcon(String path, int width, int height) {
      URL imgURL = this.getClass().getResource("/" + path);
      if (imgURL != null) {
         ImageIcon icon = new ImageIcon(imgURL);
         Image img = icon.getImage().getScaledInstance(width, height, 4);
         return new ImageIcon(img);
      } else {
         return null;
      }
   }

   private void setButtonActive(JButton btn) {
      if (this.currentBtn != null && this.currentBtn != btn) {
         this.currentBtn.setBackground(this.colorNhat);
      }

      this.currentBtn = btn;
      this.currentBtn.setBackground(this.colorDam);
   }

   private void addModule(JPanel panel, String key) {
      try {
         this.pnlContent.add(panel, key);
      } catch (Exception var4) {
         this.pnlContent.add(new JLabel("Lỗi tải module " + key), key);
      }

   }

   private JButton createMenuButton(String text, String iconPath, Color bgColor, Font font) {
      final JButton btn = new JButton(text);
      ImageIcon icon = this.getIcon(iconPath, 25, 25);
      if (icon != null) {
         btn.setIcon(icon);
      }

      btn.setFont(font);
      btn.setHorizontalAlignment(2);
      btn.setIconTextGap(15);
      btn.setMaximumSize(new Dimension(250, 50));
      btn.setAlignmentX(0.5F);
      btn.setFocusPainted(false);
      btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
      btn.setCursor(Cursor.getPredefinedCursor(12));
      btn.setBackground(bgColor);
      btn.setForeground(Color.WHITE);
      btn.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
            btn.setBackground(TrangChinh_Form.this.colorDam);
         }

         public void mouseExited(MouseEvent e) {
            if (btn != TrangChinh_Form.this.currentBtn) {
               btn.setBackground(TrangChinh_Form.this.colorNhat);
            }

         }
      });
      return btn;
   }

   private JPopupMenu createBanDatPopupMenu(JButton parentBtn) {
      JPopupMenu popupMenu = new JPopupMenu();
      popupMenu.setBackground(this.colorNhat);
      popupMenu.setBorder(BorderFactory.createLineBorder(this.colorDam, 1));
      JMenuItem itemFormDat = this.createMenuItem("Form Đặt Bàn", "image/reservation.png");
      JMenuItem itemDSDat = this.createMenuItem("Danh Sách Đặt Bàn", "image/list.png");
      itemFormDat.addActionListener((e) -> {
         this.cardLayout.show(this.pnlContent, "PANEL_DAT_BAN");
         this.setButtonActive(parentBtn);
      });
      itemDSDat.addActionListener((e) -> {
         this.cardLayout.show(this.pnlContent, "PANEL_DS_DAT_BAN");
         this.setButtonActive(parentBtn);
      });
      popupMenu.add(itemFormDat);
      popupMenu.add(itemDSDat);
      return popupMenu;
   }

   private JMenuItem createMenuItem(String text, String iconPath) {
      final JMenuItem item = new JMenuItem(text);
      item.setFont(this.fontMenu);
      item.setBackground(this.colorNhat);
      item.setForeground(Color.WHITE);
      item.setPreferredSize(new Dimension(218, 40));
      item.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
      ImageIcon icon = this.getIcon(iconPath, 20, 20);
      if (icon != null) {
         item.setIcon(icon);
         item.setIconTextGap(10);
      }

      item.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
            item.setBackground(TrangChinh_Form.this.colorDam);
         }

         public void mouseExited(MouseEvent e) {
            item.setBackground(TrangChinh_Form.this.colorNhat);
         }
      });
      return item;
   }

   public void chuyenDenManHinhGoiMon(String maBan) {
      try {
         GoiMon_GUI goiMon = new GoiMon_GUI(maBan);
         this.pnlContent.add(goiMon, "PANEL_GOI_MON");
         this.cardLayout.show(this.pnlContent, "PANEL_GOI_MON");
         if (this.currentBtn != null) {
            this.currentBtn.setBackground(this.colorNhat);
         }

         this.currentBtn = null;
      } catch (Exception e) {
         JOptionPane.showMessageDialog(this, "Lỗi mở màn hình gọi món: " + e.getMessage());
      }

   }

   public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
      if (o.equals(this.btndangxuat)) {
         int confirm = JOptionPane.showConfirmDialog((Component)null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", 0);
         if (confirm == 0) {
            this.dispose();
            DangNhap_GUI.taiKhoanDangNhap = null;

            try {
               (new DangNhap_GUI()).setVisible(true);
            } catch (Exception var5) {
               System.exit(0);
            }
         }
      }

   }
}

