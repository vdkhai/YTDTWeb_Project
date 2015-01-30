 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.CtPhieuDtDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.CtPhieuDt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuDuTru;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmTang;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.NumberFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import javax.faces.model.SelectItem;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.AuthorizationException;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3125_Lapphieudutru")
 @Synchronized(timeout=6000000L)
 public class LapPhieuDuTruAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
   private String msgFail = "";
   private String msgSuccess = "";
   private String position = IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/";
   private String resultHidden = "";
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(LapPhieuDuTruAction.class);
   private PhieuDuTru phieuDuTru;
   private boolean updateNhapct = false;
   private String ngayXuat;
   private String ngayXuatDen;
   private String maHang;
   private String tenHang;
   private Integer quyCach;
   private String donVi;
   private String tonKho;
   private Double xinLinh;
   private Double donGia;
   private Integer nguonctMaso;
   private String nguonctMa;
   private Integer nguonkpMaso;
   private String nguonkpMa;
   private Integer maSoHang;
   private String tongTien;
   private String maFinish;
   private String ngayhientai = "";
   private String reportFinished = "";
   private String reportFileNameHid = "";
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   @DataModel
   private List<CtPhieuDt> listCtkq = new ArrayList();
   @DataModelSelection
   @Out(required=false)
   private CtPhieuDt nhapctSelected;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private DtDmNhanVien nvCn;
   private String hienThiGhiNhan = "true";
   private String hienThiHuyPhieu = "";
   private String hienThiInPhieu = "";
   private String gioLapPhieuTu = "";
   private String gioLapPhieuDen = "";
   private String dmDoiTuongTen = "";
   private List<ThuocNoiTru> listTNT = new ArrayList();
   private String dmLoaiTen = "";
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs = new ArrayList();
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   HashMap<String, DmLoaiPhieu> hmDmLoaiPhieu = new HashMap();
   private String dmLoaiPhieuMa = "";
   private List<SelectItem> listDmLoaiPhieus = new ArrayList();
   private List<SelectItem> listDmDoiTuongs = new ArrayList();
   HashMap<String, DmDoiTuong> hmDoiTuong = new HashMap();
   private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
   private List<SelectItem> listDmTangs = new ArrayList();
   private List<SelectItem> listDmKhoaNTs = new ArrayList();
   private List<DmKhoa> listDmKhoaNTAll = new ArrayList();
   private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap();
   private String dmkhoaTen = "";
   private String dmkhoaMa = "";

   @Restrict("#{s:hasRole('NV_KhoaNoiTru') or s:hasRole('QT_KhoaNoiTru')}")
   @Begin(join=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");
     resetValue();
     this.phieuDuTru = new PhieuDuTru();
     SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
     Calendar cal = Calendar.getInstance();

     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayXuat = formatter.format(cal.getTime());
     this.ngayXuatDen = formatter.format(cal.getTime());
     this.maFinish = "";

     SimpleDateFormat formatter1 = new SimpleDateFormat(FORMAT_DATE);

     this.ngayhientai = formatter1.format(new Date());
     this.resultHidden = "";
     this.reportFinished = "";
     this.reportFileNameHid = "";

     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     setNvCn(nvDelegate.findByNd(this.identity.getUsername()));
     if (getNvCn() == null) {
       setNvCn(new DtDmNhanVien());
     }
     log.info("nvCn" + this.nvCn);
     this.phieuDuTru.setDtdmnhanvienCn(this.nvCn);
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     refreshDmLoaiThuoc();
     refreshDmDoiTuong();
     this.hmDmKhoaNTAll.clear();
     refreshDmKhoaNT();
     this.phieuDuTru.setDmtangMaso(new DmTang());
     log.info("***End init ***");
     return "VienPhiTaiKhoa_SoLieuCLSPhauThuat_LapPhieuDuTru";
   }

   @End
   public void endFunct() {}

   public void huyPhieuDT()
   {
     CtPhieuDtDelegate ctPhieuDTDelegate = CtPhieuDtDelegate.getInstance();
     String returnKQ = ctPhieuDTDelegate.huyPhieuDuTru_New(this.phieuDuTru.getPhieudtMa());
     if (returnKQ.equals("DX"))
     {
       FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_DA_XUAT_HANG, new Object[] { this.phieuDuTru.getPhieudtMa() });
       this.hienThiGhiNhan = "";
       this.hienThiHuyPhieu = "";
       this.hienThiInPhieu = "";
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { this.phieuDuTru.getPhieudtMa() });
       this.phieuDuTru = new PhieuDuTru();
       SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
       Calendar cal = Calendar.getInstance();

       SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
       this.ngayXuat = formatter.format(cal.getTime());
       this.ngayXuatDen = formatter.format(cal.getTime());
       this.maFinish = "";

       SimpleDateFormat formatter1 = new SimpleDateFormat(FORMAT_DATE);

       this.ngayhientai = formatter1.format(new Date());
       this.resultHidden = "";
       this.reportFinished = "";
       this.reportFileNameHid = "";
       this.listCtkq = new ArrayList();


       this.nguonctMaso = null;
       this.nguonctMa = "";

       this.nguonkpMaso = null;
       this.nguonkpMa = "";
       this.tongTien = "";

       this.hienThiGhiNhan = "true";
       this.hienThiHuyPhieu = "";
       this.hienThiInPhieu = "";
     }
   }

   public void resetValue()
   {
     log.info("Begin resetValue()");



     this.maHang = "";
     this.tenHang = "";
     this.quyCach = Integer.valueOf(0);
     this.donVi = "";
     this.tonKho = "";
     this.xinLinh = new Double(0.0D);
     this.donGia = new Double(0.0D);

     this.dmLoaiTen = "";
     this.gioLapPhieuTu = "00:00";
     this.gioLapPhieuDen = "23:59";
     this.dmDoiTuongTen = "";
     this.dmLoaiPhieuMa = "";
     this.hmDmLoaiPhieu.clear();
     this.dmkhoaTen = "";
     this.dmkhoaMa = "";
     log.info("End resetValue()");
   }

   public void Caculation(List<CtPhieuDt> ctpdtlist)
   {
     log.info("Begining Caculation: " + ctpdtlist);
     Double dTongTien = new Double(0.0D);
     if (ctpdtlist != null)
     {
       for (CtPhieuDt ctpdt : ctpdtlist) {
         dTongTien = Double.valueOf(dTongTien.doubleValue() + ctpdt.getCtdtDongia().doubleValue() * ctpdt.getCtdtSoluong().doubleValue());
       }
       log.info("Tong tien dTongTien= " + dTongTien);
     }
     log.info("End Caculation(): ");

     NumberFormat numberFormat = NumberFormat.getInstance();
     this.tongTien = numberFormat.format(dTongTien);
   }

   public String getDmkhoaTen()
   {
     return this.dmkhoaTen;
   }

   public void setDmkhoaTen(String dmkhoaTen)
   {
     this.dmkhoaTen = dmkhoaTen;
   }

   public String getDmkhoaMa()
   {
     return this.dmkhoaMa;
   }

   public void setDmkhoaMa(String dmkhoaMa)
   {
     this.dmkhoaMa = dmkhoaMa;
   }

   public void onblurMaKhoaAction()
   {
     log.info("-----BEGIN onblurMaKhoaAction()-----");
     if (this.dmkhoaMa != null)
     {
       if (this.hmDmKhoaNTAll != null)
       {
         DmKhoa dmKhoa = new DmKhoa();
         dmKhoa = (DmKhoa)this.hmDmKhoaNTAll.get(this.dmkhoaMa.toUpperCase());
         System.out.println("KhoaTen: " + dmKhoa.getDmkhoaTen());
         System.out.println("KhoaMa: " + dmKhoa.getDmkhoaMa());
         if (dmKhoa != null)
         {
           this.phieuDuTru.setDmkhoaMaso(dmKhoa);
           this.dmkhoaTen = dmKhoa.getDmkhoaTen();
           this.dmkhoaMa = dmKhoa.getDmkhoaMa().toUpperCase();
         }
       }
       refreshDmTang();
       this.phieuDuTru.setDmtangMaso(new DmTang());
     }
     log.info("-----END onblurMaKhoaAction()-----");
   }

   public void onblurTenKhoaAction()
   {
     log.info("-----BEGIN onblurTenKhoaAction()-----");
     if (this.dmkhoaTen != null)
     {
       Boolean hasTenKhoa = Boolean.valueOf(false);
       Set set = this.hmDmKhoaNTAll.entrySet();
       Iterator i = set.iterator();
       DmKhoa dmKhoa_Finded = new DmKhoa();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmKhoa dmKhoa = (DmKhoa)me.getValue();
         if ((dmKhoa.getDmkhoaTen() == this.dmkhoaTen) || (dmKhoa.getDmkhoaTen().equals(this.dmkhoaTen)))
         {
           hasTenKhoa = Boolean.valueOf(true);
           dmKhoa_Finded = dmKhoa;
           break;
         }
       }
       if (hasTenKhoa.booleanValue())
       {
         this.phieuDuTru.setDmkhoaMaso(dmKhoa_Finded);
         this.dmkhoaTen = dmKhoa_Finded.getDmkhoaTen();
         this.dmkhoaMa = dmKhoa_Finded.getDmkhoaMa().toUpperCase();
       }
       refreshDmTang();
       this.phieuDuTru.setDmtangMaso(new DmTang());
     }
     log.info("-----END onblurTenKhoaAction()-----");
   }

   public void refreshDmKhoaNT()
   {
     this.dmKhoaDel = DmKhoaDelegate.getInstance();
     this.listDmKhoaNTAll.clear();
     this.listDmKhoaNTs.clear();
     this.listDmKhoaNTAll = this.dmKhoaDel.getKhoaNT();
     this.hmDmKhoaNTAll.clear();
     for (DmKhoa o : this.listDmKhoaNTAll) {
       this.hmDmKhoaNTAll.put(o.getDmkhoaMa().toUpperCase(), o);
     }
     for (DmKhoa each : this.listDmKhoaNTAll) {
       this.listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
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
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     this.phieuDuTru.setPhieudtLoaiPhieu("");
     this.dmLoaiPhieuMa = "";
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
     this.phieuDuTru.setPhieudtLoaiPhieu("");
     this.dmLoaiPhieuMa = "";
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
     this.listCtkq.clear();
     String loaiPhieuItem = this.phieuDuTru.getPhieudtLoaiPhieu();
     if (!loaiPhieuItem.equals("")) {
       this.dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
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

     DmDoiTuong dmDoiTuong_Find = new DmDoiTuong();
     Set set = this.hmDoiTuong.entrySet();
     Iterator i = set.iterator();
     if (this.dmDoiTuongTen.equals("Tất cả"))
     {
       this.phieuDuTru.setDmdoituongMaso(new DmDoiTuong());
     }
     else
     {
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

   public void enter()
     throws Exception
   {
     log.info("xinLinh:" + this.xinLinh);
     log.info("tonKho:" + this.tonKho);
     log.info("maHang:" + this.maHang);

     log.info("xinLinh:" + this.xinLinh);
     log.info("tonKho:" + this.tonKho);
     log.info("maHang:" + this.maHang);
     if ((this.maHang == null) || (this.maHang.equals("")))
     {
       resetValue();
       this.maFinish = "";
       log.info("mahang null");
       return;
     }
     try
     {
       this.xinLinh.doubleValue();
       Double.parseDouble(this.tonKho);
     }
     catch (Exception ex)
     {
       this.xinLinh = new Double(0.0D);
       this.tonKho = "0";
     }
     if ((this.xinLinh == null) || (this.xinLinh.doubleValue() == 0.0D))
     {
       resetValue();
       this.maFinish = "";
       log.info("xinLinh null");
       return;
     }
     if ((this.tonKho == null) || (this.tonKho.equals("")) || (Double.parseDouble(this.tonKho) < this.xinLinh.doubleValue()))
     {
       resetValue();
       this.maFinish = "";
       log.info("tonKho null");
       log.info("Double.parseDouble(tonKho) < xinLinh.doubleValue()");
       return;
     }
     log.info("*****Begin Enter() *****");
     if (this.updateNhapct) {
       updateRow();
     } else {
       insertRow();
     }
     resetValue();
     this.maFinish = "";
     log.info("*****End Enter() *****");
   }

   private void updateRow()
   {
     log.info("*****updateNhapct");

     copyTo(this.nhapctSelected);
     int i = this.listCtkq.indexOf(this.nhapctSelected);
     if (i < 0) {
       insertRow();
     }
     log.info("****i=" + i + "******");
     this.listCtkq.set(i, this.nhapctSelected);
     Caculation(this.listCtkq);
     this.updateNhapct = false;
   }

   private void insertRow()
   {
     log.info("begin cache chi tiet ket qua");

     CtPhieuDt ctPhieuDuTru = new CtPhieuDt();
     SetInforUtil.setInforIfNullForCTPhieuDuTru(ctPhieuDuTru);
     copyTo(ctPhieuDuTru);
     this.listCtkq.add(ctPhieuDuTru);
     Caculation(this.listCtkq);
   }

   private void copyTo(CtPhieuDt chiTietPhieuDuTru)
   {
     log.info("maHang:" + this.maHang);
     chiTietPhieuDuTru.getDmthuocMaso().setDmthuocMa(this.maHang);
     chiTietPhieuDuTru.getDmthuocMaso().setDmthuocMaso(this.maSoHang);

     log.info("tenHang:" + this.tenHang);
     chiTietPhieuDuTru.getDmthuocMaso().setDmthuocTen(this.tenHang);


     log.info("donVi:" + this.donVi);
     chiTietPhieuDuTru.getDmthuocMaso().getDmdonvitinhMaso().setDmdonvitinhTen(this.donVi);

     chiTietPhieuDuTru.setCtdtSoluong(this.xinLinh);
     chiTietPhieuDuTru.setCtdtDongia(this.donGia);
     if ((this.nguonctMa != null) && (!this.nguonctMa.equals("")))
     {
       DmNguonChuongTrinh nguonCT = new DmNguonChuongTrinh();
       nguonCT.setDmnctMaso(this.nguonctMaso);
       nguonCT.setDmnctMa(this.nguonctMa);

       chiTietPhieuDuTru.setDmnctMaso(nguonCT);
     }
     if ((this.nguonkpMa != null) && (!this.nguonkpMa.equals("")))
     {
       DmNguonKinhPhi nguonKP = new DmNguonKinhPhi();
       nguonKP.setDmnguonkinhphiMaso(this.nguonkpMaso);
       nguonKP.setDmnguonkinhphiMa(this.nguonkpMa);

       chiTietPhieuDuTru.setDmnguonkinhphiMaso(nguonKP);
     }
   }

   private void copyFrom(CtPhieuDt chiTietPhieuDuTru)
   {
     this.maHang = chiTietPhieuDuTru.getDmthuocMaso().getDmthuocMa();
     this.maSoHang = chiTietPhieuDuTru.getDmthuocMaso().getDmthuocMaso();
     this.tenHang = chiTietPhieuDuTru.getDmthuocMaso().getDmthuocTen();

     this.donVi = chiTietPhieuDuTru.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhTen();
     this.xinLinh = chiTietPhieuDuTru.getCtdtSoluong();
     this.donGia = chiTietPhieuDuTru.getCtdtDongia();
     if ((chiTietPhieuDuTru.getDmnctMaso() != null) && (!chiTietPhieuDuTru.getDmnctMaso().getDmnctMa().equals("")))
     {
       this.nguonctMaso = chiTietPhieuDuTru.getDmnctMaso().getDmnctMaso();
       this.nguonctMa = chiTietPhieuDuTru.getDmnctMaso().getDmnctMa();
     }
     if ((chiTietPhieuDuTru.getDmnguonkinhphiMaso() != null) && (!chiTietPhieuDuTru.getDmnguonkinhphiMaso().getDmnguonkinhphiMa().equals("")))
     {
       this.nguonkpMaso = chiTietPhieuDuTru.getDmnguonkinhphiMaso().getDmnguonkinhphiMaso();
       this.nguonkpMa = chiTietPhieuDuTru.getDmnguonkinhphiMaso().getDmnguonkinhphiMa();
     }
   }

   public void delete()
     throws Exception
   {
     log.info("*****Begin delete() *****");
     this.listCtkq.remove(this.nhapctSelected);
     Caculation(this.listCtkq);
     resetValue();
     this.updateNhapct = false;
     this.maFinish = "";
     log.info("*****End delete() *****");
   }

   public void nhapctAjax()
     throws Exception
   {
     log.info("*****Begin nhapctAjax() *****");

     copyFrom(this.nhapctSelected);
     this.updateNhapct = true;
     log.info("***********end nhapctAjax***********");
   }

   public void ghinhan()
     throws Exception
   {
     log.info("*****Begin Ghi nhan() *****");
     log.info("*****Ghi nhan phieu du tru *****" + this.phieuDuTru);
     log.info("*****so phan tu insert *****" + this.listCtkq.size());

     String maphieu = this.phieuDuTru.getPhieudtMa();
     if ((maphieu != null) && (!maphieu.equals("")) &&
       (!Identity.instance().hasRole("QT_KhoaNoiTru"))) {
       throw new AuthorizationException("");
     }
     try
     {
       if ((this.listCtkq == null) || (this.listCtkq.size() == 0))
       {
         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHONGCOTHUOC, new Object[0]);
         return;
       }
       CtPhieuDtDelegate ctPhieuDelegate = CtPhieuDtDelegate.getInstance();
       RemoveUtil.removeIfNullForPhieuDuTru(this.phieuDuTru);
       setinfor(this.phieuDuTru);
       for (int i = 0; i < this.listCtkq.size(); i++)
       {
         ((CtPhieuDt)this.listCtkq.get(i)).setPhieudtMa(this.phieuDuTru);
         ((CtPhieuDt)this.listCtkq.get(i)).setCtdtNgaygiocn(new Date());
         RemoveUtil.removeIfNullForCTPhieuDuTru((CtPhieuDt)this.listCtkq.get(i));
       }
       this.phieuDuTru.setPhieudtPhanBiet(Integer.valueOf(0));
       this.phieuDuTru.setPhieudtNgaygiocn(new Date());
       if ("".equals(Utils.reFactorString(this.phieuDuTru.getDmtangMaso(true).getDmtangMa())))
       {
         this.phieuDuTru.setDmtangMaso(null);
         log.info("Buong null");
       }
       this.maFinish = ctPhieuDelegate.capNhatPhieuDuTru(this.listCtkq, this.listTNT, this.phieuDuTru, maphieu, IConstantsRes.PRIORITY_TON_LO_THUOC);
       if ((this.maFinish != null) && (!this.maFinish.equals("")))
       {
         log.info("maFinish:" + this.maFinish);
         if (this.maFinish.indexOf(".") >= 0)
         {
           FacesMessages.instance().add(this.maFinish, new Object[0]);
           this.hienThiGhiNhan = "true";
           this.hienThiHuyPhieu = "";
           this.hienThiInPhieu = "";
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
           this.phieuDuTru.setPhieudtMa(this.maFinish);
           displayInfor();

           this.hienThiGhiNhan = "";
           this.hienThiHuyPhieu = "";
           this.hienThiInPhieu = "true";
         }
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       }
       log.info("maFinish: " + this.maFinish);

       this.resultHidden = "success";
     }
     catch (Exception e)
     {
       log.error("*************loi trong qua trinh dua du lieu lien server***********" + e.toString());
       e.printStackTrace();
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
     }
     SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
     for (int i = 0; i < this.listCtkq.size(); i++)
     {
       ((CtPhieuDt)this.listCtkq.get(i)).setPhieudtMa(this.phieuDuTru);
       SetInforUtil.setInforIfNullForCTPhieuDuTru((CtPhieuDt)this.listCtkq.get(i));
     }
     log.info("*****End Ghi nhan() *****");
   }

   public String inphieu()
     throws Exception
   {
     log.info("Begining inphieu()");
     if (!this.maFinish.equals("")) {
       try
       {
         XuatReport();
       }
       catch (Exception e)
       {
         log.info("Loi trong khi xuat report" + e.toString());
       }
     }
     log.info("End inphieu()");
     return "B3360_Chonmenuxuattaptin";
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B3125_Lapphieudutru";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   int index = 0;

   public String XuatReport()
   {
     String baocao1 = null;
     this.reportTypeVP = "B3125_Lapphieudutru";
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = "";
       String loaiPhieu = this.phieuDuTru.getPhieudtLoaiPhieu();
       String loaiThuoc = loaiPhieu.substring(0, loaiPhieu.indexOf(" - ")).trim();
       if (loaiThuoc.equals("GN")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieudutru_01GayNghien.jrxml";
       } else if (loaiThuoc.equals("HT")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieudutru_01HTT.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieudutru_01.jrxml";
       }
       log.info("da thay file templete 5" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();

       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if (loaiThuoc.equals("GN")) {
         params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_GN);
       } else if (loaiThuoc.equals("HT")) {
         params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HT);
       } else if (loaiThuoc.equals("YC")) {
         params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_YC);
       } else if (loaiThuoc.equals("HC")) {
         params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC_HC);
       } else {
         params.put("TITLE", IConstantsRes.PHIEU_DU_TRU_LANH_THUOC);
       }
       params.put("makhoa", this.phieuDuTru.getDmkhoaMaso().getDmkhoaMa());
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       DmKhoa khoa = (DmKhoa)dieuTriUtilDelegate.findByMa(this.phieuDuTru.getDmkhoaMaso().getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
       params.put("khoa", khoa.getDmkhoaTen());

       log.info("------------------------------------------------------------------");
       log.info("khoa:" + this.phieuDuTru.getDmkhoaMaso().getDmkhoaTen());
       log.info("maphieu:" + this.phieuDuTru.getPhieudtMa());
       log.info("------------------------------------------------------------------");

       params.put("maphieu", this.phieuDuTru.getPhieudtMa());

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "B3125_Lapphieudutru");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       log.info("Index :" + this.index);
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
     return "B3360_Chonmenuxuattaptin";
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public void nhaplai()
     throws Exception
   {
     log.info("*****Begin nhaplai() *****");
     init();

     this.tongTien = "0";

     this.hienThiGhiNhan = "true";
     this.hienThiHuyPhieu = "";
     this.hienThiInPhieu = "";



     this.listCtkq = new ArrayList();
     log.info("*****End nhaplai() *****");
   }

   public void taothongtin()
     throws Exception
   {
     this.listCtkq = new ArrayList();
     this.listTNT = new ArrayList();
     log.info("Begin taothongtin()");
     log.info("phieuDuTru.getPhieudtMa():" + this.phieuDuTru.getPhieudtMa());
     if ((this.phieuDuTru.getPhieudtMa() == null) || (this.phieuDuTru.getPhieudtMa().equals(""))) {
       try
       {
         ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();

         SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TIME);

         Date tuNgay = null;
         Date denNgay = null;
         try
         {
           this.ngayXuat = (this.ngayXuat + " " + this.gioLapPhieuTu + ":00");
           this.ngayXuatDen = (this.ngayXuatDen + " " + this.gioLapPhieuDen + ":00");
           tuNgay = formatter.parse(this.ngayXuat);
           denNgay = formatter.parse(this.ngayXuatDen);
         }
         catch (Exception e)
         {
           System.out.println("Loi khi set gia tri ngay theo format he thong quy dinh" + e);
         }
         Integer khoaMaso = Integer.valueOf(0);
         Integer khoMaso = Integer.valueOf(0);
         Integer tangMaso = Integer.valueOf(0);
         if ((this.phieuDuTru.getDmkhoaMaso().getDmkhoaMa() != null) && (!this.phieuDuTru.getDmkhoaMaso().getDmkhoaMa().equals(""))) {
           khoaMaso = this.phieuDuTru.getDmkhoaMaso().getDmkhoaMaso();
         }
         if ((this.phieuDuTru.getPhieudtMakho().getDmkhoaMa() != null) && (!this.phieuDuTru.getPhieudtMakho().getDmkhoaMa().equals(""))) {
           khoMaso = this.phieuDuTru.getPhieudtMakho().getDmkhoaMaso();
         }
         Integer doituongMaso = Integer.valueOf(0);
         if (this.phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso() != null) {
           doituongMaso = this.phieuDuTru.getDmdoituongMaso(true).getDmdoituongMaso();
         }
         if (this.phieuDuTru.getDmtangMaso(true).getDmtangMaso() != null) {
           tangMaso = this.phieuDuTru.getDmtangMaso(true).getDmtangMaso();
         }
         String loaiMa = "";
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
         log.info("loai ma = " + loaiMa);
         log.info("Khoa ma so = " + khoaMaso);
         log.info("Kho ma so = " + khoMaso);
         log.info("Buong ma so = " + tangMaso);
         List<ThuocNoiTru> tntTemps = tntDelegate.findDanhSachTNTDuTruLinh(tuNgay, denNgay, loaiMa, khoaMaso, khoMaso, doituongMaso, tangMaso);
         this.listTNT = tntTemps;
         List<ThuocNoiTru> listThuocNoiTru = new ArrayList();
         if (tntTemps != null)
         {
           for (int i = 0; i < tntTemps.size(); i++)
           {
             ThuocNoiTru tntTemp = (ThuocNoiTru)BeanUtils.cloneBean(tntTemps.get(i));

             tntTemp.setThuocnoitruDongia(tntTemp.getThuocnoitruDongiabh());
             listThuocNoiTru.add(tntTemp);
           }
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_KHONGCOTHUOC, new Object[0]);
           return;
         }
         List<CtPhieuDt> listCtTemp = new ArrayList();
         if (listThuocNoiTru != null) {
           for (ThuocNoiTru tnttemp : listThuocNoiTru)
           {
             CtPhieuDt ctdt = changetoData(tnttemp, listCtTemp);
             SetInforUtil.setInforIfNullForCTPhieuDuTru(ctdt);
             listCtTemp.add(ctdt);
           }
         }
         if ((listCtTemp != null) && (listCtTemp.size() > 0))
         {
           for (CtPhieuDt ctdttemp : listCtTemp)
           {
             Double soluongXinLinh = ctdttemp.getCtdtSoluong();
             System.out.println("Co lam tron hay khong: " + ctdttemp.getDmthuocMaso(true).getDmphanloaithuocMaso().getDmphanloaithuocNhom3());
             if ((ctdttemp.getDmthuocMaso(true).getDmphanloaithuocMaso().getDmphanloaithuocNhom3() != null) && (ctdttemp.getDmthuocMaso(true).getDmphanloaithuocMaso().getDmphanloaithuocNhom3().equals("1")))
             {
               int sochan = soluongXinLinh.intValue();
               if (sochan < soluongXinLinh.doubleValue()) {
                 sochan++;
               }
               soluongXinLinh = Double.valueOf(Double.parseDouble(Integer.toString(sochan)));
             }
             ctdttemp.setCtdtSoluong(soluongXinLinh);
             this.listCtkq.add(ctdttemp);
           }
           Caculation(this.listCtkq);
         }
       }
       catch (Exception e)
       {
         e.printStackTrace();
         log.error("*************loi***********" + e.toString());
       }
     }
     log.info("End taothongtin()");
   }

   private CtPhieuDt changetoData(ThuocNoiTru tnt, List<CtPhieuDt> list)
     throws Exception
   {
     log.info("Begin changetoData() : " + tnt);
     CtPhieuDt result = new CtPhieuDt();
     boolean haveinlist = false;
     if (list.size() > 0) {
       for (int i = 0; i < list.size(); i++)
       {
         CtPhieuDt temp = (CtPhieuDt)list.get(i);
         if (temp.getCtdtMalk().equals(tnt.getThuocnoitruMalk()))
         {
           list.remove(i);
           temp.setCtdtSoluong(Double.valueOf(tnt.getThuocnoitruSoluong().doubleValue() + temp.getCtdtSoluong().doubleValue()));
           result = temp;
           haveinlist = true;
           break;
         }
       }
     }
     if (!haveinlist)
     {
       result.setCtdtSoluong(tnt.getThuocnoitruSoluong());
       result.setCtdtDongia(tnt.getThuocnoitruDongia());
       result.setDmthuocMaso(tnt.getThuocnoitruMathuoc());
       result.setCtdtMalk(tnt.getThuocnoitruMalk());
     }
     log.info("End changetoData()");
     return result;
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.phieuDuTru.getPhieudtMa() == null) || (this.phieuDuTru.getPhieudtMa().equals(""))) {
         return;
       }
       log.info("Begining displayInfor()");



       CtPhieuDtDelegate ctPhieuDTDelegate = CtPhieuDtDelegate.getInstance();



       PhieuDuTru objPhieuDt = ctPhieuDTDelegate.findByPhieuDuTruPhanBiet(this.phieuDuTru.getPhieudtMa(), Integer.valueOf(0));
       if (objPhieuDt == null)
       {
         log.info("displayInfor   phieu du tru 22 ....." + this.phieuDuTru);

         FacesMessages.instance().add(IConstantsRes.PHIEU_DU_TRU_NOT_FOUND, new Object[] { this.phieuDuTru.getPhieudtMa() });
         nhaplai();
       }
       else
       {
         List<CtPhieuDt> ctpdt_tmp = ctPhieuDTDelegate.findByPhieuDuTruMa(objPhieuDt.getPhieudtMa());
         log.info("Co thong tin phieu du tru" + objPhieuDt);
         this.phieuDuTru = objPhieuDt;
         SetInforUtil.setInforIfNullForPhieuDuTru(this.phieuDuTru);
         this.maFinish = this.phieuDuTru.getPhieudtMa();
         log.info("Co thong tin ct phieu du tru" + ctpdt_tmp);
         setOtherValue();
         this.listCtkq = new ArrayList();
         this.dmLoaiTen = this.phieuDuTru.getDmloaithuocMaso(true).getDmloaithuocTen();
         this.dmkhoaMa = this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaMa();
         this.dmkhoaTen = this.phieuDuTru.getDmkhoaMaso(true).getDmkhoaTen();
         if (ctpdt_tmp != null)
         {
           for (CtPhieuDt ctpdt : ctpdt_tmp)
           {
             SetInforUtil.setInforIfNullForCTPhieuDuTru(ctpdt);
             this.listCtkq.add(ctpdt);
           }
           Caculation(this.listCtkq);
         }
         this.hienThiGhiNhan = "";
         this.hienThiInPhieu = "true";
         if ((this.phieuDuTru.getPhieudtDaXuat() != null) && (this.phieuDuTru.getPhieudtDaXuat().booleanValue() == true)) {
           this.hienThiHuyPhieu = "";
         } else {
           this.hienThiHuyPhieu = "true";
         }
       }
     }
     catch (Exception e)
     {
       log.info("error on function displayInfor" + e);
     }
     log.info("maFinish : " + this.maFinish);
     log.info("End displayInfor()");
   }

   private void setOtherValue()
   {
     log.info("Begining setOtherValue()");
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if (this.phieuDuTru.getPhieudtNgay() != null)
     {
       this.ngayXuat = formatter.format(Long.valueOf(this.phieuDuTru.getPhieudtNgay().getTime()));
       log.info("Ngay xuat :" + this.ngayXuat);
     }
     log.info("End setOtherValue()");
   }

   private void setinfor(PhieuDuTru pdt)
   {
     log.info("Begining setinfor()");
     try
     {
       if (!"".equals(this.ngayXuat))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(this.ngayXuat));
         this.phieuDuTru.setPhieudtNgay(cal.getTime());
       }
     }
     catch (Exception e)
     {
       log.info("ERRoR: setinfor(PhieuDuTru pdt) " + e);
     }
   }

   public List<CtPhieuDt> getListCtkq()
   {
     return this.listCtkq;
   }

   public void setListCtkq(List<CtPhieuDt> listCtkq)
   {
     this.listCtkq = listCtkq;
   }

   public CtPhieuDt getNhapctSelected()
   {
     return this.nhapctSelected;
   }

   public void setNhapctSelected(CtPhieuDt nhapctSelected)
   {
     this.nhapctSelected = nhapctSelected;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getMaHang()
   {
     return this.maHang;
   }

   public void setMaHang(String maHang)
   {
     this.maHang = maHang;
   }

   public Integer getQuyCach()
   {
     return this.quyCach;
   }

   public void setQuyCach(Integer quyCach)
   {
     this.quyCach = quyCach;
   }

   public String getDonVi()
   {
     return this.donVi;
   }

   public void setDonVi(String donVi)
   {
     this.donVi = donVi;
   }

   public String getTonKho()
   {
     return this.tonKho;
   }

   public void setTonKho(String tonKho)
   {
     this.tonKho = tonKho;
   }

   public Double getXinLinh()
   {
     return this.xinLinh;
   }

   public void setXinLinh(Double xinLinh)
   {
     this.xinLinh = xinLinh;
   }

   public Double getDonGia()
   {
     return this.donGia;
   }

   public void setDonGia(Double donGia)
   {
     this.donGia = donGia;
   }

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public PhieuDuTru getPhieuDuTru()
   {
     return this.phieuDuTru;
   }

   public void setPhieuDuTru(PhieuDuTru phieuDuTru)
   {
     this.phieuDuTru = phieuDuTru;
   }

   public String getTongTien()
   {
     return this.tongTien;
   }

   public void setTongTien(String tongTien)
   {
     this.tongTien = tongTien;
   }

   public String getNgayXuat()
   {
     return this.ngayXuat;
   }

   public void setNgayXuat(String ngayXuat)
   {
     this.ngayXuat = ngayXuat;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getTenHang()
   {
     return this.tenHang;
   }

   public void setTenHang(String tenHang)
   {
     this.tenHang = tenHang;
   }

   public String getMaFinish()
   {
     return this.maFinish;
   }

   public void setMaFinish(String maFinish)
   {
     this.maFinish = maFinish;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getMsgFail()
   {
     return this.msgFail;
   }

   public void setMsgFail(String msgFail)
   {
     this.msgFail = msgFail;
   }

   public String getMsgSuccess()
   {
     return this.msgSuccess;
   }

   public void setMsgSuccess(String msgSuccess)
   {
     this.msgSuccess = msgSuccess;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public String getPosition()
   {
     return this.position;
   }

   public void setPosition(String position)
   {
     this.position = position;
   }

   public String getReportFileNameHid()
   {
     return this.reportFileNameHid;
   }

   public void setReportFileNameHid(String reportFileNameHid)
   {
     this.reportFileNameHid = reportFileNameHid;
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

   public Integer getNguonctMaso()
   {
     return this.nguonctMaso;
   }

   public void setNguonctMaso(Integer nguonctMaso)
   {
     this.nguonctMaso = nguonctMaso;
   }

   public String getNguonctMa()
   {
     return this.nguonctMa;
   }

   public void setNguonctMa(String nguonctMa)
   {
     this.nguonctMa = nguonctMa;
   }

   public Integer getNguonkpMaso()
   {
     return this.nguonkpMaso;
   }

   public void setNguonkpMaso(Integer nguonkpMaso)
   {
     this.nguonkpMaso = nguonkpMaso;
   }

   public String getNguonkpMa()
   {
     return this.nguonkpMa;
   }

   public void setNguonkpMa(String nguonkpMa)
   {
     this.nguonkpMa = nguonkpMa;
   }

   public Integer getMaSoHang()
   {
     return this.maSoHang;
   }

   public void setMaSoHang(Integer maSoHang)
   {
     this.maSoHang = maSoHang;
   }

   public DtDmNhanVien getNvCn()
   {
     return this.nvCn;
   }

   public void setNvCn(DtDmNhanVien nvCn)
   {
     this.nvCn = nvCn;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
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

   public String getNgayXuatDen()
   {
     return this.ngayXuatDen;
   }

   public void setNgayXuatDen(String ngayXuatDen)
   {
     this.ngayXuatDen = ngayXuatDen;
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

   public List<SelectItem> getListDmKhoaNTs()
   {
     return this.listDmKhoaNTs;
   }

   public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs)
   {
     this.listDmKhoaNTs = listDmKhoaNTs;
   }

   public List<SelectItem> getListDmTangs()
   {
     return this.listDmTangs;
   }

   public void setListDmTangs(List<SelectItem> listDmTangs)
   {
     this.listDmTangs = listDmTangs;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.LapPhieuDuTruAction

 * JD-Core Version:    0.7.0.1

 */