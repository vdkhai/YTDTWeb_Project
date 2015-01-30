 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBenhIcd;
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

 @Name("B268_Mabenh")
 @Scope(ScopeType.SESSION)
 public class B268_Mabenh
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmBenhIcd mabenh;
   @DataModel
   private List<DmBenhIcd> listB268;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B268_MabenhICD....", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B268_mabenh";
   }

   public String search()
   {
     this.log.info("Search B268_MabenhICD....", new Object[0]);

     this.listB268 = DieuTriUtilDelegate.getInstance().findByAllConditions("DmBenhIcd", "dmbenhicdMa", "dmbenhicdTen", "dmbenhicdDt", this.mabenh.getDmbenhicdMa(), this.mabenh.getDmbenhicdTen(), true);
     if (this.listB268.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
     return "/B2_Dieutri/B268_mabenh";
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete B268_MabenhICD....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       ma = ((DmBenhIcd)this.listB268.get(rowIndex)).getDmbenhicdMa();
       DmBenhIcd dm = new DmBenhIcd();
       dm = (DmBenhIcd)this.listB268.get(rowIndex);
       dm.setDmbenhicdDt(Boolean.valueOf(false));
       try
       {
         DieuTriUtilDelegate.getInstance().edit(dm);
         this.listB268.remove(rowIndex);
         FacesMessages.instance().add("XÃ³a thÃ nh cÃ´ng loáº¡i bá»‡nh mÃ£ " + ma, new Object[0]);
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add("XÃ³a tháº¥t báº¡i loáº¡i bá»‡nh mÃ£ " + ma, new Object[0]);
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset B268_MabenhICD....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.mabenh = new DmBenhIcd();
     this.listB268 = new ArrayList();
   }

   public DmBenhIcd getMabenh()
   {
     return this.mabenh;
   }

   public void setMabenh(DmBenhIcd mabenh)
   {
     this.mabenh = mabenh;
   }

   public List<DmBenhIcd> getListB268()
   {
     return this.listB268;
   }

   public void setListB268(List<DmBenhIcd> listB268)
   {
     this.listB268 = listB268;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B268_Mabenh

 * JD-Core Version:    0.7.0.1

 */