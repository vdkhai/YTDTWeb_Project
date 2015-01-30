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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmPhuongThucGayTaiNan_add")
 @Scope(ScopeType.SESSION)
 public class DmPhuongThucGayTaiNanExt_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   @In(required=false)
   @Out(required=false)
   private DmPhuongThucGayTaiNan phuongthucgaytainan;
   private List<SelectItem> listtainan;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmPhuongThucGayTaiNanExt_add....", new Object[0]);

     resetValue();
     initComboxTaiNan();

     return "/B2_Dieutri/B267_Phuongthucgaytainan_add";
   }

   public void save(int tainanId)
   {
     this.log.info("Save  DmPhuongThucGayTaiNanExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.phuongthucgaytainan.getDmptgtnMa();
     this.phuongthucgaytainan.setDmptgtnNgaygiocn(Double.valueOf(date.getTime()));
     this.phuongthucgaytainan.setDmptgtnDt(Boolean.valueOf(true));
     if (tainanId == -1) {
       this.phuongthucgaytainan.setDmtainanMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.phuongthucgaytainan);
       FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack  DmPhuongThucGayTaiNanExt_add....", new Object[0]);

     return "/B2_Dieutri/B267_Phuongthucgaytainan";
   }

   public void reset()
   {
     this.log.info("Reset  DmPhuongThucGayTaiNanExt_add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.phuongthucgaytainan = new DmPhuongThucGayTaiNan();
     this.phuongthucgaytainan.setDmtainanMaso(new DmTaiNan());
   }

   private void initComboxTaiNan()
   {
     this.listtainan = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listtainan.add(item);

     List<DmTaiNan> lstempTaiNan = DieuTriUtilDelegate.getInstance().findAll("DmTaiNan", "dmtainanDt", true);
     for (DmTaiNan dmTaiNan : lstempTaiNan)
     {
       item = new SelectItem(dmTaiNan.getDmtainanMaso(), dmTaiNan.getDmtainanTen());
       this.listtainan.add(item);
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

   public List<SelectItem> getListtainan()
   {
     return this.listtainan;
   }

   public void setListtainan(List<SelectItem> listtainan)
   {
     this.listtainan = listtainan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmPhuongThucGayTaiNanExt_add

 * JD-Core Version:    0.7.0.1

 */