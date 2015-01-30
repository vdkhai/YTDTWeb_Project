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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("InDSBNKhamThuTien")
 @Scope(ScopeType.CONVERSATION)
 public class InDSBNKhamThuTien
   implements Serializable
 {
   private static final long serialVersionUID = -619143681433776530L;
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB114 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private Integer doituong = null;
   private Integer doituongbhyt = null;
   private String doituong_ma = null;
   private String doituongbhyt_ma = null;
   private int index = 0;

   @Factory("resetFormB114")
   public void initresetFormB147()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     resetForm();
     return "TiepDon_TiepDonBenhNhan_InDanhSachBNKhamThuTien";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB114 = null;
     return "B160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.log.info("Bat dau ham reset form", new Object[0]);
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.resetFormB114 = "";
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);

     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);
     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);
     this.doituong_ma = "";
     this.doituongbhyt_ma = "";

     this.log.info("ket thuc ham reset form", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeTD = "indsbnkhamthutien";
     this.log.info("Vao Method XuatReport In danh sach benh nhan kham thu tien", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T02_dsbnkhamthutien.jrxml";
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       params.put("TuNgay", df.parse(this.tungay));
       params.put("DenNgay", df.parse(this.denngay));
       params.put("diadiem", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if ((this.doituong_ma != null) && (!this.doituong_ma.equals(""))) {
         params.put("DoiTuong", this.doituong_ma);
       } else {
         params.put("DoiTuong", null);
       }
       if ((this.doituongbhyt_ma != null) && (!this.doituongbhyt_ma.equals(""))) {
         params.put("DTBHYT", this.doituongbhyt_ma);
       } else {
         params.put("DTBHYT", null);
       }
       this.log.info("***** DoiTuong " + this.doituong_ma, new Object[0]);
       this.log.info("***** DTBHYT " + this.doituongbhyt_ma, new Object[0]);


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
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "indsbnkhamthutien");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathTD, new Object[0]);
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

   public String getThoigian_thang()
   {
     return this.thoigian_thang;
   }

   public void setThoigian_thang(String thoigian_thang)
   {
     this.thoigian_thang = thoigian_thang;
   }

   public String getThoigian_nam()
   {
     return this.thoigian_nam;
   }

   public void setThoigian_nam(String thoigian_nam)
   {
     this.thoigian_nam = thoigian_nam;
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

   public Integer getDoituong()
   {
     return this.doituong;
   }

   public void setDoituong(Integer doituong)
   {
     this.doituong = doituong;
   }

   public Integer getDoituongbhyt()
   {
     return this.doituongbhyt;
   }

   public void setDoituongbhyt(Integer doituongbhyt)
   {
     this.doituongbhyt = doituongbhyt;
   }

   public String getDoituong_ma()
   {
     return this.doituong_ma;
   }

   public void setDoituong_ma(String doituong_ma)
   {
     this.doituong_ma = doituong_ma;
   }

   public String getDoituongbhyt_ma()
   {
     return this.doituongbhyt_ma;
   }

   public void setDoituongbhyt_ma(String doituongbhyt_ma)
   {
     this.doituongbhyt_ma = doituongbhyt_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.InDSBNKhamThuTien

 * JD-Core Version:    0.7.0.1

 */