 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmPhanLoaiTaiNan;
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

 @Name("DmPhanLoaiTaiNan")
 @Scope(ScopeType.SESSION)
 public class DmPhanLoaiTaiNanExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmPhanLoaiTaiNan phanloaitainan;
   @DataModel
   private List<DmPhanLoaiTaiNan> listDmPhanLoaiTaiNan;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmPhanLoaiTaiNanExt....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B266_Phanloaitainan";
   }

   public void search()
   {
     this.log.info("Search DmPhanLoaiTaiNanExt....", new Object[0]);

     this.listDmPhanLoaiTaiNan = DieuTriUtilDelegate.getInstance().findByAllConditions("DmPhanLoaiTaiNan", "dmpltainanMa", "dmpltainanTen", "dmpltainanDt", this.phanloaitainan.getDmpltainanMa(), this.phanloaitainan.getDmpltainanTen(), true);
     if (this.listDmPhanLoaiTaiNan.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmPhanLoaiTaiNanExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DmPhanLoaiTaiNan)this.listDmPhanLoaiTaiNan.get(rowIndex)).getDmpltainanMa();
       try
       {
         DmPhanLoaiTaiNan be = (DmPhanLoaiTaiNan)this.listDmPhanLoaiTaiNan.get(rowIndex);

         be.setDmpltainanDt(Boolean.valueOf(false));
         be.setDmpltainanNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDmPhanLoaiTaiNan.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.phanloaitainan_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.phanloaitainan_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmPhanLoaiTaiNanExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmPhanLoaiTaiNanExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.phanloaitainan = new DmPhanLoaiTaiNan();
     this.listDmPhanLoaiTaiNan = new ArrayList();
   }

   public DmPhanLoaiTaiNan getPhanloaitainan()
   {
     return this.phanloaitainan;
   }

   public void setPhanloaitainan(DmPhanLoaiTaiNan phanloaitainan)
   {
     this.phanloaitainan = phanloaitainan;
   }

   public List<DmPhanLoaiTaiNan> getListDmPhanLoaiTaiNan()
   {
     return this.listDmPhanLoaiTaiNan;
   }

   public void setListDmPhanLoaiTaiNan(List<DmPhanLoaiTaiNan> listDmPhanLoaiTaiNan)
   {
     this.listDmPhanLoaiTaiNan = listDmPhanLoaiTaiNan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmPhanLoaiTaiNanExt

 * JD-Core Version:    0.7.0.1

 */