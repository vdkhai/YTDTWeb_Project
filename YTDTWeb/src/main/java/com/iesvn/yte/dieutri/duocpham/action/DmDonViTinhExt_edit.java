 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.Date;
 import java.util.List;
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
 import org.jboss.seam.jsf.ListDataModel;
 import org.jboss.seam.log.Log;

 @Name("DmDonViTinh_edit")
 @Scope(ScopeType.SESSION)
 public class DmDonViTinhExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmDonViTinh donvitinh;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmDonViTinhExt_edit....", new Object[0]);

     resetValue();
     setValueForForm();

     return "/B4_Duocpham/KhoChinh/B4153_Donvitinh_edit";
   }

   public void save()
   {
     this.log.info("Save DmDonViTinhExt_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.donvitinh.getDmdonvitinhMa();
     this.donvitinh.setDmdonvitinhNgaygiocn(Double.valueOf(date.getTime()));
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.donvitinh);
       FacesMessages.instance().add(IConstantsRes.donvitinh_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.donvitinh_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmDonViTinhExt_edit....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4153_Donvitinh";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmDonViTinhExt_edit....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmDonViTinh");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.donvitinh = ((DmDonViTinh)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.donvitinh = new DmDonViTinh();
   }

   public DmDonViTinh getDonvitinh()
   {
     return this.donvitinh;
   }

   public void setDonvitinh(DmDonViTinh donvitinh)
   {
     this.donvitinh = donvitinh;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmDonViTinhExt_edit

 * JD-Core Version:    0.7.0.1

 */