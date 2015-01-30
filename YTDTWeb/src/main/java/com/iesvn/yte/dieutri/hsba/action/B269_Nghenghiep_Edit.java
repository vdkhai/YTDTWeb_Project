 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmNgheNghiepBaoCao;
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

 @Name("B269_Nghenghiep_Edit")
 @Scope(ScopeType.SESSION)
 public class B269_Nghenghiep_Edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmNgheNghiep nghenghiep;
   private List<SelectItem> listB269_Phanloai;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B269_Nghe nghiep....", new Object[0]);

     resetValue();
     initCbo();
     load_rowIndex();

     return "/B2_Dieutri/B269_nghenghiep_edit";
   }

   public void save()
   {
     this.log.info("Save B269 Nghe nghiep_Edit....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.nghenghiep.getDmnghenghiepMa();
     this.nghenghiep.setDmnghenghiepDt(Boolean.valueOf(true));
     this.nghenghiep.setDmnghenghiepNgaygiocn(Double.valueOf(date.getTime()));
     try
     {
       if (this.nghenghiep.getDmnghenghiepPhanloai(true).getDmnghenghiepbcMaso() == null) {
         this.nghenghiep.setDmnghenghiepMaso(null);
       }
       this.nghenghiep.setDmnghenghiepNgaygiocn(Double.valueOf(date.getTime()));
       DieuTriUtilDelegate.getInstance().edit(this.nghenghiep);
       FacesMessages.instance().add(IConstantsRes.nghenghiep_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);

       FacesMessages.instance().add(IConstantsRes.nghenghiep_up_fa, new Object[] { ma });
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
     this.log.info("Reset B269 Nghe nghiep....", new Object[0]);

     resetValue();
   }

   public void load_rowIndex()
   {
     this.log.info("Load rowIndex B269 Nghe nghiep ....", new Object[0]);

     int rowIndex = -1;

     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listB269");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1)
     {
       this.nghenghiep = ((DmNgheNghiep)((List)lsDataModel.getWrappedData()).get(rowIndex));
       if (this.nghenghiep.getDmnghenghiepPhanloai() == null) {
         this.nghenghiep.setDmnghenghiepPhanloai(new DmNgheNghiepBaoCao());
       }
     }
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
     this.log.info("getList_donvitinh_item B4151_Dm Thuoc....", new Object[0]);

     this.listB269_Phanloai = new ArrayList();
     this.listB269_Phanloai.add(new SelectItem(null, ""));

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

   public static long getSerialVersionUID()
   {
     return 1L;
   }

   public List<SelectItem> getListB269_Phanloai()
   {
     return this.listB269_Phanloai;
   }

   public void setListB269_Phanloai(List<SelectItem> listB269_Phanloai)
   {
     this.listB269_Phanloai = listB269_Phanloai;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B269_Nghenghiep_Edit

 * JD-Core Version:    0.7.0.1

 */