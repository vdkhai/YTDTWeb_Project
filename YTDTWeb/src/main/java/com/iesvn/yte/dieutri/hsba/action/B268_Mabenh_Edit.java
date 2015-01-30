 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmChuongBenh;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.ListDataModel;
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
 import org.jboss.seam.log.Log;

 @Name("B268_Mabenh_Edit")
 @Scope(ScopeType.SESSION)
 public class B268_Mabenh_Edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmBenhIcd mabenh;
   private List<SelectItem> listB268_Chuongbenh;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B268_Ma benh Edit....", new Object[0]);

     resetValue();
     initCbo();
     load_rowIndex();

     return "/B2_Dieutri/B268_mabenh_edit";
   }

   public void save()
   {
     this.log.info("Save B268_mabenh_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.mabenh.getDmbenhicdMa();
     this.mabenh.setDmbenhicdDt(Boolean.valueOf(true));
     this.mabenh.setDmbenhicdNgaygiocn(Double.valueOf(date.getTime()));
     try
     {
       if (this.mabenh.getDmchuongbenhMaso(true).getDmchuongbenhMaso() == null) {
         this.mabenh.setDmchuongbenhMaso(null);
       }
       this.mabenh.setDmbenhicdNgaygiocn(Double.valueOf(date.getTime()));
       DieuTriUtilDelegate.getInstance().edit(this.mabenh);
       FacesMessages.instance().add(IConstantsRes.mabenh_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);

       FacesMessages.instance().add(IConstantsRes.mabenh_up_fa, new Object[] { ma });
     }
     finally
     {
       resetValue();
     }
   }

   public void reset()
   {
     this.log.info("Reset  B268_mabenh_edit....", new Object[0]);

     resetValue();
   }

   private void load_rowIndex()
   {
     this.log.info("Load rowIndex  B268_mabenh_edit ....", new Object[0]);

     int rowIndex = -1;

     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listB268");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1)
     {
       this.mabenh = ((DmBenhIcd)((List)lsDataModel.getWrappedData()).get(rowIndex));
       if (this.mabenh.getDmchuongbenhMaso() == null) {
         this.mabenh.setDmchuongbenhMaso(new DmChuongBenh());
       }
     }
   }

   private void resetValue()
   {
     this.mabenh = new DmBenhIcd();
     this.mabenh.setDmchuongbenhMaso(new DmChuongBenh());
   }

   private void initCbo()
   {
     this.log.info("Init Select items for combobox....", new Object[0]);

     initCboChuongbenh();
   }

   private void initCboChuongbenh()
   {
     this.log.info("getList_Chuongbenh_edit B268_mabenh_edit....", new Object[0]);

     this.listB268_Chuongbenh = new ArrayList();
     this.listB268_Chuongbenh.add(new SelectItem(null, "..."));

     List<DmChuongBenh> list = DieuTriUtilDelegate.getInstance().findAll("DmChuongBenh", "dmchuongbenhDt", true);
     for (DmChuongBenh item : list) {
       this.listB268_Chuongbenh.add(new SelectItem(item.getDmchuongbenhMaso(), item.getDmchuongbenhTen()));
     }
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

   public DmBenhIcd getMabenh()
   {
     return this.mabenh;
   }

   public void setMabenh(DmBenhIcd mabenh)
   {
     this.mabenh = mabenh;
   }

   public List<SelectItem> getListB268_Chuongbenh()
   {
     return this.listB268_Chuongbenh;
   }

   public void setListB268_Chuongbenh(List<SelectItem> listB268_Chuongbenh)
   {
     this.listB268_Chuongbenh = listB268_Chuongbenh;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B268_Mabenh_Edit

 * JD-Core Version:    0.7.0.1

 */