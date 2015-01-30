 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.BaithuocThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B4151_Mathang")
 @Scope(ScopeType.SESSION)
 public class B4151_Mathang
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private DmThuoc mathang;
   @DataModel
   private List<DmThuoc> listB4151;
   private List<SelectItem> listB4151_Phanloaithuoc;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B4151_Mathang....", new Object[0]);

     resetValue();
     this.listB4151_Phanloaithuoc = new ArrayList();
     List<DmPhanLoaiThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmPhanLoaiThuoc", "dmphanloaithuocDt", true);

     this.listB4151_Phanloaithuoc.add(new SelectItem(null, ""));
     for (DmPhanLoaiThuoc item : list) {
       this.listB4151_Phanloaithuoc.add(new SelectItem(item.getDmphanloaithuocMaso(), item.getDmphanloaithuocTen()));
     }
     return "/B4_Duocpham/KhoChinh/B4151_mathang";
   }

   public String search()
   {
     this.log.info("Search 4151_Dm Thuoc.... ma phan loai  = " + this.mathang.getDmphanloaithuocMaso(), new Object[0]);






     this.listB4151 = DieuTriUtilDelegate.getInstance().findByAllConditionsSortAsc("DmThuoc", "dmthuocMa", "dmthuocTen", "dmthuocDt", "dmphanloaithuocMaso.dmphanloaithuocMaso", this.mathang.getDmthuocMa(), this.mathang.getDmthuocTen(), this.mathang.getDmphanloaithuocMaso().getDmphanloaithuocMaso());
     if (this.listB4151.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
     return "/B4_Duocpham/KhoChinh/B4151_mathang";
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete B4151_Dm Thuoc....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       ma = ((DmThuoc)this.listB4151.get(rowIndex)).getDmthuocMa();
       try
       {
         DmThuoc dm = new DmThuoc();
         dm = (DmThuoc)this.listB4151.get(rowIndex);
         dm.setDmthuocDt(Boolean.valueOf(false));

         dm.setDmthuocNgaygiocn(Double.valueOf(new Date().getTime()));

         TonKhoDelegate tonkhoDelegate = TonKhoDelegate.getInstance();
         if (!tonkhoDelegate.checkTonKhoHienTai(dm.getDmthuocMaso()))
         {
           DieuTriUtilDelegate.getInstance().edit(dm);


           BaithuocThuocDelegate baithuocThuocDel = BaithuocThuocDelegate.getInstance();
           baithuocThuocDel.deleteAllBaithuocThuocsByMasoThuoc(dm.getDmthuocMaso());

           search();
           FacesMessages.instance().add(IConstantsRes.thuoc_de_su, new Object[] { ma });
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.thuoc_de_war, new Object[] { ma });
         }
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.thuoc_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset B4151_Mathang....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.mathang = new DmThuoc();
     this.mathang.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
     this.listB4151 = new ArrayList();
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public DmThuoc getMathang()
   {
     return this.mathang;
   }

   public void setMathang(DmThuoc mathang)
   {
     this.mathang = mathang;
   }

   public List<DmThuoc> getListB4151()
   {
     return this.listB4151;
   }

   public void setListB4151(List<DmThuoc> listB4151)
   {
     this.listB4151 = listB4151;
   }

   public List<SelectItem> getListB4151_Phanloaithuoc()
   {
     return this.listB4151_Phanloaithuoc;
   }

   public void setListB4151_Phanloaithuoc(List<SelectItem> listB4151Phanloaithuoc)
   {
     this.listB4151_Phanloaithuoc = listB4151Phanloaithuoc;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.B4151_Mathang

 * JD-Core Version:    0.7.0.1

 */