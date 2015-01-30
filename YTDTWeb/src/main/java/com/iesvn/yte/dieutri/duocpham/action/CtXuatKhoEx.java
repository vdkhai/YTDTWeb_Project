 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.entity.CtXuatKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import java.io.Serializable;

 public class CtXuatKhoEx
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private CtXuatKho ctXuatKho;
   private TonKho tonkhoXuat;
   private TonKho tonkhoNhan;

   public void setCtXuatKho(CtXuatKho ctXuatKho)
   {
     this.ctXuatKho = ctXuatKho;
   }

   public CtXuatKho getCtXuatKho()
   {
     return this.ctXuatKho;
   }

   public void setTonkhoXuat(TonKho tonkhoXuat)
   {
     this.tonkhoXuat = tonkhoXuat;
   }

   public TonKho getTonkhoXuat()
   {
     return this.tonkhoXuat;
   }

   public void setTonkhoNhan(TonKho tonkhoNhan)
   {
     this.tonkhoNhan = tonkhoNhan;
   }

   public TonKho getTonkhoNhan()
   {
     return this.tonkhoNhan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.CtXuatKhoEx

 * JD-Core Version:    0.7.0.1

 */