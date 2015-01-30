 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
 import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
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

 @Name("DmCanlamsanKetqua_add")
 @Scope(ScopeType.SESSION)
 public class DmCanlamsanKetquaExt_add
 {
   @In(required=false)
   @Out(required=false)
   private DtDmClsKetQua dtDmClsKetQua;
   private Boolean isUpdate = Boolean.valueOf(false);
   @Logger
   private Log log;
   private List<SelectItem> listKhoaCLS;
   private List<SelectItem> listClsBanggia;
   private Integer dmkhoaMaso;
   private Integer dtdmclsbgMaso;

   @Create
   public String init()
   {
     this.log.info("Init DmCanlamsanKetqua_add....", new Object[0]);

     resetValue();

     return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua_add";
   }

   public void resetValue()
   {
     this.dtDmClsKetQua = new DtDmClsKetQua();
     this.listKhoaCLS = new ArrayList();
     this.listClsBanggia = new ArrayList();
     SelectItem itemTemp = new SelectItem(null, "---");
     this.listKhoaCLS.add(itemTemp);
     List<DmKhoa> listKhoaCLSTemp = DieuTriUtilDelegate.getInstance().findAll("DmKhoa", "dmkhoaDt", true);
     for (DmKhoa dmKhoa : listKhoaCLSTemp) {
       if ((dmKhoa.getDmkhoaCls() != null) && (dmKhoa.getDmkhoaCls().booleanValue() == true))
       {
         SelectItem item = new SelectItem(dmKhoa.getDmkhoaMaso(), dmKhoa.getDmkhoaTen());
         this.listKhoaCLS.add(item);
       }
     }
   }

   public void displayCLS()
   {
     this.log.info("Init displayCLS....", new Object[0]);
     this.listClsBanggia = new ArrayList();
     DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();
     List<DtDmClsBangGia> temp = (ArrayList)dtDmClsBangGiaDelegate.getDtDmClsBangGiaByMaSoKhoa(this.dmkhoaMaso);
     SelectItem itemTemp = new SelectItem(null, "---");
     this.listClsBanggia.add(itemTemp);
     if (temp != null) {
       for (DtDmClsBangGia dtDmClsBangGia : temp) {
         if ((dtDmClsBangGia.getDtdmclsbgXn() != null) && (dtDmClsBangGia.getDtdmclsbgXn().booleanValue() == true))
         {
           SelectItem item = new SelectItem(dtDmClsBangGia.getDtdmclsbgMaso(), dtDmClsBangGia.getDtdmclsbgDiengiai());
           this.listClsBanggia.add(item);
         }
       }
     }
   }

   public String update()
   {
     this.log.info("Init DmCanlamsanKetqua_add_update....", new Object[0]);
     resetValue();

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDtDmClsKetQua");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1)
     {
       this.dtDmClsKetQua = ((DtDmClsKetQua)((List)lsDataModel.getWrappedData()).get(rowIndex));
       if (this.dtDmClsKetQua.getDtdmclsbgMaso() != null)
       {
         this.dtdmclsbgMaso = this.dtDmClsKetQua.getDtdmclsbgMaso().getDtdmclsbgMaso();
         this.dmkhoaMaso = ((DtDmClsKhoa)DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(this.dtdmclsbgMaso).get(0)).getDmkhoaMaso().getDmkhoaMaso();
         displayCLS();
       }
     }
     return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua_add";
   }

   public void save()
   {
     this.log.info("Save DmCanlamsanKetqua_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.dtDmClsKetQua.getDtdmclskqMa();
     this.dtDmClsKetQua.setDtdmclskqNgaygiocn(Double.valueOf(date.getTime()));
     DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();
     this.dtDmClsKetQua.setDtdmclsbgMaso(dtDmClsBangGiaDelegate.find(this.dtdmclsbgMaso));
     try
     {
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       if (this.dtDmClsKetQua.getDtdtclskqMaso() == null) {
         dtUtil.create(this.dtDmClsKetQua);
       } else {
         dtUtil.edit(this.dtDmClsKetQua);
       }
       FacesMessages.instance().add(IConstantsRes.dtdmclskq_cr_su, new Object[] { this.dtDmClsKetQua.getDtdmclskqMa() });
       resetValue();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.dtdmclskq_cr_fa, new Object[] { this.dtDmClsKetQua.getDtdmclskqMa() });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmCanlamsanKetqua_add....", new Object[0]);

     return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua";
   }

   public DtDmClsKetQua getDtDmClsKetQua()
   {
     return this.dtDmClsKetQua;
   }

   public void setDtDmClsKetQua(DtDmClsKetQua dtDmClsKetQua)
   {
     this.dtDmClsKetQua = dtDmClsKetQua;
   }

   public Boolean getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setIsUpdate(Boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public List<SelectItem> getListKhoaCLS()
   {
     return this.listKhoaCLS;
   }

   public void setListKhoaCLS(List<SelectItem> listKhoaCLS)
   {
     this.listKhoaCLS = listKhoaCLS;
   }

   public List<SelectItem> getListClsBanggia()
   {
     return this.listClsBanggia;
   }

   public void setListClsBanggia(List<SelectItem> listClsBanggia)
   {
     this.listClsBanggia = listClsBanggia;
   }

   public Integer getDmkhoaMaso()
   {
     return this.dmkhoaMaso;
   }

   public void setDmkhoaMaso(Integer dmkhoaMaso)
   {
     this.dmkhoaMaso = dmkhoaMaso;
   }

   public Integer getDtdmclsbgMaso()
   {
     return this.dtdmclsbgMaso;
   }

   public void setDtdmclsbgMaso(Integer dtdmclsbgMaso)
   {
     this.dtdmclsbgMaso = dtdmclsbgMaso;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.DmCanlamsanKetquaExt_add

 * JD-Core Version:    0.7.0.1

 */