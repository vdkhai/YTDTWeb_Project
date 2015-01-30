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
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3341_DSBenhnhanchothanhtoan")
 @Synchronized(timeout=6000000L)
 public class B3341_DSBenhnhanchothanhtoan
   implements Serializable
 {
   private String tungay;
   private String denngay;
   private String ngayhientai;
   private String makhoa;
   private Integer masokhoa;
   private String madoituong;
   private Integer masodoituong;
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
   private String resetFormB3341 = null;
   private static Logger log = Logger.getLogger(B3341_DSBenhnhanchothanhtoan.class);

   @Factory("resetFormB3341")
   public void init()
   {
     log.info("init()");
     resetForm();
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     this.tungay = sdf.format(new Date());
     this.denngay = sdf.format(new Date());
     this.ngayhientai = sdf.format(new Date());
     this.makhoa = (this.madoituong = "");
     this.resetFormB3341 = "";
     this.masokhoa = (this.masodoituong = new Integer("0"));
   }

   int index = 0;

   public String thuchienAction()
   {
     log.info("Begin thuchienAction(), makhoa: " + this.makhoa);
     this.reportTypeVP = "B3341_DSBenhnhanchothanhtoan";
     log.info("Vao Method XuatReport B3341_DSBenhnhanchothanhtoan");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/B3341_danhsachbenhnhanchothanhtoan.jrxml";





       log.info("da thay file templete" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();

       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("tuNgay", sdf.parse(this.tungay));
       params.put("denNgay", sdf.parse(this.denngay));

       params.put("khoaMaso", this.masokhoa);
       params.put("doituongMaso", this.masodoituong);


       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "B3341_DSBenhnhanchothanhtoan");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");

       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     return "B3360_Chonmenuxuattaptin";
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

   public String getMakhoa()
   {
     return this.makhoa;
   }

   public void setMakhoa(String makhoa)
   {
     this.makhoa = makhoa;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public Integer getMasokhoa()
   {
     return this.masokhoa;
   }

   public void setMasokhoa(Integer masokhoa)
   {
     this.masokhoa = masokhoa;
   }

   public String getMadoituong()
   {
     return this.madoituong;
   }

   public void setMadoituong(String madoituong)
   {
     this.madoituong = madoituong;
   }

   public Integer getMasodoituong()
   {
     return this.masodoituong;
   }

   public void setMasodoituong(Integer masodoituong)
   {
     this.masodoituong = masodoituong;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3341_DSBenhnhanchothanhtoan

 * JD-Core Version:    0.7.0.1

 */