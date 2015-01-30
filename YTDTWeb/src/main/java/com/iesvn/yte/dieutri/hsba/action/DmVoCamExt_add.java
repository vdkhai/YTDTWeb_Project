 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.Date;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmVoCam_add")
 @Scope(ScopeType.SESSION)
 public class DmVoCamExt_add
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
     this.log.info("Init DmVoCamExt_add....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B261_Vocam_add";
   }

   public void save()
   {
     this.log.info("Save DmVoCamExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.vocam.getDtdmvocamMa();
     this.vocam.setDtdmvocamNgaygiocn(Double.valueOf(date.getTime()));
     this.vocam.setDtdmvocamChon(Boolean.valueOf(true));
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.vocam);
       FacesMessages.instance().add(IConstantsRes.vocam_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.vocam_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmVoCamExt_add....", new Object[0]);

     return "/B2_Dieutri/B261_Vocam";
   }

   public void reset()
   {
     this.log.info("Reset DmVoCamExt_add....", new Object[0]);

     resetValue();
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmVoCamExt_add

 * JD-Core Version:    0.7.0.1

 */