 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmTaiNan;
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

 @Name("DmTaiNan_edit")
 @Scope(ScopeType.SESSION)
 public class DmTaiNanExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmTaiNan tainan;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmTaiNanExt_edit....", new Object[0]);

     resetValue();
     setValueForForm();

     return "/B2_Dieutri/B2611_Tainan_edit";
   }

   public void save()
   {
     this.log.info("Save DmTaiNanExt_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.tainan.getDmtainanMa();
     this.tainan.setDmtainanNgaygiocn(Double.valueOf(date.getTime()));
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.tainan);
       FacesMessages.instance().add(IConstantsRes.tainan_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.tainan_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmTaiNanExt_edit....", new Object[0]);

     return "/B2_Dieutri/B2611_Tainan";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmTaiNanExt_edit....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmTaiNan");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.tainan = ((DmTaiNan)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.tainan = new DmTaiNan();
   }

   public DmTaiNan getTainan()
   {
     return this.tainan;
   }

   public void setTainan(DmTaiNan tainan)
   {
     this.tainan = tainan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmTaiNanExt_edit

 * JD-Core Version:    0.7.0.1

 */