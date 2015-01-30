 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
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

 @Name("DmPhuongThucGayTaiNan")
 @Scope(ScopeType.SESSION)
 public class DmPhuongThucGayTaiNanExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmPhuongThucGayTaiNan phuongthucgaytainan;
   @DataModel
   private List<DmPhuongThucGayTaiNan> listDmPhuongThucGayTaiNan;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmPhuongThucGayTaiNanExt....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B267_Phuongthucgaytainan";
   }

   public void search()
   {
     this.log.info("Search DmPhuongThucGayTaiNanExt....", new Object[0]);

     this.listDmPhuongThucGayTaiNan = DieuTriUtilDelegate.getInstance().findByAllConditions("DmPhuongThucGayTaiNan", "dmptgtnMa", "dmptgtnTen", "dmptgtnDt", this.phuongthucgaytainan.getDmptgtnMa(), this.phuongthucgaytainan.getDmptgtnTen(), true);
     if (this.listDmPhuongThucGayTaiNan.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmPhuongThucGayTaiNanExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmPhuongThucGayTaiNan)this.listDmPhuongThucGayTaiNan.get(rowIndex)).getDmptgtnMa();
       try
       {
         DmPhuongThucGayTaiNan be = (DmPhuongThucGayTaiNan)this.listDmPhuongThucGayTaiNan.get(rowIndex);

         be.setDmptgtnDt(Boolean.valueOf(false));
         be.setDmptgtnNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmPhuongThucGayTaiNan.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmPhuongThucGayTaiNanExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmPhuongThucGayTaiNanExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.phuongthucgaytainan = new DmPhuongThucGayTaiNan();
     this.listDmPhuongThucGayTaiNan = new ArrayList();
   }

   public DmPhuongThucGayTaiNan getPhuongthucgaytainan()
   {
     return this.phuongthucgaytainan;
   }

   public void setPhuongthucgaytainan(DmPhuongThucGayTaiNan phuongthucgaytainan)
   {
     this.phuongthucgaytainan = phuongthucgaytainan;
   }

   public List<DmPhuongThucGayTaiNan> getListDmPhuongThucGayTaiNan()
   {
     return this.listDmPhuongThucGayTaiNan;
   }

   public void setListDmPhuongThucGayTaiNan(List<DmPhuongThucGayTaiNan> listDmPhuongThucGayTaiNan)
   {
     this.listDmPhuongThucGayTaiNan = listDmPhuongThucGayTaiNan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmPhuongThucGayTaiNanExt

 * JD-Core Version:    0.7.0.1

 */