 package com.iesvn.yte.dieutri.hsba.action;

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
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B277_BaoCaoTinhHinhBenhTatVaTuVongTaiBV")
 @Scope(ScopeType.CONVERSATION)
 public class B277_BaoCaoTinhHinhBenhTatVaTuVongTaiBV
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
   private String tieude = "";

   public String getTieude()
   {
     return this.tieude;
   }

   public void setTieude(String tieude)
   {
     this.tieude = tieude;
   }

   @Begin(join=true)
   public String init()
   {
     resetForm();
     return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhBenhTatVaTuVongTaiBV";
   }

   @End
   public void end() {}

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
     this.thoigian_nam = sdf.format(currentDate).substring(6);
     this.thoigian_thang = sdf.format(currentDate).substring(3, 5);

     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);
     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);
     this.log.info("Ket thuc ham reset form B277_BaoCaoTinhHinhBenhTatVaTuVongTaiBV", new Object[0]);
   }

   public void XuatReport()
   {
     this.loaiBCDT = "BCTinhHinhBenhTatVaTuVongTaiBV";
     this.log.info("Vao Method XuatReport In danh sach B277_BaoCaoTinhHinhBenhTatVaTuVongTaiBV", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/B04_THBenhtatvatuvongtaibenhvien_VERSION2.jrxml";
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();

       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       params.put("TuNgay", df.parse(this.tungay));
       params.put("DenNgay", df.parse(this.denngay));

       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE_SHORT);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "tinhhinhbenhtatvatuvongtaibv");
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B277_BaoCaoTinhHinhBenhTatVaTuVongTaiBV

 * JD-Core Version:    0.7.0.1

 */