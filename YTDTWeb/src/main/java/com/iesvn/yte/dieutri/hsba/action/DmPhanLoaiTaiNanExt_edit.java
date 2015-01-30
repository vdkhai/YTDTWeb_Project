 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmPhanLoaiTaiNan;
 import com.iesvn.yte.entity.DmTaiNan;
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

 @Name("DmPhanLoaiTaiNan_edit")
 @Scope(ScopeType.SESSION)
 public class DmPhanLoaiTaiNanExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   @In(required=false)
   @Out(required=false)
   private DmPhanLoaiTaiNan phanloaitainan;
   private List<SelectItem> listPhanLoaiTaiNan;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmPhanLoaiTaiNanExt_edit....", new Object[0]);

     resetValue();
     initComboxTaiNan();
     setValueForForm();

     return "/B2_Dieutri/B266_Phanloaitainan_edit";
   }

   public void save(int tainanId)
   {
     this.log.info("Save DmPhanLoaiTaiNanExt_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.phanloaitainan.getDmpltainanMa();
     this.phanloaitainan.setDmpltainanNgaygiocn(Double.valueOf(date.getTime()));
     if (tainanId == -1) {
       this.phanloaitainan.setDmtainanMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.phanloaitainan);
       FacesMessages.instance().add(IConstantsRes.phanloaitainan_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.phanloaitainan_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmPhanLoaiTaiNanExt_edit....", new Object[0]);

     return "/B2_Dieutri/B266_Phanloaitainan";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmPhanLoaiTaiNanExt_edit....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmPhanLoaiTaiNan");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.phanloaitainan = ((DmPhanLoaiTaiNan)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.phanloaitainan = new DmPhanLoaiTaiNan();
     this.phanloaitainan.setDmtainanMaso(new DmTaiNan());
   }

   private void initComboxTaiNan()
   {
     this.listPhanLoaiTaiNan = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listPhanLoaiTaiNan.add(item);

     List<DmTaiNan> lstempTaiNan = DieuTriUtilDelegate.getInstance().findAll("DmTaiNan", "dmtainanDt", true);
     for (DmTaiNan dmTaiNan : lstempTaiNan)
     {
       item = new SelectItem(dmTaiNan.getDmtainanMaso(), dmTaiNan.getDmtainanTen());
       this.listPhanLoaiTaiNan.add(item);
     }
   }

   public DmPhanLoaiTaiNan getPhanloaitainan()
   {
     return this.phanloaitainan;
   }

   public void setPhanloaitainan(DmPhanLoaiTaiNan phanloaitainan)
   {
     this.phanloaitainan = phanloaitainan;
   }

   public List<SelectItem> getListPhanLoaiTaiNan()
   {
     return this.listPhanLoaiTaiNan;
   }

   public void setListPhanLoaiTaiNan(List<SelectItem> listPhanLoaiTaiNan)
   {
     this.listPhanLoaiTaiNan = listPhanLoaiTaiNan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmPhanLoaiTaiNanExt_edit

 * JD-Core Version:    0.7.0.1

 */