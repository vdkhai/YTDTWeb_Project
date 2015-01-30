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
 @Name("B3144_Xuatphieubangiao")
 public class XuatPhieuBanGiaoToTietCheAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String ngayan;
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
   private String strXuatPbgttc;
   @Logger
   private Log log;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   @Factory("strXuatPbgttc")
   public void init()
   {
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());
     cal.add(5, 1);
     this.ngayan = this.sdf.format(cal.getTime());
     this.strXuatPbgttc = "";
   }

   public String initFromMenu()
   {
     return "/B3_Vienphi/TaiKhoa/B3144_Xuatphieubangiaokhauphanan.xhtml";
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "xuatphieubangiaokhauphanan";
     this.log.info("Vao Method XuatReport phieu ban giao", new Object[0]);
     try
     {
       String baocao1 = null;

       this.log.info("bat dau method XuatReport ", new Object[0]);

       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3141_PhieuBanGiaoKhauPhanAnToTietChe.jrxml";
       String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3141_PhieuBanGiaoKhauPhanAnToTietChe_subreport0.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3141_PhieuBanGiaoKhauPhanAnToTietChe_subreport1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3141_PhieuBanGiaoKhauPhanAnToTietChe_subreport2.jrxml";


       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);

       Map<String, Object> params = new HashMap();

       params.put("ngayan", this.sdf.parse(this.ngayan));
       params.put("sub0", sub0Report);
       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Phieubangiaokhauphanan");
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.XuatPhieuBanGiaoToTietCheAction

 * JD-Core Version:    0.7.0.1

 */