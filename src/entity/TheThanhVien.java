// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package entity;

import java.util.Objects;

public class TheThanhVien {
   private String maThe;
   private KhachHang khachHang;
   private int diemTichLuy;
   private String loaiHang;

   public static String tinhLoaiHang(int diem) {
      if (diem >= 250) {
         return "Kim cương";
      } else {
         return diem >= 100 ? "Vàng" : "Bạc";
      }
   }

   public TheThanhVien(String maThe, KhachHang khachHang, int diemTichLuy) {
      this.maThe = maThe;
      this.khachHang = khachHang;
      this.setDiemTichLuy(diemTichLuy);
   }

   public TheThanhVien(String maThe, KhachHang khachHang, int diemTichLuy, String loaiHangTuCSDL) {
      this.maThe = maThe;
      this.khachHang = khachHang;
      this.diemTichLuy = diemTichLuy;
      this.loaiHang = loaiHangTuCSDL != null ? loaiHangTuCSDL : tinhLoaiHang(diemTichLuy);
   }

   public TheThanhVien(String maThe) {
      this.maThe = maThe;
   }

   public String getMaThe() {
      return this.maThe;
   }

   public KhachHang getKhachHang() {
      return this.khachHang;
   }

   public int getDiemTichLuy() {
      return this.diemTichLuy;
   }

   public String getLoaiHang() {
      return this.loaiHang;
   }

   public void setMaThe(String maThe) {
      this.maThe = maThe;
   }

   public void setKhachHang(KhachHang khachHang) {
      this.khachHang = khachHang;
   }

   public void setDiemTichLuy(int diemTichLuy) {
      if (diemTichLuy < 0) {
         diemTichLuy = 0;
      }

      this.diemTichLuy = diemTichLuy;
      this.loaiHang = tinhLoaiHang(diemTichLuy);
   }

   public void setLoaiHang(String loaiHang) {
      this.loaiHang = loaiHang;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof TheThanhVien)) {
         return false;
      } else {
         TheThanhVien that = (TheThanhVien)o;
         return Objects.equals(this.maThe, that.maThe);
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.maThe});
   }
}

