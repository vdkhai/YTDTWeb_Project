 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
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

 @Name("DmVoCam")
 @Scope(ScopeType.SESSION)
 public class DmVoCamExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DtDmVoCam vocam;
   @DataModel
   private List<DtDmVoCam> listDmVoCam;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmVoCamExt....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B261_Vocam";
   }

   public void search()
   {
     this.log.info("Search DmVoCamExt....", new Object[0]);

     this.listDmVoCam = DieuTriUtilDelegate.getInstance().findByAllConditions("DtDmVoCam", "dtdmvocamMa", "dtdmvocamDiengiai", "dtdmvocamChon", this.vocam.getDtdmvocamMa(), this.vocam.getDtdmvocamDiengiai(), true);
     if (this.listDmVoCam.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmVoCamExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DtDmVoCam)this.listDmVoCam.get(rowIndex)).getDtdmvocamMa();
       try
       {
         DtDmVoCam be = (DtDmVoCam)this.listDmVoCam.get(rowIndex);

         be.setDtdmvocamChon(Boolean.valueOf(false));
         be.setDtdmvocamNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmVoCam.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.vocam_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.vocam_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmVoCamExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmVoCamExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.vocam = new DtDmVoCam();
     this.listDmVoCam = new ArrayList();
   }

   public DtDmVoCam getVocam()
   {
     return this.vocam;
   }

   public void setVocam(DtDmVoCam vocam)
   {
     this.vocam = vocam;
   }

   public List<DtDmVoCam> getListDmVoCam()
   {
     return this.listDmVoCam;
   }

   public void setListDmVoCam(List<DtDmVoCam> listDmVoCam)
   {
     this.listDmVoCam = listDmVoCam;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmVoCamExt

 * JD-Core Version:    0.7.0.1

 */