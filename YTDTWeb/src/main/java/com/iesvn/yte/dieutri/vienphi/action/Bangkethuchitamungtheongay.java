 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B3311_Bangkethuchitamungtheongay")
 @Scope(ScopeType.CONVERSATION)
 public class Bangkethuchitamungtheongay
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB3311 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private DtDmNhanVien thungan;

   public DtDmNhanVien getThungan()
   {
     return this.thungan;
   }

   public DtDmNhanVien getThungan(boolean create)
   {
     if ((this.thungan == null) && (create)) {
       return new DtDmNhanVien();
     }
     return this.thungan;
   }

   public void setThungan(DtDmNhanVien thungan)
   {
     this.thungan = thungan;
   }

   @Factory("resetFormB3311")
   public void initResetFormB3311()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
     return "BaoCaoVienPhi_SoLieuThanhToan_BangKeThuChiTamUngTheoNgay";
   }

   @End
   public void endFunc() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB3311 = null;
     return "B3360_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.resetFormB3311 = "";
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     this.thungan = new DtDmNhanVien();
   }

   public void XuatReport()
   {
     System.out.println("Ma = " + this.thungan.getDtdmnhanvienMa());

     this.reportTypeVP = "Bangkethuchitamungtheongay";
     this.log.info("Vao Method XuatReport bang ke thu chi tam ung theo ngay", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);

       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/VP02_Tinhhinhtamung.jrxml";

       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("TuNgay", sdf.parse(this.tungay));
       params.put("DenNgay", sdf.parse(this.denngay));
       params.put("THUNGAN_MA", this.thungan.getDtdmnhanvienMa());


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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Bangkethuchitamungtheongay");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathVP, new Object[0]);
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.Bangkethuchitamungtheongay

 * JD-Core Version:    0.7.0.1

 */