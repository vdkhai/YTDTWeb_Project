 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DuTruThucPhamDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
 import com.iesvn.yte.dieutri.entity.DuTruThucPham;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.apache.commons.beanutils.BeanUtils;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.SESSION)
 @Name("B3152_DuTruThucPham")
 public class B3152_DuTruThucPhamAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String ngaynhap;
   private String soluong;
   private DuTruThucPham dutruTP;
   private String dvtTen;
   private String ngayhientai;
   private boolean lockedSaveButton;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strDuTruTP;
   @Logger
   private Log log;

   @Factory("strDuTruTP")
   public void init()
   {
     reset();
     this.strDuTruTP = "";
   }

   public void reset()
   {
     this.ngaynhap = this.sdf.format(new Date());
     this.dutruTP = new DuTruThucPham();
     this.dutruTP.setDtdmltpMaso(new DtDmLoaiThucPham());
     this.soluong = "";
     this.dvtTen = "";
     this.ngayhientai = this.sdf.format(new Date());
     this.lockedSaveButton = false;
     FacesMessages.instance().clear();
   }

   public void saveDuTruTP()
   {
     this.log.info("begin save saveDuTruTP()", new Object[0]);
     try
     {
       this.dutruTP.setDttpNgaydutru(this.sdf.parse(this.ngaynhap));
       this.dutruTP.setDttpSlthucdat(new Float(this.soluong));

       DuTruThucPhamDelegate dttpDel = DuTruThucPhamDelegate.getInstance();
       DuTruThucPham dttpTmp = dttpDel.findByLoaiTPNgayDutru(this.dutruTP.getDtdmltpMaso().getDtdmltpMa(), this.sdf.parse(this.ngaynhap));
       if (dttpTmp == null)
       {
         DuTruThucPhamDelegate.getInstance().create(this.dutruTP);
         FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
         this.lockedSaveButton = true;
       }
       else if (this.dutruTP.getDttpMaso() != null)
       {
         DuTruThucPhamDelegate.getInstance().edit(this.dutruTP);
         FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
         this.lockedSaveButton = true;
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.DU_TRU_TP_EXISTS, new Object[] { this.ngaynhap });
         this.lockedSaveButton = false;
       }
       this.dutruTP = new DuTruThucPham();
       this.dutruTP.setDtdmltpMaso(new DtDmLoaiThucPham());
       this.soluong = "";
     }
     catch (Exception e)
     {
       this.log.info("ERROR : " + e.toString(), new Object[0]);
       e.printStackTrace();
       FacesMessages.instance().add(IConstantsRes.INSERT_FAIL, new Object[0]);
       this.lockedSaveButton = false;
     }
     this.log.info("end save", new Object[0]);
   }

   public void searchPhieuDuTruTP()
   {
     this.log.info("--enter searchPhieuDuTruTP()--- ma TP = " + this.dutruTP.getDtdmltpMaso(), new Object[0]);
     try
     {
       DuTruThucPhamDelegate dttpDel = DuTruThucPhamDelegate.getInstance();
       DuTruThucPham dttpTmp = dttpDel.findByLoaiTPNgayDutru(this.dutruTP.getDtdmltpMaso().getDtdmltpMa(), this.sdf.parse(this.ngaynhap));
       if (dttpTmp != null)
       {
         this.dutruTP = ((DuTruThucPham)BeanUtils.cloneBean(dttpTmp));
         this.soluong = ("" + this.dutruTP.getDttpSlthucdat());
         FacesMessages.instance().clear();
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     this.log.info("--exit searchPhieuDuTruTP()---", new Object[0]);
   }

   public void showDvt()
   {
     this.log.info("begin showDvt(), donviTinh ma so = " + this.dutruTP.getDtdmltpMaso().getDmdonvitinhMaso(), new Object[0]);
     DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
     DtDmLoaiThucPham dmLoaiTP = (DtDmLoaiThucPham)dtUtil.findByMaSo(this.dutruTP.getDtdmltpMaso().getDtdmltpMaso(), "DtDmLoaiThucPham", "dtdmltpMaso");
     if (dmLoaiTP == null)
     {
       this.dvtTen = "";
       return;
     }
     this.dvtTen = ("(" + (dmLoaiTP.getDmdonvitinhMaso() == null ? "" : dmLoaiTP.getDmdonvitinhMaso().getDmdonvitinhTen()) + ")");
     this.log.info("end showDvt(), dvtTen = " + this.dvtTen, new Object[0]);
   }

   public String getNgaynhap()
   {
     return this.ngaynhap;
   }

   public void setNgaynhap(String ngaynhap)
   {
     this.ngaynhap = ngaynhap;
   }

   public String getSoluong()
   {
     return this.soluong;
   }

   public void setSoluong(String soluong)
   {
     this.soluong = soluong;
   }

   public boolean isLockedSaveButton()
   {
     return this.lockedSaveButton;
   }

   public void setLockedSaveButton(boolean lockedSaveButton)
   {
     this.lockedSaveButton = lockedSaveButton;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public DuTruThucPham getDutruTP()
   {
     return this.dutruTP;
   }

   public void setDutruTP(DuTruThucPham dutruTP)
   {
     this.dutruTP = dutruTP;
   }

   public String getDvtTen()
   {
     return this.dvtTen;
   }

   public void setDvtTen(String dvtTen)
   {
     this.dvtTen = dvtTen;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3152_DuTruThucPhamAction

 * JD-Core Version:    0.7.0.1

 */