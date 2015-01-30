 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuDuTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtPhieuDt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmTang;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.report.XuatReportDuocPham;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.*;
 import java.util.Map.Entry;
 import javax.faces.model.SelectItem;
 import net.sf.jasperreports.engine.JasperPrint;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Destroy;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3142_Phieulanhthuoctutruc")
 @Synchronized(timeout=6000000L)
 public class PhieuDTLanhThuocTuTrucAction
   implements Serializable
 {
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(PhieuDTLanhThuocTuTrucAction.class);
   @DataModel
   private ArrayList<CtPhieuDuTruExt> listCtDTLanhThuocEx;
   private ArrayList<CtPhieuDt> listCtPhieuDtDel;
   @DataModelSelection
   private CtPhieuDuTruExt selected;
   private List<Integer> listMas;
   private PhieuDuTru phieuDuTru;
   private String maPhieu;
   private String ngayXuat;
   private String ngayXuatDen;
   private String dmtMa;
   private Double tonkho;
   private Double xuat;
   private String tonkhoMa;
   private Integer updateItem;
   private Integer count;
   private String ngayHienTai;
   private String nguonMa;
   private String kpMa;
   private String malk;
   private Double tongTien;
   private String hienThiGhiNhan;
   private String hienThiHuyPhieu;
   private String hienThiInPhieu;
   private String gioLapPhieuTu;
   private String gioLapPhieuDen;
   private String dmDoiTuongTen;
   String dmKhoXuat;
   String dmKhoaNhan;
   private String dmLoaiTen;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs;
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc;
   HashMap<String, DmThuoc> hmDmThuoc;
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   private List<SelectItem> listDmLoaiPhieus;
   HashMap<String, DmLoaiPhieu> hmDmLoaiPhieu;
   private String dmLoaiPhieuMa;
   private List<SelectItem> listDmDoiTuongs;
   HashMap<String, DmDoiTuong> hmDoiTuong;
   private List<SelectItem> listDmTangs;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   private String isUpdate;
   private String isDeleted;
   private int CHUA_LUU_PHIEU_XUAT = 0;
   private static String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";
   private DmThuocDelegate dmThuocDelegate;
   private String dmtTen;
   private List<SelectItem> listDmThuocs;
   private DmKhoaDelegate dmKhoaDelegate;
   private List<SelectItem> listDmKhoaNhans;
   HashMap<String, DmKhoa> hmDmKhoaNhan;
   private String dmkhoaNhanTen;
   private List<SelectItem> listDmKhoXuats;
   HashMap<String, DmKhoa> hmDmKhoXuat;
   private Integer dmkhoaXuatMaso;
   private String dmkhoaXuatMa;
   private String dmkhoaXuatTen;
   int index;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP;

   @Begin(join=true)
   public String init(String loaiDT, String khoNhan, String khoXuat)
   {
     log.info("***** init PhieuDTLanhThuocTuTrucAction() *****");
     reset();
     this.dmKhoXuat = khoXuat;
     this.dmKhoaNhan = khoNhan;
     this.listDmKhoXuats.clear();
     this.hmDmKhoXuat.clear();
     refreshDmKhoXuat();

     this.listDmThuocs.clear();
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();

     refreshDmLoaiThuoc();
     refreshDmDoiTuong();
     refreshDmKhoaNhan();
     return "VienPhiTaiKhoa_PhieuDTLanhThuocTuTru";
   }

   @Factory("listCtDTLanhThuocEx")
   public void createTable()
   {
     log.info("-----createTable()-----");
     this.listCtDTLanhThuocEx = new ArrayList();
   }

   public String getDmtTen()
   {
     return this.dmtTen;
   }

   public void setDmtTen(String dmtTen)
   {
     this.dmtTen = dmtTen;
   }

   public List<SelectItem> getListDmThuocs()
   {
     return this.listDmThuocs;
   }

   public void setListDmThuocs(List<SelectItem> listDmThuocs)
   {
     this.listDmThuocs = listDmThuocs;
   }

   public void onblurMaThuocAction()
   {
     log.info("-----BEGIN onblurMaThuocAction()-----");
     DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.dmtMa.toUpperCase());
     if (dmThuoc != null)
     {
       setDmtTen(dmThuoc.getDmthuocTen());
       log.info("-----DA THAY DMTHUOC-----");
     }
     else
     {
       setDmtTen("");
     }
     log.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     log.info("-----BEGIN onblurTenThuocAction()-----");
     Boolean hasTenThuoc = Boolean.valueOf(false);
     String maThuoc = null;
     if (this.hmDmThuoc != null)
     {
       Set set = this.hmDmThuoc.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmThuoc dmThuoc = (DmThuoc)me.getValue();
         if ((dmThuoc.getDmthuocTen() == this.dmtTen) || (dmThuoc.getDmthuocTen().equals(this.dmtTen)))
         {
           hasTenThuoc = Boolean.valueOf(true);
           maThuoc = dmThuoc.getDmthuocMa();
           break;
         }
       }
     }
     if (hasTenThuoc.booleanValue())
     {
       setDmtMa(maThuoc);
       log.info("-----DA THAY DMTHUOC-----");
     }
     else
     {
       setDmtMa("");
     }
     log.info("-----END onblurTenThuocAction()-----");
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();

     String loaiMa = "";
     String phanloaiMa = "";
     String loaiPhieu = this.phieuDuTru.getPhieudtLoaiPhieu();
     if ((loaiPhieu != null) || (!loaiPhieu.equals(""))) {
       if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa() != null) {
         if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
         {
           if ((!this.dmLoaiPhieuMa.equals("HT")) && (!this.dmLoaiPhieuMa.equals("GN")))
           {
             loaiMa = "TD-TT";
             DmLoaiPhieu lp = (DmLoaiPhieu)this.hmDmLoaiPhieu.get(this.dmLoaiPhieuMa);
             if ((lp != null) && (lp.getDmloaiphieuDvt() != null)) {
               loaiMa = loaiMa + ";" + lp.getDmloaiphieuDvt();
             }
           }
           else
           {
             loaiMa = "TD-" + this.dmLoaiPhieuMa;
           }
         }
         else if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("DY"))
         {
           DmLoaiPhieu lp = (DmLoaiPhieu)this.hmDmLoaiPhieu.get(this.dmLoaiPhieuMa);
           if (lp.getDmloaiphieuDvt() != null)
           {
             phanloaiMa = lp.getDmloaiphieuDvt();
             loaiMa = this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
           }
         }
         else
         {
           loaiMa = this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
         }
       }
     }
     System.out.println("loaiMa: " + loaiMa);
     System.out.println("phanloaiMa: " + phanloaiMa);
     System.out.println("Kho: " + this.phieuDuTru.getPhieudtMakho().getDmkhoaMaso());
     List<DmThuoc> lstDmThuoc = new ArrayList();
     if ((!loaiMa.equals("")) && (!this.phieuDuTru.getPhieudtLoaiPhieu().equals(""))) {
       lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa, phanloaiMa, this.phieuDuTru.getPhieudtMakho().getDmkhoaMaso());
     }
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0))
     {
       System.out.println("lstDmThuoc.size: " + lstDmThuoc.size());
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
       }
     }
   }

   public String getDmkhoaNhanTen()
   {
     return this.dmkhoaNhanTen;
   }

   public void setDmkhoaNhanTen(String dmkhoaNhanTen)
   {
     this.dmkhoaNhanTen = dmkhoaNhanTen;
   }

   public List<SelectItem> getListDmKhoaNhans()
   {
     return this.listDmKhoaNhans;
   }

   public void setListDmKhoaNhans(List<SelectItem> listDmKhoaNhans)
   {
     this.listDmKhoaNhans = listDmKhoaNhans;
   }

   public void onblurMaKhoaNhanAction()
   {
     log.info("-----BEGIN onblurMaKhoaNhanAction()-----");
     if ((this.phieuDuTru != null) && ((this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa() != "") || (this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa() != null)))
     {
       DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoaNhan.get(this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa().toUpperCase());
       if (dmKhoa != null)
       {
         this.phieuDuTru.setDmkhoaMaso(dmKhoa);
         setDmkhoaNhanTen(dmKhoa.getDmkhoaTen());
         log.info("-----DA THAY DMKHOANHAN-----");
       }
       else
       {
         this.phieuDuTru.setDmkhoaMaso(new DmKhoa());
         setDmkhoaNhanTen("");
       }
     }
     refreshDmTang();
     this.phieuDuTru.setDmtangMaso(new DmTang());
     log.info("-----END onblurMaKhoaNhanAction()-----");
   }

   public void onblurTenKhoaNhanAction()
   {
     log.info("-----BEGIN onblurTenKhoaNhanAction()-----");
     Boolean hasTenKhoaNhan = Boolean.valueOf(false);
     DmKhoa dmKhoa_Finded = new DmKhoa();
     if ((this.dmkhoaNhanTen != "") &&
       (this.hmDmKhoaNhan != null))
     {
       Set set = this.hmDmKhoaNhan.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmKhoa dmKhoa = (DmKhoa)me.getValue();
         if ((dmKhoa.getDmkhoaTen() == this.dmkhoaNhanTen) || (dmKhoa.getDmkhoaTen().equals(this.dmkhoaNhanTen)))
         {
           hasTenKhoaNhan = Boolean.valueOf(true);
           dmKhoa_Finded = dmKhoa;
           break;
         }
       }
     }
     if (hasTenKhoaNhan.booleanValue())
     {
       this.phieuDuTru.setDmkhoaMaso(dmKhoa_Finded);
       setDmkhoaNhanTen(dmKhoa_Finded.getDmkhoaTen());
       log.info("-----DA THAY DMKHOANHAN-----");
     }
     else
     {
       this.phieuDuTru.setDmkhoaMaso(new DmKhoa());
       setDmkhoaNhanTen("");
     }
     refreshDmTang();
     this.phieuDuTru.setDmtangMaso(new DmTang());
     log.info("-----END onblurTenKhoaNhanAction()-----");
   }

   public void refreshDmKhoaNhan()
   {
     this.listDmKhoaNhans.clear();
     this.hmDmKhoaNhan.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();

     List<DmKhoa> lstDmKhoa = this.dmKhoaDelegate.getKhoaNhom12NOTINKhoDuoc();
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhoaNhans.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoaNhan.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public Integer getDmkhoaXuatMaso()
   {
     return this.dmkhoaXuatMaso;
   }

   public void setDmkhoaXuatMaso(Integer dmkhoaXuatMaso)
   {
     this.dmkhoaXuatMaso = dmkhoaXuatMaso;
   }

   public String getDmkhoaXuatMa()
   {
     return this.dmkhoaXuatMa;
   }

   public void setDmkhoaXuatMa(String dmkhoaXuatMa)
   {
     this.dmkhoaXuatMa = dmkhoaXuatMa;
   }

   public String getDmkhoaXuatTen()
   {
     return this.dmkhoaXuatTen;
   }

   public void setDmkhoaXuatTen(String dmkhoaXuatTen)
   {
     this.dmkhoaXuatTen = dmkhoaXuatTen;
   }

   public List<SelectItem> getListDmKhoXuats()
   {
     return this.listDmKhoXuats;
   }

   public void setListDmKhoXuats(List<SelectItem> listDmKhoXuats)
   {
     this.listDmKhoXuats = listDmKhoXuats;
   }

   public void onblurMaKhoXuatAction()
   {
     log.info("-----BEGIN onblurMaKhoXuatAction()-----");
     if (this.dmkhoaXuatMa != "")
     {
       DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoXuat.get(this.dmkhoaXuatMa.toUpperCase());
       if (dmKhoa != null)
       {
         this.phieuDuTru.setPhieudtMakho(dmKhoa);
         setDmkhoaXuatMaso(dmKhoa.getDmkhoaMaso());
         setDmkhoaXuatTen(dmKhoa.getDmkhoaTen());
         log.info("-----DA THAY DMKHOXUAT-----");
       }
       else
       {
         this.phieuDuTru.setPhieudtMakho(null);
         setDmkhoaXuatMaso(null);
         setDmkhoaXuatTen("");
         setDmkhoaXuatMa("");
       }
     }
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     setDmtMa("");
     setDmtTen("");
     log.info("-----END onblurMaKhoXuatAction()-----");
   }

   public void onblurTenKhoXuatAction()
   {
     log.info("-----BEGIN onblurTenKhoXuatAction()-----");
     Boolean hasTenKhoXuat = Boolean.valueOf(false);
     DmKhoa dmKhoa_Finded = new DmKhoa();
     if ((this.dmkhoaXuatTen != "") &&
       (this.hmDmKhoXuat != null))
     {
       Set set = this.hmDmKhoXuat.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmKhoa dmKhoa = (DmKhoa)me.getValue();
         if ((dmKhoa.getDmkhoaTen() == this.dmkhoaXuatTen) || (dmKhoa.getDmkhoaTen().equals(this.dmkhoaXuatTen)))
         {
           hasTenKhoXuat = Boolean.valueOf(true);
           dmKhoa_Finded = dmKhoa;
           break;
         }
       }
     }
     if (hasTenKhoXuat.booleanValue())
     {
       this.phieuDuTru.setPhieudtMakho(dmKhoa_Finded);
       setDmkhoaXuatMa(dmKhoa_Finded.getDmkhoaMa());
       setDmkhoaXuatMaso(dmKhoa_Finded.getDmkhoaMaso());
       log.info("-----DA THAY DMKHOXUAT-----");
     }
     else
     {
       this.phieuDuTru.setPhieudtMakho(null);
       setDmkhoaXuatMa("");
       setDmkhoaXuatMaso(null);
     }
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     setDmtMa("");
     setDmtTen("");
     log.info("-----END onblurTenKhoXuatAction()-----");
   }

   public void refreshDmKhoXuat()
   {
     this.listDmKhoXuats.clear();
     this.hmDmKhoXuat.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();

     List<DmKhoa> lstDmKhoa = this.dmKhoaDelegate.getKhoChinh_KhoLe();
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhoXuats.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoXuat.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public void onblurTenTangAction()
   {
     if ((this.phieuDuTru != null) && (this.phieuDuTru.getDmtangMaso(true).getDmtangTen() != null))
     {
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", this.phieuDuTru.getDmtangMaso(true).getDmtangTen());
       if ((lstTangs != null) && (lstTangs.size() > 0)) {
         this.phieuDuTru.setDmtangMaso((DmTang)lstTangs.get(0));
       } else {
         this.phieuDuTru.setDmtangMaso(new DmTang());
       }
     }
   }

   public void refreshDmTang()
   {
     this.listDmTangs.clear();
     if ((this.phieuDuTru != null) && (this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaMaso() != null))
     {
       String khoaMa = this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa();
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       this.listDmTangs.add(new SelectItem("Tat ca"));

       List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang", "dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
       if ((lstDmTangs != null) && (lstDmTangs.size() > 0)) {
         for (DmTang dmTang : lstDmTangs) {
           this.listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
         }
       }
     }
   }

   public String getDmLoaiTen()
   {
     return this.dmLoaiTen;
   }

   public void setDmLoaiTen(String dmLoaiTen)
   {
     this.dmLoaiTen = dmLoaiTen;
   }

   public List<SelectItem> getListDmLoaiThuocs()
   {
     return this.listDmLoaiThuocs;
   }

   public void setListDmLoaiThuocs(List<SelectItem> listDmLoaiThuocs)
   {
     this.listDmLoaiThuocs = listDmLoaiThuocs;
   }

   public void onblurMaLoaiAction()
   {
     log.info("-----BEGIN onblurMaLoaiAction()-----" + this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa());
     String loaihang_ma = this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocMa();
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null)
     {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
       this.phieuDuTru.setDmloaithuocMaso(dmLoaiThuoc);
     }
     else
     {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setDmtMa("");
     setDmtTen("");
     this.phieuDuTru.setPhieudtLoaiPhieu("");
     setDmLoaiPhieuMa("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     log.info("-----END onblurMaLoaiAction()-----");
   }

   public void onblurTenLoaiThuocAction()
   {
     log.info("-----BEGIN onblurTenLoaiThuocAction()-----");
     Boolean hasTenLoai = Boolean.valueOf(false);
     String maLoai = null;

     DmLoaiThuoc dmLoaiThuoc_Find = new DmLoaiThuoc();
     Set set = this.hmLoaiThuoc.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
       if ((dmLoaiThuoc.getDmloaithuocTen() == this.dmLoaiTen) || (dmLoaiThuoc.getDmloaithuocTen().equals(this.dmLoaiTen)))
       {
         hasTenLoai = Boolean.valueOf(true);
         dmLoaiThuoc_Find = dmLoaiThuoc;
         break;
       }
     }
     if (hasTenLoai.booleanValue()) {
       this.phieuDuTru.setDmloaithuocMaso(dmLoaiThuoc_Find);
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setDmtMa("");
     setDmtTen("");
     this.phieuDuTru.setPhieudtLoaiPhieu("");
     setDmLoaiPhieuMa("");
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     log.info("-----END onblurTenLoaiThuocAction()-----");
   }

   public void refreshDmLoaiThuoc()
   {
     this.listDmLoaiThuocs.clear();
     this.dmLoaiThuocDelegate = DmLoaiThuocDelegate.getInstance();
     this.hmLoaiThuoc.clear();
     this.hmLoaiThuoc = this.dmLoaiThuocDelegate.findAllDm();
     if (this.hmLoaiThuoc != null)
     {
       Set set = this.hmLoaiThuoc.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
         this.listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
       }
     }
   }

   public List<SelectItem> getListDmLoaiPhieus()
   {
     return this.listDmLoaiPhieus;
   }

   public void setListDmLoaiPhieus(List<SelectItem> listDmLoaiPhieus)
   {
     this.listDmLoaiPhieus = listDmLoaiPhieus;
   }

   public void refreshDmLoaiPhieu()
   {
     this.listDmLoaiPhieus.clear();
     this.hmDmLoaiPhieu.clear();
     this.dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
     if ((this.phieuDuTru != null) && (this.phieuDuTru.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus)
         {
           this.hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
           if (this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " + o.getDmloaiphieuTen()));
           }
         }
       }
     }
   }

   public void onblurLoaiPhieuAction()
   {
     log.info("-----BEGIN onblurLoaiPhieuAction()-----");
     if ((this.maPhieu == null) || (this.maPhieu.equals(""))) {
       this.listCtDTLanhThuocEx.clear();
     }
     String loaiPhieuItem = this.phieuDuTru.getPhieudtLoaiPhieu();
     if (!loaiPhieuItem.equals("")) {
       this.dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
     }
     if ((this.phieuDuTru != null) && (this.phieuDuTru.getPhieudtMakho().getDmkhoaMaso() != null)) {
       refreshDmThuoc();
     }
     log.info("-----END onblurLoaiPhieuAction()-----");
   }

   public String getDmDoiTuongTen()
   {
     return this.dmDoiTuongTen;
   }

   public void setDmDoiTuongTen(String dmDoiTuongTen)
   {
     this.dmDoiTuongTen = dmDoiTuongTen;
   }

   public List<SelectItem> getListDmDoiTuongs()
   {
     return this.listDmDoiTuongs;
   }

   public void setListDmDoiTuongs(List<SelectItem> listDmDoiTuongs)
   {
     this.listDmDoiTuongs = listDmDoiTuongs;
   }

   public void onblurMaDoiTuongAction()
   {
     log.info("-----BEGIN onblurMaDoiTuongAction()-----");
     String doituong_ma = this.phieuDuTru.getDmdoituongMaso(true).getDmdoituongMa();
     DmDoiTuong dmDoiTuong = (DmDoiTuong)this.hmDoiTuong.get(doituong_ma.toUpperCase());
     if (dmDoiTuong != null)
     {
       setDmDoiTuongTen(dmDoiTuong.getDmdoituongTen());
       this.phieuDuTru.setDmdoituongMaso(dmDoiTuong);
     }
     else
     {
       setDmDoiTuongTen("");
       this.phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
     }
     log.info("-----END onblurMaDoiTuongAction()-----");
   }

   public void onblurTenDoiTuongAction()
   {
     log.info("-----BEGIN onblurTenDoiTuongAction()-----");
     Boolean hasTenDT = Boolean.valueOf(false);
     String maDT = null;
     if (this.dmDoiTuongTen.equals("Tất cả"))
     {
       this.phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
     }
     else
     {
       DmDoiTuong dmDoiTuong_Find = new DmDoiTuong();
       Set set = this.hmDoiTuong.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmDoiTuong dmDoiTuong = (DmDoiTuong)me.getValue();
         if ((dmDoiTuong.getDmdoituongTen() == this.dmDoiTuongTen) || (dmDoiTuong.getDmdoituongTen().equals(this.dmDoiTuongTen)))
         {
           hasTenDT = Boolean.valueOf(true);
           dmDoiTuong_Find = dmDoiTuong;
           break;
         }
       }
       if (hasTenDT.booleanValue()) {
         this.phieuDuTru.setDmdoituongMaso(dmDoiTuong_Find);
       } else {
         this.phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
       }
     }
     log.info("-----END onblurTenDoiTuongAction()-----");
   }

   public void refreshDmDoiTuong()
   {
     this.listDmDoiTuongs.clear();
     DieuTriUtilDelegate dieutriDel = DieuTriUtilDelegate.getInstance();
     this.hmDoiTuong.clear();
     this.hmDoiTuong = dieutriDel.findByDoiTuongThuPhi();
     if (this.hmDoiTuong != null)
     {
       Set set = this.hmDoiTuong.entrySet();
       Iterator i = set.iterator();
       this.listDmDoiTuongs.add(new SelectItem("Tất cả"));
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmDoiTuong dmDoiTuong = (DmDoiTuong)me.getValue();
         this.listDmDoiTuongs.add(new SelectItem(dmDoiTuong.getDmdoituongTen()));
       }
     }
   }

   public void deleteCt(int index)
   {
     log.info("-----deleteCt()-----");
     CtPhieuDuTruExt ctExDel = (CtPhieuDuTruExt)this.listCtDTLanhThuocEx.remove(index);
     this.listCtPhieuDtDel.add(ctExDel.getCtPhieuDt());
     this.count = Integer.valueOf(this.listCtDTLanhThuocEx.size());
     tinhTien();
   }

   public String getDmKhoaNhan()
   {
     return this.dmKhoaNhan;
   }

   public void setDmKhoaNhan(String dmKhoaNhan)
   {
     this.dmKhoaNhan = dmKhoaNhan;
   }

   public void selectCt(Integer index)
   {
     log.info("-----selectCt()-----");
     try
     {
       this.selected = ((CtPhieuDuTruExt)this.listCtDTLanhThuocEx.get(index.intValue()));
       this.listMas = this.selected.getListThuocNoiTruMa();
       TonKho tk = this.selected.getTonKhoXuat();
       CtPhieuDt ctx = this.selected.getCtPhieuDt();
       this.updateItem = index;
       System.out.println("tonkhoMa: " + tk);
       if (tk.getTonkhoMa() != null)
       {
         this.tonkhoMa = tk.getTonkhoMa().toString();
         this.tonkho = tk.getTonkhoTon();
       }
       this.dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
       this.dmtTen = ctx.getDmthuocMaso().getDmthuocTen();
       this.xuat = ctx.getCtdtSoluong();
     }
     catch (Exception e)
     {
       log.info("***** error: " + e);
     }
   }

   public void tiepTucNhap()
   {
     log.info("-----tiepTucNhap()-----");
     if ((this.xuat == null) || (this.xuat.equals("")) || (this.tonkho == null) || (this.tonkho.equals(""))) {
       return;
     }
     if (("".equals(this.tonkhoMa)) && (this.tonkhoMa == null))
     {
       log.info("-----tonkho ma is null.");
     }
     else
     {
       log.info(String.format("-----tonkho ma: %s", new Object[] { this.tonkhoMa }));
       TonKho tk = null;
       try
       {
         TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();

         tk = tkDelegate.find(Integer.valueOf(this.tonkhoMa));
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
       CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
       CtPhieuDt ctx = new CtPhieuDt();
       Double slXuat = new Double("0");



       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       Date dHt = null;
       try
       {
         dHt = df.parse(this.ngayXuat);
       }
       catch (ParseException er)
       {
         return;
       }
       int viTri = -1;
       if (this.updateItem.intValue() != -1) {
         if (this.listCtDTLanhThuocEx.size() > this.updateItem.intValue())
         {
           viTri = this.updateItem.intValue();
           this.updateItem = Integer.valueOf(-1);
         }
         else
         {
           this.updateItem = Integer.valueOf(-1);
           return;
         }
       }
       if (tk != null)
       {
         ctx.setCtdtNgaygiocn(new Date());
         slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(this.xuat.doubleValue()).doubleValue());
         ctx.setCtdtSoluong(slXuat);
         ctx.setCtdtMalk(tk.getTonkhoMalienket());

         String malk = ctx.getCtdtMalk();
         Double slXuatKho = new Double("0");
         if (this.listCtDTLanhThuocEx.size() > 0)
         {
           for (int i = 0; i < this.listCtDTLanhThuocEx.size(); i++)
           {
             CtPhieuDuTruExt ctPhieuDuTruExt = (CtPhieuDuTruExt)this.listCtDTLanhThuocEx.get(i);
             CtPhieuDt ctxk = ctPhieuDuTruExt.getCtPhieuDt();
             if (malk.toUpperCase().equals(ctxk.getCtdtMalk())) {
               slXuatKho = Double.valueOf(slXuatKho.doubleValue() + ctxk.getCtdtSoluong().doubleValue());
             }
           }
           Double xuat = ctx.getCtdtSoluong();
           slXuatKho = Double.valueOf(slXuatKho.doubleValue() + Double.valueOf(xuat.doubleValue()).doubleValue());
           if (slXuatKho.doubleValue() > tk.getTonkhoTon().doubleValue())
           {
             FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_SLKHONGDUXUAT, new Object[0]);
             return;
           }
         }
         if (viTri == -1)
         {
           log.info("-----them moi ct");
           if (this.listCtDTLanhThuocEx.size() > 0)
           {
             int vt = -1;
             double sl = 0.0D;
             boolean foundOnNewsList = false;
             for (int i = 0; i < this.listCtDTLanhThuocEx.size(); i++)
             {
               CtPhieuDuTruExt ctPhieuDuTruExt = (CtPhieuDuTruExt)this.listCtDTLanhThuocEx.get(i);
               CtPhieuDt ctxk = ctPhieuDuTruExt.getCtPhieuDt();
               if (ctx.getCtdtMalk().equals(ctxk.getCtdtMalk()))
               {
                 if (ctxk.getCtdtMa() == null)
                 {
                   if (ctPhieuDuTruExt.getIsTongHopButton()) {
                     break;
                   }
                   foundOnNewsList = true;
                   vt = i;
                   sl = ctx.getCtdtSoluong().doubleValue() + ctxk.getCtdtSoluong().doubleValue(); break;
                 }
                 if (ctxk.getPhieudtMa().getPhieudtDaXuat() != null) {
                   break;
                 }
                 foundOnNewsList = true;
                 vt = i;
                 sl = ctx.getCtdtSoluong().doubleValue() + ctxk.getCtdtSoluong().doubleValue();
                 ctx.setCtdtMa(ctxk.getCtdtMa()); break;
               }
             }
             if (!foundOnNewsList)
             {
               ctxEx = createCtDTru(ctx, tk);
               this.listCtDTLanhThuocEx.add(ctxEx);
             }
             else
             {
               ctx.setCtdtSoluong(Double.valueOf(sl));
               ctxEx = createCtDTru(ctx, tk);
               this.listCtDTLanhThuocEx.set(vt, ctxEx);
             }
           }
           else
           {
             ctxEx = createCtDTru(ctx, tk);
             this.listCtDTLanhThuocEx.add(ctxEx);
           }
         }
         else
         {
           log.info("-----Cap nhat ct-----");
           this.selected = ((CtPhieuDuTruExt)this.listCtDTLanhThuocEx.get(viTri));
           System.out.println("Duoc phep chinh sua: " + this.selected.getIsTongHopButton());
           if (this.selected.getIsTongHopButton())
           {
             if (ctx.getCtdtSoluong().equals(this.selected.getCtPhieuDt().getCtdtSoluong()))
             {
               ctx.setCtdtMa(this.selected.getCtPhieuDt().getCtdtMa());
               ctxEx = new CtPhieuDuTruExt();
               ctxEx = createCtDTru(ctx, tk);
             }
             else
             {
               FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_NOTEDIT, new Object[0]);
               this.tonkhoMa = "";
               this.dmtMa = "";
               this.dmtTen = "";
               this.tonkho = new Double(0.0D);
               this.xuat = new Double(0.0D);
               this.updateItem = Integer.valueOf(-1);
             }
           }
           else
           {
             ctx.setCtdtMa(this.selected.getCtPhieuDt().getCtdtMa());
             ctxEx = new CtPhieuDuTruExt();
             ctxEx = createCtDTru(ctx, tk);
           }
           this.listCtDTLanhThuocEx.set(viTri, ctxEx);
         }
       }
       this.count = Integer.valueOf(this.listCtDTLanhThuocEx.size());
       log.info(String.format("-----listCtXuatKho: %s", new Object[] { Integer.valueOf(this.listCtDTLanhThuocEx.size()) }));
       this.tonkhoMa = "";
       this.dmtMa = "";
       this.dmtTen = "";
       this.tonkho = new Double(0.0D);
       this.xuat = new Double(0.0D);
       this.updateItem = Integer.valueOf(-1);
       this.listMas = new ArrayList();
       this.selected = new CtPhieuDuTruExt();
     }
     tinhTien();
   }

   public List<ThuocNoiTru> getThuocNoiTru()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     Date tuNgay = null;
     Date denNgay = null;
     try
     {
       this.ngayXuat = (this.ngayXuat + " " + this.gioLapPhieuTu + ":00");
       this.ngayXuatDen = (this.ngayXuatDen + " " + this.gioLapPhieuDen + ":00");
       tuNgay = formatter.parse(this.ngayXuat);
       denNgay = formatter.parse(this.ngayXuatDen);
     }
     catch (Exception er)
     {
       er.printStackTrace();
     }
     Integer khoaMaso = Integer.valueOf(0);
     if ((this.phieuDuTru.getDmkhoaMaso().getDmkhoaMa() != null) && (!this.phieuDuTru.getDmkhoaMaso().getDmkhoaMa().equals(""))) {
       khoaMaso = this.phieuDuTru.getDmkhoaMaso().getDmkhoaMaso();
     }
     String loaiMa = "";
     Integer doituongMaso = Integer.valueOf(0);
     if (this.phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso() != null) {
       doituongMaso = this.phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso();
     }
     Integer dmTangMaso = Integer.valueOf(0);
     if (this.phieuDuTru.getDmtangMaso(true).getDmtangMaso() != null) {
       dmTangMaso = this.phieuDuTru.getDmtangMaso(true).getDmtangMaso();
     }
     String loaiPhieu = this.phieuDuTru.getPhieudtLoaiPhieu();
     if ((loaiPhieu != null) || (!loaiPhieu.equals(""))) {
       if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa() != null) {
         if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
         {
           if ((!this.dmLoaiPhieuMa.equals("HT")) && (!this.dmLoaiPhieuMa.equals("GN")))
           {
             loaiMa = "TD-TT";
             DmLoaiPhieu lp = (DmLoaiPhieu)this.hmDmLoaiPhieu.get(this.dmLoaiPhieuMa);
             if (lp.getDmloaiphieuDvt() != null) {
               loaiMa = loaiMa + ";" + lp.getDmloaiphieuDvt();
             }
           }
           else
           {
             loaiMa = "TD-" + this.dmLoaiPhieuMa;
           }
         }
         else if (this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("DY"))
         {
           DmLoaiPhieu lp = (DmLoaiPhieu)this.hmDmLoaiPhieu.get(this.dmLoaiPhieuMa);
           if (lp.getDmloaiphieuDvt() != null) {
             loaiMa = "DY-" + lp.getDmloaiphieuDvt();
           }
         }
         else
         {
           loaiMa = this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocMa();
         }
       }
     }
     ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
     List<ThuocNoiTru> listTNT = tntDelegate.findDanhSachTNTDuTruLinhTuTruc(tuNgay, denNgay, loaiMa, khoaMaso, doituongMaso, dmTangMaso);

     return listTNT;
   }

   public void end()
   {
     log.info("-----end()-----");
     if (this.listCtDTLanhThuocEx.size() > 0)
     {
       log.info(String.format("-----ngayxuat %s", new Object[] { this.ngayXuat }));
       if (!this.ngayXuat.equals(""))
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         try
         {
           Date dtNgayXuat = df.parse(this.ngayXuat);
           Calendar cal = Calendar.getInstance();
           cal.setTime(dtNgayXuat);
           this.phieuDuTru.setPhieudtNgaygiocn(cal.getTime());
           this.phieuDuTru.setPhieudtNgay(cal.getTime());
         }
         catch (ParseException e)
         {
           log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
         }
       }
       try
       {
         double tt = Double.valueOf("0").doubleValue();
         ArrayList<CtPhieuDt> listCtPhieuDt = new ArrayList();
         List<Integer> listThuocNoiTruMa = new ArrayList();
         HashMap<Integer, List> ctdt_matnt = new HashMap();
         CtPhieuDuTruExt obj = new CtPhieuDuTruExt();
         for (int i = 0; i < this.listCtDTLanhThuocEx.size(); i++)
         {
           CtPhieuDt ct = ((CtPhieuDuTruExt)this.listCtDTLanhThuocEx.get(i)).getCtPhieuDt();
           List<Integer> listThuocNoiTruMa_Temp = ((CtPhieuDuTruExt)this.listCtDTLanhThuocEx.get(i)).getListThuocNoiTruMa();
           if (listThuocNoiTruMa_Temp.size() > 0) {
             for (int j = 0; j < listThuocNoiTruMa_Temp.size(); j++) {
               listThuocNoiTruMa.add(listThuocNoiTruMa_Temp.get(j));
             }
           }
           ct.setCtdtNgaygiocn(new Date());
           ctdt_matnt.put(Integer.valueOf(i), listThuocNoiTruMa_Temp);
           log.info(String.format("-----so luong: %s", new Object[] { ct.getCtdtSoluong() }));
           tt += ct.getCtdtSoluong().doubleValue() * ct.getCtdtDongia().doubleValue();
           listCtPhieuDt.add(ct);
         }
         log.info(String.format("-----thanh tien: %s", new Object[] { Double.valueOf(tt) }));
         this.phieuDuTru.setPhieudtNgaygiocn(new Date());
         this.phieuDuTru.setPhieudtTrangThai(Integer.valueOf(0));
         this.phieuDuTru.setPhieudtPhanBiet(Integer.valueOf(1));
         if ("".equals(Utils.reFactorString(this.phieuDuTru.getDmtangMaso(true).getDmtangMa())))
         {
           this.phieuDuTru.setDmtangMaso(new DmTang());
           log.info("Buong null");
         }
         CtPhieuDtDelegate pxDelegate = CtPhieuDtDelegate.getInstance();
         clearInfor();
         String ketqua = pxDelegate.updatePhieuDuTruLinhNT(this.phieuDuTru, listCtPhieuDt, this.listCtPhieuDtDel, listThuocNoiTruMa, ctdt_matnt, IConstantsRes.PRIORITY_TON_LO_THUOC);
         if ((ketqua != null) && (!ketqua.equals("")))
         {
           if (ketqua.indexOf(".") >= 0)
           {
             FacesMessages.instance().add(ketqua, new Object[0]);
             this.hienThiGhiNhan = "true";
             this.hienThiHuyPhieu = "";
             this.hienThiInPhieu = "";
           }
           else
           {
             resetInfo();
             this.maPhieu = ketqua;
             FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { this.maPhieu });
             displayPhieuXuatKho();

             setIsUpdate("1");
             this.isDeleted = this.phieuDuTru.getPhieudtTrangThai().toString();
             this.hienThiGhiNhan = "";
             this.hienThiHuyPhieu = "";
             this.hienThiInPhieu = "true";
           }
         }
         else {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL, new Object[0]);
     }
   }

   public void taothongtin()
   {
     log.info("Begin taothongtin() ******");
     log.info("phieuDuTru.getPhieudtMa():");
     if ((this.phieuDuTru.getPhieudtMa() == null) || (this.phieuDuTru.getPhieudtMa().equals(""))) {
       try
       {
         this.listCtDTLanhThuocEx.clear();
         List<ThuocNoiTru> tnt_temp = getThuocNoiTru();
         if (tnt_temp != null)
         {
           log.info("tnt_temp = " + tnt_temp.size());



           ArrayList<CtPhieuDuTruExt> listCtDTEx = new ArrayList();
           CtPhieuDt ctPhieuDt = new CtPhieuDt();
           List<Integer> tntMaList = new ArrayList();
           for (ThuocNoiTru tnt : tnt_temp)
           {
             ctPhieuDt = new CtPhieuDt();
             tntMaList = new ArrayList();

             ctPhieuDt.setDmthuocMaso(tnt.getThuocnoitruMathuoc());
             ctPhieuDt.setCtdtSoluong(tnt.getThuocnoitruSoluong());
             ctPhieuDt.setCtdtDongia(tnt.getThuocnoitruDongiabh());
             tntMaList.add(tnt.getThuocnoitruMa());
             if (listCtDTEx.size() == 0)
             {
               CtPhieuDuTruExt ctPhieuDuTruExt = new CtPhieuDuTruExt();
               ctPhieuDuTruExt.setIsTongHopButton(true);

               ctPhieuDuTruExt.setListThuocPhongKhamMa(tntMaList);
               ctPhieuDuTruExt.setCtPhieuDt(ctPhieuDt);

               listCtDTEx.add(ctPhieuDuTruExt);
             }
             else
             {
               CtPhieuDuTruExt resultListExt = new CtPhieuDuTruExt();
               boolean foundInList = false;
               int vitri = -1;
               for (int j = 0; j < listCtDTEx.size(); j++)
               {
                 CtPhieuDuTruExt ctPhieuDuTruExt1 = (CtPhieuDuTruExt)listCtDTEx.get(j);
                 CtPhieuDt ctPhieuDt1 = ctPhieuDuTruExt1.getCtPhieuDt();
                 if (ctPhieuDt1.getDmthuocMaso().getDmthuocMa().equals(tnt.getThuocnoitruMathuoc().getDmthuocMa()))
                 {
                   resultListExt = ctPhieuDuTruExt1;
                   foundInList = true;
                   vitri = j;
                   break;
                 }
               }
               if (foundInList == true)
               {
                 ctPhieuDt = resultListExt.getCtPhieuDt();
                 tntMaList = resultListExt.getListThuocNoiTruMa();
                 ctPhieuDt.setCtdtSoluong(Double.valueOf(ctPhieuDt.getCtdtSoluong().doubleValue() + tnt.getThuocnoitruSoluong().doubleValue()));
                 tntMaList = resultListExt.getListThuocNoiTruMa();
                 tntMaList.add(tnt.getThuocnoitruMa());

                 resultListExt.setCtPhieuDt(ctPhieuDt);
                 resultListExt.setListThuocPhongKhamMa(tntMaList);
                 listCtDTEx.set(vitri, resultListExt);
               }
               else
               {
                 CtPhieuDuTruExt ctPhieuDuTruExt = new CtPhieuDuTruExt();
                 ctPhieuDuTruExt.setIsTongHopButton(true);
                 ctPhieuDuTruExt.setListThuocPhongKhamMa(tntMaList);
                 ctPhieuDuTruExt.setCtPhieuDt(ctPhieuDt);

                 listCtDTEx.add(ctPhieuDuTruExt);
               }
             }
             ctPhieuDt = new CtPhieuDt();
             tntMaList = new ArrayList();
           }
           System.out.println("listCtDTEx.size: " + listCtDTEx.size());

           List<CtPhieuDuTruExt> listCtDTExTemp1 = new ArrayList();
           if (listCtDTEx.size() > 0) {
             for (CtPhieuDuTruExt ctPhieuDuTruExt : listCtDTEx)
             {
               CtPhieuDt ctdt = ctPhieuDuTruExt.getCtPhieuDt();
               Double soluongXinLinh = ctdt.getCtdtSoluong();
               System.out.println("Co lam tron hay khong: " + ctdt.getDmthuocMaso().getDmphanloaithuocMaso().getDmphanloaithuocNhom3());
               if ((ctdt.getDmthuocMaso().getDmphanloaithuocMaso().getDmphanloaithuocNhom3() != null) && (ctdt.getDmthuocMaso().getDmphanloaithuocMaso().getDmphanloaithuocNhom3().equals("1")))
               {
                 int sochan = soluongXinLinh.intValue();
                 if (sochan < soluongXinLinh.doubleValue()) {
                   sochan++;
                 }
                 soluongXinLinh = Double.valueOf(Double.parseDouble(Integer.toString(sochan)));
               }
               ctdt.setCtdtSoluong(soluongXinLinh);
               ctPhieuDuTruExt.setCtPhieuDt(ctdt);
               listCtDTExTemp1.add(ctPhieuDuTruExt);
             }
           }
           ArrayList<CtPhieuDuTruExt> listCtDTEx_Temp = new ArrayList();
           if (listCtDTExTemp1.size() > 0) {
             for (CtPhieuDuTruExt ctPhieuDuTruExt : listCtDTEx)
             {
               Integer thuocMaso = ctPhieuDuTruExt.getCtPhieuDt().getDmthuocMaso().getDmthuocMaso();
               Double soluongXinLinh = ctPhieuDuTruExt.getCtPhieuDt().getCtdtSoluong();
               TonKhoDelegate tonkhoDel = TonKhoDelegate.getInstance();
               List<TonKho> lstTonKhoHT = new ArrayList();
               lstTonKhoHT = tonkhoDel.getTonKhoHienTai(thuocMaso, this.dmkhoaXuatMaso, IConstantsRes.PRIORITY_TON_LO_THUOC);
               if ((lstTonKhoHT != null) && (lstTonKhoHT.size() > 0)) {
                 for (int i = 0; i < lstTonKhoHT.size(); i++)
                 {
                   TonKho tonkhoHienTai = (TonKho)lstTonKhoHT.get(i);
                   Double tonLo = tonkhoHienTai.getTonkhoTon();
                   CtPhieuDt ctPhieuDt_temp = ctPhieuDuTruExt.getCtPhieuDt();
                   CtPhieuDuTruExt ctPhieuDuTruExt_Temp = ctPhieuDuTruExt;
                   if (soluongXinLinh.doubleValue() <= tonLo.doubleValue())
                   {
                     ctPhieuDt_temp.setCtdtSoluong(soluongXinLinh);
                     System.out.println("So luong xin linh1: " + soluongXinLinh);
                     ctPhieuDuTruExt_Temp.setCtPhieuDt(ctPhieuDt_temp);
                     ctPhieuDuTruExt_Temp.setTonKho(tonkhoHienTai);
                     ctPhieuDuTruExt_Temp = createCtDTru(ctPhieuDt_temp, tonkhoHienTai);
                     ctPhieuDuTruExt_Temp.setIsTongHopButton(ctPhieuDuTruExt.getIsTongHopButton());
                     ctPhieuDuTruExt_Temp.setListThuocPhongKhamMa(ctPhieuDuTruExt.getListThuocNoiTruMa());

                     listCtDTEx_Temp.add(ctPhieuDuTruExt_Temp);

                     ctPhieuDt_temp = new CtPhieuDt();
                     ctPhieuDuTruExt_Temp = new CtPhieuDuTruExt();
                     break;
                   }
                   soluongXinLinh = Double.valueOf(soluongXinLinh.doubleValue() - tonLo.doubleValue());
                   System.out.println("So luong xin linh2: " + tonLo);
                   ctPhieuDt_temp.setCtdtSoluong(tonLo);
                   ctPhieuDuTruExt_Temp.setCtPhieuDt(ctPhieuDt_temp);
                   ctPhieuDuTruExt_Temp.setTonKho(tonkhoHienTai);
                   ctPhieuDuTruExt_Temp = createCtDTru(ctPhieuDt_temp, tonkhoHienTai);
                   ctPhieuDuTruExt_Temp.setIsTongHopButton(ctPhieuDuTruExt.getIsTongHopButton());
                   ctPhieuDuTruExt_Temp.setListThuocPhongKhamMa(ctPhieuDuTruExt.getListThuocNoiTruMa());

                   listCtDTEx_Temp.add(ctPhieuDuTruExt_Temp);

                   ctPhieuDt_temp = new CtPhieuDt();
                   ctPhieuDuTruExt_Temp = new CtPhieuDuTruExt();
                 }
               }
             }
           }
           System.out.println("listCtDTEx_Temp.size: " + listCtDTEx_Temp.size());
           if (listCtDTEx_Temp.size() > 0) {
             for (CtPhieuDuTruExt ctPhieuDuTruExt : listCtDTEx_Temp)
             {
               this.listCtDTLanhThuocEx.add(ctPhieuDuTruExt);
               System.out.println("List tnt: " + ctPhieuDuTruExt.getListThuocNoiTruMa());
             }
           }
           tinhTien();
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHONGCOTHUOC, new Object[0]);
           return;
         }
       }
       catch (Exception e)
       {
         e.printStackTrace();
         log.error("Error: " + e.toString());
       }
     }
     log.info("End taothongtin()");
   }

   public void huyPhieuDT()
   {
     log.info("****** start huyPhieuDT() ******");
     PhieuDuTruDelegate ctPhieuDTDelegate = PhieuDuTruDelegate.getInstance();
     boolean returnKQ = ctPhieuDTDelegate.removeAllPhieuDuTru(this.phieuDuTru);
     if (!returnKQ)
     {
       FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THAT_BAI, new Object[] { this.phieuDuTru.getPhieudtMa() });
       this.hienThiGhiNhan = "";
       this.hienThiHuyPhieu = "";
       this.hienThiInPhieu = "";
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { this.phieuDuTru.getPhieudtMa() });
       reset();
       this.hienThiGhiNhan = "true";
       this.hienThiHuyPhieu = "";
       this.hienThiInPhieu = "";
       this.isUpdate = "1";
       this.isDeleted = "0";
     }
     log.info("****** end huyPhieuDT() ******");
   }

   @Destroy
   public void destroy()
   {
     log.info("-----destroy()-----");
   }

   public void displayPhieuXuatKho()
   {
     log.info("-----displayPhieuXuatKho()-----");
     this.listCtDTLanhThuocEx = new ArrayList();
     this.listCtPhieuDtDel = new ArrayList();
     if (!this.maPhieu.equals(""))
     {
       log.info(String.format("-----Phieu xuat ma: %s", new Object[] { this.maPhieu }));
       try
       {
         CtPhieuDtDelegate pxkWS = CtPhieuDtDelegate.getInstance();
         this.phieuDuTru = pxkWS.findByPhieuDuTruPhanBiet(this.maPhieu, Integer.valueOf(1));
         if (this.phieuDuTru != null)
         {
           this.maPhieu = this.phieuDuTru.getPhieudtMa();
           resetInfo();
           this.dmkhoaXuatMa = this.phieuDuTru.getPhieudtMakho().getDmkhoaMa();
           this.dmkhoaXuatTen = this.phieuDuTru.getPhieudtMakho().getDmkhoaTen();
           this.dmkhoaXuatMaso = this.phieuDuTru.getPhieudtMakho().getDmkhoaMaso();
           this.dmLoaiTen = this.phieuDuTru.getDmloaithuocMaso().getDmloaithuocTen();
           this.dmDoiTuongTen = this.phieuDuTru.getDmdoituongMaso(true).getDmdoituongTen();
           this.dmkhoaNhanTen = this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaTen();
           for (CtPhieuDt ct : pxkWS.findByPhieuDuTruMa(this.maPhieu))
           {
             log.info("Ct xuat kho ma: " + ct.getCtdtMa());
             CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
             ctxEx.setCtPhieuDt(ct);
             this.listCtDTLanhThuocEx.add(ctxEx);
           }
           this.count = Integer.valueOf(this.listCtDTLanhThuocEx.size());

           this.hienThiInPhieu = "true";
           if (this.phieuDuTru.getPhieudtDaXuat() == null)
           {
             setIsUpdate("1");
             this.hienThiHuyPhieu = "true";
             this.hienThiGhiNhan = "true";
           }
           else
           {
             this.hienThiHuyPhieu = "";
             this.hienThiGhiNhan = "";
             setIsUpdate("0");
           }
           this.isDeleted = this.phieuDuTru.getPhieudtTrangThai().toString();
           refreshDmThuoc();
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { this.maPhieu });
           reset();
         }
         tinhTien();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { this.maPhieu });
         reset();
         log.error(String.format("-----Error: %s", new Object[] { e }));
       }
     }
   }

   private CtPhieuDuTruExt createCtDTru(CtPhieuDt ctx, TonKho tk)
   {
     log.info(String.format("-----createCtTraKho()-----", new Object[0]));
     log.info(String.format("-----Ct xuat kho (input): %s", new Object[] { ctx.getCtdtMa() }));
     log.info(String.format("-----ton kho (input): %s", new Object[] { tk.getTonkhoMa() }));

     TonKho tkTra = null;
     try
     {
       tkTra = (TonKho)BeanUtils.cloneBean(tk);
       tkTra.setTonkhoNgaygiocn(new Date());
       tkTra.setTonkhoMa(null);
     }
     catch (IllegalAccessException e)
     {
       e.printStackTrace();
     }
     catch (InstantiationException e)
     {
       e.printStackTrace();
     }
     catch (InvocationTargetException e)
     {
       e.printStackTrace();
     }
     catch (NoSuchMethodException e)
     {
       e.printStackTrace();
     }
     if (tkTra != null)
     {
       tkTra.setTonkhoXuat(ctx.getCtdtSoluong());
       tkTra.setTonkhoTra(null);
       tkTra.setTonkhoNhap(null);
       tkTra.setTonkhoMa(tk.getTonkhoMa());
     }
     TonKho tkNhan = null;
     try
     {
       tkNhan = (TonKho)BeanUtils.cloneBean(tk);
       tkNhan.setTonkhoNgaygiocn(new Date());
       tkNhan.setTonkhoMa(null);
     }
     catch (IllegalAccessException e)
     {
       e.printStackTrace();
     }
     catch (InstantiationException e)
     {
       e.printStackTrace();
     }
     catch (InvocationTargetException e)
     {
       e.printStackTrace();
     }
     catch (NoSuchMethodException e)
     {
       e.printStackTrace();
     }
     if (tkNhan != null)
     {
       tkNhan.setTonkhoNhap(ctx.getCtdtSoluong());
       tkNhan.setTonkhoTra(null);
       tkNhan.setTonkhoXuat(null);
     }
     ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     ctx.setDmthuocMaso(tk.getDmthuocMaso());
     ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     ctx.setDmnctMaso(tk.getDmnctMaso());
     ctx.setCtdtDongia(tk.getTonkhoDongia());
     ctx.setCtdtLo(tk.getTonkhoLo());
     ctx.setCtdtMalk(tk.getTonkhoMalienket());
     ctx.setCtdtNamhandung(tk.getTonkhoNamhandung());
     ctx.setCtdtNamnhap(tk.getTonkhoNamnhap());

     ctx.setCtdtNgayhandung(tk.getTonkhoNgayhandung());
     ctx.setCtdtThanghandung(tk.getTonkhoThanghandung());
     ctx.setPhieudtMa(this.phieuDuTru);
     ctx.setCtdtSodangky(tk.getTonkhoSodangky());


     CtPhieuDuTruExt ctxEx = new CtPhieuDuTruExt();
     ctxEx.setCtPhieuDt(ctx);
     ctxEx.setTonKhoXuat(tkTra);
     ctxEx.setTonKhoNhan(tkNhan);
     return ctxEx;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public PhieuDTLanhThuocTuTrucAction()
   {
     this.listCtDTLanhThuocEx = new ArrayList();




     this.listMas = new ArrayList();

     this.phieuDuTru = new PhieuDuTru();
     this.maPhieu = "";
     this.ngayXuat = "";
     this.ngayXuatDen = "";
     this.dmtMa = "";


     this.tonkhoMa = "";
     this.updateItem = Integer.valueOf(-1);

     this.ngayHienTai = "";
     this.nguonMa = "";
     this.kpMa = "";
     this.malk = "";

     this.hienThiGhiNhan = "true";
     this.hienThiHuyPhieu = "";
     this.hienThiInPhieu = "";
     this.gioLapPhieuTu = "";
     this.gioLapPhieuDen = "";
     this.dmDoiTuongTen = "";

     this.dmKhoXuat = "";
     this.dmKhoaNhan = "";

     this.dmLoaiTen = "";

     this.listDmLoaiThuocs = new ArrayList();
     this.hmLoaiThuoc = new HashMap();
     this.hmDmThuoc = new HashMap();

     this.listDmLoaiPhieus = new ArrayList();
     this.hmDmLoaiPhieu = new HashMap();
     this.dmLoaiPhieuMa = "";
     this.listDmDoiTuongs = new ArrayList();
     this.hmDoiTuong = new HashMap();
     this.listDmTangs = new ArrayList();




     this.isReport = "false";




     this.CHUA_LUU_PHIEU_XUAT = 0;
     this.dmtTen = "";
     this.listDmThuocs = new ArrayList();
     this.listDmKhoaNhans = new ArrayList();
     this.hmDmKhoaNhan = new HashMap();
     this.listDmKhoXuats = new ArrayList();
     this.hmDmKhoXuat = new HashMap();
     this.index = 0;
       this.reportPathVP = null;
     this.reportTypeVP = null;
     this.jasperPrintVP = null;
   }

   public void XuatReport()
   {
     this.reportTypeVP = "PhieuLanhThuocTuTrucKhoaPhong";
     log.info("Vao Method XuatReport PhieuLanhThuocTuTrucKhoaPhong");
     String baocao1 = null;
     Date currentDate = new Date();
     if ((this.maPhieu != null) && (!this.maPhieu.equals(""))) {
       try
       {
         CtPhieuDtDelegate pxkWS = CtPhieuDtDelegate.getInstance();
         this.phieuDuTru = pxkWS.findByPhieuDuTruPhanBiet(this.maPhieu, Integer.valueOf(1));
         resetInfo();
         String loaiPhieu = this.phieuDuTru.getPhieudtLoaiPhieu();
         XuatReportDuocPham xuatReport = new XuatReportDuocPham();
         xuatReport.reportTypeKC = this.reportTypeVP;
         String loaiThuoc = loaiPhieu.substring(0, loaiPhieu.indexOf(" - ")).trim();
         xuatReport.XuatReportPhieuDuTruTuTruc(log, this.phieuDuTru, this.index, loaiThuoc);
         this.jasperPrintVP = xuatReport.jasperPrintKC;
         this.reportPathVP = xuatReport.reportPathKC;
         this.reportTypeVP = xuatReport.reportTypeKC;
         this.index += 1;
       }
       catch (Exception e)
       {
         log.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     }
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B3142_Phieulanhthuoctutruc";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void reset()
   {
     log.info("-----reset()-----");
     this.maPhieu = "";
     this.updateItem = Integer.valueOf(-1);
     this.tonkhoMa = "";
     this.dmtMa = "";
     this.tonkho = new Double(0.0D);
     this.xuat = new Double(0.0D);
     this.nguonMa = "";
     this.kpMa = "";
     this.count = Integer.valueOf(0);
     this.phieuDuTru = new PhieuDuTru();
     this.listCtDTLanhThuocEx = new ArrayList();
     this.listCtPhieuDtDel = new ArrayList();
     this.listMas = new ArrayList();
     resetInfo();
     this.ngayXuat = Utils.getCurrentDate();
     this.ngayXuatDen = Utils.getCurrentDate();
     this.ngayHienTai = Utils.getCurrentDate();
     this.isUpdate = "1";
     this.isDeleted = "0";
     log.info("-----identity: " + this.identity.getUsername());
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuDuTru.setDtdmnhanvienCn(nv);
     }
     this.tongTien = new Double("0");
     this.dmkhoaXuatMa = "";
     this.dmkhoaXuatMaso = null;
     this.dmkhoaXuatTen = "";
     this.dmLoaiTen = "";
     this.listDmThuocs.clear();
     this.listDmLoaiPhieus.clear();
     this.gioLapPhieuTu = "00:00";
     this.gioLapPhieuDen = "23:59";
     this.dmDoiTuongTen = "";
     this.dmLoaiPhieuMa = "";
     this.hmDmLoaiPhieu.clear();
     this.phieuDuTru.setDmtangMaso(new DmTang());
   }

   private void tinhTien()
   {
     this.tongTien = new Double("0");
     for (CtPhieuDuTruExt ctExt : this.listCtDTLanhThuocEx)
     {
       Double sl = ctExt.getCtPhieuDt().getCtdtSoluong();
       if (sl == null) {
         sl = new Double("0");
       }
       Double dg = ctExt.getCtPhieuDt().getCtdtDongia();
       if (dg == null) {
         dg = new Double("0");
       }
       this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
     }
     log.info("-----tong tien: " + this.tongTien);
   }

   private void resetInfo()
   {
     log.info("-----resetInfo()-----");
     if (this.phieuDuTru.getDmkhoaMaso() == null) {
       this.phieuDuTru.setDmkhoaMaso(new DmKhoa());
     }
     if (this.phieuDuTru.getPhieudtMakho() == null) {
       this.phieuDuTru.setPhieudtMakho(new DmKhoa());
     }
     if (this.phieuDuTru.getDmdoituongMaso() == null) {
       this.phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
     }
     if (this.phieuDuTru.getDtdmnhanvienBacsiky() == null) {
       this.phieuDuTru.setDtdmnhanvienBacsiky(new DtDmNhanVien());
     }
     if (this.phieuDuTru.getDtdmnhanvienCn() == null) {
       this.phieuDuTru.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (this.phieuDuTru.getDtdmnhanvienLapphieu() == null) {
       this.phieuDuTru.setDtdmnhanvienLapphieu(new DtDmNhanVien());
     }
     if (this.phieuDuTru.getDmtangMaso() == null) {
       this.phieuDuTru.setDmtangMaso(new DmTang());
     }
   }

   private void clearInfor()
   {
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDmkhoaMaso().getDmkhoaMaso()))) {
       this.phieuDuTru.setDmkhoaMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getPhieudtMakho().getDmkhoaMaso()))) {
       this.phieuDuTru.setPhieudtMakho(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDmdoituongMaso().getDmdoituongMaso()))) {
       this.phieuDuTru.setDmdoituongMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDtdmnhanvienBacsiky().getDtdmnhanvienMaso()))) {
       this.phieuDuTru.setDtdmnhanvienBacsiky(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDtdmnhanvienCn().getDtdmnhanvienMaso()))) {
       this.phieuDuTru.setDtdmnhanvienCn(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDtdmnhanvienLapphieu().getDtdmnhanvienMaso()))) {
       this.phieuDuTru.setDtdmnhanvienLapphieu(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuDuTru.getDmtangMaso().getDmtangMaso()))) {
       this.phieuDuTru.setDmtangMaso(null);
     }
   }

   public void setPhieuDuTru(PhieuDuTru phieuXuat)
   {
     this.phieuDuTru = phieuXuat;
   }

   public PhieuDuTru getPhieuDuTru()
   {
     return this.phieuDuTru;
   }

   public void setNgayXuat(String ngayXuat)
   {
     this.ngayXuat = ngayXuat;
   }

   public String getNgayXuat()
   {
     return this.ngayXuat;
   }

   public void setNgayXuatDen(String ngayXuatDen)
   {
     this.ngayXuatDen = ngayXuatDen;
   }

   public String getNgayXuatDen()
   {
     return this.ngayXuatDen;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setDmtMa(String dmtMa)
   {
     this.dmtMa = dmtMa;
   }

   public String getDmtMa()
   {
     return this.dmtMa;
   }

   public void setTonkho(Double tonkho)
   {
     this.tonkho = tonkho;
   }

   public Double getTonkho()
   {
     return this.tonkho;
   }

   public void setXuat(Double xuat)
   {
     this.xuat = xuat;
   }

   public Double getXuat()
   {
     return this.xuat;
   }

   public void setTonkhoMa(String tonkhoMa)
   {
     this.tonkhoMa = tonkhoMa;
   }

   public String getTonkhoMa()
   {
     return this.tonkhoMa;
   }

   public void setUpdateItem(Integer updateItem)
   {
     this.updateItem = updateItem;
   }

   public Integer getUpdateItem()
   {
     return this.updateItem;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getNgayHienTai()
   {
     return this.ngayHienTai;
   }

   public static Logger getLogger()
   {
     return log;
   }

   public static void setLogger(Logger logger)
   {
     log = logger;
   }

   public CtPhieuDuTruExt getSelected()
   {
     return this.selected;
   }

   public void setSelected(CtPhieuDuTruExt selected)
   {
     this.selected = selected;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public void setIsUpdate(String isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setKpMa(String kpMa)
   {
     this.kpMa = kpMa;
   }

   public String getKpMa()
   {
     return this.kpMa;
   }

   public void setNguonMa(String nguonMa)
   {
     this.nguonMa = nguonMa;
   }

   public String getNguonMa()
   {
     return this.nguonMa;
   }

   public String getMalk()
   {
     return this.malk;
   }

   public void setMalk(String malk)
   {
     this.malk = malk;
   }

   public Double getTongTien()
   {
     return this.tongTien;
   }

   public void setTongTien(Double tongTien)
   {
     this.tongTien = tongTien;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   public String getHienThiGhiNhan()
   {
     return this.hienThiGhiNhan;
   }

   public void setHienThiGhiNhan(String hienThiGhiNhan)
   {
     this.hienThiGhiNhan = hienThiGhiNhan;
   }

   public String getHienThiHuyPhieu()
   {
     return this.hienThiHuyPhieu;
   }

   public void setHienThiHuyPhieu(String hienThiHuyPhieu)
   {
     this.hienThiHuyPhieu = hienThiHuyPhieu;
   }

   public String getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(String hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }

   public String getIsDeleted()
   {
     return this.isDeleted;
   }

   public void setIsDeleted(String isDeleted)
   {
     this.isDeleted = isDeleted;
   }

   public String getGioLapPhieuTu()
   {
     return this.gioLapPhieuTu;
   }

   public void setGioLapPhieuTu(String gioLapPhieuTu)
   {
     this.gioLapPhieuTu = gioLapPhieuTu;
   }

   public String getGioLapPhieuDen()
   {
     return this.gioLapPhieuDen;
   }

   public void setGioLapPhieuDen(String gioLapPhieuDen)
   {
     this.gioLapPhieuDen = gioLapPhieuDen;
   }

   public String getDmLoaiPhieuMa()
   {
     return this.dmLoaiPhieuMa;
   }

   public void setDmLoaiPhieuMa(String dmLoaiPhieuMa)
   {
     this.dmLoaiPhieuMa = dmLoaiPhieuMa;
   }

   public List<SelectItem> getListDmTangs()
   {
     return this.listDmTangs;
   }

   public void setListDmTangs(List<SelectItem> listDmTangs)
   {
     this.listDmTangs = listDmTangs;
   }

   @End
   public void endConversation() {}

   public class CtPhieuDuTruExt
     implements Serializable
   {
     private static final long serialVersionUID = 0L;
     private CtPhieuDt ctDuTru;
     private TonKho tonKho;
     private TonKho tonKhoXuat;
     private TonKho tonKhoNhan;
     private Double thanhTien;
     private boolean isTongHopButton;
     private List<Integer> listThuocNoiTruMa;

     public Double getThanhTien()
     {
       return this.thanhTien;
     }

     public void setThanhTien(Double thanhTien)
     {
       this.thanhTien = thanhTien;
     }

     public CtPhieuDuTruExt()
     {
       this.ctDuTru = new CtPhieuDt();
       this.tonKho = new TonKho();
       this.listThuocNoiTruMa = new ArrayList();
       SetInforUtil.setInforIfNullForTonKho(this.tonKho);
       if (this.tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
         this.tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.isTongHopButton = false;
       this.thanhTien = new Double(0.0D);
     }

     public CtPhieuDt getCtPhieuDt()
     {
       return this.ctDuTru;
     }

     public void setCtPhieuDt(CtPhieuDt obj)
     {
       this.ctDuTru = obj;
     }

     public TonKho getTonKho()
     {
       return this.tonKho;
     }

     public void setTonKho(TonKho obj)
     {
       this.tonKho = obj;
     }

     public TonKho getTonKhoXuat()
     {
       if (this.tonKhoXuat == null) {
         this.tonKhoXuat = new TonKho();
       }
       return this.tonKhoXuat;
     }

     public void setTonKhoXuat(TonKho obj)
     {
       this.tonKhoXuat = obj;
     }

     public TonKho getTonKhoNhan()
     {
       return this.tonKhoNhan;
     }

     public void setTonKhoNhan(TonKho tonKhoNhan)
     {
       this.tonKhoNhan = tonKhoNhan;
     }

     public boolean getIsTongHopButton()
     {
       return this.isTongHopButton;
     }

     public void setIsTongHopButton(boolean isTongHopButton)
     {
       this.isTongHopButton = isTongHopButton;
     }

     public List<Integer> getListThuocNoiTruMa()
     {
       return this.listThuocNoiTruMa;
     }

     public void setListThuocPhongKhamMa(List<Integer> listThuocNoiTruMa)
     {
       this.listThuocNoiTruMa = listThuocNoiTruMa;
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.PhieuDTLanhThuocTuTrucAction

 * JD-Core Version:    0.7.0.1

 */