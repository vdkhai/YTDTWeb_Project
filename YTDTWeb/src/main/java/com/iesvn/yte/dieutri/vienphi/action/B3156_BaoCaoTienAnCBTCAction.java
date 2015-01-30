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
 @Name("B3156_BaoCaoTienAnCBTC")
 public class B3156_BaoCaoTienAnCBTCAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String thangbc;
   private String nambc;
   private String tungay;
   private String denngay;
   private String ngayhientai;
   private String noinhanbc;
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
   private String strBcTienAnCBTC;
   @Logger
   private Log log;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   @Factory("strBcTienAnCBTC")
   public void init()
   {
     this.tungay = "";
     this.denngay = (this.ngayhientai = this.sdf.format(new Date()));
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());
     this.thangbc = ("" + (cal.get(2) + 1));
     this.nambc = ("" + cal.get(1));
     this.noinhanbc = "0";
     this.strBcTienAnCBTC = "";
   }

   public String initFromMenu()
   {
     return "/B3_Vienphi/TaiKhoa/B3156_BaoCaoTienAnCBTC.xhtml";
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "baocaotienanCBTC";
     this.log.info("Vao Method XuatReport tien an CBTC", new Object[0]);
     try
     {
       String baocao1 = null;
       Integer DOI_TUONG_AN_MA_SO = new Integer("3");

       Integer LOAI_AN_MA_SO = new Integer("1");
       Integer LOAI_AN2_MA_SO = new Integer("2");
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = "";
       if (this.noinhanbc.equals("0")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/BaoCaoTienAnCBTC_SoTaiChinh.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/BaoCaoTienAnCBTC_Cantin.jrxml";
       }
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       Map<String, Object> params = new HashMap();

       params.put("thangbaocao", this.thangbc);
       params.put("nambaocao", this.nambc);
       params.put("tungay", this.sdf.parse(this.tungay));
       params.put("denngay", this.sdf.parse(this.denngay));
       params.put("doituong_maso", DOI_TUONG_AN_MA_SO);
       params.put("loaian_maso", LOAI_AN_MA_SO);
       if (this.noinhanbc.equals("0")) {
         params.put("loaian2_maso", LOAI_AN2_MA_SO);
       }
       params.put("donvibaocao", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL + "\n" + IConstantsRes.REPORT_KHOA_DINH_DUONG);
       params.put("mucan", new Long(IConstantsRes.MUC_TIEN_AN_CAN_BO_TRUNG_CAO));
       params.put("subtitle", IConstantsRes.REPORT_KHOA_DINH_DUONG + " " + IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);




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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "baocaotienanCBTC");
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

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getReportTypeVP()
   {
     return this.reportTypeVP;
   }

   public void setReportTypeVP(String reportTypeVP)
   {
     this.reportTypeVP = reportTypeVP;
   }

   public String getThangbc()
   {
     return this.thangbc;
   }

   public void setThangbc(String thangbc)
   {
     this.thangbc = thangbc;
   }

   public String getNambc()
   {
     return this.nambc;
   }

   public void setNambc(String nambc)
   {
     this.nambc = nambc;
   }

   public String getNoinhanbc()
   {
     return this.noinhanbc;
   }

   public void setNoinhanbc(String noinhanbc)
   {
     this.noinhanbc = noinhanbc;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3156_BaoCaoTienAnCBTCAction

 * JD-Core Version:    0.7.0.1

 */