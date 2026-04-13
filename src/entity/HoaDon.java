// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {
   private String maHoaDon;
   private LocalDateTime ngayLap;
   private String trangThaiThanhToan;
   private List<CT_HoaDon> danhSachChiTietHoaDon = new ArrayList();
   private TheThanhVien theThanhVien;
   private NhanVien nhanVien;
   private Ban ban;
   private BanDat banDat;
   private KhuyenMai khuyenMai;

   public HoaDon() {
      this.ngayLap = LocalDateTime.now();
      this.trangThaiThanhToan = "Chờ thanh toán";
   }

   public HoaDon(String maHoaDon) {
      this.setMaHoaDon(maHoaDon);
      this.ngayLap = LocalDateTime.now();
      this.trangThaiThanhToan = "Chờ thanh toán";
   }

   public HoaDon(String maHoaDon, TheThanhVien theThanhVien, NhanVien nhanVien, Ban ban, BanDat banDat, KhuyenMai khuyenMai, LocalDateTime ngayLap, List<CT_HoaDon> danhSachChiTietHoaDon, String trangThaiThanhToan) {
      this.setMaHoaDon(maHoaDon);
      this.setTheThanhVien(theThanhVien);
      this.setNhanVien(nhanVien);
      this.setBan(ban);
      this.setBanDat(banDat);
      this.setKhuyenMai(khuyenMai);
      this.setNgayLap(ngayLap);
      this.setDanhSachChiTietHoaDon(danhSachChiTietHoaDon);
      this.setTrangThaiThanhToan(trangThaiThanhToan);
   }

   public double tinhTongTien() {
      if (this.danhSachChiTietHoaDon != null && !this.danhSachChiTietHoaDon.isEmpty()) {
         double tongChuaGiam = this.danhSachChiTietHoaDon.stream().mapToDouble(CT_HoaDon::tinhThanhTien).sum();
         double phanTramGiam = (double)0.0F;
         if (this.khuyenMai != null && this.khuyenMai.getPhanTramGiam() != null) {
            phanTramGiam = this.khuyenMai.getPhanTramGiam();
         }

         return tongChuaGiam * ((double)1.0F - phanTramGiam);
      } else {
         return (double)0.0F;
      }
   }

   public String getMaHoaDon() {
      return this.maHoaDon;
   }

   public void setMaHoaDon(String maHoaDon) {
      if (maHoaDon != null && !maHoaDon.trim().isEmpty()) {
         this.maHoaDon = maHoaDon;
      } else {
         throw new IllegalArgumentException("Mã Hóa đơn không được rỗng");
      }
   }

   public LocalDateTime getNgayLap() {
      return this.ngayLap;
   }

   public void setNgayLap(LocalDateTime ngayLap) {
      if (ngayLap == null) {
         this.ngayLap = LocalDateTime.now();
      } else {
         this.ngayLap = ngayLap;
      }

   }

   public String getTrangThaiThanhToan() {
      return this.trangThaiThanhToan;
   }

   public void setTrangThaiThanhToan(String trangThaiThanhToan) {
      if (trangThaiThanhToan != null && !trangThaiThanhToan.trim().isEmpty()) {
         this.trangThaiThanhToan = trangThaiThanhToan;
      } else {
         this.trangThaiThanhToan = "Chờ thanh toán";
      }

   }

   public List<CT_HoaDon> getDanhSachChiTietHoaDon() {
      return this.danhSachChiTietHoaDon;
   }

   public void setDanhSachChiTietHoaDon(List<CT_HoaDon> danhSachChiTietHoaDon) {
      this.danhSachChiTietHoaDon = danhSachChiTietHoaDon;
   }

   public TheThanhVien getTheThanhVien() {
      return this.theThanhVien;
   }

   public void setTheThanhVien(TheThanhVien theThanhVien) {
      this.theThanhVien = theThanhVien;
   }

   public NhanVien getNhanVien() {
      return this.nhanVien;
   }

   public void setNhanVien(NhanVien nhanVien) {
      if (nhanVien == null) {
         throw new IllegalArgumentException("Hóa đơn phải có Nhân viên lập");
      } else {
         this.nhanVien = nhanVien;
      }
   }

   public Ban getBan() {
      return this.ban;
   }

   public void setBan(Ban ban) {
      if (ban == null) {
         throw new IllegalArgumentException("Hóa đơn phải liên kết với Bàn");
      } else {
         this.ban = ban;
      }
   }

   public BanDat getBanDat() {
      return this.banDat;
   }

   public void setBanDat(BanDat banDat) {
      this.banDat = banDat;
   }

   public KhuyenMai getKhuyenMai() {
      return this.khuyenMai;
   }

   public void setKhuyenMai(KhuyenMai khuyenMai) {
      this.khuyenMai = khuyenMai;
   }

   public double tinhTongGiamGia() {
      double giamTV = (double)0.0F;
      double giamKM = (double)0.0F;
      double tong = this.tinhTongTien();
      if (this.theThanhVien != null && this.theThanhVien.getLoaiHang() != null) {
         label25: {
            switch (this.theThanhVien.getLoaiHang()) {
               case "Kim cương":
                  giamTV = tong * 0.15;
                  break label25;
               case "Vàng":
                  giamTV = tong * 0.12;
                  break label25;
            }

            giamTV = tong * 0.1;
         }
      }

      if (this.khuyenMai != null) {
         giamKM = tong * this.khuyenMai.getPhanTramGiam();
      }

      return giamTV + giamKM;
   }

   public String toString() {
      String var10000 = this.maHoaDon;
      return "HoaDon [maHD=" + var10000 + ", ngayLap=" + String.valueOf(this.ngayLap) + ", tongTien=" + this.tinhTongTien() + "]";
   }
}

