 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNhaSanXuat;
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

 @Name("DmNhaSanXuat_edit")
 @Scope(ScopeType.SESSION)
 public class DmNhaSanXuatExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmNhaSanXuat nhasanxuat;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmNhaSanXuatExt_edit....", new Object[0]);

     resetValue();
     setValueForForm();

     return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat_edit";
   }

   public void save()
   {
     this.log.info("Save DmNhaSanXuatExt_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.nhasanxuat.getDmnhasanxuatMa();
     this.nhasanxuat.setDmnhasanxuatNgaygiocn(Double.valueOf(date.getTime()));
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.nhasanxuat);
       FacesMessages.instance().add(IConstantsRes.nhasanxuat_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.nhasanxuat_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmNhaSanXuatExt_edit....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4154_Nhasanxuat";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmNhaSanXuatExt_edit....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmNhaSanXuat");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.nhasanxuat = ((DmNhaSanXuat)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.nhasanxuat = new DmNhaSanXuat();
   }

   public DmNhaSanXuat getNhasanxuat()
   {
     return this.nhasanxuat;
   }

   public void setNhasanxuat(DmNhaSanXuat nhasanxuat)
   {
     this.nhasanxuat = nhasanxuat;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmNhaSanXuatExt_edit

 * JD-Core Version:    0.7.0.1

 */