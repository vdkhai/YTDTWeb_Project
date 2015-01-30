 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
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
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B278_BaoCaoHoatDongCanLamSan")
 @Scope(ScopeType.CONVERSATION)
 public class B278_BaoCaoHoatDongCanLamSan
   implements Serializable
 {
   private static final long serialVersionUID = 1749463646874557659L;
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;
   private String thoigian_thang = "";
   private String thoigian_nam = "";
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai = "";
   private int index = 0;
   public String nam;
   public int thoigian = 3;

   public String getNam()
   {
     return this.nam;
   }

   public void setNam(String nam)
   {
     this.nam = nam;
   }

   public int getThoigian()
   {
     return this.thoigian;
   }

   public void setThoigian(int thoigian)
   {
     this.thoigian = thoigian;
   }

   @Begin(join=true)
   public String init()
   {
     resetForm();
     return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoHoatDongCanLamSan";
   }

   private String tieude = "";

   @End
   public void end() {}

   public String getTieude()
   {
     return this.tieude;
   }

   public void setTieude(String tieude)
   {
     this.tieude = tieude;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.ngayhientai = sdf.format(currentDate);









     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);

     this.log.info("Ket thuc ham reset form B278_BaoCaoHoatDongCanLamSan", new Object[0]);
   }

   public String tinhDenngay()
   {
     if ((this.thoigian == 6) || (this.thoigian == 9)) {
       return "30/" + this.thoigian + "/" + this.nam;
     }
     return "31/" + this.thoigian + "/" + this.nam;
   }

   public void XuatReport()
   {
     this.loaiBCDT = "BCHoatDongCanLamSan";
     this.log.info("Vao Method XuatReport In danh sach B278_BaoCaoHoatDongCanLamSan", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/DT06_HoatDongCanLamSang.jrxml";
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();


       System.out.println("tungay=" + this.tungay);
       System.out.println("denngay=" + this.denngay);
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       params.put("fromDate", df.parse(this.tungay));
       params.put("toDate", df.parse(this.denngay));
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE_SHORT);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       if (this.tieude.trim().equals("")) {
         params.put("tieude", IConstantsRes.TU + " " + this.tungay + " " + IConstantsRes.DEN + " " + this.denngay);
       } else {
         params.put("tieude", this.tieude);
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
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "hoatdongcanlamsan");
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B278_BaoCaoHoatDongCanLamSan

 * JD-Core Version:    0.7.0.1

 */