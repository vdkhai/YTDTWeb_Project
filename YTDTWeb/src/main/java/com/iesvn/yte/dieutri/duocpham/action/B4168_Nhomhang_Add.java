 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B4168_Nhomhang_Add")
 @Scope(ScopeType.SESSION)
 public class B4168_Nhomhang_Add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmPhanLoaiThuoc nhomhang;
   @Logger
   private Log log;
   private List<SelectItem> listLoaiThuocs;
   private List<SelectItem> listKhoPhats;
   private Boolean lamtron = Boolean.valueOf(false);

   public String init()
   {
     this.log.info("Init B4168_Nhomhang_Add....", new Object[0]);

     resetValue();
     initCbo();
     this.log.info("End Init B4168_Nhomhang_Add", new Object[0]);
     return "/B4_Duocpham/KhoChinh/B4168_nhomhang_add";
   }

   public void save()
   {
     this.log.info("Save B4168_Nhomhang_Add....", new Object[0]);
     String ma = "";
     Date date = new Date();
     if (this.lamtron.booleanValue()) {
       this.nhomhang.setDmphanloaithuocNhom3("1");
     } else {
       this.nhomhang.setDmphanloaithuocNhom3("0");
     }
     ma = this.nhomhang.getDmphanloaithuocMa();
     this.nhomhang.setDmphanloaithuocNgaygiocn(Double.valueOf(date.getTime()));
     if (this.nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso() == null) {
       this.nhomhang.setDmloaithuocMaso(null);
     }
     DmPhanLoaiThuoc plthuoc = (DmPhanLoaiThuoc)DieuTriUtilDelegate.getInstance().findByMa(this.nhomhang.getDmphanloaithuocMa(), "DmPhanLoaiThuoc", "dmphanloaithuocMa");
     if (plthuoc != null)
     {
       FacesMessages.instance().add(IConstantsRes.plthuoc_cr_ma_trung, new Object[] { ma });
       return;
     }
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.nhomhang);
       FacesMessages.instance().add(IConstantsRes.plthuoc_cr_su, new Object[] { ma });
       resetValue();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       e.printStackTrace();
       FacesMessages.instance().add(IConstantsRes.plthuoc_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack B4168 Nhom hang....", new Object[0]);
     resetValue();
     return "/B4_Duocpham/KhoChinh/B4168_nhomhang";
   }

   public void reset()
   {
     this.log.info("Reset B4168_nhomhang....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.nhomhang = new DmPhanLoaiThuoc();
     this.nhomhang.setDmphanloaithuocDt(Boolean.valueOf(true));
     this.lamtron = Boolean.valueOf(false);
   }

   private void initCbo()
   {
     this.log.info("Init Select items for combobox....", new Object[0]);
     initCboKhoPhat();
     initCboLoaithuoc();
   }

   private void initCboKhoPhat()
   {
     this.log.info("getList_Kho phat....", new Object[0]);
     this.listKhoPhats = new ArrayList();
     this.listKhoPhats.add(new SelectItem(null, ""));
     DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
     List<DmKhoa> list = dmKhoaDel.getKhoChinh_KhoLe();
     for (DmKhoa item : list) {
       this.listKhoPhats.add(new SelectItem(item.getDmkhoaMaso(), item.getDmkhoaTen()));
     }
   }

   private void initCboLoaithuoc()
   {
     this.log.info("getList_loai thuoc....", new Object[0]);

     this.listLoaiThuocs = new ArrayList();
     this.listLoaiThuocs.add(new SelectItem(null, ""));
     List<DmLoaiThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmLoaiThuoc", "dmloaithuocDt", true);
     for (DmLoaiThuoc item : list) {
       this.listLoaiThuocs.add(new SelectItem(item.getDmloaithuocMaso(), item.getDmloaithuocTen()));
     }
   }

   public DmPhanLoaiThuoc getNhomhang()
   {
     return this.nhomhang;
   }

   public void setNhomhang(DmPhanLoaiThuoc nhomhang)
   {
     this.nhomhang = nhomhang;
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

   public List<SelectItem> getListLoaiThuocs()
   {
     return this.listLoaiThuocs;
   }

   public void setListLoaiThuocs(List<SelectItem> listLoaiThuocs)
   {
     this.listLoaiThuocs = listLoaiThuocs;
   }

   public List<SelectItem> getListKhoPhats()
   {
     return this.listKhoPhats;
   }

   public void setListKhoPhats(List<SelectItem> listKhoPhats)
   {
     this.listKhoPhats = listKhoPhats;
   }

   public Boolean getLamtron()
   {
     return this.lamtron;
   }

   public void setLamtron(Boolean lamtron)
   {
     this.lamtron = lamtron;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.B4168_Nhomhang_Add

 * JD-Core Version:    0.7.0.1

 */