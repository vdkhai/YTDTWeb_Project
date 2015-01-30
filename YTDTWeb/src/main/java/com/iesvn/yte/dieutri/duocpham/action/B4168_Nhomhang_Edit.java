 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.ListDataModel;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B4168_Nhomhang_Edit")
 @Scope(ScopeType.CONVERSATION)
 public class B4168_Nhomhang_Edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmPhanLoaiThuoc nhomhang;
   private List<SelectItem> listLoaiThuocs;
   private List<SelectItem> listKhoPhats;
   @Logger
   private Log log;
   private Boolean lamtron = Boolean.valueOf(false);

   @Begin(join=true)
   public String init()
   {
     this.log.info("Init B4168_Nhomhang_Edit....", new Object[0]);
     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listPhanLoaiThuocs");
     rowIndex = lsDataModel.getRowIndex();
     this.log.info("rowIndex..****************..:" + rowIndex, new Object[0]);
     if (rowIndex != -1)
     {
       resetValue();
       load_rowIndex(rowIndex, lsDataModel);
       initCbo();
       return "/B4_Duocpham/KhoChinh/B4168_nhomhang_edit";
     }
     return "";
   }

   public void reset()
   {
     this.log.info("Reset B4168_Nhomhang_Edit....", new Object[0]);
     resetValue();
   }

   public void load_rowIndex(int rowIndex, ListDataModel lsDataModel)
   {
     this.log.info("Load rowIndex B4168_Nhomhang_Edit ....", new Object[0]);
     DmKhoa dmKhoa;
     if (rowIndex != -1)
     {
       this.nhomhang = ((DmPhanLoaiThuoc)((List)lsDataModel.getWrappedData()).get(rowIndex));
       if (this.nhomhang.getDmloaithuocMaso() == null) {
         this.nhomhang.setDmloaithuocMaso(new DmLoaiThuoc());
       }
       System.out.println("nhom hang: " + this.nhomhang.getDmphanloaithuocNhom2());
       Integer khoMasoInt = Integer.valueOf(Integer.parseInt(this.nhomhang.getDmphanloaithuocNhom2()));
       String strLamtron = this.nhomhang.getDmphanloaithuocNhom3();
       if (strLamtron != null)
       {
         if (strLamtron.equals("1")) {
           this.lamtron = Boolean.valueOf(true);
         } else {
           this.lamtron = Boolean.valueOf(false);
         }
       }
       else {
         this.lamtron = Boolean.valueOf(false);
       }
       DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
       dmKhoa = (DmKhoa)dtDel.findByMaSo(khoMasoInt, "DmKhoa", "dmkhoaMaso");
     }
     this.log.info("rowIndex =" + rowIndex, new Object[0]);
   }

   public void save()
   {
     this.log.info("Save B4168_Nhomhang_Edit....", new Object[0]);

     String ma = "";
     Date date = new Date();
     this.nhomhang.setDmphanloaithuocNgaygiocn(Double.valueOf(date.getTime()));
     ma = this.nhomhang.getDmphanloaithuocMa();
     if (this.lamtron.booleanValue()) {
       this.nhomhang.setDmphanloaithuocNhom3("1");
     } else {
       this.nhomhang.setDmphanloaithuocNhom3("0");
     }
     if (this.nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso() == null) {
       this.nhomhang.setDmloaithuocMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.nhomhang);
       FacesMessages.instance().add(IConstantsRes.plthuoc_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.plthuoc_up_fa, new Object[] { ma });
     }
     finally
     {
       resetValue();
     }
   }

   @End
   public String goBack()
   {
     this.log.info("GoBack B4168 Mat hang....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4168_nhomhang";
   }

   private void resetValue()
   {
     this.nhomhang = new DmPhanLoaiThuoc();
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

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.B4168_Nhomhang_Edit

 * JD-Core Version:    0.7.0.1

 */