 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmTaiNan;
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

 @Name("DmTaiNan")
 @Scope(ScopeType.SESSION)
 public class DmTaiNanExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmTaiNan tainan;
   @DataModel
   private List<DmTaiNan> listDmTaiNan;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmTaiNanExt....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B2611_Tainan";
   }

   public void search()
   {
     this.log.info("Search DmTaiNanExt....", new Object[0]);

     this.listDmTaiNan = DieuTriUtilDelegate.getInstance().findByAllConditions("DmTaiNan", "dmtainanMa", "dmtainanTen", "dmtainanDt", this.tainan.getDmtainanMa(), this.tainan.getDmtainanTen(), true);
     if (this.listDmTaiNan.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmTaiNanExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmTaiNan)this.listDmTaiNan.get(rowIndex)).getDmtainanMa();
       try
       {
         DmTaiNan be = (DmTaiNan)this.listDmTaiNan.get(rowIndex);

         be.setDmtainanDt(Boolean.valueOf(false));
         be.setDmtainanNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmTaiNan.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.tainan_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.tainan_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmTaiNanExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmTaiNanExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.tainan = new DmTaiNan();
     this.listDmTaiNan = new ArrayList();
   }

   public DmTaiNan getTainan()
   {
     return this.tainan;
   }

   public void setTainan(DmTaiNan tainan)
   {
     this.tainan = tainan;
   }

   public List<DmTaiNan> getListDmTaiNan()
   {
     return this.listDmTaiNan;
   }

   public void setListDmTaiNan(List<DmTaiNan> listDmTaiNan)
   {
     this.listDmTaiNan = listDmTaiNan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmTaiNanExt

 * JD-Core Version:    0.7.0.1

 */