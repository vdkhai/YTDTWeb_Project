 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
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

 @Name("DmPhuongThucGayTaiNan_edit")
 @Scope(ScopeType.SESSION)
 public class DmPhuongThucGayTaiNanExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   @In(required=false)
   @Out(required=false)
   private DmPhuongThucGayTaiNan phuongthucgaytainan;
   private List<SelectItem> listTaiNan;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmPhuongThucGayTaiNanExt_edit....", new Object[0]);

     resetValue();
     initComboxTaiNan();
     setValueForForm();

     return "/B2_Dieutri/B267_Phuongthucgaytainan_edit";
   }

   public void save(int tainanId)
   {
     this.log.info("Save DmPhuongThucGayTaiNanExt_edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.phuongthucgaytainan.getDmptgtnMa();
     this.phuongthucgaytainan.setDmptgtnNgaygiocn(Double.valueOf(date.getTime()));
     if (tainanId == -1) {
       this.phuongthucgaytainan.setDmtainanMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.phuongthucgaytainan);
       FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_up_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmPhuongThucGayTaiNanExt_edit....", new Object[0]);

     return "/B2_Dieutri/B267_Phuongthucgaytainan";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmPhuongThucGayTaiNanExt_edit....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmPhuongThucGayTaiNan");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.phuongthucgaytainan = ((DmPhuongThucGayTaiNan)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.phuongthucgaytainan = new DmPhuongThucGayTaiNan();
     this.phuongthucgaytainan.setDmtainanMaso(new DmTaiNan());
   }

   private void initComboxTaiNan()
   {
     this.listTaiNan = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listTaiNan.add(item);

     List<DmTaiNan> lstempTaiNan = DieuTriUtilDelegate.getInstance().findAll("DmTaiNan", "dmtainanDt", true);
     for (DmTaiNan dmTaiNan : lstempTaiNan)
     {
       item = new SelectItem(dmTaiNan.getDmtainanMaso(), dmTaiNan.getDmtainanTen());
       this.listTaiNan.add(item);
     }
   }

   public DmPhuongThucGayTaiNan getPhuongthucgaytainan()
   {
     return this.phuongthucgaytainan;
   }

   public void setPhuongthucgaytainan(DmPhuongThucGayTaiNan phuongthucgaytainan)
   {
     this.phuongthucgaytainan = phuongthucgaytainan;
   }

   public List<SelectItem> getListTaiNan()
   {
     return this.listTaiNan;
   }

   public void setListTaiNan(List<SelectItem> listTaiNan)
   {
     this.listTaiNan = listTaiNan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmPhuongThucGayTaiNanExt_edit

 * JD-Core Version:    0.7.0.1

 */