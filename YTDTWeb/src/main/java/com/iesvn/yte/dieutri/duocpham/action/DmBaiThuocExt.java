 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmBaiThuoc")
 @Scope(ScopeType.SESSION)
 public class DmBaiThuocExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmBaiThuoc dmBaiThuoc;
   @DataModel
   private List<DmBaiThuoc> listDmBaiThuoc;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmBaiThuocExt....", new Object[0]);

     resetValue();

     return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy";
   }

   public void search()
   {
     this.log.info("Search DmBaiThuocExt....", new Object[0]);

     this.listDmBaiThuoc = DieuTriUtilDelegate.getInstance().findByAllConditions("DmBaiThuoc", "dmbaithuocMa", "dmbaithuocTen", "dmbaithuocDt", this.dmBaiThuoc.getDmbaithuocMa(), this.dmBaiThuoc.getDmbaithuocTen(), true);
     if (this.listDmBaiThuoc.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmBaiThuocExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmBaiThuoc)this.listDmBaiThuoc.get(rowIndex)).getDmbaithuocMa();
       try
       {
         DmBaiThuoc be = (DmBaiThuoc)this.listDmBaiThuoc.get(rowIndex);

         be.setDmbaithuocDt(Boolean.valueOf(false));
         be.setDmbaithuocNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmBaiThuoc.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.baithuoc_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.baithuoc_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmBaiThuocExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmBaiThuocExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.dmBaiThuoc = new DmBaiThuoc();
     this.listDmBaiThuoc = new ArrayList();
   }

   public DmBaiThuoc getDmBaiThuoc()
   {
     return this.dmBaiThuoc;
   }

   public void setDmBaiThuoc(DmBaiThuoc dmBaiThuoc)
   {
     this.dmBaiThuoc = dmBaiThuoc;
   }

   public List<DmBaiThuoc> getListDmBaiThuoc()
   {
     return this.listDmBaiThuoc;
   }

   public void setListDmBaiThuoc(List<DmBaiThuoc> listDmBaiThuoc)
   {
     this.listDmBaiThuoc = listDmBaiThuoc;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmBaiThuocExt

 * JD-Core Version:    0.7.0.1

 */