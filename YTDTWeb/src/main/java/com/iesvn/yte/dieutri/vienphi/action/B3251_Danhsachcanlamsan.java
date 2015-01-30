 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
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

 @Name("B3251_Danhsachcanlamsan")
 @Scope(ScopeType.SESSION)
 public class B3251_Danhsachcanlamsan
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DtDmCls cls;
   @DataModel
   private List<DtDmCls> listCls;
   @Logger
   private Log log;

   public DtDmCls getCls()
   {
     return this.cls;
   }

   public void setCls(DtDmCls cls)
   {
     this.cls = cls;
   }

   public List<DtDmCls> getListCls()
   {
     return this.listCls;
   }

   public void setListCls(List<DtDmCls> listCls)
   {
     this.listCls = listCls;
   }

   @Create
   public String init()
   {
     this.log.info("Init canlamsan...", new Object[0]);

     resetValue();

     return "/B3_Vienphi/ThuVienPhi/B3251_Danhsachcanlamsan";
   }

   public void search()
   {
     this.log.info("Search canlamsan...", new Object[0]);

     this.listCls = DieuTriUtilDelegate.getInstance().findByAllConditions("DtDmCls", "dtdmclsMa", "dtdmclsTen", "dtdmclsChon", this.cls.getDtdmclsMa(), this.cls.getDtdmclsTen(), true);
     if (this.listCls.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete canlamsan...", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       ma = ((DtDmCls)this.listCls.get(rowIndex)).getDtdmclsMa();
       try
       {
         DtDmCls be = (DtDmCls)this.listCls.get(rowIndex);

         be.setDtdmclsChon(Boolean.valueOf(false));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listCls.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.canlamsan_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.canlamsan_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset canlamsan....", new Object[0]);

     resetValue();
   }

   public String goMainPage()
   {
     this.log.info("Go main page...", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.cls = new DtDmCls();
     this.listCls = new ArrayList();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3251_Danhsachcanlamsan

 * JD-Core Version:    0.7.0.1

 */