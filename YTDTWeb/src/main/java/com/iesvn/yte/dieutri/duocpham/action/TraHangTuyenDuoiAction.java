 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiPhieuDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtTraKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiPhieu;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
 import java.sql.Connection;
 import java.sql.DriverManager;
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
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4167_TuyenDuoiTraHang")
 @Synchronized(timeout=6000000L)
 public class TraHangTuyenDuoiAction
   implements Serializable
 {
   private static final long serialVersionUID = 4366573502725366923L;
   private static Logger log = Logger.getLogger(TraHangTuyenDuoiAction.class);
   private SimpleDateFormat formatter;
   private PhieuTraKho phieuTrakho;
   private String ngaytra;
   private DmPhanLoaiThuoc plthuoc;
   private DmNguonKinhPhi nguonkinhphi;
   private DmNguonChuongTrinh nguonchuongtrinh;
   @DataModel
   private List<CtTraKhoExt> listCtTraKhoExt;
   @DataModelSelection
   private CtTraKhoExt selectedCtTraKhoExt;
   private CtTraKhoExt ctTraKhoExt;
   private boolean flagUpdateCtTrakho;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private String nofound;
   private String nosuccess;
   private String noinphieu;
   private Integer count;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   String dmKhoNhan;
   HashMap<String, DmThuoc> hmDmThuoc;
   private String dmLoaiTen;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private List<SelectItem> listDmLoaiThuocs;
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc;
   private DmLoaiPhieuDelegate dmLoaiPhieuDelegate;
   HashMap<String, DmLoaiPhieu> hmDmLoaiPhieu;
   private String dmLoaiPhieuMa;
   String loaiPhieu;
   private List<SelectItem> listDmLoaiPhieus;
   private DmThuocDelegate dmThuocDelegate;
   private List<SelectItem> listDmThuocs;
   private DmKhoaDelegate dmKhoaDelegate;
   private List<SelectItem> listDmKhoTras;
   HashMap<String, DmKhoa> hmDmKhoTra;
   private Integer dmkhoaMaso;
   private String dmkhoaMa;
   private String dmkhoaTen;
   int index;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC;

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init(String kho)
   {
     log.info("***** init() *****");
     resetValue();
     this.dmKhoNhan = kho;
     log.info("***** Kho nhan:? " + kho);
     if ("NT".equals(kho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
     } else if ("KC".equals(kho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     }
     this.listDmLoaiThuocs.clear();
     this.listDmLoaiPhieus.clear();
     this.listDmThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmDmThuoc.clear();
     refreshDmLoaiThuoc();
     refreshDmKhoTra();
     log.info("***** ten chuong trinh: " + this.tenChuongTrinh);
     log.info("***** end init() *****");

     return "QuanLyKhoChinh_NhapKhoChinh_TraHangTuyenDuoi";
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
     if ((this.ctTraKhoExt != null) && (this.ctTraKhoExt.tonKho != null) && (this.ctTraKhoExt.tonKho.getDmthuocMaso() != null))
     {
       String maThuoc = this.ctTraKhoExt.tonKho.getDmthuocMaso().getDmthuocMa();
       DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(maThuoc.toUpperCase());
       if (dmThuoc != null)
       {
         this.ctTraKhoExt.tonKho.setDmthuocMaso(dmThuoc);
         log.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         this.ctTraKhoExt.tonKho.setDmthuocMaso(new DmThuoc());
         this.ctTraKhoExt.tonKho.setDmthuocMaso(null);
       }
     }
     log.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     log.info("-----BEGIN onblurTenThuocAction()-----");
     if ((this.ctTraKhoExt != null) && (this.ctTraKhoExt.tonKho != null) && (this.ctTraKhoExt.tonKho.getDmthuocMaso() != null))
     {
       String tenThuoc = this.ctTraKhoExt.tonKho.getDmthuocMaso().getDmthuocTen();








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
           if ((dmThuoc.getDmthuocTen().trim() == tenThuoc) || (dmThuoc.getDmthuocTen().trim().equals(tenThuoc)))
           {
             hasTenThuoc = Boolean.valueOf(true);
             maThuoc = dmThuoc.getDmthuocMa();
             break;
           }
         }
       }
       if (hasTenThuoc.booleanValue())
       {
         DieuTriUtilDelegate dieutriUtilDelegate = DieuTriUtilDelegate.getInstance();
         DmThuoc dmThuoc = (DmThuoc)dieutriUtilDelegate.findByMa(maThuoc, "DmThuoc", "dmthuocMa");
         this.ctTraKhoExt.tonKho.setDmthuocMaso(dmThuoc);
         log.info("-----DA THAY DMTHUOC-----");
       }
       else
       {
         this.ctTraKhoExt.tonKho.setDmthuocMaso(null);
       }
     }
     log.info("-----END onblurTenThuocAction()-----");
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();

     String loaiMa = "";
     if (!this.loaiPhieu.equals("")) {
       if ((this.phieuTrakho != null) && (this.phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocMa() != null)) {
         if (this.phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocMa().toUpperCase().equals("TD"))
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
         else {
           loaiMa = this.phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocMa();
         }
       }
     }
     System.out.println("loaiMa: " + loaiMa);
     System.out.println("Kho: " + this.dmkhoaMaso);

     List<DmThuoc> lstDmThuoc = new ArrayList();
     if ((!loaiMa.equals("")) && (!this.loaiPhieu.equals("")) && (this.dmkhoaMaso != null)) {
       lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuocNhomThuocDVTKho(loaiMa.toUpperCase(), "", this.dmkhoaMaso);
     }
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
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
     log.info("-----BEGIN onblurMaLoaiAction()-----");
     String loaihang_ma = this.phieuTrakho.getDmloaithuocMaso().getDmloaithuocMa();
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null)
     {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
       this.phieuTrakho.setDmloaithuocMaso(dmLoaiThuoc);
     }
     else
     {
       setDmLoaiTen("");
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setLoaiPhieu("");
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
       this.phieuTrakho.setDmloaithuocMaso(dmLoaiThuoc_Find);
     }
     this.listDmLoaiPhieus.clear();
     refreshDmLoaiPhieu();
     setLoaiPhieu("");
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

   public void onblurTenLoaiPhieuAction()
   {
     log.info("-----BEGIN onblurTenLoaiPhieuAction()-----");
     String loaiPhieuItem = this.loaiPhieu;
     this.dmLoaiPhieuMa = loaiPhieuItem.substring(0, loaiPhieuItem.indexOf(" - ")).trim();
     refreshDmThuoc();
     log.info("-----END onblurTenLoaiPhieuAction()-----");
   }

   public void refreshDmLoaiPhieu()
   {
     this.listDmLoaiPhieus.clear();
     this.hmDmLoaiPhieu.clear();
     this.dmLoaiPhieuDelegate = DmLoaiPhieuDelegate.getInstance();
     if ((this.phieuTrakho != null) && (this.phieuTrakho.getDmloaithuocMaso() != null))
     {
       List<DmLoaiPhieu> lstDmLoaiPhieus = this.dmLoaiPhieuDelegate.findAll();
       if ((lstDmLoaiPhieus != null) && (lstDmLoaiPhieus.size() > 0)) {
         for (DmLoaiPhieu o : lstDmLoaiPhieus)
         {
           this.hmDmLoaiPhieu.put(o.getDmloaiphieuMa(), o);
           if (this.phieuTrakho.getDmloaithuocMaso().getDmloaithuocMaso().equals(o.getDmloaithuocMaso().getDmloaithuocMaso())) {
             this.listDmLoaiPhieus.add(new SelectItem(o.getDmloaiphieuMa() + " - " + o.getDmloaiphieuTen()));
           }
         }
       }
     }
   }

   public String getLoaiPhieu()
   {
     return this.loaiPhieu;
   }

   public void setLoaiPhieu(String loaiPhieu)
   {
     this.loaiPhieu = loaiPhieu;
   }

   public Integer getDmkhoaMaso()
   {
     return this.dmkhoaMaso;
   }

   public void setDmkhoaMaso(Integer dmkhoaMaso)
   {
     this.dmkhoaMaso = dmkhoaMaso;
   }

   public String getDmkhoaMa()
   {
     return this.dmkhoaMa;
   }

   public void setDmkhoaMa(String dmkhoaMa)
   {
     this.dmkhoaMa = dmkhoaMa;
   }

   public String getDmkhoaTen()
   {
     return this.dmkhoaTen;
   }

   public void setDmkhoaTen(String dmkhoaTen)
   {
     this.dmkhoaTen = dmkhoaTen;
   }

   public List<SelectItem> getListDmKhoTras()
   {
     return this.listDmKhoTras;
   }

   public void setListDmKhoTras(List<SelectItem> listDmKhoXuats)
   {
     this.listDmKhoTras = this.listDmKhoTras;
   }

   public void onblurMaKhoTraAction()
   {
     log.info("-----BEGIN onblurMaKhoTraAction()-----");
     if (this.dmkhoaMa != null)
     {
       DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoTra.get(this.dmkhoaMa.toUpperCase());
       if (dmKhoa != null)
       {
         this.phieuTrakho.setDmkhoaTra(dmKhoa);
         this.dmkhoaMaso = dmKhoa.getDmkhoaMaso();
         this.dmkhoaMa = dmKhoa.getDmkhoaMa().toUpperCase();
         this.dmkhoaTen = dmKhoa.getDmkhoaTen();
         log.info("-----DA THAY DMKHOTRA-----");
       }
       else
       {
         this.phieuTrakho.setDmkhoaTra(new DmKhoa());
         this.dmkhoaMaso = null;
         this.dmkhoaMa = "";
         this.dmkhoaTen = "";
       }
     }
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     log.info("-----END onblurMaKhoTraAction()-----");
   }

   public void onblurTenKhoTraAction()
   {
     log.info("-----BEGIN onblurTenKhoTraAction()-----");
     Boolean hasTenKhoTra = Boolean.valueOf(false);
     DmKhoa dmKhoa_Finded = new DmKhoa();
     if ((this.dmkhoaTen != "") &&
       (this.hmDmKhoTra != null))
     {
       Set set = this.hmDmKhoTra.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmKhoa dmKhoa = (DmKhoa)me.getValue();
         if ((dmKhoa.getDmkhoaTen() == this.dmkhoaTen) || (dmKhoa.getDmkhoaTen().equals(this.dmkhoaTen)))
         {
           hasTenKhoTra = Boolean.valueOf(true);
           dmKhoa_Finded = dmKhoa;
           break;
         }
       }
     }
     if (hasTenKhoTra.booleanValue())
     {
       this.phieuTrakho.setDmkhoaTra(dmKhoa_Finded);
       this.dmkhoaMaso = dmKhoa_Finded.getDmkhoaMaso();
       this.dmkhoaMa = dmKhoa_Finded.getDmkhoaMa();
       log.info("-----DA THAY DMKHOTRA-----");
     }
     else
     {
       this.phieuTrakho.setDmkhoaTra(new DmKhoa());
       this.dmkhoaMaso = null;
       this.dmkhoaMa = "";
       this.dmkhoaTen = "";
     }
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     log.info("-----END onblurTenKhoTraAction()-----");
   }

   public void refreshDmKhoTra()
   {
     this.listDmKhoTras.clear();
     this.hmDmKhoTra.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();

     List<DmKhoa> lstDmKhoa = new ArrayList();
     lstDmKhoa = this.dmKhoaDelegate.getKhoaNhom12NOTINKhoDuoc();
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhoTras.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoTra.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public void resetValue()
   {
     log.info("---resetValue");
     this.phieuTrakho = new PhieuTraKho();
     SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTrakho);
     String format_date = "dd/MM/yyyy";
     this.formatter = new SimpleDateFormat(format_date);
     Calendar cal = Calendar.getInstance();
     this.ngaytra = this.formatter.format(cal.getTime());
     this.ctTraKhoExt = new CtTraKhoExt();
     this.plthuoc = new DmPhanLoaiThuoc();
     this.nguonkinhphi = new DmNguonKinhPhi();
     this.nguonchuongtrinh = new DmNguonChuongTrinh();
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuTrakho.setDtdmnhanvienCn(nv);
     }
     this.flagUpdateCtTrakho = false;
     this.listCtTraKhoExt = new ArrayList();
     this.resultReportFileName = "";
     this.resultReportName = "";
     this.loaiFileXuat = "";
     this.nofound = "false";
     this.nosuccess = "false";
     this.noinphieu = "false";

     this.loaiPhieu = "";
     this.dmLoaiTen = "";
     this.dmkhoaMaso = null;
     this.dmkhoaMa = "";
     this.dmkhoaTen = "";
     this.listDmLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     refreshDmLoaiThuoc();
     this.listDmLoaiPhieus.clear();

     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     refreshDmThuoc();
     this.dmLoaiPhieuMa = "";
     this.hmDmLoaiPhieu.clear();
   }

   public void enter()
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     log.info("Add vao luoi");
     updateTonKho(this.ctTraKhoExt);
     if (this.flagUpdateCtTrakho)
     {
       this.flagUpdateCtTrakho = false;
     }
     else
     {
       log.info("enter11" + this.ctTraKhoExt);
       this.listCtTraKhoExt.add(this.ctTraKhoExt);
     }
     tinhTongTien();
     this.ctTraKhoExt = new CtTraKhoExt();
     this.count = Integer.valueOf(this.listCtTraKhoExt.size());
     log.info("Count: " + this.count);
   }

   private void updateTonKho(CtTraKhoExt ctTraKhoExt)
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     Integer matonkho = ctTraKhoExt.getTonKho().getTonkhoMa();
     if (matonkho == null) {
       return;
     }
     TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
     TonKho tk = tkDelegate.find(matonkho);

     int updateCaseMalk = -1;

     CtTraKho cttk = ctTraKhoExt.getCtTraKho();



     String malk = ctTraKhoExt.getTonKho().getTonkhoMalienket();
     Double slTra = new Double("0");
     for (int i = 0; i < this.listCtTraKhoExt.size(); i++)
     {
       CtTraKho cttk_temp = ((CtTraKhoExt)this.listCtTraKhoExt.get(i)).getCtTraKho();
       if (malk.toUpperCase().equals(cttk_temp.getCttrakhoMalk()))
       {
         slTra = Double.valueOf(slTra.doubleValue() + cttk_temp.getCttrakhoSoluong().doubleValue());
         this.flagUpdateCtTrakho = true;
         updateCaseMalk = i;
       }
     }
     Double tra = ctTraKhoExt.getCtTraKho().getCttrakhoSoluong();
     slTra = Double.valueOf(slTra.doubleValue() + Double.valueOf(tra.doubleValue()).doubleValue());
     if (slTra.doubleValue() > tk.getTonkhoTon().doubleValue())
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.PHIEUTRAKHO_SLKHONGDUTRA, new Object[0]);
       return;
     }
     cttk.setCttrakhoSoluong(slTra);


     TonKho tkTra = duplicateTk(tk);
     if (tkTra != null)
     {
       tkTra.setTonkhoXuat(cttk.getCttrakhoSoluong());
       tkTra.setTonkhoNhap(null);
       tkTra.setTonkhoTra(null);
     }
     TonKho tkNhan = duplicateTk(tk);
     if (tkNhan != null)
     {
       tkNhan.setTonkhoNhap(null);
       tkNhan.setTonkhoXuat(null);
       tkNhan.setTonkhoTra(cttk.getCttrakhoSoluong());
     }
     ctTraKhoExt.setTonKhoTra(tkTra);
     ctTraKhoExt.setTonKhoNhan(tkNhan);
     cttk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     cttk.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     cttk.setDmthuocMaso(tk.getDmthuocMaso());
     cttk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     cttk.setDmnctMaso(tk.getDmnctMaso());
     cttk.setCttrakhoDongia(tk.getTonkhoDongia());
     cttk.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
     cttk.setCttrakhoLo(tk.getTonkhoLo());
     cttk.setCttrakhoMalk(tk.getTonkhoMalienket());
     cttk.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
     cttk.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
     cttk.setCttrakhoNgaygiocn(new Date());
     cttk.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
     cttk.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
     cttk.setPhieutrakhoMa(this.phieuTrakho);
     cttk.setCttrakhoNgaygiocn(new Date());
     if (updateCaseMalk > -1) {
       this.listCtTraKhoExt.set(updateCaseMalk, ctTraKhoExt);
     }
   }

   private TonKho duplicateTk(TonKho tk)
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     if (tk != null)
     {
       TonKho temp = (TonKho)BeanUtils.cloneBean(tk);
       temp.setTonkhoMa(null);
       temp.setTonkhoNgaygiocn(new Date());
       return temp;
     }
     return null;
   }

   private void tinhTongTien()
   {
     Double tongtien = new Double(0.0D);
     for (CtTraKhoExt obj : this.listCtTraKhoExt) {
       tongtien = Double.valueOf(tongtien.doubleValue() + obj.getThanhtien().doubleValue());
     }
     this.phieuTrakho.setPhieutrakhoThanhtien(tongtien);
   }

   public void delete()
   {
     log.info("delete");
     this.listCtTraKhoExt.remove(this.selectedCtTraKhoExt);
     tinhTongTien();
     this.ctTraKhoExt = new CtTraKhoExt();

     this.count = Integer.valueOf(this.listCtTraKhoExt.size());
   }

   public void selected()
   {
     log.info("selected");
     this.ctTraKhoExt = this.selectedCtTraKhoExt;
     this.flagUpdateCtTrakho = true;
   }

   public void ghinhan()
   {
     try
     {
       log.info("ghinhan");
       if ((this.phieuTrakho.getPhieutrakhoMa() != null) && (!this.phieuTrakho.getPhieutrakhoMa().equals(""))) {
         return;
       }
       if (this.listCtTraKhoExt.size() > 0)
       {
         List<CtTraKho> list = new ArrayList();
         List<TonKho> listTonKhoTra = new ArrayList();
         List<TonKho> listTonKhoNhan = new ArrayList();
         for (CtTraKhoExt obj : this.listCtTraKhoExt)
         {
           list.add(obj.getCtTraKho());
           listTonKhoTra.add(obj.getTonKhoTra());
           listTonKhoNhan.add(obj.getTonKhoNhan());
         }
         this.phieuTrakho.setPhieutrakhoNgaygiocn(new Date());
         if (!this.ngaytra.equals("")) {
           this.phieuTrakho.setPhieutrakhoNgay(this.formatter.parse(this.ngaytra));
         }
         this.phieuTrakho.setPhieutrakhoLoaiPhieu(this.loaiPhieu);
         DmKhoa dmkhoaTra = new DmKhoa();
         dmkhoaTra.setDmkhoaMaso(this.dmkhoaMaso);
         dmkhoaTra.setDmkhoaMa(this.dmkhoaMa);
         dmkhoaTra.setDmkhoaTen(this.dmkhoaTen);
         this.phieuTrakho.setDmkhoaTra(dmkhoaTra);
         RemoveUtil.removeIfNullForPhieuTraKho(this.phieuTrakho);

         PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
         String result = delegate.createPhieuTra(this.phieuTrakho, list, listTonKhoNhan, listTonKhoTra);
         if (result.equals(""))
         {
           this.nosuccess = "true";
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         }
         else if (result.equals("Soluongxuatkhonghople"))
         {
           this.nosuccess = "true";
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_SLKHONGDUXUATTL, new Object[0]);
         }
         else
         {
           this.phieuTrakho.setPhieutrakhoMa(result);
           SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTrakho);
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { result });
           displayPhieuXuatKho();
         }
       }
       else
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL, new Object[0]);
       }
     }
     catch (Exception er)
     {
       this.nosuccess = "true";
       System.out.println("Loi");
       return;
     }
   }

   public void nhapmoi()
     throws Exception
   {
     log.info("nhapmoi");
     resetValue();
   }

   public void displayPhieuXuatKho()
     throws Exception
   {
     log.info("displayPhieuXuatKho");
     this.listCtTraKhoExt.clear();
     this.ctTraKhoExt = new CtTraKhoExt();
     String maPhieuXuat = this.phieuTrakho.getPhieutrakhoMa();
     if ((maPhieuXuat != null) && (!maPhieuXuat.equals("")))
     {
       PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
       PhieuTraKho ptk_tmp = delegate.findByPhieutrakhoMa(maPhieuXuat);
       if (ptk_tmp != null)
       {
         this.phieuTrakho = ptk_tmp;
         SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTrakho);
         this.dmLoaiTen = this.phieuTrakho.getDmloaithuocMaso(true).getDmloaithuocTen();
         this.loaiPhieu = this.phieuTrakho.getPhieutrakhoLoaiPhieu();
         this.dmkhoaTen = this.phieuTrakho.getDmkhoaTra().getDmkhoaTen();
         this.dmkhoaMa = this.phieuTrakho.getDmkhoaTra().getDmkhoaMa();
         this.dmkhoaMaso = this.phieuTrakho.getDmkhoaTra().getDmkhoaMaso();
         this.ngaytra = this.formatter.format(this.phieuTrakho.getPhieutrakhoNgay());
         CtTraKhoDelegate delegateCt = CtTraKhoDelegate.getInstance();
         for (CtTraKho obj : delegateCt.findByphieutrakhoMa(this.phieuTrakho.getPhieutrakhoMa()))
         {
           CtTraKhoExt ct = new CtTraKhoExt();
           ct.setCtTraKho(obj);
           ct.setThanhtien(Double.valueOf(obj.getCttrakhoSoluong().doubleValue() * obj.getCttrakhoDongia().doubleValue()));
           this.listCtTraKhoExt.add(ct);
         }
       }
       else
       {
         this.nofound = "true";
       }
       this.noinphieu = "false";
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   public TraHangTuyenDuoiAction()
   {
     this.flagUpdateCtTrakho = false;




     this.isReport = "false";

















     this.dmKhoNhan = "";









     this.hmDmThuoc = new HashMap();

     this.dmLoaiTen = "";

     this.listDmLoaiThuocs = new ArrayList();
     this.hmLoaiThuoc = new HashMap();

     this.hmDmLoaiPhieu = new HashMap();
     this.dmLoaiPhieuMa = "";
     this.loaiPhieu = "";
     this.listDmLoaiPhieus = new ArrayList();

































     this.listDmThuocs = new ArrayList();






































































































































































































































     this.listDmKhoTras = new ArrayList();
     this.hmDmKhoTra = new HashMap();















































































































































































































































































































































































     this.index = 0;




     this.reportPathKC = null;



     this.reportTypeKC = null;



     this.jasperPrintKC = null;
   }

   public void XuatReport()
   {
     this.reportTypeKC = "D03_phieutrakhoTD";
     log.info("Vao Method XuatReport D03_phieutrakhoTD");
     String baocao1 = null;
     Date currentDate = new Date();
     if (!this.phieuTrakho.getPhieutrakhoMa().equals("")) {
       try
       {
         log.info("bat dau method XuatReport ");
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieutrakho_01.jrxml";
         log.info("da thay file templete 5" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         log.info("da thay file template ");
         Map<String, Object> params = new HashMap();

         PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
         PhieuTraKho pt = pxkWS.find(this.phieuTrakho.getPhieutrakhoMa());

         params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         log.info(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
         params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         log.info(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
         params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
         log.info(String.format("-----dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
         params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
         Calendar cal = Calendar.getInstance();
         cal.setTime(pt.getPhieutrakhoNgay());
         if (cal != null)
         {
           log.info(String.format("-----ngay lap: %s", new Object[] { cal.getTime() }));
           params.put("ngayHt", "" + cal.get(5));
           params.put("thangHt", "" + (cal.get(2) + 1));
           params.put("namHt", "" + cal.get(1));
         }
         else
         {
           log.info("-----ngay lap is null");
           params.put("ngayHt", "");
           params.put("thangHt", "");
           params.put("namHt", "");
         }
         SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
         String ngayGioHt = df.format(Calendar.getInstance().getTime());
         log.info(String.format("-----ngay gio hien tai: %s", new Object[] { ngayGioHt }));
         params.put("gioHt", ngayGioHt);

         params.put("pxMa", pt.getPhieutrakhoMa());
         if (pt.getDmkhoaNhan() != null) {
           params.put("khoaNhan", pt.getDmkhoaNhan().getDmkhoaTen());
         } else {
           params.put("khoaNhan", "");
         }
         log.info(String.format("-----khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
         if (pt.getDmkhoaTra() != null) {
           params.put("khoaXuat", pt.getDmkhoaTra().getDmkhoaTen());
         } else {
           params.put("khoaXuat", "");
         }
         log.info(String.format("-----khoaXuat: %s", new Object[] { params.get("khoaXuat") }));

         params.put("tongTien", pt.getPhieutrakhoThanhtien());
         log.info(String.format("-----tongTien: %s", new Object[] { params.get("tongTien") }));
         params.put("loaiMa", pt.getDmloaithuocMaso().getDmloaithuocMa());
         params.put("nhanvienCn", pt.getDtdmnhanvienCn().getDtdmnhanvienMa());

         log.info("================= ");
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
         this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "D03_phieuxuatkho_khoaphong");
         this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan -------------------- :" + this.reportPathKC);
         this.index += 1;
         log.info("Index :" + this.index);
         if (conn != null) {
           conn.close();
         }
       }
       catch (Exception e)
       {
         log.info("ERROR Method XuatReport!!!");
         e.printStackTrace();
       }
     }
     log.info("Thoat Method XuatReport");
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B4123_Phieuxuathangkhoaphong";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public CtTraKhoExt getSelectedCtTraKhoExt()
   {
     return this.selectedCtTraKhoExt;
   }

   public void setSelectedCtTraKhoExt(CtTraKhoExt selectedCtTraKhoExt)
   {
     this.selectedCtTraKhoExt = selectedCtTraKhoExt;
   }

   public CtTraKhoExt getCtTraKhoExt()
   {
     return this.ctTraKhoExt;
   }

   public void setCtTraKhoExt(CtTraKhoExt CtTraKhoExt)
   {
     this.ctTraKhoExt = CtTraKhoExt;
   }

   public String getNgaytra()
   {
     return this.ngaytra;
   }

   public void setNgaytra(String ngaytra)
   {
     this.ngaytra = ngaytra;
   }

   public PhieuTraKho getPhieuTrakho()
   {
     return this.phieuTrakho;
   }

   public void setPhieuTrakho(PhieuTraKho phieuTrakho)
   {
     this.phieuTrakho = phieuTrakho;
   }

   public DmPhanLoaiThuoc getPlthuoc()
   {
     return this.plthuoc;
   }

   public void setPlthuoc(DmPhanLoaiThuoc plthuoc)
   {
     this.plthuoc = plthuoc;
   }

   public DmNguonKinhPhi getNguonkinhphi()
   {
     return this.nguonkinhphi;
   }

   public void setNguonkinhphi(DmNguonKinhPhi nguonkinhphi)
   {
     this.nguonkinhphi = nguonkinhphi;
   }

   public DmNguonChuongTrinh getNguonchuongtrinh()
   {
     return this.nguonchuongtrinh;
   }

   public void setNguonchuongtrinh(DmNguonChuongTrinh nguonchuongtrinh)
   {
     this.nguonchuongtrinh = nguonchuongtrinh;
   }

   public List<CtTraKhoExt> getListCtTraKhoExt()
   {
     return this.listCtTraKhoExt;
   }

   public void setListCtTraKhoExt(List<CtTraKhoExt> listCtTraKhoExt)
   {
     this.listCtTraKhoExt = listCtTraKhoExt;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
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

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNoinphieu()
   {
     return this.noinphieu;
   }

   public void setNoinphieu(String noinphieu)
   {
     this.noinphieu = noinphieu;
   }

   public SimpleDateFormat getFormatter()
   {
     return this.formatter;
   }

   public void setFormatter(SimpleDateFormat formatter)
   {
     this.formatter = formatter;
   }

   public boolean isFlagUpdateCtTrakho()
   {
     return this.flagUpdateCtTrakho;
   }

   public void setFlagUpdateCtTrakho(boolean flagUpdateCtTrakho)
   {
     this.flagUpdateCtTrakho = flagUpdateCtTrakho;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public String getDmKhoNhan()
   {
     return this.dmKhoNhan;
   }

   public void setDmKhoNhan(String dmKhoNhan)
   {
     this.dmKhoNhan = dmKhoNhan;
   }

   public static long getSerialVersionUID()
   {
     return 4366573502725366923L;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public String getDmLoaiPhieuMa()
   {
     return this.dmLoaiPhieuMa;
   }

   public void setDmLoaiPhieuMa(String dmLoaiPhieuMa)
   {
     this.dmLoaiPhieuMa = dmLoaiPhieuMa;
   }

   @End
   public void end() {}

   public class CtTraKhoExt
     implements Serializable
   {
     private static final long serialVersionUID = 5212617651932213622L;
     private CtTraKho ctTraKho;
     private TonKho tonKho;
     private Double thanhtien;
     private TonKho tonKhoTra;
     private TonKho tonKhoNhan;

     public CtTraKhoExt()
     {
       this.ctTraKho = new CtTraKho();
       this.tonKho = new TonKho();
       SetInforUtil.setInforIfNullForTonKho(this.tonKho);
       if (this.tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
         this.tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.thanhtien = new Double(0.0D);
     }

     public Double getThanhtien()
     {
       return this.thanhtien;
     }

     public void setThanhtien(Double thanhtien)
     {
       this.thanhtien = thanhtien;
     }

     public CtTraKho getCtTraKho()
     {
       return this.ctTraKho;
     }

     public void setCtTraKho(CtTraKho ctTraKho)
     {
       this.ctTraKho = ctTraKho;
     }

     public TonKho getTonKho()
     {
       return this.tonKho;
     }

     public void setTonKho(TonKho tonKho)
     {
       this.tonKho = tonKho;
     }

     public TonKho getTonKhoTra()
     {
       return this.tonKhoTra;
     }

     public void setTonKhoTra(TonKho tonKhoTra)
     {
       this.tonKhoTra = tonKhoTra;
     }

     public TonKho getTonKhoNhan()
     {
       return this.tonKhoNhan;
     }

     public void setTonKhoNhan(TonKho tonKhoNhan)
     {
       this.tonKhoNhan = tonKhoNhan;
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.TraHangTuyenDuoiAction

 * JD-Core Version:    0.7.0.1

 */