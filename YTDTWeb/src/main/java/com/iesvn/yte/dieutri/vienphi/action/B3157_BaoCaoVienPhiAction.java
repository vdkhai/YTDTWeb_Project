 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
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
 @Name("B3157_BaoCaoVienPhi")
 public class B3157_BaoCaoVienPhiAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String tungay;
   private String denngay;
   private String ngayhientai;
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
   private String strBcVienPhi;
   @Logger
   private Log log;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   @Factory("strBcVienPhi")
   public void init()
   {
     this.tungay = "";
     this.denngay = (this.ngayhientai = this.sdf.format(new Date()));
     this.strBcVienPhi = "";
   }

   public String initFromMenu()
   {
     return "/B3_Vienphi/TaiKhoa/B3157_BaoCaoVienPhi.xhtml";
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "baocaovienphi";
     this.log.info("Vao Method XuatReport bao cao vien phi dinh duong", new Object[0]);
     try
     {
       String baocao1 = null;
       Integer DOI_TUONG_BENH_VIEN_MA_SO = new Integer("1");


       Integer DOI_TUONG_NHA_NUOI_MA_SO = new Integer("2");
       Integer DOI_TUONG_TRUNG_CAO_MA_SO = new Integer("3");
       Integer DOI_TUONG_DONG_TIEN_MA_SO = new Integer("4");

       Integer MUC_AN_30_MA_SO = new Integer("1");
       Integer MUC_AN_60_MA_SO = new Integer("2");

       Integer DONG_THEM_10_MA_SO = new Integer("1");
       Integer DONG_THEM_20_MA_SO = new Integer("2");
       Integer DONG_THEM_30_MA_SO = new Integer("3");
       Integer DONG_THEM_40_MA_SO = new Integer("4");

       this.log.info("bat dau method XuatReport ", new Object[0]);

       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/BaoCaoVienPhi_DinhDuong.jrxml";


       String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/BaoCaoVienPhi_DinhDuong_subreport0.jrxml";


       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/BaoCaoVienPhi_DinhDuong_subreport1.jrxml";



       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);

       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);


       Map<String, Object> params = new HashMap();

       params.put("tungay", this.sdf.parse(this.tungay));
       params.put("denngay", this.sdf.parse(this.denngay));
       params.put("donvibaocao", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL + "\n" + IConstantsRes.REPORT_KHOA_DINH_DUONG);

       params.put("doituong_benhvien_maso", DOI_TUONG_BENH_VIEN_MA_SO);
       params.put("doituong_nhanuoi_maso", DOI_TUONG_NHA_NUOI_MA_SO);
       params.put("doituong_trungcao_maso", DOI_TUONG_TRUNG_CAO_MA_SO);
       params.put("doituong_dongtien_maso", DOI_TUONG_DONG_TIEN_MA_SO);

       params.put("mucan30_maso", MUC_AN_30_MA_SO);
       params.put("mucan60_maso", MUC_AN_60_MA_SO);

       params.put("dongthem10_maso", DONG_THEM_10_MA_SO);
       params.put("dongthem20_maso", DONG_THEM_20_MA_SO);
       params.put("dongthem30_maso", DONG_THEM_30_MA_SO);
       params.put("dongthem40_maso", DONG_THEM_40_MA_SO);

       params.put("sub0", sub0Report);
       params.put("sub1", sub1Report);

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

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "baocaovienphi");



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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3157_BaoCaoVienPhiAction

 * JD-Core Version:    0.7.0.1

 */