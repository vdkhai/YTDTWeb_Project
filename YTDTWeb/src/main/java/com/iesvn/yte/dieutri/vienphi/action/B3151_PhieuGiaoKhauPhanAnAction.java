 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
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
 @Name("B3151_PhieuGiaoKhauPhanAn")
 public class B3151_PhieuGiaoKhauPhanAnAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String khoaMaso;
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
   private String strPgkpa;
   @Logger
   private Log log;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   @Factory("strPgkpa")
   public void init()
   {
     this.ngayan = this.sdf.format(new Date());
     this.strPgkpa = "";
   }

   public String initFromMenu()
   {
     return "/B3_Vienphi/TaiKhoa/B3151_PhieuGiaoKhauPhanAn.xhtml";
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "phieugiaokhauphanan";
     this.log.info("Vao Method XuatReport phieu giao khau phan an", new Object[0]);
     try
     {
       String baocao1 = null;
       Integer LOAI_AN_MA_SO = new Integer("1");

       this.log.info("bat dau method XuatReport ", new Object[0]);

       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       DmKhoa dmKhoa = (DmKhoa)dtUtil.findByMaSo(new Integer(this.khoaMaso), "DmKhoa", "dmkhoaMaso");

       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/PhieuGiaoKhauPhanAn.jrxml";
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();

       params.put("ngayan", this.sdf.parse(this.ngayan));
       params.put("khoamaso", dmKhoa.getDmkhoaMaso());
       params.put("khoaten", dmKhoa.getDmkhoaTen());
       params.put("loaianmaso", LOAI_AN_MA_SO);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Phieugiaokhauphanan");
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

   public String getKhoaMaso()
   {
     return this.khoaMaso;
   }

   public void setKhoaMaso(String khoaMaso)
   {
     this.khoaMaso = khoaMaso;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3151_PhieuGiaoKhauPhanAnAction

 * JD-Core Version:    0.7.0.1

 */