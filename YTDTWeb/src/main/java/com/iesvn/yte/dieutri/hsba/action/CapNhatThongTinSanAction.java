 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaSanDelegate;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaSan;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiSinh;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.Date;
 import org.apache.commons.lang.time.DateUtils;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B211_2_CapNhatThongTinSan")
 @Synchronized(timeout=6000000L)
 public class CapNhatThongTinSanAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinSanAction.class);
   private HsbaSan hsbaSan;
   @In(required=false)
   @Out(required=false)
   private String soBenhAn;
   @In(required=false)
   @Out(required=false)
   private String khoaMa;
   @In(required=false)
   @Out(required=false)
   private String goToB211_2 = null;
   private Integer giosinh;
   private Integer giochet;
   private String donViTuoi;
   private String ghiNhanException;

   @Factory("goToB211_2")
   @Create
   @Begin(nested=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");


     log.info("soBenhAn = " + this.soBenhAn);
     log.info("khoaMa = " + this.khoaMa);
     HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(this.soBenhAn, this.khoaMa);
     log.info("hsbaChuyenMon = " + hsbaChuyenMon);
     if (hsbaChuyenMon != null) {
       getHsbaSan(true).setHsbacmMa(hsbaChuyenMon);
     }
     if ((getHsbaSan() != null) && (getHsbaSan().getHsbasanNgaygiosinh() != null)) {
       this.giosinh = getSeconds(getHsbaSan().getHsbasanNgaygiosinh());
     } else {
       this.giosinh = Integer.valueOf(0);
     }
     if ((getHsbaSan() != null) && (getHsbaSan().getHsbasanNgaygiochet() != null)) {
       this.giochet = getSeconds(getHsbaSan().getHsbasanNgaygiochet());
     } else {
       this.giochet = Integer.valueOf(0);
     }
     log.info("***Finished init **");
     return "DieuTri_CapNhat_CapNhatThongTinSan";
   }

   @End
   public String back()
     throws Exception
   {
     log.info("***Starting back ***");
     log.info("***Finished back **");
     return "DieuTri_CapNhat_CapNhatThongTinChung";
   }

   public void ghinhan()
   {
     log.info("***Starting ghinhan **");
     this.ghiNhanException = null;
     HsbaSanDelegate hsbaSanDelegate = HsbaSanDelegate.getInstance();
     if (this.hsbaSan != null)
     {
       if ((this.hsbaSan.getDmloaisinhMaso() != null) && (this.hsbaSan.getDmloaisinhMaso().getDmloaisinhMaso() == null)) {
         this.hsbaSan.setDmloaisinhMaso(null);
       }
       if ((this.hsbaSan.getHsbasanMabenh() != null) && (this.hsbaSan.getHsbasanMabenh().getDmbenhicdMaso() == null)) {
         this.hsbaSan.setHsbasanMabenh(null);
       }
       if ((this.hsbaSan.getHsbasanMatuvong() != null) && (this.hsbaSan.getHsbasanMatuvong().getDmbenhicdMaso() == null)) {
         this.hsbaSan.setHsbasanMatuvong(null);
       }
       if ((this.hsbaSan.getHsbasanChuyenkhoa() != null) && (this.hsbaSan.getHsbasanChuyenkhoa().getDmkhoaMaso() == null)) {
         this.hsbaSan.setHsbasanChuyenkhoa(null);
       }
       try
       {
         if ((getHsbaSan() != null) && (getHsbaSan().getHsbasanNgaygiosinh() != null)) {
           getHsbaSan().setHsbasanNgaygiosinh(DateUtils.addSeconds(DateUtils.truncate(getHsbaSan().getHsbasanNgaygiosinh(), 5), this.giosinh.intValue()));
         }
         if ((getHsbaSan() != null) && (getHsbaSan().getHsbasanNgaygiochet() != null)) {
           getHsbaSan().setHsbasanNgaygiochet(DateUtils.addSeconds(DateUtils.truncate(getHsbaSan().getHsbasanNgaygiochet(), 5), this.giochet.intValue()));
         }
         if (this.hsbaSan.getHsbasanMa() == null)
         {
           log.info("create HsbaSan ...");

           this.hsbaSan = hsbaSanDelegate.create(this.hsbaSan);
           log.info("create HsbaSan ... end.");
           FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.SO_BENH_AN, this.hsbaSan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien() });
         }
         else
         {
           log.info("edit HsbaSan ...");
           hsbaSanDelegate.edit(this.hsbaSan);
           log.info("edit HsbaSan ... end");
           FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.SO_BENH_AN, this.hsbaSan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien() });
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         this.ghiNhanException = e.getClass().getName();
         log.error("Ghi nhan khong thanh cong");
         log.error("Loi : " + e);
       }
     }
     log.info("***Finished ghinhan **");
   }

   public void nhaplai()
     throws Exception
   {
     try
     {
       resetForm();
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   private void resetForm()
   {
     log.info("Begining ResetForm(): ");
     this.hsbaSan = null;
     this.ghiNhanException = null;
     this.donViTuoi = null;
     log.info("End ResetForm(): ");
   }

   public Integer getSeconds(Date d)
   {
     long seconds = DateUtils.getFragmentInHours(d, 5) * 60L * 60L + DateUtils.getFragmentInMinutes(d, 11) * 60L;
     return Integer.valueOf(seconds + "");
   }

   public HsbaSan getHsbaSan(boolean create)
   {
     if ((create) && (this.hsbaSan == null)) {
       this.hsbaSan = new HsbaSan();
     }
     return this.hsbaSan;
   }

   public HsbaSan getHsbaSan()
   {
     return this.hsbaSan;
   }

   public void setHsbaSan(HsbaSan hsbaSan)
   {
     this.hsbaSan = hsbaSan;
   }

   public String getGhiNhanException()
   {
     return this.ghiNhanException;
   }

   public void setGhiNhanException(String ghiNhanException)
   {
     this.ghiNhanException = ghiNhanException;
   }

   public String getDonViTuoi()
   {
     return this.donViTuoi;
   }

   public void setDonViTuoi(String donViTuoi)
   {
     this.donViTuoi = donViTuoi;
   }

   public Integer getGiosinh()
   {
     return this.giosinh;
   }

   public void setGiosinh(Integer giosinh)
   {
     this.giosinh = giosinh;
   }

   public String getGoToB211_2()
   {
     return this.goToB211_2;
   }

   public void setGoToB211_2(String goToB211_2)
   {
     this.goToB211_2 = goToB211_2;
   }

   public Integer getGiochet()
   {
     return this.giochet;
   }

   public void setGiochet(Integer giochet)
   {
     this.giochet = giochet;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinSanAction

 * JD-Core Version:    0.7.0.1

 */