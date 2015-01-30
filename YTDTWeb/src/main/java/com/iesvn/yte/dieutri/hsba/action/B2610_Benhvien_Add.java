 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmLoaiBenhVien;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmTuyen;
 import com.iesvn.yte.entity.DmVungMien;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B2610_Benhvien_Add")
 @Scope(ScopeType.SESSION)
 public class B2610_Benhvien_Add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmBenhVien benhvien;
   @Logger
   private Log log;
   private List<SelectItem> listB2610_Tinh;
   private List<SelectItem> listB2610_Tuyen;
   private List<SelectItem> listB2610_LoaiBenhVien;
   private List<SelectItem> listB2610_VungMien;

   @Create
   public String init()
   {
     this.log.info("Init B2610 Benh vien add....", new Object[0]);

     resetValue();
     initCbo();

     return "/B2_Dieutri/B2610_benhvien_add";
   }

   public void save()
   {
     this.log.info("Save B2610 Benh vien_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.benhvien.getDmbenhvienMa();
     this.benhvien.setDmbenhvienDt(Boolean.valueOf(true));
     this.benhvien.setDmbenhvienNgaygiocn(Double.valueOf(date.getTime()));
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.benhvien);
       FacesMessages.instance().add(IConstantsRes.benhvien_cr_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.benhvien_cr_fa, new Object[] { ma });
     }
   }

   public void reset()
   {
     this.log.info("Reset B2610 Benh vien_add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.benhvien = new DmBenhVien();
     this.benhvien.setDmtinhMaso(new DmTinh());
     this.benhvien.setDmtuyenMaso(new DmTuyen());
     this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
     this.benhvien.setDmvungmienMaso(new DmVungMien());
   }

   private void initCbo()
   {
     this.log.info("Init Select items for combobox....", new Object[0]);

     initCboTinh();

     initCboTuyen();
     initCboLoaiBenhVien();
     initCboVungMien();
   }

   private void initCboTinh()
   {
     this.log.info("getList_Tinh_item B2610 Benh vien_add....", new Object[0]);

     this.listB2610_Tinh = new ArrayList();

     List<DmTinh> list = DieuTriUtilDelegate.getInstance().findAll("DmTinh");
     for (DmTinh item : list) {
       this.listB2610_Tinh.add(new SelectItem(item.getDmtinhMaso(), item.getDmtinhTen()));
     }
   }

   private void initCboTuyen()
   {
     this.log.info("getList_Tuyen_item B2610 Benh vien_add....", new Object[0]);

     this.listB2610_Tuyen = new ArrayList();
     List<DmTuyen> list = DieuTriUtilDelegate.getInstance().findAll("DmTuyen", "dmtuyenDt", true);
     for (DmTuyen item : list) {
       this.listB2610_Tuyen.add(new SelectItem(item.getDmtuyenMaso(), item.getDmtuyenTen()));
     }
   }

   private void initCboLoaiBenhVien()
   {
     this.log.info("getList_Loai Benh Vien _item B2610 Benh vien_add....", new Object[0]);

     this.listB2610_LoaiBenhVien = new ArrayList();
     List<DmLoaiBenhVien> list = DieuTriUtilDelegate.getInstance().findAll("DmLoaiBenhVien", "dmloaibvDt", true);
     for (DmLoaiBenhVien item : list) {
       this.listB2610_LoaiBenhVien.add(new SelectItem(item.getDmloaibvMaso(), item.getDmloaibvTen()));
     }
   }

   private void initCboVungMien()
   {
     this.log.info("getList_Vung Mien_item B2610 Benh vien_add....", new Object[0]);

     this.listB2610_VungMien = new ArrayList();
     List<DmVungMien> list = DieuTriUtilDelegate.getInstance().findAll("DmVungMien");
     for (DmVungMien item : list) {
       this.listB2610_VungMien.add(new SelectItem(item.getDmvungmienMaso(), item.getDmvungmienTen()));
     }
   }

   public DmBenhVien getBenhvien()
   {
     return this.benhvien;
   }

   public void setBenhvien(DmBenhVien benhvien)
   {
     this.benhvien = benhvien;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public List<SelectItem> getListB2610_Tinh()
   {
     return this.listB2610_Tinh;
   }

   public void setListB2610_Tinh(List<SelectItem> listB2610_Tinh)
   {
     this.listB2610_Tinh = listB2610_Tinh;
   }

   public List<SelectItem> getListB2610_Tuyen()
   {
     return this.listB2610_Tuyen;
   }

   public void setListB2610_Tuyen(List<SelectItem> listB2610_Tuyen)
   {
     this.listB2610_Tuyen = listB2610_Tuyen;
   }

   public List<SelectItem> getListB2610_LoaiBenhVien()
   {
     return this.listB2610_LoaiBenhVien;
   }

   public void setListB2610_LoaiBenhVien(List<SelectItem> listB2610_LoaiBenhVien)
   {
     this.listB2610_LoaiBenhVien = listB2610_LoaiBenhVien;
   }

   public List<SelectItem> getListB2610_VungMien()
   {
     return this.listB2610_VungMien;
   }

   public void setListB2610_VungMien(List<SelectItem> listB2610_VungMien)
   {
     this.listB2610_VungMien = listB2610_VungMien;
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B2610_Benhvien_Add

 * JD-Core Version:    0.7.0.1

 */