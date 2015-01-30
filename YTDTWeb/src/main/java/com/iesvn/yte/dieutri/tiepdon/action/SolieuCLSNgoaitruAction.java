 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
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
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3322_Solieuclsngoaitru")
 @Synchronized(timeout=6000000L)
 public class SolieuCLSNgoaitruAction
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @Logger
   private Log log;
   private String thang;
   private String nam;
   private String tuNgay;
   private String denNgay;
   private String khoaThuchien;
   private String loaiClsMa;
   private String pbClsMa;
   private String khoaTen;
   private String clsTen;
   private String reportFinished;
   private String reportFileName;
   private String ngayhientai;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   @Out(required=false)
   @In(required=false)
   private String loaiCLSNgoaiTruHoacNoiTru = null;

   @Begin(join=true)
   public String init(String loaiCLSBC)
   {
     this.log.info("Begin init()", new Object[0]);

     this.loaiCLSNgoaiTruHoacNoiTru = loaiCLSBC;

     this.reportFinished = "";
     this.ngayhientai = Utils.getCurrentDate();
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.thang = String.valueOf(currentDate.getMonth() + 1);
     this.nam = String.valueOf(currentDate.getYear() + 1900);
     this.tuNgay = sdf.format(currentDate);
     this.denNgay = sdf.format(currentDate);
     this.log.info("End init()", new Object[0]);
     return "BaoCaoVienPhi_HoSoBaoCao_SoLieuCLSNgoaiTru";
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

   @End
   public void endFunc() {}

   public String thuchienAction()
   {
     XuatReport();

     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "V06_Chiphicanlamsangbaravien";
     this.log.info("Vao Method XuatReport bao cao BHYT noi tru", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if ("NgoaiTru".equals(this.loaiCLSNgoaiTruHoacNoiTru)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V06_Chiphicanlamsangbaravien.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V07_Chiphicanlamsangnoitru.jrxml";
       }
       this.log.info("da thay file templete " + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();


       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       SimpleDateFormat sqlFormatter = new SimpleDateFormat("yyyy-MM-dd");
       this.log.info("begin set params cho sql", new Object[0]);
       this.log.info("khoaThuchienSql: " + this.khoaThuchien, new Object[0]);
       this.log.info("loaiClsSql: " + this.loaiClsMa, new Object[0]);
       this.log.info("pbClsMaSql: " + this.pbClsMa, new Object[0]);
       this.log.info("tuNgay: " + this.tuNgay, new Object[0]);
       this.log.info("denNgay: " + this.denNgay, new Object[0]);
       this.log.info("khoaTen: " + this.khoaTen, new Object[0]);
       this.log.info("clsTen: " + this.clsTen, new Object[0]);

       params.put("khoaThuchienSql", this.khoaThuchien);
       params.put("loaiClsSql", this.loaiClsMa);
       params.put("pbClsMaSql", this.pbClsMa);
       if ((this.khoaThuchien == null) || (this.khoaThuchien.equals(""))) {
         this.khoaThuchien = null;
       }
       if ((this.loaiClsMa == null) || (this.loaiClsMa.equals(""))) {
         this.loaiClsMa = null;
       }
       params.put("khoathuchienPr", this.khoaTen);
       params.put("clsPr", this.clsTen);

       Date tungaySql = null;
       Date denngaySql = null;
       if ((this.tuNgay != null) && (!this.tuNgay.equals("")))
       {
         Date tnTmp = formatter.parse(this.tuNgay);
         String tmp = sqlFormatter.format(tnTmp);
         this.log.info("tungaySql: " + tmp, new Object[0]);
         sqlFormatter = new SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATETIME);
         tungaySql = sqlFormatter.parse(tmp + " 00:00:00");
         params.put("tungaySql", tungaySql);
         this.log.info("tungaySqlFormat: " + sqlFormatter.format(tungaySql), new Object[0]);
       }
       if ((this.denNgay != null) && (!this.denNgay.equals("")))
       {
         Date tnTmp = formatter.parse(this.denNgay);
         sqlFormatter = new SimpleDateFormat("yyyy-MM-dd");
         String tmp = sqlFormatter.format(tnTmp);
         this.log.info("dengaySql: " + tmp, new Object[0]);
         sqlFormatter = new SimpleDateFormat(IConstantsRes.SQL_FORMAT_DATETIME);
         denngaySql = sqlFormatter.parse(tmp + " 23:59:59");
         params.put("denngaySql", denngaySql);
         this.log.info("denngaySqlFormat: " + sqlFormatter.format(denngaySql), new Object[0]);
       }
       this.log.info("end set params cho sql", new Object[0]);
       this.log.info("begin set params cho report", new Object[0]);
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("TUNGAY", formatter.parse(this.tuNgay));
       params.put("DENNGAY", formatter.parse(this.denNgay));
       params.put("ngayIn", this.ngayhientai);
       String tmp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
       String hhMm = tmp.substring(11);
       params.put("gioIn", hhMm);
       Calendar cal = Calendar.getInstance();
       params.put("ngay", "" + cal.get(5));
       params.put("thang", "" + (cal.get(2) + 1));
       params.put("nam", "" + cal.get(1));

       this.log.info("End set params cho report", new Object[0]);

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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "V06_Chiphicanlamsangbaravien");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathVP, new Object[0]);
       this.index += 1;
       this.log.info("Index :" + this.index, new Object[0]);
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

   public String troVe()
   {
     try
     {
       this.log.info("***** troVe()", new Object[0]);
       this.khoaTen = "";
       this.clsTen = "";
       return "B3322_Solieuclsngoaitru";
     }
     catch (Exception e)
     {
       this.log.info("***** End exception = " + e, new Object[0]);
     }
     return null;
   }

   public void nhaplai()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.tuNgay = sdf.format(currentDate);
     this.denNgay = sdf.format(currentDate);
     this.thang = String.valueOf(currentDate.getMonth() + 1);
     this.nam = String.valueOf(currentDate.getYear() + 1900);
     this.khoaThuchien = "";
     this.loaiClsMa = null;
     this.pbClsMa = null;
     this.reportFileName = "";
     this.reportFinished = "";
     this.khoaTen = "";
     this.clsTen = "";
   }

   public String getThang()
   {
     return this.thang;
   }

   public void setThang(String thang)
   {
     this.thang = thang;
   }

   public String getNam()
   {
     return this.nam;
   }

   public void setNam(String nam)
   {
     this.nam = nam;
   }

   public String getTuNgay()
   {
     return this.tuNgay;
   }

   public void setTuNgay(String tuNgay)
   {
     this.tuNgay = tuNgay;
   }

   public String getDenNgay()
   {
     return this.denNgay;
   }

   public void setDenNgay(String denNgay)
   {
     this.denNgay = denNgay;
   }

   public String getKhoaThuchien()
   {
     return this.khoaThuchien;
   }

   public void setKhoaThuchien(String khoaThuchien)
   {
     this.khoaThuchien = khoaThuchien;
   }

   public String getLoaiClsMa()
   {
     return this.loaiClsMa;
   }

   public void setLoaiClsMa(String loaiClsMa)
   {
     this.loaiClsMa = loaiClsMa;
   }

   public String getPbClsMa()
   {
     return this.pbClsMa;
   }

   public void setPbClsMa(String pbClsMa)
   {
     this.pbClsMa = pbClsMa;
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public String getReportFileName()
   {
     return this.reportFileName;
   }

   public void setReportFileName(String reportFileName)
   {
     this.reportFileName = reportFileName;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
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

   public String getKhoaTen()
   {
     return this.khoaTen;
   }

   public void setKhoaTen(String khoaTen)
   {
     this.khoaTen = khoaTen;
   }

   public String getClsTen()
   {
     return this.clsTen;
   }

   public void setClsTen(String clsTen)
   {
     this.clsTen = clsTen;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.SolieuCLSNgoaitruAction

 * JD-Core Version:    0.7.0.1

 */