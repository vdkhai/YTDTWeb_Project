 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.SESSION)
 @Name("B3141_Phieugiaoban")
 public class PhieuGiaoBanAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String ngayan;
   private String chutri;
   private String thuky;
   private String thamdu;
   private String vang;
   private String ketluan;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strXuatPgbkdd;
   @Logger
   private Log log;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   @Factory("strXuatPgbkdd")
   public void init()
   {
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());

     this.ngayan = this.sdf.format(cal.getTime());
     this.chutri = (this.thuky = this.thamdu = this.vang = this.ketluan = "");
     this.strXuatPgbkdd = "";
   }

   public String initFromMenu()
   {
     return "/B3_Vienphi/TaiKhoa/B3141_Phieugiaoban.xhtml";
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "PhieugiaobankhoaDD";
     this.log.info("Vao Method XuatReport phieu giao ban khoa dinh duong", new Object[0]);
     try
     {
       String baocao1 = null;


       Integer LOAIAN_KP_MASO = new Integer("1");
       Integer LOAIAN_SPDD_MASO = new Integer("2");
       Integer LOAIAN_SDN_MASO = new Integer("3");


       Integer LOAIAN2_SUANT_MASO = new Integer("6");
       Integer LOAIAN2_SUASTEP1_MASO = new Integer("7");


       Integer CDOAN_LAT_MASO = new Integer("1");
       Integer CDOAN_DD_MASO = new Integer("2");
       Integer CDOAN_STM_MASO = new Integer("3");
       Integer CDOAN_THUONG_MASO = new Integer("4");
       Integer CDOAN_CM_MASO = new Integer("5");
       Integer CDOAN_BDUONG_MASO = new Integer("6");


       Integer GIOAN_6H_MASO = new Integer("1");
       Integer GIOAN_11H_MASO = new Integer("2");
       Integer GIOAN_16H_MASO = new Integer("3");
       Integer GIOAN_20H_MASO = new Integer("4");
       this.log.info("bat dau method XuatReport, ngay an = " + this.ngayan, new Object[0]);

       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3142_GiaoBanKhoaDinhDuong.jrxml";
       String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3142_GiaoBanKhoaDinhDuong_subreport0.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3142_GiaoBanKhoaDinhDuong_subreport1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3142_GiaoBanKhoaDinhDuong_subreport2.jrxml";
       String sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3142_GiaoBanKhoaDinhDuong_subreport3.jrxml";
       String sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3142_GiaoBanKhoaDinhDuong_subreport4.jrxml";


       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);
       JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);

       Map<String, Object> params = new HashMap();

       params.put("ngayan", this.sdf.parse(this.ngayan));
       params.put("chutri", this.chutri);
       params.put("thuky", this.thuky);
       params.put("thamdu", this.thamdu);
       params.put("vang", this.vang);
       params.put("ketluan", this.ketluan);

       params.put("loaian_kp_maso", LOAIAN_KP_MASO);
       params.put("loaian_spdd_maso", LOAIAN_SPDD_MASO);
       params.put("loaian_sdn_maso", LOAIAN_SDN_MASO);

       params.put("loaian2_suant_maso", LOAIAN2_SUANT_MASO);
       params.put("loaian2_suastep1_maso", LOAIAN2_SUASTEP1_MASO);

       params.put("cdoan_lat_maso", CDOAN_LAT_MASO);
       params.put("cdoan_dd_maso", CDOAN_DD_MASO);
       params.put("cdoan_stm_maso", CDOAN_STM_MASO);
       params.put("cdoan_thuong_maso", CDOAN_THUONG_MASO);
       params.put("cdoan_cm_maso", CDOAN_CM_MASO);
       params.put("cdoan_bduong_maso", CDOAN_BDUONG_MASO);

       params.put("gioan_6h_maso", GIOAN_6H_MASO);
       params.put("gioan_11h_maso", GIOAN_11H_MASO);
       params.put("gioan_16h_maso", GIOAN_16H_MASO);
       params.put("gioan_20h_maso", GIOAN_20H_MASO);

       params.put("sub0", sub0Report);
       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("sub3", sub3Report);
       params.put("sub4", sub4Report);
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
         this.log.info("ERORR : " + e.toString(), new Object[0]);
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "PhieugiaobankhoaDD");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathVP, new Object[0]);
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

   public String getNgayan()
   {
     return this.ngayan;
   }

   public void setNgayan(String ngayan)
   {
     this.ngayan = ngayan;
   }

   public String getReportTypeVP()
   {
     return this.reportTypeVP;
   }

   public void setReportTypeVP(String reportTypeVP)
   {
     this.reportTypeVP = reportTypeVP;
   }

   public String getChutri()
   {
     return this.chutri;
   }

   public void setChutri(String chutri)
   {
     this.chutri = chutri;
   }

   public String getThuky()
   {
     return this.thuky;
   }

   public void setThuky(String thuky)
   {
     this.thuky = thuky;
   }

   public String getThamdu()
   {
     return this.thamdu;
   }

   public void setThamdu(String thamdu)
   {
     this.thamdu = thamdu;
   }

   public String getVang()
   {
     return this.vang;
   }

   public void setVang(String vang)
   {
     this.vang = vang;
   }

   public String getKetluan()
   {
     return this.ketluan;
   }

   public void setKetluan(String ketluan)
   {
     this.ketluan = ketluan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.PhieuGiaoBanAction

 * JD-Core Version:    0.7.0.1

 */