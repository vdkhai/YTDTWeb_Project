 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
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

 @Name("B237_Sochuyenvien")
 @Scope(ScopeType.CONVERSATION)
 public class Sochuyenvien
   implements Serializable
 {
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
   @Out(required=false)
   @In(required=false)
   private String resetFormB237 = null;
   private int index = 0;
   private String ngayhientai = "";
   private String tungaystr = "";
   private String denngaystr = "";
   private String lydo_ma = "";
   private String tainan_ma = "";
   private String benhICD_ma = "";
   private String khoa_ma = "";
   private String benhvien_ma = "";
   private Integer lydo_maso = null;
   private Integer tainan_maso = null;
   private Integer benhICD_maso = null;
   private Integer khoa_maso = null;
   private Integer benhvien_maso = null;

   @Factory("resetFormB237")
   public void initresetFormB237()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     resetForm();
     return "DieuTri_LapVanBanTheoMau_SoChuyenVien";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB237 = null;
     return "B260_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.tungaystr = "";
     this.denngaystr = "";
     this.lydo_ma = "";
     this.tainan_ma = "";
     this.benhICD_ma = "";
     this.khoa_ma = "";
     this.benhvien_ma = "";
     this.lydo_maso = null;
     this.tainan_maso = null;
     this.benhICD_maso = null;
     this.khoa_maso = null;
     this.benhvien_maso = null;
   }

   public void XuatReport()
   {
     this.loaiBCDT = "Sochuyenvien";
     this.log.info("Vao Method XuatReport so chuyen vien", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/sochuyenvien.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       if (!this.tungaystr.trim().equals("")) {
         params.put("TUNGAYDATE", sdf.parse(this.tungaystr));
       }
       if (!this.denngaystr.trim().equals("")) {
         params.put("DENNGAYDATE", sdf.parse(this.denngaystr));
       }
       if (!this.lydo_ma.trim().equals("")) {
         params.put("LYDOMASO", this.lydo_maso);
       }
       if (!this.tainan_ma.trim().equals("")) {
         params.put("TAINANMASO", this.tainan_maso);
       }
       if (!this.benhICD_ma.trim().equals("")) {
         params.put("BENHMASO", this.benhICD_maso);
       }
       if (!this.khoa_ma.trim().equals("")) {
         params.put("KHOAMASO", this.khoa_maso);
       }
       if (!this.benhvien_ma.trim().equals("")) {
         params.put("BVMASO", this.benhvien_maso);
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Sochuyenvien");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.duongdanStrDT, new Object[0]);
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

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getTungaystr()
   {
     return this.tungaystr;
   }

   public void setTungaystr(String tungaystr)
   {
     this.tungaystr = tungaystr;
   }

   public String getDenngaystr()
   {
     return this.denngaystr;
   }

   public void setDenngaystr(String denngaystr)
   {
     this.denngaystr = denngaystr;
   }

   public String getLydo_ma()
   {
     return this.lydo_ma;
   }

   public void setLydo_ma(String lydo_ma)
   {
     this.lydo_ma = lydo_ma;
   }

   public String getTainan_ma()
   {
     return this.tainan_ma;
   }

   public void setTainan_ma(String tainan_ma)
   {
     this.tainan_ma = tainan_ma;
   }

   public String getBenhICD_ma()
   {
     return this.benhICD_ma;
   }

   public void setBenhICD_ma(String benhICD_ma)
   {
     this.benhICD_ma = benhICD_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getBenhvien_ma()
   {
     return this.benhvien_ma;
   }

   public void setBenhvien_ma(String benhvien_ma)
   {
     this.benhvien_ma = benhvien_ma;
   }

   public Integer getLydo_maso()
   {
     return this.lydo_maso;
   }

   public void setLydo_maso(Integer lydo_maso)
   {
     this.lydo_maso = lydo_maso;
   }

   public Integer getTainan_maso()
   {
     return this.tainan_maso;
   }

   public void setTainan_maso(Integer tainan_maso)
   {
     this.tainan_maso = tainan_maso;
   }

   public Integer getBenhICD_maso()
   {
     return this.benhICD_maso;
   }

   public void setBenhICD_maso(Integer benhICD_maso)
   {
     this.benhICD_maso = benhICD_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getBenhvien_maso()
   {
     return this.benhvien_maso;
   }

   public void setBenhvien_maso(Integer benhvien_maso)
   {
     this.benhvien_maso = benhvien_maso;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.Sochuyenvien

 * JD-Core Version:    0.7.0.1

 */