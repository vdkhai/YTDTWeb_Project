 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DtDmClsDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmPbClsDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmPbCls;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.jsf.ListDataModel;
 import org.jboss.seam.log.Log;

 @Name("B3251_Danhsachcanlamsan_edit")
 @Scope(ScopeType.SESSION)
 public class B3251_Danhsachcanlamsan_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private DtDmCls cls;
   @Logger
   private Log log;
   private List<SelectItem> listPhanbiet;
   private DtDmPbClsDelegate phanbietDAO;

   public List<SelectItem> getListPhanbiet()
   {
     return this.listPhanbiet;
   }

   public void setListPhanbiet(List<SelectItem> listPhanbiet)
   {
     this.listPhanbiet = listPhanbiet;
   }

   public DtDmCls getCls()
   {
     return this.cls;
   }

   public void setCls(DtDmCls cls)
   {
     this.cls = cls;
   }

   public void initListPhanbiet()
   {
     this.listPhanbiet = new ArrayList();
     for (DtDmPbCls each : this.phanbietDAO.findAll()) {
       this.listPhanbiet.add(new SelectItem(each.getDtdmpbclsMaso(), each.getDtdmpbclsTen()));
     }
   }

   @Create
   public String init()
   {
     this.log.info("Init canlamsan_edit...", new Object[0]);
     this.phanbietDAO = DtDmPbClsDelegate.getInstance();
     resetValue();
     setValueForForm();
     initListPhanbiet();

     return "/B3_Vienphi/ThuVienPhi/B3251_Danhsachcanlamsan_edit";
   }

   public void save()
   {
     this.log.info("Save canlamsan...", new Object[0]);

     String ma = "";

     ma = this.cls.getDtdmclsMa();
     setValueDmCls();
     try
     {
       DtDmClsDelegate.getInstance().edit(this.cls);
       FacesMessages.instance().add(IConstantsRes.canlamsan_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.canlamsan_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack Danhsachcanlamsan...", new Object[0]);

     return "/B3_Vienphi/ThuVienPhi/B3251_Danhsachcanlamsan";
   }

   public void setValueDmCls()
   {
     Date date = new Date();
     this.cls.setDtdmclsNgaygiocn(Double.valueOf(date.getTime()));
   }

   private void setValueForForm()
   {
     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listCls");
     rowIndex = lsDataModel.getRowIndex();
     this.cls = ((DtDmCls)((List)lsDataModel.getWrappedData()).get(rowIndex));
   }

   public void reset()
   {
     this.log.info("Reset canlamsan...", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.cls = new DtDmCls();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3251_Danhsachcanlamsan_edit

 * JD-Core Version:    0.7.0.1

 */