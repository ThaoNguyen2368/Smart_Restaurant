// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package entity;

import java.util.Objects;

public class KhachHang {
   private String maKH;
   private String hoTenKH;
   private String soDienThoai;
   private String email;
   private boolean gioiTinh;

   public KhachHang() {
   }

   public KhachHang(String maKH) {
      this.maKH = maKH;
   }

   public KhachHang(String maKH, String hoTenKH, String soDienThoai, String email, boolean gioiTinh) {
      this.maKH = maKH;
      this.hoTenKH = hoTenKH;
      this.soDienThoai = soDienThoai;
      this.email = email;
      this.gioiTinh = gioiTinh;
   }

   public String getMaKH() {
      return this.maKH;
   }

   public String getHoTenKH() {
      return this.hoTenKH;
   }

   public String getSoDienThoai() {
      return this.soDienThoai;
   }

   public String getEmail() {
      return this.email;
   }

   public boolean isGioiTinh() {
      return this.gioiTinh;
   }

   public void setMaKH(String maKH) {
      if (maKH != null && !maKH.trim().isEmpty()) {
         if (!maKH.matches("^KH[0-9]{3}$")) {
            throw new IllegalArgumentException("Mã khách hàng phải theo định dạng KHxxx (ví dụ: KH001).");
         } else {
            this.maKH = maKH;
         }
      } else {
         throw new IllegalArgumentException("Mã khách hàng không được để trống.");
      }
   }

   public void setHoTenKH(String hoTenKH) {
      if (hoTenKH != null && !hoTenKH.trim().isEmpty()) {
         this.hoTenKH = hoTenKH;
      } else {
         throw new IllegalArgumentException("Họ tên khách hàng không được để trống.");
      }
   }

   public void setSoDienThoai(String soDienThoai) {
      if (soDienThoai != null && !soDienThoai.trim().isEmpty()) {
         if (!soDienThoai.matches("^(09|03)[0-9]{8}$")) {
            throw new IllegalArgumentException("Số điện thoại phải có 10 chữ số và bắt đầu bằng 09 hoặc 03.");
         } else {
            this.soDienThoai = soDienThoai;
         }
      } else {
         throw new IllegalArgumentException("Số điện thoại không được để trống.");
      }
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setGioiTinh(boolean gioiTinh) {
      this.gioiTinh = gioiTinh;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.maKH});
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         KhachHang other = (KhachHang)obj;
         return Objects.equals(this.maKH, other.maKH);
      } else {
         return false;
      }
   }

   public String toString() {
      return "KhachHang [maKH=" + this.maKH + ", hoTenKH=" + this.hoTenKH + ", soDienThoai=" + this.soDienThoai + ", email=" + this.email + ", gioiTinh=" + this.gioiTinh + "]";
   }
}

