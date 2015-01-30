 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsKhoaDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
 import com.iesvn.yte.entity.DanhSachLoaiClsBangGia;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.jsf.ListDataModel;
 import org.jboss.seam.log.Log;

 @Name("DtDmBangGiaCanLamSan_edit")
 @Scope(ScopeType.SESSION)
 public class DmBanggiacanlamsanExt_edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   private DtDmClsBangGia clsbanggia;
   private DtDmClsBangGia clsbanggiaTemp;
   private List<SelectItem> listMaLoai;
   private List<SelectItem> listPhanLoai;
   private List<SelectItem> listLoaiPTTT;
   private String maLoaiCLS;
   private DanhSachLoaiClsBangGia dsLoaiCLS;
   private String maCls;
   private List<SelectItem> listKhoa;
   private Integer dmkhoaMaso;
   @Logger
   private Log log;

   public String init(Integer clsbgMaso)
   {
     this.log.info("Init DmBanggiacanlamsanExt_edit...., ma so = " + clsbgMaso, new Object[0]);
     this.clsbanggia = DtDmClsBangGiaDelegate.getInstance().find(clsbgMaso);
     resetValue();
     initComboxKhoa();
     initComboxMaLoai();
     initComboxPhanLoai();
     initComboxLoaiPTTT();
     setValueForForm();
     this.maCls = this.clsbanggia.getDtdmclsbgMa();
     this.clsbanggiaTemp = this.clsbanggia;
     this.dsLoaiCLS = new DanhSachLoaiClsBangGia();
     this.maLoaiCLS = this.clsbanggia.getDtdmclsbgLoai();

     List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(clsbgMaso);
     if (dtDmClsKhoaList.size() > 0) {
       this.dmkhoaMaso = Integer.valueOf(((DtDmClsKhoa)dtDmClsKhoaList.get(0)).getDmkhoaMaso() == null ? -1 : ((DtDmClsKhoa)dtDmClsKhoaList.get(0)).getDmkhoaMaso().getDmkhoaMaso().intValue());
     }
     System.out.println("DmBanggiacanlamsanExt_edit.init()*********maCls=" + this.maCls);

     return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan_edit";
   }

   public void save(int phanLoaiId)
   {
     this.log.info("Save DmBanggiacanlamsanExt_edit....", new Object[0]);


     Date date = new Date();



     this.clsbanggia.setDtdmclsbgNgaygiocn(Double.valueOf(date.getTime()));
     if (phanLoaiId == -1)
     {
       this.clsbanggia.setDtdmclsbgPhanloai(null);
       this.clsbanggia.setDtdmclsbgMaloai(null);
     }
     else
     {
       this.clsbanggia.setDtdmclsbgMaloai(this.clsbanggia.getDtdmclsbgPhanloai());
     }
     this.clsbanggia.setDtdmclsbgLoai(this.maLoaiCLS);
     if ((this.clsbanggia.getDtdmclsbgLoaipttt() != null) &&
       (this.clsbanggia.getDtdmclsbgLoaipttt().getDtdmloaiptMaso() != null) &&
       (this.clsbanggia.getDtdmclsbgLoaipttt().getDtdmloaiptMaso().intValue() == -1)) {
       this.clsbanggia.setDtdmclsbgLoaipttt(null);
     }
     try
     {
       DtDmClsBangGia bg = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.maCls, "DtDmClsBangGia", "dtdmclsbgMa");
       if (bg != null)
       {
         System.out.println("DmBanggiacanlamsanExt_edit.save()************* clsbanggiaTemp.maso= " + this.clsbanggia.getDtdmclsbgMaso());
         if (bg.getDtdmclsbgMaso().intValue() != this.clsbanggia.getDtdmclsbgMaso().intValue())
         {
           this.log.info("*****clsbanggia !=null", new Object[0]);
           FacesMessages.instance().add(IConstantsRes.TEN_KO_TRUNG_NHAU, new Object[0]);
           return;
         }
       }
       DieuTriUtilDelegate.getInstance().edit(this.clsbanggia);
       List<DtDmClsKhoa> dtDmClsKhoaList = DtDmClsKhoaDelegate.getInstance().findByMaSoCLS(this.clsbanggia.getDtdmclsbgMaso());
       if (dtDmClsKhoaList.size() > 0)
       {
         DtDmClsKhoa dtDmClsKhoa = (DtDmClsKhoa)dtDmClsKhoaList.get(0);
         dtDmClsKhoa.setDtdmclsKhoaNgaygiocn(Double.valueOf(new Date().getTime()));
         if (this.dmkhoaMaso.intValue() == -1) {
           dtDmClsKhoa.setDmkhoaMaso(null);
         } else {
           dtDmClsKhoa.setDmkhoaMaso(new DmKhoa(this.dmkhoaMaso));
         }
         if (dtDmClsKhoa.getDmkhoaMaso() != null) {
           DieuTriUtilDelegate.getInstance().edit(dtDmClsKhoa);
         } else {
           DieuTriUtilDelegate.getInstance().remove(dtDmClsKhoa);
         }
       }
       else
       {
         DtDmClsKhoa dtDmClsKhoa = new DtDmClsKhoa();
         dtDmClsKhoa.setDtdmclsMaso(this.clsbanggia);
         dtDmClsKhoa.setDtdmclsKhoaNgaygiocn(Double.valueOf(new Date().getTime()));
         if (this.dmkhoaMaso.intValue() == -1) {
           dtDmClsKhoa.setDmkhoaMaso(null);
         } else {
           dtDmClsKhoa.setDmkhoaMaso(new DmKhoa(this.dmkhoaMaso));
         }
         dtDmClsKhoa.setDtdmclsKhoaChon(Boolean.valueOf(true));
         if (dtDmClsKhoa.getDmkhoaMaso() != null) {
           DieuTriUtilDelegate.getInstance().create(dtDmClsKhoa);
         }
       }
       FacesMessages.instance().add(IConstantsRes.clsbanggia_up_su, new Object[] { this.maCls });
       if (phanLoaiId == -1) {
         this.clsbanggia.setDtdmclsbgPhanloai(new DtDmCls());
       }
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       e.printStackTrace();
       FacesMessages.instance().add(IConstantsRes.clsbanggia_up_fa, new Object[] { this.maCls });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmBanggiacanlamsanExt_edit....", new Object[0]);

     return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan";
   }

   private void setValueForForm()
   {
     this.log.info("Set value for form DmNhaCungCapExt_edit...., ma so = " + this.clsbanggia.getDtdmclsbgMaso(), new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDtDmClsBangGia");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.clsbanggia = ((DtDmClsBangGia)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
   }

   private void resetValue()
   {
     this.clsbanggia = new DtDmClsBangGia();
   }

   private void initComboxMaLoai()
   {
     this.listMaLoai = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listMaLoai.add(item);

     List<DtDmCls> lstempCls = DieuTriUtilDelegate.getInstance().findAll("DtDmCls", "dtdmclsChon", true);
     for (DtDmCls dtDmCls : lstempCls)
     {
       item = new SelectItem(dtDmCls.getDtdmclsMaso(), dtDmCls.getDtdmclsTen());
       this.listMaLoai.add(item);
     }
   }

   private void initComboxKhoa()
   {
     this.listKhoa = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listKhoa.add(item);

     List<DmKhoa> listTemp = DieuTriUtilDelegate.getInstance().findAll("DmKhoa");
     for (DmKhoa dmKhoa : listTemp) {
       if ((dmKhoa.getDmkhoaCls() != null) && (dmKhoa.getDmkhoaCls().booleanValue() == true))
       {
         item = new SelectItem(dmKhoa.getDmkhoaMaso(), dmKhoa.getDmkhoaTen());
         this.listKhoa.add(item);
       }
     }
   }

   private void initComboxPhanLoai()
   {
     this.listPhanLoai = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listPhanLoai.add(item);

     List<DtDmCls> lstempCls = DieuTriUtilDelegate.getInstance().findAll("DtDmCls", "dtdmclsChon", true);
     for (DtDmCls dtDmCls : lstempCls)
     {
       item = new SelectItem(dtDmCls.getDtdmclsMaso(), dtDmCls.getDtdmclsTen());
       this.listPhanLoai.add(item);
     }
   }

   private void initComboxLoaiPTTT()
   {
     this.listLoaiPTTT = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listLoaiPTTT.add(item);

     List<DtDmLoaiPhauThuat> lstempLoaiPT = DieuTriUtilDelegate.getInstance().findAll("DtDmLoaiPhauThuat", "dtdmloaiptChon", true);
     for (DtDmLoaiPhauThuat dtDmLoaiPT : lstempLoaiPT)
     {
       item = new SelectItem(dtDmLoaiPT.getDtdmloaiptMaso(), dtDmLoaiPT.getDtdmloaiptTen());
       this.listLoaiPTTT.add(item);
     }
   }

   public DtDmClsBangGia getClsbanggia()
   {
     return this.clsbanggia;
   }

   public void setClsbanggia(DtDmClsBangGia clsbanggia)
   {
     this.clsbanggia = clsbanggia;
   }

   public List<SelectItem> getListMaLoai()
   {
     return this.listMaLoai;
   }

   public void setListMaLoai(List<SelectItem> listMaLoai)
   {
     this.listMaLoai = listMaLoai;
   }

   public List<SelectItem> getListPhanLoai()
   {
     return this.listPhanLoai;
   }

   public void setListPhanLoai(List<SelectItem> listPhanLoai)
   {
     this.listPhanLoai = listPhanLoai;
   }

   public String getMaCls()
   {
     return this.maCls;
   }

   public void setMaCls(String maCls)
   {
     this.maCls = maCls;
   }

   public DtDmClsBangGia getClsbanggiaTemp()
   {
     return this.clsbanggiaTemp;
   }

   public void setClsbanggiaTemp(DtDmClsBangGia clsbanggiaTemp)
   {
     this.clsbanggiaTemp = clsbanggiaTemp;
   }

   public List<SelectItem> getListKhoa()
   {
     return this.listKhoa;
   }

   public void setListKhoa(List<SelectItem> listKhoa)
   {
     this.listKhoa = listKhoa;
   }

   public Integer getDmkhoaMaso()
   {
     return this.dmkhoaMaso;
   }

   public void setDmkhoaMaso(Integer dmkhoaMaso)
   {
     this.dmkhoaMaso = dmkhoaMaso;
   }

   public String getMaLoaiCLS()
   {
     return this.maLoaiCLS;
   }

   public void setMaLoaiCLS(String maLoaiCLS)
   {
     this.maLoaiCLS = maLoaiCLS;
   }

   public DanhSachLoaiClsBangGia getDsLoaiCLS()
   {
     return this.dsLoaiCLS;
   }

   public void setDsLoaiCLS(DanhSachLoaiClsBangGia dsLoaiCLS)
   {
     this.dsLoaiCLS = dsLoaiCLS;
   }

   public List<SelectItem> getListLoaiPTTT()
   {
     return this.listLoaiPTTT;
   }

   public void setListLoaiPTTT(List<SelectItem> listLoaiPTTT)
   {
     this.listLoaiPTTT = listLoaiPTTT;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.DmBanggiacanlamsanExt_edit

 * JD-Core Version:    0.7.0.1

 */