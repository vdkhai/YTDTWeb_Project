 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
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

 @Name("DmVoCam_edit")
 @Scope(ScopeType.SESSION)
 public class DmVoCamExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DtDmVoCam vocam;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmVoCamExt_edit....", new Object[0]);

     resetValue();
     setValueForForm();

     return "/B2_Dieutri/B261_Vocam_edit";
   }

   public void save()
   {
     this.log.info("Save DmVoCamExt_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.vocam.getDtdmvocamMa();
     this.vocam.setDtdmvocamNgaygiocn(Double.valueOf(date.getTime()));
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.vocam);
       FacesMessages.instance().add(IConstantsRes.vocam_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.vocam_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmVoCamExt_edit....", new Object[0]);

     return "/B2_Dieutri/B261_Vocam";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmVoCamExt_edit....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmVoCam");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.vocam = ((DtDmVoCam)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.vocam = new DtDmVoCam();
   }

   public DtDmVoCam getVocam()
   {
     return this.vocam;
   }

   public void setVocam(DtDmVoCam vocam)
   {
     this.vocam = vocam;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmVoCamExt_edit

 * JD-Core Version:    0.7.0.1

 */