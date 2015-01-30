 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuBaoAnDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
 import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
 import com.iesvn.yte.entity.DmKhoa;
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
 @Name("B3142_Xuatphieubaoan")
 public class XuatPhieuBaoAnAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String khoaMaso;
   private String ngayan;
   private String loaian1Maso;
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
   private String strXuatPba;
   @Logger
   private Log log;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   @Factory("strXuatPba")
   public void init()
   {
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());
     cal.add(5, 1);
     this.ngayan = this.sdf.format(cal.getTime());
     this.khoaMaso = "";
     this.loaian1Maso = "";
     this.strXuatPba = "";
   }

   public String initFromMenu()
   {
     return "/B3_Vienphi/TaiKhoa/B3142_Xuatphieubaoan.xhtml";
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "xuatphieubaoan";
     this.log.info("Vao Method XuatReport phieu bao an, laoian1Maso = " + this.loaian1Maso + ", khoaMaso" + this.khoaMaso, new Object[0]);

     DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
     DmKhoa dmKhoa = (DmKhoa)dtUtil.findByMaSo(new Integer(this.khoaMaso), "DmKhoa", "dmkhoaMaso");
     this.log.info("Khoa ten = " + dmKhoa != null ? dmKhoa.getDmkhoaTen() : "", new Object[0]);

     DtDmLoaiAn dmLoaian = (DtDmLoaiAn)dtUtil.findByMaSo(new Integer(this.loaian1Maso), "DtDmLoaiAn", "dtdmlaMaso");

     PhieuBaoAnDelegate pbaDel = PhieuBaoAnDelegate.getInstance();
     try
     {
       PhieuBaoAn pbaTmp = pbaDel.findByKhoaNgayAn(dmKhoa.getDmkhoaMa(), this.sdf.parse(this.ngayan));

       String baocao1 = null;

       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + (this.loaian1Maso.equals("3") ? "vienphi/B3140_Phieubaoan_suaduongnhi.jrxml" : "vienphi/B3140_Phieubaoan_loaiankhac.jrxml");




       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       Map<String, Object> params = new HashMap();
       params.put("khoa", dmKhoa != null ? dmKhoa.getDmkhoaTen() : "");
       params.put("khoamaso", dmKhoa != null ? dmKhoa.getDmkhoaMaso() : new Integer(0));
       params.put("ngayan", this.sdf.parse(this.ngayan));
       params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       if (dmLoaian != null)
       {
         params.put("loaianmaso", dmLoaian.getDtdmlaMaso());
         params.put("loaian", dmLoaian.getDtdmlaTen());
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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Xuatphieubaoan");
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

   public String getLoaian1Maso()
   {
     return this.loaian1Maso;
   }

   public void setLoaian1Maso(String loaian1Maso)
   {
     this.loaian1Maso = loaian1Maso;
   }

   public String getKhoaMaso()
   {
     return this.khoaMaso;
   }

   public void setKhoaMaso(String khoaMaso)
   {
     this.khoaMaso = khoaMaso;
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.XuatPhieuBaoAnAction

 * JD-Core Version:    0.7.0.1

 */