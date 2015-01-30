 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.HsbaNopDelegate;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaNop;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B251_Timsobenhansoluutru")
 @Synchronized(timeout=6000000L)
 public class TimSoBenhAnSoLuuTru
   implements Serializable
 {
   private static Logger log = Logger.getLogger(TimSoBenhAnSoLuuTru.class);
   private String sovaovien;
   private String soluutru;
   private String maKhoa;
   private Integer maSoKhoa;

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Create
   @Begin(join=true)
   public String init()
   {
     log.info("begin init()");

     resetValue();

     log.info("end init()");

     return "DieuTri_DichVuTienIch_TimBenhAnSoLuuTru";
   }

   @End
   public void end() {}

   private void resetValue()
   {
     this.sovaovien = "";
     this.soluutru = "";
     this.maKhoa = "";
     this.maSoKhoa = null;
   }

   public void nhaplai()
     throws Exception
   {
     resetValue();
   }

   public void displayInforSoLuuTru()
     throws Exception
   {
     if ((this.soluutru == null) || (this.soluutru.equals("")))
     {
       resetValue();

       return;
     }
     HsbaNopDelegate hsbanopdele = HsbaNopDelegate.getInstance();
     HsbaNop hsbaNop = hsbanopdele.findBySoLuuTru(this.soluutru);
     if (hsbaNop != null)
     {
       this.soluutru = hsbaNop.getHsbanopSoluutru();
       if ((this.soluutru == null) || (this.soluutru.equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru });
         resetValue();
         return;
       }
       this.sovaovien = hsbaNop.getHsbaSovaovien(true).getHsbaSovaovien();
       DmKhoa khoa = hsbaNop.getHsbaSovaovien(true).getHsbaKhoarav();
       if (khoa != null)
       {
         this.maKhoa = khoa.getDmkhoaMa();
         this.maSoKhoa = khoa.getDmkhoaMaso();
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru });
       resetValue();
     }
   }

   public void displayInforSoVaoVien()
     throws Exception
   {
     if ((this.sovaovien == null) || (this.sovaovien.equals("")))
     {
       resetValue();
       return;
     }
     HsbaNopDelegate hsbanopdele = HsbaNopDelegate.getInstance();
     HsbaNop hsbaNop = hsbanopdele.findBySoVaoVien(this.sovaovien);
     if (hsbaNop != null)
     {
       this.soluutru = hsbaNop.getHsbanopSoluutru();
       if ((this.soluutru == null) || (this.soluutru.equals("")))
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru });
         resetValue();
         return;
       }
       this.sovaovien = hsbaNop.getHsbaSovaovien(true).getHsbaSovaovien();
       DmKhoa khoa = hsbaNop.getHsbaSovaovien(true).getHsbaKhoarav();
       if (khoa != null)
       {
         this.maKhoa = khoa.getDmkhoaMa();
         this.maSoKhoa = khoa.getDmkhoaMaso();
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.HSBA_NOP_NULL, new Object[] { this.soluutru });
       resetValue();
     }
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getSovaovien()
   {
     return this.sovaovien;
   }

   public void setSovaovien(String sovaovien)
   {
     this.sovaovien = sovaovien;
   }

   public String getSoluutru()
   {
     return this.soluutru;
   }

   public void setSoluutru(String soluutru)
   {
     this.soluutru = soluutru;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public Integer getMaSoKhoa()
   {
     return this.maSoKhoa;
   }

   public void setMaSoKhoa(Integer maSoKhoa)
   {
     this.maSoKhoa = maSoKhoa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.TimSoBenhAnSoLuuTru

 * JD-Core Version:    0.7.0.1

 */