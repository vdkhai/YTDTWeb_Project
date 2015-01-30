 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
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
 import org.jboss.seam.log.Log;

 @Name("B4135_BCnhapxuat1mathang")
 @Scope(ScopeType.CONVERSATION)
 public class BCnhapxuatmotmathang
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB4135 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private Integer mathang_maso = null;
   private Integer khoa_maso = null;
   private Integer hangSX_maso = null;
   private Integer nuocSX_maso = null;
   private Integer nct_maso = null;
   private Integer nkp_maso = null;
   private String mathang_ma = "";
   private String khoa_ma = "";
   private String hangSX_ma = "";
   private String nuocSX_ma = "";
   private String nct_ma = "";
   private String nkp_ma = "";
   private int index = 0;
   private String nxSelect = null;
   private String lthuoc_ma = null;
   private String plthuoc_ma = null;
   private String donvi_ma = null;
   private DmThuocDelegate dmThuocDelegate;
   private DmLoaiThuocDelegate dmLoaiThuocDelegate;
   private DmPhanLoaiThuocDelegate dmPhanLoaiThuocDelegate;
   private String dmtTen = "";
   private List<SelectItem> listDmThuocs = new ArrayList();
   private String dmLoaiTen = "";
   private List<SelectItem> listDmLoaiThuocs = new ArrayList();
   private String dmPhanLoaiTen = "";
   private List<SelectItem> listDmPhanLoaiThuocs = new ArrayList();
   HashMap<String, DmLoaiThuoc> hmLoaiThuoc = new HashMap();
   HashMap<String, DmPhanLoaiThuoc> hmPhanLoaiThuoc = new HashMap();
   HashMap<String, DmThuoc> hmDmThuoc = new HashMap();
   String dmKhoXuat = "";

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetFormB4135")
   public void initresetFormB4135()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init(String kho)
   {
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InChiTietNhapXuatMotMatHang";
   }

   @End
   public void end() {}

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
     this.log.info("-----BEGIN onblurMaLoaiAction()-----" + this.lthuoc_ma, new Object[0]);
     DmLoaiThuoc dmLoaiThuoc = (DmLoaiThuoc)this.hmLoaiThuoc.get(this.lthuoc_ma.toUpperCase());
     if (dmLoaiThuoc != null) {
       setDmLoaiTen(dmLoaiThuoc.getDmloaithuocTen());
     } else {
       setDmLoaiTen("");
     }
     setDonvi_ma("");
     setPlthuoc_ma("");
     setDmPhanLoaiTen("");
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
     this.log.info("-----END onblurMaLoaiAction()-----", new Object[0]);
   }

   public void onblurTenLoaiThuocAction()
   {
     this.log.info("-----BEGIN onblurTenLoaiThuocAction()-----", new Object[0]);
     Boolean hasTenLoai = Boolean.valueOf(false);
     String maLoai = null;
     if (this.dmLoaiTen == "")
     {
       setLthuoc_ma("");
       setDonvi_ma("");
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
       setLthuoc_ma(maLoai);
     } else {
       setLthuoc_ma("");
     }
     setDonvi_ma("");
     setPlthuoc_ma("");
     setDmPhanLoaiTen("");
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
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
     setDonvi_ma("");
     refreshDmThuoc();
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
     setDonvi_ma("");
     refreshDmThuoc();
     this.log.info("-----END onblurTenThuocAction()-----", new Object[0]);
   }

   public void refreshDmPhanLoaiThuoc()
   {
     this.listDmPhanLoaiThuocs.clear();
     this.listDmPhanLoaiThuocs.add(new SelectItem("All"));
     this.dmPhanLoaiThuocDelegate = DmPhanLoaiThuocDelegate.getInstance();
     this.hmPhanLoaiThuoc.clear();
     if (!this.lthuoc_ma.equals(""))
     {
       List<DmPhanLoaiThuoc> lstDmPLThuoc = this.dmPhanLoaiThuocDelegate.findByDtdmloaiMa(this.lthuoc_ma);
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
     DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.mathang_ma.toUpperCase());
     if (dmThuoc != null)
     {
       setDmtTen(dmThuoc.getDmthuocTen());
       setDonvi_ma(dmThuoc.getDmdonvitinhMaso().getDmdonvitinhMa());
       this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
     }
     else
     {
       setDmtTen("");
       setDonvi_ma("");
     }
     this.log.info("-----END onblurMaThuocAction()-----", new Object[0]);
   }

   public void onblurTenThuocAction()
   {
     this.log.info("-----BEGIN onblurTenThuocAction()-----" + this.dmtTen, new Object[0]);
     Boolean hasTenThuoc = Boolean.valueOf(false);
     String maThuoc = null;
     String maDv = null;
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
           maDv = dmThuoc.getDmdonvitinhMaso().getDmdonvitinhMa();
           break;
         }
       }
     }
     if (hasTenThuoc.booleanValue())
     {
       setMathang_ma(maThuoc);
       setDonvi_ma(maDv);
       this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
     }
     else
     {
       setMathang_ma("");
       setDonvi_ma("");
     }
     this.log.info("-----END onblurTenThuocAction()-----", new Object[0]);
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     List<DmThuoc> lstDmThuoc = this.dmThuocDelegate.findByLoaiPhanLoaiThuoc(this.lthuoc_ma, this.plthuoc_ma);
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
       }
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB4135 = null;
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     setNxSelect("r1");
     this.resetFormB4135 = "";
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.mathang_maso = null;
     this.hangSX_maso = null;
     this.nuocSX_maso = null;
     this.nct_maso = null;
     this.nkp_maso = null;
     this.mathang_ma = null;
     this.dmtTen = "";
     this.hangSX_ma = "";
     this.nuocSX_ma = "";
     this.nct_ma = "";
     this.nkp_ma = "";
     this.lthuoc_ma = "";
     this.plthuoc_ma = "";
     this.donvi_ma = "";
     refreshDmLoaiThuoc();
     refreshDmPhanLoaiThuoc();
     refreshDmThuoc();
   }

   public void XuatReport()
   {
     this.reportTypeKC = "BCnhapxuat1mathang";
     this.log.info("Vao Method XuatReport nhap xuat 1 mat hang", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       System.out.println("Kho xuat " + this.dmKhoXuat);
       if (this.nxSelect.equals("r1"))
       {
         if (("BHYT".equals(this.dmKhoXuat)) || ("TE".equals(this.dmKhoXuat)) || ("NT".equals(this.dmKhoXuat)))
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D08_tinhhinhxuat_khole.jrxml";
           System.out.println("KNT, TE, BHYT");
         }
         else
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D08_tinhhinhxuat_kc.jrxml";
         }
       }
       else if (this.nxSelect.equals("r2")) {
         if (("BHYT".equals(this.dmKhoXuat)) || ("TE".equals(this.dmKhoXuat)) || ("NT".equals(this.dmKhoXuat))) {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D08_tinhhinhnhap_khole.jrxml";
         } else {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D08_tinhhinhnhap_kc.jrxml";
         }
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(strInputTonDauDate));
       c.add(5, 1);
       strInputTonDauDate = sdf.format(c.getTime());
       Date dNgayTonDau = sdf.parse(strInputTonDauDate);
       Date dTuNgay = sdf.parse(this.tungay);
       if ((dTuNgay.before(dNgayTonCuoi)) || (dTuNgay.equals(dNgayTonCuoi))) {
         params.put("TUNGAY", dNgayTonDau);
       } else {
         params.put("TUNGAY", dTuNgay);
       }
       params.put("DENNGAY", sdf.parse(this.denngay));
       this.log.info("set gia tri cho thuoc", new Object[0]);
       if ((!this.mathang_ma.trim().equals("")) || (this.mathang_ma.trim() != null))
       {
         DmThuoc dmthuoc = new DmThuoc();
         dmthuoc = this.dmThuocDelegate.findByMaThuoc(this.mathang_ma.toUpperCase());
         if (dmthuoc != null)
         {
           this.mathang_maso = dmthuoc.getDmthuocMaso();



           params.put("DONVITINH", dmthuoc.getDmdonvitinhMaso().getDmdonvitinhTen());
           params.put("PHANLOAIHANG", dmthuoc.getDmphanloaithuocMaso().getDmphanloaithuocTen());
           params.put("THUOCMASO", this.mathang_maso);
           params.put("MAHANG", dmthuoc.getDmthuocMa());
           params.put("TENHANG", dmthuoc.getDmthuocTen());
           this.log.info("set dmthuoc.getDmdonvitinhMaso().getDmdonvitinhTen()" + dmthuoc.getDmdonvitinhMaso().getDmdonvitinhTen(), new Object[0]);
         }
       }
       else
       {
         this.mathang_maso = null;
         params.put("THUOCMASO", this.mathang_maso);
       }
       this.log.info("set gia tri cho nguon kinh phi", new Object[0]);
       if (!this.nkp_ma.trim().equals(""))
       {
         DmNguonKinhPhi dmnkp = new DmNguonKinhPhi();
         Object objdmnkp = dtUtils.findByMa(this.nkp_ma, "DmNguonKinhPhi", "dmnguonkinhphiMa");
         dmnkp = (DmNguonKinhPhi)objdmnkp;
         params.put("NKPMASO", this.nkp_maso);
       }
       else
       {
         this.nkp_maso = null;
         params.put("NKPMASO", this.nkp_maso);
       }
       this.log.info("set gia tri nuoc san xuat", new Object[0]);
       if (!this.nuocSX_ma.trim().equals(""))
       {
         DmQuocGia dmqg = new DmQuocGia();
         Object objdmqg = dtUtils.findByMa(this.nuocSX_ma, "DmQuocGia", "dmquocgiaMa");
         dmqg = (DmQuocGia)objdmqg;
         params.put("NUOCSX", dmqg.getDmquocgiaTen());
         params.put("QGMASO", this.nuocSX_maso);
       }
       else
       {
         this.nuocSX_maso = null;
         params.put("QGMASO", this.nuocSX_maso);
       }
       this.log.info("set gia tri hang san xuat", new Object[0]);
       if (!this.hangSX_ma.trim().equals(""))
       {
         DmNhaSanXuat dmnsx = new DmNhaSanXuat();
         Object objdmnsx = dtUtils.findByMa(this.hangSX_ma, "DmNhaSanXuat", "dmnhasanxuatMa");
         dmnsx = (DmNhaSanXuat)objdmnsx;
         params.put("NSXMASO", this.hangSX_maso);
       }
       else
       {
         this.hangSX_maso = null;
         params.put("NSXMASO", this.hangSX_maso);
       }
       this.log.info("set gia tri cho nguon chuong trinh", new Object[0]);
       if (!this.nct_ma.trim().equals(""))
       {
         DmNguonChuongTrinh dmnct = new DmNguonChuongTrinh();
         Object objdmnct = dtUtils.findByMa(this.nct_ma, "DmNguonChuongTrinh", "dmnctMa");
         dmnct = (DmNguonChuongTrinh)objdmnct;
         params.put("NGUON", dmnct.getDmnctTen());
         params.put("NCTMASO", this.nct_maso);
       }
       else
       {
         this.nct_maso = null;
         params.put("NCTMASO", this.nct_maso);
       }
       if (!this.khoa_ma.trim().equals(""))
       {
         DmKhoa dmkhoa = new DmKhoa();
         Object objdmkhoa = dtUtils.findByMa(this.khoa_ma, "DmKhoa", "dmkhoaMa");
         dmkhoa = (DmKhoa)objdmkhoa;
         params.put("KHOXUAT", dmkhoa.getDmkhoaTen());
         params.put("KHOMASO", dmkhoa.getDmkhoaMaso());
       }
       else
       {
         this.khoa_maso = null;
         params.put("KHOMASO", this.khoa_maso);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "BCnhapxuat1mathang");
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

   public Integer getMathang_maso()
   {
     return this.mathang_maso;
   }

   public void setMathang_maso(Integer mathang_maso)
   {
     this.mathang_maso = mathang_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getHangSX_maso()
   {
     return this.hangSX_maso;
   }

   public void setHangSX_maso(Integer hangSX_maso)
   {
     this.hangSX_maso = hangSX_maso;
   }

   public Integer getNuocSX_maso()
   {
     return this.nuocSX_maso;
   }

   public void setNuocSX_maso(Integer nuocSX_maso)
   {
     this.nuocSX_maso = nuocSX_maso;
   }

   public Integer getNct_maso()
   {
     return this.nct_maso;
   }

   public void setNct_maso(Integer nct_maso)
   {
     this.nct_maso = nct_maso;
   }

   public Integer getNkp_maso()
   {
     return this.nkp_maso;
   }

   public void setNkp_maso(Integer nkp_maso)
   {
     this.nkp_maso = nkp_maso;
   }

   public String getMathang_ma()
   {
     return this.mathang_ma;
   }

   public void setMathang_ma(String mathang_ma)
   {
     this.mathang_ma = mathang_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getHangSX_ma()
   {
     return this.hangSX_ma;
   }

   public void setHangSX_ma(String hangSX_ma)
   {
     this.hangSX_ma = hangSX_ma;
   }

   public String getNuocSX_ma()
   {
     return this.nuocSX_ma;
   }

   public void setNuocSX_ma(String nuocSX_ma)
   {
     this.nuocSX_ma = nuocSX_ma;
   }

   public String getNct_ma()
   {
     return this.nct_ma;
   }

   public void setNct_ma(String nct_ma)
   {
     this.nct_ma = nct_ma;
   }

   public String getNkp_ma()
   {
     return this.nkp_ma;
   }

   public void setNkp_ma(String nkp_ma)
   {
     this.nkp_ma = nkp_ma;
   }

   public void setNxSelect(String nxSelect)
   {
     this.nxSelect = nxSelect;
   }

   public String getNxSelect()
   {
     return this.nxSelect;
   }

   public String getLthuoc_ma()
   {
     return this.lthuoc_ma;
   }

   public void setLthuoc_ma(String lthuoc_ma)
   {
     this.lthuoc_ma = lthuoc_ma;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public String getDonvi_ma()
   {
     return this.donvi_ma;
   }

   public void setDonvi_ma(String donvi_ma)
   {
     this.donvi_ma = donvi_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.BCnhapxuatmotmathang

 * JD-Core Version:    0.7.0.1

 */