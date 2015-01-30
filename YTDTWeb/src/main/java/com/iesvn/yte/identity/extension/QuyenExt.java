 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.entity.VaiTro;
 import java.io.Serializable;

 public class QuyenExt
   implements Serializable
 {
   private VaiTro vaitro = null;
   private Boolean chon = Boolean.valueOf(false);

   public VaiTro getVaitro()
   {
     if (this.vaitro == null) {
       this.vaitro = new VaiTro();
     }
     return this.vaitro;
   }

   public void setVaitro(VaiTro vaitro)
   {
     this.vaitro = vaitro;
   }

   public Boolean getChon()
   {
     return this.chon;
   }

   public void setChon(Boolean chon)
   {
     this.chon = chon;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.QuyenExt

 * JD-Core Version:    0.7.0.1

 */