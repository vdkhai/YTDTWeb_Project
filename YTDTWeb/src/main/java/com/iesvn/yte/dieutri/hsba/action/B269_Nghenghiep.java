 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
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

 @Name("B269_Nghenghiep")
 @Scope(ScopeType.SESSION)
 public class B269_Nghenghiep
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmNgheNghiep nghenghiep;
   @DataModel
   private List<DmNgheNghiep> listB269;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B269_Mathang....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B269_nghenghiep";
   }

   public String search()
   {
     this.log.info("Search B269_Dm Nghe nghiep....", new Object[0]);

     this.listB269 = DieuTriUtilDelegate.getInstance().findByAllConditions("DmNgheNghiep", "dmnghenghiepMa", "dmnghenghiepTen", "dmnghenghiepDt", this.nghenghiep.getDmnghenghiepMa(), this.nghenghiep.getDmnghenghiepTen(), true);
     if (this.listB269.size() == 0) {
       FacesMessages.instance().add("KhÃ´ng TÃ¬m tháº¥y Káº¿t quáº£ nÃ o", new Object[0]);
     }
     return "/B2_Dieutri/B269_nghenghiep";
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete B269_Dm Nghe nghiep....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       ma = ((DmNgheNghiep)this.listB269.get(rowIndex)).getDmnghenghiepMa();
       DmNgheNghiep dm = new DmNgheNghiep();
       dm = (DmNgheNghiep)this.listB269.get(rowIndex);
       dm.setDmnghenghiepDt(Boolean.valueOf(false));
       try
       {
         DieuTriUtilDelegate.getInstance().edit(dm);
         this.listB269.remove(rowIndex);
         FacesMessages.instance().add(IConstantsRes.nghenghiep_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.nghenghiep_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset B269_Nghe nghiep....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.nghenghiep = new DmNgheNghiep();
     this.listB269 = new ArrayList();
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public DmNgheNghiep getNghenghiep()
   {
     return this.nghenghiep;
   }

   public void setNghenghiep(DmNgheNghiep nghenghiep)
   {
     this.nghenghiep = nghenghiep;
   }

   public List<DmNgheNghiep> getListB269()
   {
     return this.listB269;
   }

   public void setListB4151(List<DmNgheNghiep> listB269)
   {
     this.listB269 = listB269;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B269_Nghenghiep

 * JD-Core Version:    0.7.0.1

 */