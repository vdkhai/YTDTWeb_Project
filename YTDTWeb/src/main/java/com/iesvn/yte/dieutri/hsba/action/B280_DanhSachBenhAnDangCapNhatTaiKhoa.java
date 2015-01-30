 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
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

 @Name("B280_DanhSachBenhAnDangCapNhatTaiKhoa")
 @Scope(ScopeType.CONVERSATION)
 public class B280_DanhSachBenhAnDangCapNhatTaiKhoa
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @Logger
   private Log log;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String resetFormB280 = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private Integer khoaMaso = null;
   private String khoaMa = "";
   private int index = 0;

   @Factory("resetFormB280")
   public String init()
   {
     resetForm();
     return "/B2_Dieutri/B280_DanhSachBenhAnDangCapNhatTaiKhoa.xhtml";
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB280 = null;
     return "B260_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.log.info("Bat dau ham reset form", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.resetFormB280 = "";
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.khoaMaso = null;
     this.khoaMa = "";
     this.log.info("ket thuc ham reset form", new Object[0]);
   }

   public void XuatReport()
   {
     this.loaiBCDT = "DanhSachBenhAnDangCapNhatTaiKhoa";
     this.log.info("Vao Method XuatReport DanhSachBenhAnDangCapNhatTaiKhoa", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport, ma so khoa =  " + this.khoaMaso, new Object[0]);

       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B280_Danhsachbenhandangcapnhattaikhoa.jrxml";



       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);

       Map<String, Object> params = new HashMap();

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("donvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("KHOAMASO", this.khoaMaso);
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "DanhSachBenhAnDangCapNhatTaiKhoa");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- : " + this.duongdanStrDT, new Object[0]);
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

   public Integer getKhoaMaso()
   {
     return this.khoaMaso;
   }

   public void setKhoaMaso(Integer khoaMaso)
   {
     this.khoaMaso = khoaMaso;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B280_DanhSachBenhAnDangCapNhatTaiKhoa

 * JD-Core Version:    0.7.0.1

 */