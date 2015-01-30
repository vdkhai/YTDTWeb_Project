 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
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
 import javax.faces.component.html.HtmlSelectOneRadio;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4414_Inchitietnhaphang")
 @Synchronized(timeout=6000000L)
 public class Inchitietnhaphang
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   String dmKhoXuat = "";
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @DataModel
   private List<DmPhanLoaiThuoc> listDmPLThuoc = new ArrayList();
   @DataModelSelection("listDmPLThuoc")
   @Out(required=false)
   private DmPhanLoaiThuoc dmPLThuocSelect;
   @Out(required=false)
   @In(required=false)
   private String resetFormB4414 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private Integer loaihang_maso = null;
   private Integer hang_maso = null;
   private Integer kho_maso = null;
   private Integer noiban_maso = null;
   private String chon = null;
   private String loaihang_ma = "";
   private String hang_ma = "";
   private String kho_ma = "";
   private String noiban_ma = "";
   private String plthuoc_ma = "";
   private String nhapXuatHang = "0";
   private String dmTenKho = "";
   private List<SelectItem> listDmKhos = new ArrayList();
   HashMap<String, DmKhoa> hmDmKho = new HashMap();
   private Boolean coTra = Boolean.valueOf(false);
   private String khoatra_ma = "";
   private Integer khoatra_maso = null;
   private String dmTenKhoaTra = "";
   private List<SelectItem> listDmKhoaTras = new ArrayList();
   HashMap<String, DmKhoa> hmDmKhoaTra = new HashMap();
   private Boolean coTraNCC = Boolean.valueOf(false);
   private Integer ncc_maso = null;
   private String ncc_ma = "";
   private Integer noinhan_maso = null;
   private String noinhan_ma = "";
   private String dmTenKhoaNhan = "";
   private List<SelectItem> listDmKhoaNhans = new ArrayList();
   HashMap<String, DmKhoa> hmDmKhoaNhan = new HashMap();
   private DmThuocDelegate dmThuocDelegate;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private DmPhanLoaiThuocDelegate dmPhanLoaiThuocDelegate;
   private DmKhoaDelegate dmKhoaDelegate;
   private String dmtTen = "";
   private List<SelectItem> listDmThuocs = new ArrayList();
   private String dmLoaiTen = "";
   private List<SelectItem> listDmLoaiThuocs = new ArrayList();
   private String dmPhanLoaiTen = "";
   private List<SelectItem> listDmPhanLoaiThuocs = new ArrayList();
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   HashMap<String, DmPhanLoaiThuoc> hmPhanLoaiThuoc = new HashMap();
   HashMap<String, DmThuoc> hmDmThuoc = new HashMap();

   @Begin(join=true)
   public String init(String kho)
   {
     this.log.info("init()", new Object[0]);
     this.dmKhoXuat = kho;
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InChiTietNhapXuatHang";
   }

   @Factory("resetFormB4414")
   public void initresetFormB4413()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @End
   public void end() {}

   public String getDmTenKho()
   {
     return this.dmTenKho;
   }

   public void setDmTenKho(String dmTenKho)
   {
     this.dmTenKho = dmTenKho;
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

   public String getDmPhanLoaiTen()
   {
     return this.dmPhanLoaiTen;
   }

   public void setDmPhanLoaiTen(String dmPhanLoaiTen)
   {
     this.dmPhanLoaiTen = dmPhanLoaiTen;
   }

   public List<SelectItem> getListDmPhanLoaiThuocs()
   {
     return this.listDmPhanLoaiThuocs;
   }

   public void setListDmPhanLoaiThuocs(List<SelectItem> listDmPhanLoaiThuocs)
   {
     this.listDmPhanLoaiThuocs = listDmPhanLoaiThuocs;
   }

   public void onblurMaLoaiAction()
   {
     this.log.info("-----BEGIN onblurMaLoaiAction()-----" + this.loaihang_ma, new Object[0]);
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(this.loaihang_ma.toUpperCase());
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     setPlthuoc_ma("");
     setDmPhanLoaiTen("");
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
     this.listDmPLThuoc.clear();
     this.log.info("-----END onblurMaLoaiAction()-----", new Object[0]);
   }

   public void onblurTenLoaiThuocAction()
   {
     this.log.info("-----BEGIN onblurTenLoaiThuocAction()-----", new Object[0]);
     Boolean hasTenLoai = Boolean.valueOf(false);
     String maLoai = null;
     if (this.dmLoaiTen == "") {
       setLoaihang_ma("");
     }
     Set set = this.hmLoaiThuoc.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
       if ((dmLoaiThuoc.getDmloaithuocTen() == this.dmLoaiTen) || (dmLoaiThuoc.getDmloaithuocTen().equals(this.dmLoaiTen)))
       {
         hasTenLoai = Boolean.valueOf(true);
         maLoai = dmLoaiThuoc.getDmloaithuocMa();
         break;
       }
     }
     if (hasTenLoai.booleanValue()) {
       setLoaihang_ma(maLoai);
     } else {
       setLoaihang_ma("");
     }
     setPlthuoc_ma("");
     setDmPhanLoaiTen("");
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
     this.listDmPLThuoc.clear();
     this.log.info("-----END onblurTenLoaiThuocAction()-----", new Object[0]);
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
       this.listDmLoaiThuocs.add(new SelectItem("All"));
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)me.getValue();
         this.listDmLoaiThuocs.add(new SelectItem(dmLoaiThuoc.getDmloaithuocTen()));
       }
     }
   }

   public void onblurMaPhanLoaiAction()
   {
     this.log.info("-----BEGIN onblurMaPhanLoaiAction()-----", new Object[0]);
     DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)this.hmPhanLoaiThuoc.get(this.plthuoc_ma.toUpperCase());
     if (dmPhanLoaiThuoc != null)
     {
       setDmPhanLoaiTen(dmPhanLoaiThuoc.getDmphanloaithuocTen());
     }
     else
     {
       setDmPhanLoaiTen("");
       setPlthuoc_ma("");
     }
     this.log.info("-----END onblurMaThuocAction()-----", new Object[0]);
   }

   public void onblurTenPhanLoaiThuocAction()
   {
     this.log.info("-----BEGIN onblurTenPhanLoaiThuocAction()-----", new Object[0]);
     Boolean hasTenLoai = Boolean.valueOf(false);
     String maPhanLoai = null;
     if (this.dmPhanLoaiTen == "") {
       setPlthuoc_ma("");
     }
     Set set = this.hmPhanLoaiThuoc.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmPhanLoaiThuoc dmPhanLoaiThuoc = (DmPhanLoaiThuoc)me.getValue();
       if ((dmPhanLoaiThuoc.getDmphanloaithuocTen() == this.dmPhanLoaiTen) || (dmPhanLoaiThuoc.getDmphanloaithuocTen().equals(this.dmPhanLoaiTen)))
       {
         hasTenLoai = Boolean.valueOf(true);
         maPhanLoai = dmPhanLoaiThuoc.getDmphanloaithuocMa();
         break;
       }
     }
     if (hasTenLoai.booleanValue()) {
       setPlthuoc_ma(maPhanLoai);
     } else {
       setPlthuoc_ma("");
     }
     this.log.info("-----END onblurTenThuocAction()-----", new Object[0]);
   }

   public void refreshDmPhanLoaiThuoc()
   {
     this.listDmPhanLoaiThuocs.clear();
     this.listDmPhanLoaiThuocs.add(new SelectItem("All"));
     this.dmPhanLoaiThuocDelegate = DmPhanLoaiThuocDelegate.getInstance();
     this.hmPhanLoaiThuoc.clear();
     if (!this.loaihang_ma.equals(""))
     {
       List<DmPhanLoaiThuoc> lstDmPLThuoc = this.dmPhanLoaiThuocDelegate.findByDtdmloaiMa(this.loaihang_ma);
       if ((lstDmPLThuoc != null) && (lstDmPLThuoc.size() > 0)) {
         for (DmPhanLoaiThuoc o : lstDmPLThuoc)
         {
           this.listDmPhanLoaiThuocs.add(new SelectItem(o.getDmphanloaithuocTen()));
           this.hmPhanLoaiThuoc.put(o.getDmphanloaithuocMa(), o);
         }
       }
     }
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
     this.log.info("-----BEGIN onblurMaThuocAction()-----", new Object[0]);
     DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.hang_ma.toUpperCase());
     if (dmThuoc != null)
     {
       setDmtTen(dmThuoc.getDmthuocTen());
       setHang_maso(dmThuoc.getDmthuocMaso());
       this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
     }
     else
     {
       setDmtTen("");
       setHang_maso(null);
       setHang_ma("");
     }
     this.log.info("-----END onblurMaThuocAction()-----", new Object[0]);
   }

   public void onblurTenThuocAction()
   {
     this.log.info("-----BEGIN onblurTenThuocAction()-----" + this.dmtTen, new Object[0]);
     Boolean hasTenThuoc = Boolean.valueOf(false);
     String maThuoc = null;
     Integer masoThuoc = null;
     if (this.hmDmThuoc != null)
     {
       Set set = this.hmDmThuoc.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmThuoc dmThuoc = (DmThuoc)me.getValue();
         if ((dmThuoc.getDmthuocTen().trim() == this.dmtTen) || (dmThuoc.getDmthuocTen().trim().equals(this.dmtTen)))
         {
           hasTenThuoc = Boolean.valueOf(true);
           maThuoc = dmThuoc.getDmthuocMa();
           masoThuoc = dmThuoc.getDmthuocMaso();
           break;
         }
       }
     }
     if (hasTenThuoc.booleanValue())
     {
       setHang_ma(maThuoc);
       setHang_maso(masoThuoc);
       this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
     }
     else
     {
       setHang_ma("");
       setHang_maso(masoThuoc);
     }
     this.log.info("-----END onblurTenThuocAction()-----", new Object[0]);
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     List<DmThuoc> lstDmThuoc = this.dmThuocDelegate.findByLoai_ListPhanLoaiThuoc(this.loaihang_ma, this.listDmPLThuoc);
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
       }
     }
   }

   public List<SelectItem> getListDmKhos()
   {
     return this.listDmKhos;
   }

   public void setListDmKhos(List<SelectItem> listDmKhos)
   {
     this.listDmKhos = listDmKhos;
   }

   public void onblurMaKhoAction()
   {
     this.log.info("-----BEGIN onblurMaKhoAction()-----", new Object[0]);
     DmKhoa dmKhoa = (DmKhoa)this.hmDmKho.get(this.kho_ma.toUpperCase());
     if (dmKhoa != null)
     {
       setDmTenKho(dmKhoa.getDmkhoaTen());
       setKho_maso(dmKhoa.getDmkhoaMaso());
     }
     else
     {
       setDmTenKho("");
       setKho_maso(null);
     }
     setKhoatra_ma("");
     setDmTenKhoaTra("");
     setNoinhan_ma("");
     setDmTenKhoaNhan("");
     if (this.kho_ma.toUpperCase().equals(IConstantsRes.KHOA_KC_MA)) {
       refreshDmKhoaKhoTra();
     } else {
       refreshDmKhoaTra();
     }
     this.log.info("-----END onblurMaKhoAction()-----", new Object[0]);
   }

   public void onblurTenKhoAction()
   {
     this.log.info("-----BEGIN onblurTenKhoAction()-----", new Object[0]);
     Boolean hasTenKho = Boolean.valueOf(false);
     String maKho = null;
     Integer masoKho = null;
     if (this.dmTenKho == "") {
       setKho_ma("");
     }
     Set set = this.hmDmKho.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmKhoa dmKhoa = (DmKhoa)me.getValue();
       if ((dmKhoa.getDmkhoaTen() == this.dmTenKho) || (dmKhoa.getDmkhoaTen().equals(this.dmTenKho)))
       {
         hasTenKho = Boolean.valueOf(true);
         maKho = dmKhoa.getDmkhoaMa();
         masoKho = dmKhoa.getDmkhoaMaso();
         break;
       }
     }
     if (hasTenKho.booleanValue())
     {
       setKho_ma(maKho);
       setKho_maso(masoKho);
     }
     else
     {
       setKho_ma("");
       setKho_maso(null);
     }
     setKhoatra_ma("");
     setDmTenKhoaTra("");
     setNoinhan_ma("");
     setDmTenKhoaNhan("");
     if (maKho.equals(IConstantsRes.KHOA_KC_MA)) {
       refreshDmKhoaKhoTra();
     } else {
       refreshDmKhoaTra();
     }
     this.log.info("-----END onblurTenKhoAction()-----", new Object[0]);
   }

   public void refreshDmKho()
   {
     this.listDmKhos.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();
     this.hmDmKho.clear();
     List<DmKhoa> lstDmKho = this.dmKhoaDelegate.getKhoChinh_KhoLe();
     if ((lstDmKho != null) && (lstDmKho.size() > 0)) {
       for (DmKhoa o : lstDmKho)
       {
         this.listDmKhos.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKho.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public List<SelectItem> getListDmKhoaTras()
   {
     return this.listDmKhoaTras;
   }

   public void setListDmKhoaTras(List<SelectItem> listDmKhoaTras)
   {
     this.listDmKhoaTras = listDmKhoaTras;
   }

   public String getDmTenKhoaTra()
   {
     return this.dmTenKhoaTra;
   }

   public void setDmTenKhoaTra(String dmTenKhoaTra)
   {
     this.dmTenKhoaTra = dmTenKhoaTra;
   }

   public void onblurMaKhoaTraAction()
   {
     this.log.info("-----BEGIN onblurMaKhoaTraAction()-----", new Object[0]);
     DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoaTra.get(this.khoatra_ma.toUpperCase());
     if (dmKhoa != null)
     {
       setDmTenKhoaTra(dmKhoa.getDmkhoaTen());
       setKhoatra_maso(dmKhoa.getDmkhoaMaso());
     }
     else
     {
       setDmTenKhoaTra("");
       setKhoatra_maso(null);
     }
     this.log.info("-----END onblurMaKhoaTraAction()-----", new Object[0]);
   }

   public void onblurTenKhoaTraAction()
   {
     this.log.info("-----BEGIN onblurTenKhoaTraAction()-----", new Object[0]);
     Boolean hasTenKhoa = Boolean.valueOf(false);
     String maKhoa = null;
     Integer masoKhoa = null;
     if (this.dmTenKhoaTra == "")
     {
       setKhoatra_ma("");
       setKhoatra_maso(null);
     }
     Set set = this.hmDmKhoaTra.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmKhoa dmKhoa = (DmKhoa)me.getValue();
       if ((dmKhoa.getDmkhoaTen() == this.dmTenKhoaTra) || (dmKhoa.getDmkhoaTen().equals(this.dmTenKhoaTra)))
       {
         hasTenKhoa = Boolean.valueOf(true);
         maKhoa = dmKhoa.getDmkhoaMa();
         masoKhoa = dmKhoa.getDmkhoaMaso();
         break;
       }
     }
     if (hasTenKhoa.booleanValue())
     {
       setKhoatra_ma(maKhoa);
       setKhoatra_maso(masoKhoa);
     }
     else
     {
       setKhoatra_ma("");
       setKhoatra_maso(null);
     }
     this.log.info("-----END onblurTenKhoaTraAction()-----", new Object[0]);
   }

   public void refreshDmKhoaTra()
   {
     this.listDmKhoaTras.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();
     this.hmDmKhoaTra.clear();
     List<DmKhoa> lstDmKhoa = this.dmKhoaDelegate.getKhoaLamSang();
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhoaTras.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoaTra.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public void refreshDmKhoaKhoTra()
   {
     this.listDmKhoaTras.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();
     this.hmDmKhoaTra.clear();
     List<DmKhoa> lstDmKhoa = this.dmKhoaDelegate.getKhoaLamSang();
     List<DmKhoa> lstDmKho = this.dmKhoaDelegate.getKhoLe();
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhoaTras.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoaTra.put(o.getDmkhoaMa(), o);
       }
     }
     if ((lstDmKho != null) && (lstDmKho.size() > 0)) {
       for (DmKhoa o : lstDmKho)
       {
         this.listDmKhoaTras.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoaTra.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public List<SelectItem> getListDmKhoaNhans()
   {
     return this.listDmKhoaNhans;
   }

   public void setListDmKhoaNhans(List<SelectItem> listDmKhoaNhans)
   {
     this.listDmKhoaNhans = listDmKhoaNhans;
   }

   public String getDmTenKhoaNhan()
   {
     return this.dmTenKhoaNhan;
   }

   public void setDmTenKhoaNhan(String dmTenKhoaNhan)
   {
     this.dmTenKhoaNhan = dmTenKhoaNhan;
   }

   public void onblurMaKhoaNhanAction()
   {
     this.log.info("-----BEGIN onblurMaKhoaNhanAction()-----", new Object[0]);
     DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoaNhan.get(this.noinhan_ma.toUpperCase());
     if (dmKhoa != null)
     {
       setDmTenKhoaNhan(dmKhoa.getDmkhoaTen());
       setNoinhan_maso(dmKhoa.getDmkhoaMaso());
     }
     else
     {
       setDmTenKhoaNhan("");
       setNoinhan_maso(null);
     }
     this.log.info("-----END onblurMaKhoaNhanAction()-----", new Object[0]);
   }

   public void onblurTenKhoaNhanAction()
   {
     this.log.info("-----BEGIN onblurTenKhoaNhanAction()-----", new Object[0]);
     Boolean hasTenKhoa = Boolean.valueOf(false);
     String maKhoa = null;
     Integer masoKhoa = null;
     if (this.dmTenKhoaNhan == "")
     {
       setNoinhan_ma("");
       setNoinhan_maso(null);
     }
     Set set = this.hmDmKhoaNhan.entrySet();
     Iterator i = set.iterator();
     while (i.hasNext())
     {
       Map.Entry me = (Map.Entry)i.next();
       DmKhoa dmKhoa = (DmKhoa)me.getValue();
       if ((dmKhoa.getDmkhoaTen() == this.dmTenKhoaNhan) || (dmKhoa.getDmkhoaTen().equals(this.dmTenKhoaNhan)))
       {
         hasTenKhoa = Boolean.valueOf(true);
         maKhoa = dmKhoa.getDmkhoaMa();
         masoKhoa = dmKhoa.getDmkhoaMaso();
         break;
       }
     }
     if (hasTenKhoa.booleanValue())
     {
       setNoinhan_ma(maKhoa);
       setNoinhan_maso(masoKhoa);
     }
     else
     {
       setNoinhan_ma("");
       setNoinhan_maso(null);
     }
     this.log.info("-----END onblurTenKhoaNhanAction()-----", new Object[0]);
   }

   public void refreshDmKhoaNhan()
   {
     this.listDmKhoaNhans.clear();
     this.dmKhoaDelegate = DmKhoaDelegate.getInstance();
     this.hmDmKhoaNhan.clear();
     List<DmKhoa> lstDmKhoa = this.dmKhoaDelegate.getKhoaLamSang();
     if ((lstDmKhoa != null) && (lstDmKhoa.size() > 0)) {
       for (DmKhoa o : lstDmKhoa)
       {
         this.listDmKhoaNhans.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoaNhan.put(o.getDmkhoaMa(), o);
       }
     }
     List<DmKhoa> lstDmKhoLe = this.dmKhoaDelegate.getKhoLe_TL_TD();
     if ((lstDmKhoLe != null) && (lstDmKhoLe.size() > 0)) {
       for (DmKhoa o : lstDmKhoLe)
       {
         this.listDmKhoaNhans.add(new SelectItem(o.getDmkhoaTen()));
         this.hmDmKhoaNhan.put(o.getDmkhoaMa(), o);
       }
     }
   }

   public Boolean getCoTra()
   {
     return this.coTra;
   }

   public void setCoTra(Boolean coTra)
   {
     this.coTra = coTra;
   }

   public String getNhapXuatHang()
   {
     return this.nhapXuatHang;
   }

   public void setNhapXuatHang(String nhapXuatHang)
   {
     this.nhapXuatHang = nhapXuatHang;
   }

   public void selectRadioValue(ValueChangeEvent event)
   {
     HtmlSelectOneRadio radio = (HtmlSelectOneRadio)event.getComponent();
     System.out.println(radio.getValue());
     if (radio.getValue().toString().equals("0")) {
       setNhapXuatHang("0");
     } else {
       setNhapXuatHang("1");
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     resetForm();
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetList()
   {
     this.log.info("=============reset list=============", new Object[0]);
     this.listDmPLThuoc.clear();
   }

   private String getListDVMaParamsHelp(List<String> inputs)
   {
     this.log.info("Vao method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Inputs: " + inputs.toString(), new Object[0]);
     String result = "";
     for (String each : inputs) {
       if (each != "") {
         result = result + "'" + each + "',";
       }
     }
     result = result.substring(0, result.length() - 1);
     this.log.info("Thoat method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Output: " + result, new Object[0]);
     return result;
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.resetFormB4414 = "";
     this.loaihang_ma = "";
     this.hang_ma = "";
     this.kho_ma = "";
     this.noiban_ma = "";
     this.noinhan_ma = "";
     this.plthuoc_ma = "";
     this.noinhan_maso = null;
     this.noiban_maso = null;
     this.khoatra_ma = "";
     this.khoatra_maso = null;
     this.listDmPLThuoc.clear();
     this.nhapXuatHang = "0";
     this.coTra = Boolean.valueOf(false);
     this.coTraNCC = Boolean.valueOf(false);
     this.ncc_maso = null;
     this.ncc_ma = "";

     setDmLoaiTen("");
     setDmPhanLoaiTen("");
     setHang_ma("");
     setDmTenKho("");
     setDmtTen("");
     setDmTenKhoaTra("");
     setDmTenKhoaNhan("");
     this.listDmThuocs.clear();
     this.listDmLoaiThuocs.clear();
     this.listDmPhanLoaiThuocs.clear();
     this.hmLoaiThuoc.clear();
     this.hmPhanLoaiThuoc.clear();
     this.hmDmThuoc.clear();
     this.listDmKhoaNhans.clear();
     this.listDmKhoaTras.clear();
     this.hmDmKhoaNhan.clear();
     this.hmDmKhoaTra.clear();
     this.listDmKhos.clear();
     this.hmDmKho.clear();

     refreshDmLoaiThuoc();
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
     refreshDmKho();
     refreshDmKhoaNhan();
     refreshDmKhoaTra();
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.plthuoc_ma.equals("")) {
       if ((this.listDmPLThuoc.size() == 0) && (this.plthuoc_ma != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
         DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
         this.listDmPLThuoc.add(plThuoc);
         this.log.info("da add phan tu dau tien" + this.listDmPLThuoc.size(), new Object[0]);
       }
       else if ((this.listDmPLThuoc.size() > 0) && (this.plthuoc_ma != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DmPhanLoaiThuoc item : this.listDmPLThuoc) {
           if (item.getDmphanloaithuocMa().equals(this.plthuoc_ma)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
           DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
           this.listDmPLThuoc.add(plThuoc);
         }
         this.log.info("da add phan tu" + this.listDmPLThuoc.size(), new Object[0]);
       }
     }
     setPlthuoc_ma("");
     refreshDmThuoc();
   }

   public void deletedmPLThuoc()
   {
     this.log.info("bat dau xoa , size" + this.listDmPLThuoc.size(), new Object[0]);
     this.listDmPLThuoc.remove(this.dmPLThuocSelect);
     refreshDmThuoc();
     this.log.info("da xoa , size" + this.listDmPLThuoc.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Inchitietnhaphang";
     this.log.info("Vao Method XuatReport In chi tiet nhap hang", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     String filename = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       this.log.info("chon: " + this.chon, new Object[0]);
       if ("0".equals(this.nhapXuatHang))
       {
         if (IConstantsRes.KHOA_KC_MA.equals(this.dmKhoXuat))
         {
           if (!this.coTra.booleanValue()) {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D19_solieunhapkho.jrxml";
           } else {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D19_solieunhapkho_trakho.jrxml";
           }
           this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
           filename = "Inchitietnhaphang";
           this.log.info("ten file " + filename, new Object[0]);
         }
         else if (IConstantsRes.KHOA_NT_MA.equals(this.dmKhoXuat))
         {
           if (!this.coTra.booleanValue()) {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D19_solieunhapkho_knt.jrxml";
           } else {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D19_solieunhapkho_knt_trakho.jrxml";
           }
           this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
           filename = "Inchitietnhaphang";
           this.log.info("ten file " + filename, new Object[0]);
         }
         else
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D19_BHYT_solieunhapkho.jrxml";
           this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
           filename = "Inchitietnhaphang";
           this.log.info("ten file " + filename, new Object[0]);
         }
       }
       else if ("1".equals(this.nhapXuatHang)) {
         if ((IConstantsRes.KHOA_KC_MA.equals(this.dmKhoXuat)) || (IConstantsRes.KHOA_NT_MA.equals(this.dmKhoXuat)))
         {
           if (!this.coTraNCC.booleanValue())
           {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D20_solieucapphat.jrxml";pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D20_solieucapphat.jrxml";
           }
           else
           {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D20_solieucapphat_TraNCC.jrxml";
           }
           this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
           filename = "Inchitietxuathang";
           this.log.info("ten file " + filename, new Object[0]);
         }
         else
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D20_BHYT_solieucapphat.jrxml";
           this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
           filename = "Inchitietnhaphang";
           this.log.info("ten file " + filename, new Object[0]);
         }
       }
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       this.log.info("tu ngay " + this.tungay, new Object[0]);
       this.log.info("den ngay " + this.denngay, new Object[0]);


       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(strInputTonDauDate));
       c.add(5, 1);
       strInputTonDauDate = sdf.format(c.getTime());
       Date dNgayTonDau = sdf.parse(strInputTonDauDate);
       Date dTuNgay = sdf.parse(this.tungay);
       if ((dTuNgay.before(dNgayTonCuoi)) || (dTuNgay.equals(dNgayTonCuoi)))
       {
         params.put("TUNGAY", strInputTonDauDate);
         params.put("TUNGAYDATE", dNgayTonDau);
       }
       else
       {
         params.put("TUNGAY", this.tungay);
         params.put("TUNGAYDATE", dTuNgay);
       }
       params.put("DENNGAY", this.denngay);
       params.put("DENNGAYDATE", sdf.parse(this.denngay));
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       this.log.info("noi ban ma so thanh" + this.noiban_maso, new Object[0]);
       if (this.nhapXuatHang.equals("0"))
       {
         if (this.noiban_maso != null)
         {
           params.put("NOIBANMASO", this.noiban_maso);
           DmNhaCungCap cungcap = new DmNhaCungCap();
           Object obj = dtutilDelegate.findByMa(this.noiban_ma, "DmNhaCungCap", "dmnhacungcapMa");
           cungcap = (DmNhaCungCap)obj;
           params.put("NOIBAN", cungcap.getDmnhacungcapTen());
         }
         else
         {
           params.put("NOIBAN", "Tất cả");
         }
         if (this.khoatra_maso != null) {
           params.put("KHOTRAMASO", this.khoatra_maso);
         }
       }
       else if (this.nhapXuatHang.equals("1"))
       {
         if (this.noinhan_maso != null)
         {
           params.put("NOINHANMASO", this.noinhan_maso);
           DmKhoa khoa = new DmKhoa();
           Object obj = dtutilDelegate.findByMa(this.noinhan_ma, "DmKhoa", "dmkhoaMa");
           khoa = (DmKhoa)obj;
           params.put("NOINHAN", khoa.getDmkhoaTen());
         }
         else
         {
           params.put("NOINHAN", "Tất cả");
         }
       }
       this.log.info("thuoc ma so " + this.hang_maso, new Object[0]);
       if (this.hang_maso != null) {
         params.put("THUOCMASO", this.hang_maso);
       }
       this.log.info("loai thuoc ma so " + this.loaihang_maso, new Object[0]);
       if (this.loaihang_maso != null) {
         params.put("LOAITHUOCMASO", this.loaihang_maso);
       }
       this.log.info("kho ma so " + this.kho_maso, new Object[0]);
       if (this.kho_maso != null)
       {
         params.put("KHOMASO", this.kho_maso);
         DmKhoa khoa = new DmKhoa();
         Object obj = dtutilDelegate.findByMaSo(this.kho_maso, "DmKhoa", "dmkhoaMaso");
         khoa = (DmKhoa)obj;
         params.put("TENKHO", khoa.getDmkhoaTen());
       }
       if (this.listDmPLThuoc.size() > 0)
       {
         this.log.info("bat dau add danh sach phan loai thuoc", new Object[0]);
         List<String> listTemp = new ArrayList();
         String phanloaistr = new String("");
         for (DmPhanLoaiThuoc item : this.listDmPLThuoc)
         {
           listTemp.add(item.getDmphanloaithuocMa());
           phanloaistr = phanloaistr + item.getDmphanloaithuocTen() + ",";
         }
         this.log.info("choi phan loai " + phanloaistr, new Object[0]);
         phanloaistr = phanloaistr.substring(0, phanloaistr.length() - 1) + ".";
         this.log.info("sau khi modify " + phanloaistr, new Object[0]);
         String listLoaiPT = getListDVMaParamsHelp(listTemp);
         this.log.info("danh sach phan loai thuoc" + listLoaiPT, new Object[0]);
         params.put("PLTHUOCMA", listLoaiPT);
         params.put("PHANLOAI", phanloaistr);
       }
       else
       {
         params.put("PHANLOAI", "Tất cả");
       }
       if (this.ncc_maso != null) {
         params.put("NCC_MASO", this.ncc_maso);
       }
       this.log.info("================= ", new Object[0]);
       Class.forName("com.mysql.jdbc.Driver");
       this.log.info("da thay driver mysql", new Object[0]);
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         this.log.info(e.getMessage(), new Object[0]);
       }
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", filename);
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathKC, new Object[0]);
       this.index += 1;
       this.log.info("Index :" + getIndex(), new Object[0]);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       this.log.info("ERROR Method XuatReport!!!", new Object[0]);
       e.printStackTrace();
     }
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public String getThoigian_thang()
   {
     return this.thoigian_thang;
   }

   public void setThoigian_thang(String thoigian_thang)
   {
     this.thoigian_thang = thoigian_thang;
   }

   public String getThoigian_nam()
   {
     return this.thoigian_nam;
   }

   public void setThoigian_nam(String thoigian_nam)
   {
     this.thoigian_nam = thoigian_nam;
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getDenngay()
   {
     return this.denngay;
   }

   public void setDenngay(String denngay)
   {
     this.denngay = denngay;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihang_maso)
   {
     this.loaihang_maso = loaihang_maso;
   }

   public Integer getHang_maso()
   {
     return this.hang_maso;
   }

   public void setHang_maso(Integer hang_maso)
   {
     this.hang_maso = hang_maso;
   }

   public Integer getKho_maso()
   {
     return this.kho_maso;
   }

   public void setKho_maso(Integer kho_maso)
   {
     this.kho_maso = kho_maso;
   }

   public void setNoiban_maso(Integer noiban_maso)
   {
     this.noiban_maso = noiban_maso;
   }

   public Integer getNoiban_maso()
   {
     return this.noiban_maso;
   }

   public void setNoiban_ma(String noiban_ma)
   {
     this.noiban_ma = noiban_ma;
   }

   public String getNoiban_ma()
   {
     return this.noiban_ma;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public void setNoinhan_maso(Integer noinhan_maso)
   {
     this.noinhan_maso = noinhan_maso;
   }

   public Integer getNoinhan_maso()
   {
     return this.noinhan_maso;
   }

   public void setNoinhan_ma(String noinhan_ma)
   {
     this.noinhan_ma = noinhan_ma;
   }

   public String getNoinhan_ma()
   {
     return this.noinhan_ma;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihang_ma)
   {
     this.loaihang_ma = loaihang_ma;
   }

   public String getHang_ma()
   {
     return this.hang_ma;
   }

   public void setHang_ma(String hang_ma)
   {
     this.hang_ma = hang_ma;
   }

   public String getKho_ma()
   {
     return this.kho_ma;
   }

   public void setKho_ma(String kho_ma)
   {
     this.kho_ma = kho_ma;
   }

   public Integer getKhoatra_maso()
   {
     return this.khoatra_maso;
   }

   public void setKhoatra_maso(Integer khoatra_maso)
   {
     this.khoatra_maso = khoatra_maso;
   }

   public String getKhoatra_ma()
   {
     return this.khoatra_ma;
   }

   public void setKhoatra_ma(String khoatra_ma)
   {
     this.khoatra_ma = khoatra_ma;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   public Boolean getCoTraNCC()
   {
     return this.coTraNCC;
   }

   public void setCoTraNCC(Boolean coTraNCC)
   {
     this.coTraNCC = coTraNCC;
   }

   public void setNcc_maso(Integer ncc_maso)
   {
     this.ncc_maso = ncc_maso;
   }

   public Integer getNcc_maso()
   {
     return this.noinhan_maso;
   }

   public void setNcc_ma(String ncc_ma)
   {
     this.ncc_ma = ncc_ma;
   }

   public String getNcc_ma()
   {
     return this.ncc_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Inchitietnhaphang

 * JD-Core Version:    0.7.0.1

 */