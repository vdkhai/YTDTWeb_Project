 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.util.Date;
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
 @Name("B3218_XacNhanTreEm")
 @Synchronized(timeout=6000000L)
 public class XacNhanTreEmAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(XacNhanTreEmAction.class);
   private HsbaBhyt hsbaBhyt;
   private String ghiNhanException;
   private String donViTuoi;
   private boolean isNew = true;

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Create
   @Begin(join=true)
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     getHsbaBhyt(true).setHsbabhytNgaygiocn(new Date());

     log.info("***Finished init **");
   }

   @End
   public String back()
     throws Exception
   {
     log.info("***Starting back ***");

     log.info("***Finished back **");
     return "MyMainForm";
   }

   public void layTheoSoVaoVien()
     throws Exception
   {
     if (getHsbaBhyt() == null) {
       return;
     }
     log.info("***Starting layTheoSoVaoVien ***");
     String khoa = "null";
     String soVaoVien = "null";
     if ((getHsbaBhyt().getHsbaSovaovien() != null) && (getHsbaBhyt().getHsbaSovaovien().getHsbaSovaovien() != null) && (getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt() != null) && (getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaMa() != null)) {
       try
       {
         khoa = getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaMa();
         soVaoVien = getHsbaBhyt().getHsbaSovaovien().getHsbaSovaovien();
         qryBenhNhanBhtyThanhToan(khoa, soVaoVien);
         if (getHsbaBhyt() == null) {
           FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien });
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien });
         log.error(e.toString());
       }
     }
     log.info("***Finished layTheoSoVaoVien **");
   }

   private void qryBenhNhanBhtyThanhToan(String khoa, String soVaoVien)
   {
     HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
     this.hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienKhoadangdtLastHsbaBhyt(soVaoVien, khoa);
     this.isNew = true;
     if (this.hsbaBhyt != null)
     {
       this.isNew = false;
     }
     else
     {
       HsbaChuyenMonDelegate hsbaChuyenMonDelegate = HsbaChuyenMonDelegate.getInstance();
       HsbaChuyenMon hsbaChuyenMon = hsbaChuyenMonDelegate.findBySoVaoVien_MaKhoa(soVaoVien, khoa);
       getHsbaBhyt(true).setHsbaSovaovien(hsbaChuyenMon.getHsbaSovaovien());
       getHsbaBhyt().setHsbabhytNgaygiocn(new Date());
     }
     if ((getHsbaBhyt() != null) && (getHsbaBhyt().getHsbaSovaovien() != null) && (getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa() != null))
     {
       int dvt = getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
       this.donViTuoi = Utils.taoDonViTuoi(dvt);
       if (getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDantocMa() != null)
       {
         log.info("benhNhan.getDantocMa().getDmdantocMa()=" + getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDantocMa().getDmdantocMa());
         log.info("benhNhan.getDantocMa().getDmdantocTen()=" + getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDantocMa().getDmdantocTen());
       }
       if (getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDmgtMaso() != null) {
         log.info("benhNhan.getDmgtMaso()=" + getHsbaBhyt().getHsbaSovaovien().getBenhnhanMa().getDmgtMaso().getDmgtMaso());
       }
     }
   }

   public void ghinhan()
   {
     log.info("***Starting ghinhan **");
     this.ghiNhanException = null;

     HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
     if (getHsbaBhyt() != null)
     {
       if ((getHsbaBhyt().getHsbabhytMakcb() != null) && (getHsbaBhyt().getHsbabhytMakcb().getDmbenhvienMaso() == null)) {
         getHsbaBhyt().setHsbabhytMakcb(null);
       }
       try
       {
         DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
         DmDoiTuong dt = (DmDoiTuong)dieuTriUtilDelegate.findByMa(Utils.maDoiTuongTreEm(), "DmDoiTuong", "dmdoituongMa");
         if (dt != null) {
           getHsbaBhyt().getHsbaSovaovien().setDoituongMa(dt);
         }
         if (this.isNew) {
           this.hsbaBhyt = hsbaBhytDelegate.create(getHsbaBhyt());
         } else {
           hsbaBhytDelegate.edit(getHsbaBhyt());
         }
         FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, getHsbaBhyt().getHsbaSovaovien().getHsbaKhoadangdt().getDmkhoaMa(), IConstantsRes.SO_BENH_AN, getHsbaBhyt().getHsbaSovaovien().getHsbaSovaovien() });
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         log.error(e.toString());
         this.ghiNhanException = e.getClass().getName();
         log.error("Ghi nhan khong thanh cong");
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
     this.hsbaBhyt = null;
     getHsbaBhyt(true).setHsbabhytNgaygiocn(new Date());
     this.donViTuoi = null;
     log.info("End ResetForm(): ");
   }

   public HsbaBhyt getHsbaBhyt()
   {
     return this.hsbaBhyt;
   }

   public HsbaBhyt getHsbaBhyt(boolean create)
   {
     if ((create) && (this.hsbaBhyt == null)) {
       this.hsbaBhyt = new HsbaBhyt();
     }
     return this.hsbaBhyt;
   }

   public void setHsbaBhyt(HsbaBhyt hsbaBhyt)
   {
     this.hsbaBhyt = hsbaBhyt;
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.XacNhanTreEmAction

 * JD-Core Version:    0.7.0.1

 */