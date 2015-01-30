 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmTaiNan;
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

 @Name("DmTaiNan_add")
 @Scope(ScopeType.SESSION)
 public class DmTaiNanExt_add
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
     this.log.info("Init DmTaiNanExt_add....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B2611_Tainan_add";
   }

   public void save()
   {
     this.log.info("Save DmTaiNanExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.tainan.getDmtainanMa();
     this.tainan.setDmtainanNgaygiocn(Double.valueOf(date.getTime()));
     this.tainan.setDmtainanDt(Boolean.valueOf(true));
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.tainan);
       FacesMessages.instance().add(IConstantsRes.tainan_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.tainan_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmTaiNanExt_add....", new Object[0]);

     return "/B2_Dieutri/B2611_Tainan";
   }

   public void reset()
   {
     this.log.info("Reset DmTaiNanExt_add....", new Object[0]);

     resetValue();
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmTaiNanExt_add

 * JD-Core Version:    0.7.0.1

 */