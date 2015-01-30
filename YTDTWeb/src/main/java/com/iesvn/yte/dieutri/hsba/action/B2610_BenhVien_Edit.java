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
 import javax.faces.model.ListDataModel;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B2610_BenhVien_Edit")
 @Scope(ScopeType.SESSION)
 public class B2610_BenhVien_Edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmBenhVien benhvien;
   private List<SelectItem> listB2610_Tinh;
   private List<SelectItem> listB2610_Tuyen;
   private List<SelectItem> listB2610_LoaiBenhVien;
   private List<SelectItem> listB2610_VungMien;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B2610 Benh vien Edit....", new Object[0]);

     resetValue();
     load_rowIndex();
     initCbo();
     return "/B2_Dieutri/B2610_benhvien_edit";
   }

   public void reset()
   {
     this.log.info("Reset B2610 Benh vien Edit....", new Object[0]);

     resetValue();
   }

   public void load_rowIndex()
   {
     this.log.info("Load rowIndex B2610 Benh vien Edit ....", new Object[0]);

     int rowIndex = -1;

     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listB2610");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1)
     {
       this.benhvien = ((DmBenhVien)((List)lsDataModel.getWrappedData()).get(rowIndex));
       if (this.benhvien.getDmtinhMaso() == null) {
         this.benhvien.setDmtinhMaso(new DmTinh());
       }
       if (this.benhvien.getDmtuyenMaso() == null) {
         this.benhvien.setDmtuyenMaso(new DmTuyen());
       }
       if (this.benhvien.getDmloaibvMaso() == null) {
         this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
       }
       if (this.benhvien.getDmvungmienMaso() == null) {
         this.benhvien.setDmvungmienMaso(new DmVungMien());
       }
     }
     this.log.info("rowIndex =" + rowIndex, new Object[0]);
   }

   public void save()
   {
     this.log.info("Save B2610 Benh vien Edit....", new Object[0]);

     String ma = "";
     Date date = new Date();
     this.benhvien.setDmbenhvienNgaygiocn(Double.valueOf(date.getTime()));

     ma = this.benhvien.getDmbenhvienMa();


     this.benhvien.setDmbenhvienDt(Boolean.valueOf(true));
     if (this.benhvien.getDmtinhMaso().getDmtinhMaso() == null) {
       this.benhvien.setDmtinhMaso(null);
     }
     if (this.benhvien.getDmtuyenMaso().getDmtuyenMaso() == null) {
       this.benhvien.setDmtuyenMaso(null);
     }
     if (this.benhvien.getDmloaibvMaso().getDmloaibvMaso() == null) {
       this.benhvien.setDmloaibvMaso(null);
     }
     if (this.benhvien.getDmvungmienMaso().getDmvungmienMaso() == null) {
       this.benhvien.setDmvungmienMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.benhvien);
       FacesMessages.instance().add(IConstantsRes.benhvien_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);

       FacesMessages.instance().add(IConstantsRes.benhvien_up_fa, new Object[] { ma });
     }
     finally
     {
       resetValue();
     }
   }

   private void resetValue()
   {
     this.benhvien = new DmBenhVien();
     this.benhvien.setDmtinhMaso(new DmTinh());
     this.benhvien.setDmloaibvMaso(new DmLoaiBenhVien());
     this.benhvien.setDmvungmienMaso(new DmVungMien());
     this.benhvien.setDmtuyenMaso(new DmTuyen());
   }

   private void initCbo()
   {
     this.log.info("Init Select items for combobox....", new Object[0]);

     initCboTinh();
     initCboTuyen();
     initCboLoaibv();
     initCboVungmien();
   }

   private void initCboTinh()
   {
     this.log.info("getList_tinh_item B2610 Benh vien Edit....", new Object[0]);

     this.listB2610_Tinh = new ArrayList();

     this.listB2610_Tinh.add(new SelectItem(null, "chá»�n tá»‰nh..."));

     List<DmTinh> list = DieuTriUtilDelegate.getInstance().findAll("DmTinh");
     for (DmTinh item : list) {
       this.listB2610_Tinh.add(new SelectItem(item.getDmtinhMaso(), item.getDmtinhTen()));
     }
   }

   private void initCboTuyen()
   {
     this.log.info("getList_Tuyen_item B2610 Benh vien Edit....", new Object[0]);

     this.listB2610_Tuyen = new ArrayList();
     this.listB2610_Tuyen.add(new SelectItem(null, "chá»�n tuyáº¿n..."));

     List<DmTuyen> list = DieuTriUtilDelegate.getInstance().findAll("DmTuyen", "dmtuyenDt", true);
     for (DmTuyen item : list) {
       this.listB2610_Tuyen.add(new SelectItem(item.getDmtuyenMaso(), item.getDmtuyenTen()));
     }
   }

   private void initCboLoaibv()
   {
     this.log.info("getList_Loai benhvien_item B2610 Benh vien Edit....", new Object[0]);

     this.listB2610_LoaiBenhVien = new ArrayList();
     this.listB2610_LoaiBenhVien.add(new SelectItem(null, "Chá»�n lá»�ai bá»‡nh viá»‡n..."));

     List<DmLoaiBenhVien> list = DieuTriUtilDelegate.getInstance().findAll("DmLoaiBenhVien", "dmloaibvDt", true);
     for (DmLoaiBenhVien item : list) {
       this.listB2610_LoaiBenhVien.add(new SelectItem(item.getDmloaibvMaso(), item.getDmloaibvTen()));
     }
   }

   private void initCboVungmien()
   {
     this.log.info("getList_Vungmien_item B2610 Benh vien Edit....", new Object[0]);

     this.listB2610_VungMien = new ArrayList();
     this.listB2610_VungMien.add(new SelectItem(null, "Chá»�n vÃ¹ng miá»�n..."));

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

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B2610_BenhVien_Edit

 * JD-Core Version:    0.7.0.1

 */