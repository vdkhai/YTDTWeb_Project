 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
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

 @Name("B4131_Inbaocaonhapxuattrongngay")
 @Scope(ScopeType.CONVERSATION)
 public class Inbaocaonhapxuattrongngay
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
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB4131 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private Integer khoa_maso = null;
   private Integer lthuoc_maso = null;
   private Integer nct_maso = null;
   private Integer nkp_maso = null;
   private Integer plthuoc_maso = null;
   private String khoa_ma = "";
   private String nct_ma = "";
   private String nkp_ma = "";
   private String lthuoc_ma = null;
   private String plthuoc_ma = null;
   private boolean chon = false;
   String dmKhoXuat = "";

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetFormB4131")
   public void resetFormB4131Func()
   {
     this.log.info("init()", new Object[0]);
     resetForm();
     this.ngayhientai = Utils.getCurrentDate();
   }

   @Begin(join=true)
   public String init(String kho)
   {
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_InBaoCaoNhapXuatTrongNgay";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB4131 = null;
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.resetFormB4131 = "";
     this.log.info("bat dau ham reset", new Object[0]);
     setChon(false);
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.lthuoc_maso = null;
     this.nct_maso = null;
     this.nkp_maso = null;
     this.plthuoc_maso = null;
     this.nct_ma = "";
     this.nkp_ma = "";
     this.lthuoc_ma = null;
     this.plthuoc_ma = null;
     this.log.info("ket thuc ham reset", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Inbaocaonhapxuattrongngay";
     this.log.info("Vao Method XuatReport bao cao nhap xuat trong ngay", new Object[0]);
     String baocao1 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D04_tinhhinhnhapxuat.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("NGAY", this.ngayhientai);
       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       Calendar c = Calendar.getInstance();
       c.setTime(sdf.parse(strInputTonDauDate));
       c.add(5, 1);
       strInputTonDauDate = sdf.format(c.getTime());
       Date dNgayTonDau = sdf.parse(strInputTonDauDate);
       Date dTuNgay = sdf.parse(this.tungay);
       if ((dTuNgay.before(dNgayTonCuoi)) || (dTuNgay.equals(dNgayTonCuoi))) {
         params.put("TUNGAYDATE", dNgayTonDau);
       } else {
         params.put("TUNGAYDATE", dTuNgay);
       }
       params.put("DENNGAYDATE", sdf.parse(this.denngay));
       String boqua = null;
       if (this.chon == true)
       {
         boqua = "1";
         params.put("BOQUA", boqua);
       }
       else
       {
         params.put("BOQUA", boqua);
       }
       this.log.info("BOQUA :" + boqua, new Object[0]);
       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       this.log.info("set gia tri cho loai thuoc :" + this.lthuoc_ma, new Object[0]);
       if (this.lthuoc_ma.trim().equals(""))
       {
         this.lthuoc_maso = null;
         params.put("LOAITHUOCMASO", this.lthuoc_maso);
       }
       else
       {
         DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
         Object objLThuoc = dtUtils.findByMa(this.lthuoc_ma, "DmLoaiThuoc", "dmloaithuocMa");
         dmloaithuoc = (DmLoaiThuoc)objLThuoc;
         params.put("LOAITHUOC", dmloaithuoc.getDmloaithuocTen());
         params.put("LOAITHUOCMASO", this.lthuoc_maso);
       }
       this.log.info("set gia tri cho phan loai thuoc :" + this.plthuoc_ma, new Object[0]);
       if (this.plthuoc_ma.trim().equals(""))
       {
         this.plthuoc_maso = null;
         params.put("PLMASO", this.plthuoc_maso);
       }
       else
       {
         DmPhanLoaiThuoc dmplthuoc = new DmPhanLoaiThuoc();
         Object objLThuoc = dtUtils.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
         dmplthuoc = (DmPhanLoaiThuoc)objLThuoc;
         params.put("PHANLOAI", dmplthuoc.getDmphanloaithuocTen());
         params.put("PLMASO", this.plthuoc_maso);
       }
       this.log.info("set gia tri cho kho :" + this.khoa_ma, new Object[0]);
       if (this.khoa_ma.trim().equals(""))
       {
         this.khoa_maso = null;
         params.put("KHOMASO", this.khoa_maso);
       }
       else
       {
         DmKhoa dmkhoa = new DmKhoa();
         Object objdmkhoa = dtUtils.findByMa(this.khoa_ma, "DmKhoa", "dmkhoaMa");
         dmkhoa = (DmKhoa)objdmkhoa;
         params.put("THUOCKHO", dmkhoa.getDmkhoaTen());
         params.put("KHOMASO", this.khoa_maso);
       }
       this.log.info("set gia tri cho nguon kinh phi", new Object[0]);
       if (this.nkp_ma.trim().equals(""))
       {
         this.nkp_maso = null;
         params.put("NKPMASO", this.nkp_maso);
       }
       else
       {
         DmNguonKinhPhi dmnkp = new DmNguonKinhPhi();
         Object objdmnkp = dtUtils.findByMa(this.nkp_ma, "DmNguonKinhPhi", "dmnguonkinhphiMa");
         dmnkp = (DmNguonKinhPhi)objdmnkp;
         params.put("NKPMASO", this.nkp_maso);
       }
       this.log.info("set gia tri cho nguon chuong trinh", new Object[0]);
       if (this.nct_ma.trim().equals(""))
       {
         this.nct_maso = null;
         params.put("NCTMASO", this.nct_maso);
       }
       else
       {
         DmNguonChuongTrinh dmnct = new DmNguonChuongTrinh();
         Object objdmnct = dtUtils.findByMa(this.nct_ma, "DmNguonChuongTrinh", "dmnctMa");
         dmnct = (DmNguonChuongTrinh)objdmnct;
         params.put("NGUON", dmnct.getDmnctTen());
         params.put("NCTMASO", this.nct_maso);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Inbaocaonhapxuattrongngay");
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

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getLthuoc_maso()
   {
     return this.lthuoc_maso;
   }

   public void setLthuoc_maso(Integer lthuoc_maso)
   {
     this.lthuoc_maso = lthuoc_maso;
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

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuoc_maso)
   {
     this.plthuoc_maso = plthuoc_maso;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
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

   public void setChon(boolean chon)
   {
     this.chon = chon;
   }

   public boolean isChon()
   {
     return this.chon;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Inbaocaonhapxuattrongngay

 * JD-Core Version:    0.7.0.1

 */