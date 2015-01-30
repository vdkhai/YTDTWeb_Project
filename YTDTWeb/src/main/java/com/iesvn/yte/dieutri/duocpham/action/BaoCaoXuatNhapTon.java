 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanNhomThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4412_Inbaocaonhapxuatthang")
 @Synchronized(timeout=6000000L)
 public class BaoCaoXuatNhapTon
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String position = IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/";
   private static Logger logger = Logger.getLogger(BaoCaoXuatNhapTon.class);
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
   private String resetFormB4412 = null;
   @DataModel
   private List<DmPhanLoaiThuoc> listDmPLThuoc = new ArrayList();
   @DataModelSelection("listDmPLThuoc")
   @Out(required=false)
   private DmPhanLoaiThuoc dmPLThuocSelect;
   private String inTheo = null;
   private int index = 0;
   private String ngayhientai = "";
   private String ngaykiem = "";
   private Integer loaihang_maso = null;
   private Integer plthuoc_maso = null;
   private Integer khoa_maso = null;
   private Integer ct_maso = null;
   private Integer kp_maso = null;
   private String plthuoc_ma = "";
   private String loaihang_ma = "";
   private String khoa_ma = "";
   private String ct_ma = "";
   private String kp_ma = "";
   Map<String, Object> params = null;
   private String tungay = "";
   private String tenThuoc = "";
   private String nhomthuoc_ma = "";
   private Integer nhomthuoc_maso = null;

   @Factory("resetFormB4412")
   public void initresetFormB4412()
   {
     logger.info("init()");
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   String dmKhoXuat = "";

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Begin(join=true)
   public String init(String kho)
   {
     logger.info("init()");
     resetForm();
     this.dmKhoXuat = kho;
     return "BaoCaoDuoc_InBaoCaoKhoChinh_InBaoCaoNhapXuatTrongThang";
   }

   @End
   public void end() {}

   public void resetList()
   {
     logger.info("=============reset listttttttttttt " + this.listDmPLThuoc.size());
     if (this.listDmPLThuoc.size() > 0)
     {
       DmPhanLoaiThuoc dmplt = new DmPhanLoaiThuoc();
       dmplt = (DmPhanLoaiThuoc)this.listDmPLThuoc.get(0);
       logger.info(dmplt);
       if (!dmplt.getDmloaithuocMaso().getDmloaithuocMa().equals(this.loaihang_ma))
       {
         this.listDmPLThuoc.clear();
         setPlthuoc_ma("");
       }
     }
   }

   private String getListDVMaParamsHelp(List<String> inputs)
   {
     logger.info("Vao method getListDVMaParamsHelp ");
     logger.info("Inputs: " + inputs.toString());
     String result = "";
     for (String each : inputs) {
       if (each != "") {
         result = result + "'" + each + "',";
       }
     }
     result = result.substring(0, result.length() - 1);
     logger.info("Thoat method getListDVMaParamsHelp ");
     logger.info("Output: " + result);
     return result;
   }

   public void resetForm()
   {
     setPlthuoc_ma("");
     this.listDmPLThuoc.clear();
     this.ngaykiem = Utils.getCurrentDate();
     this.tungay = Utils.getCurrentDate();
     setLoaihang_ma("");
     setCt_ma("");
     this.resetFormB4412 = "";
     setKp_ma("");
     setInTheo("r1");
     this.tenThuoc = "";
     this.nhomthuoc_ma = "";
   }

   public void enter()
   {
     logger.info("bat dau them du lieu vao luoi");

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.plthuoc_ma.equals("")) {
       if ((this.listDmPLThuoc.size() == 0) && (this.plthuoc_ma != null))
       {
         logger.info("size list bang 0");
         Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
         DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
         this.listDmPLThuoc.add(plThuoc);
         logger.info("da add phan tu dau tien" + this.listDmPLThuoc.size());
       }
       else if ((this.listDmPLThuoc.size() > 0) && (this.plthuoc_ma != null))
       {
         logger.info("size list lon hon 0");
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
         logger.info("da add phan tu" + this.listDmPLThuoc.size());
       }
     }
     setPlthuoc_ma("");
   }

   public void deletedmPLThuoc()
   {
     logger.info("bat dau xoa , size" + this.listDmPLThuoc.size());
     this.listDmPLThuoc.remove(this.dmPLThuocSelect);
     logger.info("da xoa , size" + this.listDmPLThuoc.size());
     logger.info("ket thuc xoa");
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Inbaocaonhapxuathang";
     logger.info("Vao Method XuatReport In bao coa nhap xuat hang");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       if ((this.khoa_ma != null) && (!this.khoa_ma.equals("")))
       {
         if (this.khoa_ma.toUpperCase().equals("KCH"))
         {
           if (this.inTheo.equals("r1")) {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_gopgia.jrxml";
           } else if (this.inTheo.equals("r2")) {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_tachgia.jrxml";
           } else {
             pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_goplo.jrxml";
           }
         }
         else if (this.inTheo.equals("r1")) {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_gopgia_KL.jrxml";
         } else if (this.inTheo.equals("r2")) {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_tachgia_KL.jrxml";
         } else {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_goplo_KL.jrxml";
         }
       }
       else if (this.inTheo.equals("r1")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_gopgia_ALL.jrxml";
       } else if (this.inTheo.equals("r2")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_tachgia_ALL.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D17_BCXuatnhapton_goplo_ALL.jrxml";
       }
       logger.info("da thay file templete 5" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       logger.info("da thay file template ");
       logger.info("================= ");

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       DieuTriUtilDelegate dtDAO = DieuTriUtilDelegate.getInstance();
       this.params = new HashMap();
       this.params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       this.params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       this.params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       this.params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(strInputTonDauDate));
       c.add(5, 1);
       strInputTonDauDate = sdf.format(c.getTime());
       Date dNgayTonDau = sdf.parse(strInputTonDauDate);
       Date dTuNgay = sdf.parse(this.tungay);
       if ((dTuNgay.before(dNgayTonCuoi)) || (dTuNgay.equals(dNgayTonCuoi))) {
         this.params.put("TUNGAY", dNgayTonDau);
       } else {
         this.params.put("TUNGAY", dTuNgay);
       }
       this.params.put("DENNGAY", sdf.parse(this.ngaykiem));
       logger.info("tu ngay " + dTuNgay);
       logger.info("den ngay " + this.ngaykiem);

       String listPl = "(";
       logger.info("loai hang " + this.loaihang_ma);
       logger.info("phan loai " + this.listDmPLThuoc.size());
       logger.info("khoa " + this.khoa_ma);
       logger.info("ten thuoc " + this.tenThuoc);
       logger.info("chuong trinh " + this.ct_ma);
       logger.info("kinh phi " + this.kp_ma);
       if (!this.loaihang_ma.trim().equals(""))
       {
         this.params.put("DMLOAITHUOC_MASO", this.loaihang_maso);
         DmLoaiThuoc lthuoc = new DmLoaiThuoc();
         Object obj = dtDAO.findByMa(this.loaihang_ma, "DmLoaiThuoc", "dmloaithuocMa");
         lthuoc = (DmLoaiThuoc)obj;
         this.params.put("DTDMLOAI_TEN", lthuoc.getDmloaithuocTen());
       }
       else
       {
         this.loaihang_maso = null;
         this.params.put("DMLOAITHUOC_MASO", this.loaihang_maso);
       }
       if (!this.khoa_ma.trim().equals(""))
       {
         this.params.put("DMKHOA_MASO", this.khoa_maso);
         DmKhoa dmKhoa = (DmKhoa)dtDAO.findByMa(this.khoa_ma, "DmKhoa", "dmkhoaMa");
         this.params.put("DMKHOA_TEN", dmKhoa.getDmkhoaTen());
       }
       else
       {
         this.khoa_maso = null;
         this.params.put("DMKHOA_MASO", this.khoa_maso);
         this.params.put("DMKHOA_TEN", "TẤT CẢ KHO");
       }
       logger.info("DMKHOA_MASO " + this.khoa_maso);
       if (!this.tenThuoc.trim().equals("")) {
         this.params.put("DMTHUOC_TEN", this.tenThuoc.toUpperCase());
       } else {
         this.params.put("DMTHUOC_TEN", null);
       }
       if (!this.ct_ma.trim().equals(""))
       {
         this.params.put("DMNCT_MASO", this.ct_maso);
         DmNguonChuongTrinh nct = new DmNguonChuongTrinh();
         Object obj = dtDAO.findByMa(this.ct_ma, "DmNguonChuongTrinh", "dmnctMa");
         nct = (DmNguonChuongTrinh)obj;
         this.params.put("DTDMNGUON_TEN", nct.getDmnctTen());
       }
       else
       {
         this.ct_maso = null;
         this.params.put("DMNCT_MASO", this.ct_maso);
       }
       if (!this.kp_ma.trim().equals(""))
       {
         this.params.put("DMNGUONKINHPHIMASO", this.kp_maso);
         DmNguonKinhPhi nkp = new DmNguonKinhPhi();
         Object obj = dtDAO.findByMa(this.kp_ma, "DmNguonKinhPhi", "dmnguonkinhphiMa");
         nkp = (DmNguonKinhPhi)obj;
         this.params.put("DMKINHPHI_TEN", nkp.getDmnguonkinhphiTen());
       }
       else
       {
         this.kp_maso = null;
         this.params.put("DMNGUONKINHPHIMASO", this.kp_maso);
       }
       if (this.listDmPLThuoc.size() > 0)
       {
         setLoaihang_ma(null);
         List<String> listtemp = new ArrayList();
         for (DmPhanLoaiThuoc item : this.listDmPLThuoc) {
           listtemp.add(item.getDmphanloaithuocMa());
         }
         this.params.put("DMPHANLOAI_MA", getListDVMaParamsHelp(listtemp));
         listPl = listPl + getListDVMaParamsHelp(listtemp) + ")";
         logger.info("list phan loai " + listPl);
       }
       else
       {
         this.params.put("DMPHANLOAI_MA", null);
       }
       if (!this.nhomthuoc_ma.equals(""))
       {
         DmPhanNhomThuoc dmpnthuoc = new DmPhanNhomThuoc();
         dmpnthuoc = (DmPhanNhomThuoc)dtDAO.findByMa(this.nhomthuoc_ma, "DmPhanNhomThuoc", "dmphannhomthuocMa");
         this.params.put("NHOMTHUOCMASO", dmpnthuoc.getDmphannhomthuocMaso());
       }
       else
       {
         this.params.put("NHOMTHUOCMASO", null);
       }
       Class.forName(IConstantsRes.MYSQL_DRIVER);
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         logger.info(e.getMessage());
       }
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, this.params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Inbaocaonhapxuathang");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       logger.info("duong dan file xuat report :" + baocao1);
       logger.info("duong dan -------------------- :" + this.reportPathKC);
       this.index += 1;
       logger.info("Index :" + getIndex());
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     logger.info("Thoat Method XuatReport");
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB4412 = null;
     return "B4160_Chonmenuxuattaptin";
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihang_maso)
   {
     this.loaihang_maso = loaihang_maso;
   }

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuoc_maso)
   {
     this.plthuoc_maso = plthuoc_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getCt_maso()
   {
     return this.ct_maso;
   }

   public void setCt_maso(Integer ct_maso)
   {
     this.ct_maso = ct_maso;
   }

   public Integer getKp_maso()
   {
     return this.kp_maso;
   }

   public void setKp_maso(Integer kp_maso)
   {
     this.kp_maso = kp_maso;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihang_ma)
   {
     this.loaihang_ma = loaihang_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getCt_ma()
   {
     return this.ct_ma;
   }

   public void setCt_ma(String ct_ma)
   {
     this.ct_ma = ct_ma;
   }

   public String getKp_ma()
   {
     return this.kp_ma;
   }

   public void setKp_ma(String kp_ma)
   {
     this.kp_ma = kp_ma;
   }

   public void setInTheo(String inTheo)
   {
     this.inTheo = inTheo;
   }

   public String getInTheo()
   {
     return this.inTheo;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setNgaykiem(String ngaykiem)
   {
     this.ngaykiem = ngaykiem;
   }

   public String getNgaykiem()
   {
     return this.ngaykiem;
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getTenThuoc()
   {
     return this.tenThuoc;
   }

   public void setTenThuoc(String tenThuoc)
   {
     this.tenThuoc = tenThuoc;
   }

   public Integer getNhomthuoc_maso()
   {
     return this.nhomthuoc_maso;
   }

   public void setNhomthuoc_maso(Integer nhomthuoc_maso)
   {
     this.nhomthuoc_maso = nhomthuoc_maso;
   }

   public String getNhomthuoc_ma()
   {
     return this.nhomthuoc_ma;
   }

   public void setNhomthuoc_ma(String nhomthuoc_ma)
   {
     this.nhomthuoc_ma = nhomthuoc_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.BaoCaoXuatNhapTon

 * JD-Core Version:    0.7.0.1

 */