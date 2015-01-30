 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import java.io.Serializable;

 public class DmPhanLoaiExt
   implements Serializable
 {
   private DmPhanLoaiThuoc dmPhanLoai;
   private boolean checked;

   public void setDmPhanLoai(DmPhanLoaiThuoc dmPhanLoai)
   {
     this.dmPhanLoai = dmPhanLoai;
   }

   public DmPhanLoaiThuoc getDmPhanLoai()
   {
     return this.dmPhanLoai;
   }

   public void setChecked(boolean checked)
   {
     this.checked = checked;
   }

   public boolean isChecked()
   {
     return this.checked;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmPhanLoaiExt

 * JD-Core Version:    0.7.0.1

 */