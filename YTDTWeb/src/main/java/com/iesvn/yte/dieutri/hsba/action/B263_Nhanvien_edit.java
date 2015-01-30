 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.Date;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.jsf.ListDataModel;
 import org.jboss.seam.log.Log;

 @Name("B263_NhanVien_edit")
 @Scope(ScopeType.SESSION)
 public class B263_Nhanvien_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private DtDmNhanVien nhanvien;
   @Logger
   private Log log;

   public DtDmNhanVien getNhanvien()
   {
     return this.nhanvien;
   }

   public void setNhanvien(DtDmNhanVien nhanvien)
   {
     this.nhanvien = nhanvien;
   }

   @Create
   public String init()
   {
     this.log.info("Init nhanvien_edit...", new Object[0]);

     resetValue();
     setValueForForm();

     return "/B2_Dieutri/B263_Nhanvien_edit";
   }

   public void save()
   {
     this.log.info("Save nhanvien...", new Object[0]);

     String ma = "";

     ma = this.nhanvien.getDtdmnhanvienMa();
     setValueDmNhanVien();
     try
     {
       DtDmNhanVienDelegate.getInstance().edit(this.nhanvien);
       FacesMessages.instance().add(IConstantsRes.nhanvien_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.nhanvien_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack Danhsachnhanvien...", new Object[0]);

     return "/B2_Dieutri/B263_Nhanvien";
   }

   public void setValueDmNhanVien()
   {
     Date date = new Date();
     this.nhanvien.setDtdmnhanvienNgaygiocn(Double.valueOf(date.getTime()));
   }

   private void setValueForForm()
   {
     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listNhanvien");
     rowIndex = lsDataModel.getRowIndex();
     this.nhanvien = ((DtDmNhanVien)((List)lsDataModel.getWrappedData()).get(rowIndex));
   }

   public void reset()
   {
     this.log.info("Reset nhanvien...", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.nhanvien = new DtDmNhanVien();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B263_Nhanvien_edit

 * JD-Core Version:    0.7.0.1

 */