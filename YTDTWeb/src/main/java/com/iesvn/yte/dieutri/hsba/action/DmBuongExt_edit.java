 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmBuong;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.jsf.ListDataModel;
 import org.jboss.seam.log.Log;

 @Name("DmBuong_edit")
 @Scope(ScopeType.SESSION)
 public class DmBuongExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   @In(required=false)
   @Out(required=false)
   private DtDmBuong dmbuong;
   private List<SelectItem> listDmKhoa;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmBuongExt_edit....", new Object[0]);

     resetValue();
     initComboxKhoa();
     setValueForForm();

     return "/B2_Dieutri/B273_DmBuong_edit";
   }

   public void save(int khoaId)
   {
     this.log.info("Save DmBuongExt_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.dmbuong.getDtdmbMa();
     this.dmbuong.setDtdmbNgaygiocn(Double.valueOf(date.getTime()));
     if (khoaId == -1) {
       this.dmbuong.setDmkhoaMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.dmbuong);
       FacesMessages.instance().add(IConstantsRes.buong_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.buong_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmBuongExt_edit....", new Object[0]);

     return "/B2_Dieutri/B273_DmBuong";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmBuongExt_edit....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmBuong");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.dmbuong = ((DtDmBuong)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.dmbuong = new DtDmBuong();
     this.dmbuong.setDmkhoaMaso(new DmKhoa());
   }

   private void initComboxKhoa()
   {
     this.listDmKhoa = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listDmKhoa.add(item);

     List<DmKhoa> lstempKhoa = DieuTriUtilDelegate.getInstance().findKhoaNhapNuoc();
     for (DmKhoa dmKhoa : lstempKhoa)
     {
       item = new SelectItem(dmKhoa.getDmkhoaMaso(), dmKhoa.getDmkhoaTen());
       this.listDmKhoa.add(item);
     }
   }

   public DtDmBuong getDmbuong()
   {
     return this.dmbuong;
   }

   public void setDmbuong(DtDmBuong dmbuong)
   {
     this.dmbuong = dmbuong;
   }

   public List<SelectItem> getListDmKhoa()
   {
     return this.listDmKhoa;
   }

   public void setListDmKhoa(List<SelectItem> listDmKhoa)
   {
     this.listDmKhoa = listDmKhoa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmBuongExt_edit

 * JD-Core Version:    0.7.0.1

 */