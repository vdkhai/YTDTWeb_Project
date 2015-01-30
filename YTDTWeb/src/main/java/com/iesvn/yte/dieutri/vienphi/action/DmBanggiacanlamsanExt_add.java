 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
 import com.iesvn.yte.entity.DanhSachLoaiClsBangGia;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DtDmBangGiaCanLamSan_add")
 @Scope(ScopeType.SESSION)
 public class DmBanggiacanlamsanExt_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   @In(required=false)
   @Out(required=false)
   private DtDmClsBangGia clsbanggia;
   private List<SelectItem> listMaLoai;
   private List<SelectItem> listPhanLoai;
   private List<SelectItem> listKhoa;
   private List<SelectItem> listLoaiPTTT;
   private Integer dmkhoaMaso;
   private String maLoaiCLS;
   private DanhSachLoaiClsBangGia dsLoaiCLS;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmBanggiacanlamsanExt_add....", new Object[0]);

     resetValue();
     initComboxMaLoai();
     initComboxPhanLoai();
     initComboxKhoa();
     initComboxLoaiPTTT();
     this.dsLoaiCLS = new DanhSachLoaiClsBangGia();
     return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan_add";
   }

   public void save(int phanLoaiId)
   {
     this.log.info("Save DmBanggiacanlamsanExt_add...., maLoaiCLS = " + this.maLoaiCLS, new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.clsbanggia.getDtdmclsbgMa();
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
       DtDmClsBangGia bg = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.clsbanggia.getDtdmclsbgMa(), "DtDmClsBangGia", "dtdmclsbgMa");
       if (bg != null)
       {
         FacesMessages.instance().add(IConstantsRes.MA_KO_TRUNG_NHAU, new Object[0]);
         return;
       }
       bg = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.clsbanggia.getDtdmclsbgDiengiai(), "DtDmClsBangGia", "dtdmclsbgDiengiai");
       if (bg != null)
       {
         FacesMessages.instance().add(IConstantsRes.TEN_KO_TRUNG_NHAU, new Object[0]);
         return;
       }
       this.log.info("ngayCN = " + this.clsbanggia.getDtdmclsbgNgaygiocn(), new Object[0]);
       DieuTriUtilDelegate.getInstance().create(this.clsbanggia);
       DtDmClsBangGia bg1 = (DtDmClsBangGia)DieuTriUtilDelegate.getInstance().findByMa(this.clsbanggia.getDtdmclsbgMa(), "DtDmClsBangGia", "dtdmclsbgMa");
       DtDmClsKhoa dtDmClsKhoa = new DtDmClsKhoa();
       dtDmClsKhoa.setDtdmclsKhoaChon(Boolean.valueOf(true));
       dtDmClsKhoa.setDtdmclsKhoaNgaygiocn(Double.valueOf(new Date().getTime()));
       if (this.dmkhoaMaso.intValue() == -1) {
         dtDmClsKhoa.setDmkhoaMaso(null);
       } else {
         dtDmClsKhoa.setDmkhoaMaso(new DmKhoa(this.dmkhoaMaso));
       }
       dtDmClsKhoa.setDtdmclsMaso(bg1);
       if (dtDmClsKhoa.getDmkhoaMaso() != null) {
         DieuTriUtilDelegate.getInstance().create(dtDmClsKhoa);
       }
       FacesMessages.instance().add(IConstantsRes.clsbanggia_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.clsbanggia_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmBanggiacanlamsanExt_add....", new Object[0]);

     return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan";
   }

   public void reset()
   {
     this.log.info("Reset DmBanggiacanlamsanExt_add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.clsbanggia = new DtDmClsBangGia();
     this.clsbanggia.setDtdmclsbgMaloai(new DtDmCls());
     this.clsbanggia.setDtdmclsbgPhanloai(new DtDmCls());
     this.clsbanggia.setDtdmclsbgLoaipttt(new DtDmLoaiPhauThuat());
     this.clsbanggia.setDtdmclsbgChon(Boolean.valueOf(true));
     this.dmkhoaMaso = new Integer(-1);
     this.maLoaiCLS = "";
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.DmBanggiacanlamsanExt_add

 * JD-Core Version:    0.7.0.1

 */