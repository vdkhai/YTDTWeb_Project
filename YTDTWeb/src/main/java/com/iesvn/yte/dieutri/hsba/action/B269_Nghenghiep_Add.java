 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmNgheNghiepBaoCao;
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

 @Name("B269_Nghenghiep_Add")
 @Scope(ScopeType.SESSION)
 public class B269_Nghenghiep_Add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmNgheNghiep nghenghiep;
   @Logger
   private Log log;
   private List<SelectItem> listB269_Phanloai;

   @Create
   public String init()
   {
     this.log.info("Init B269 Nghe nghiep....", new Object[0]);

     resetValue();
     initCbo();

     return "/B2_Dieutri/B269_nghenghiep_add";
   }

   public void save()
   {
     this.log.info("Save B269_Nghe nghiep_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.nghenghiep.getDmnghenghiepMa();
     this.nghenghiep.setDmnghenghiepDt(Boolean.valueOf(true));
     this.nghenghiep.setDmnghenghiepNgaygiocn(Double.valueOf(date.getTime()));
     if (this.nghenghiep.getDmnghenghiepPhanloai(true).getDmnghenghiepbcMaso() == null) {
       this.nghenghiep.setDmnghenghiepPhanloai(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.nghenghiep);
       FacesMessages.instance().add(IConstantsRes.nghenghiep_cr_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.nghenghiep_cr_fa, new Object[] { ma });
     }
     finally
     {
       resetValue();
     }
   }

   public String goBack()
   {
     this.log.info("GoBack B269 Nghe nghiep....", new Object[0]);

     return "/B2_Dieutri/B269_nghenghiep";
   }

   public void reset()
   {
     this.log.info("Reset B269 Nghe nghiep add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.nghenghiep = new DmNgheNghiep();
     this.nghenghiep.setDmnghenghiepPhanloai(new DmNgheNghiepBaoCao());
   }

   private void initCbo()
   {
     this.log.info("Init Select items for combobox....", new Object[0]);

     initCboPhanloai();
   }

   private void initCboPhanloai()
   {
     this.log.info("getList_Phanloai_item B269_Dm Nghe nghiep....", new Object[0]);

     this.listB269_Phanloai = new ArrayList();

     List<DmNgheNghiepBaoCao> list = DieuTriUtilDelegate.getInstance().findAll("DmNgheNghiepBaoCao", "dmnghenghiepbcDt", true);
     for (DmNgheNghiepBaoCao item : list) {
       this.listB269_Phanloai.add(new SelectItem(item.getDmnghenghiepbcMaso(), item.getDmnghenghiepbcTen()));
     }
   }

   public DmNgheNghiep getNghenghiep()
   {
     return this.nghenghiep;
   }

   public void setNghenghiep(DmNgheNghiep nghenghiep)
   {
     this.nghenghiep = nghenghiep;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public List<SelectItem> getListB269_Phanloai()
   {
     return this.listB269_Phanloai;
   }

   public void setListB269_Phanloai(List<SelectItem> listB269_Phanloai)
   {
     this.listB269_Phanloai = listB269_Phanloai;
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B269_Nghenghiep_Add

 * JD-Core Version:    0.7.0.1

 */