// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package entity;

public class CT_HoaDon {
   private int soLuong;
   private HoaDon hoaDon;
   private MonAn monAn;

   public CT_HoaDon(HoaDon hoaDon, MonAn monAn) {
      this.hoaDon = hoaDon;
      this.monAn = monAn;
   }

   public CT_HoaDon(HoaDon hoaDon, MonAn monAn, int soLuong) {
      this.hoaDon = hoaDon;
      this.monAn = monAn;
      this.soLuong = soLuong;
   }

   public int getSoLuong() {
      return this.soLuong;
   }

   public HoaDon getHoaDon() {
      return this.hoaDon;
   }

   public MonAn getMonAn() {
      return this.monAn;
   }

   public double tinhThanhTien() {
      if (this.monAn == null) {
         throw new RuntimeException("Không có thông tin món ăn để tính thành tiền.");
      } else {
         return (double)this.soLuong * this.monAn.getGiaMonAn();
      }
   }

   public void setSoLuong(int soLuong) {
      if (soLuong <= 0) {
         throw new RuntimeException("Số lượng phải lớn hơn 0");
      } else {
         this.soLuong = soLuong;
      }
   }

   public void setHoaDon(HoaDon hoaDon) {
      if (hoaDon == null) {
         throw new RuntimeException("Chi tiết phải có Hóa đơn");
      } else {
         this.hoaDon = hoaDon;
      }
   }

   public void setMonAn(MonAn monAn) {
      if (monAn == null) {
         throw new RuntimeException("Chi tiết phải có Món ăn");
      } else {
         this.monAn = monAn;
      }
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.hoaDon == null ? 0 : this.hoaDon.hashCode());
      result = 31 * result + (this.monAn == null ? 0 : this.monAn.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         CT_HoaDon other = (CT_HoaDon)obj;
         if (this.hoaDon == null) {
            if (other.hoaDon != null) {
               return false;
            }
         } else if (!this.hoaDon.equals(other.hoaDon)) {
            return false;
         }

         if (this.monAn == null) {
            if (other.monAn != null) {
               return false;
            }
         } else if (!this.monAn.equals(other.monAn)) {
            return false;
         }

         return true;
      } else {
         return false;
      }
   }
}

