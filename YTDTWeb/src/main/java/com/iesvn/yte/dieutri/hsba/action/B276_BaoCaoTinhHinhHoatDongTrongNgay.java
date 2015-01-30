 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
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

 @Name("B276_BaoCaoTinhHinhHoatDongTrongNgay")
 @Scope(ScopeType.CONVERSATION)
 public class B276_BaoCaoTinhHinhHoatDongTrongNgay
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
   JasperPrint jasperPrintDT = null;
   private String thoigian_thang = "";
   private String thoigian_nam = "";
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai = "";
   private int index = 0;

   @Begin(join=true)
   public String init()
   {
     resetForm();
     return "DieuTri_BaoCaoHoatDongDieuTri_BaoCaoTinhHinhHoatDongTrongNgay";
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
     this.log.info("Ket thuc ham reset form B276_BaoCaoTinhHinhHoatDongTrongNgay", new Object[0]);
   }

   public void XuatReport()
   {
     this.loaiBCDT = "BCTinhHinhHoatDongTrongNgay";
     this.log.info("Vao Method XuatReport In danh sach B276_BaoCaoTinhHinhHoatDongTrongNgay", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     String sub0Template = null;
     String sub1Template = null;
     String sub2Template = null;
     String sub3Template = null;
     String sub4Template = null;
     try
     {
       System.out.println("Den ngay = " + this.denngay);
       Date time1 = null;
       Date time2 = null;
       Date time3 = null;
       time1 = Utils.getDBDate("07:30", this.denngay).getTime();
       time2 = Utils.getDBDate("16:30", this.denngay).getTime();
       Calendar c = Calendar.getInstance();
       c.setTime(time1);
       c.add(5, 1);
       time3 = c.getTime();

       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/BA01_baocaotinhhinhhoatdongtrongngay.jrxml";
       sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport0.jrxml";
       sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport1.jrxml";
       sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport2.jrxml";
       sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport3.jrxml";
       sub4Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/BA01_baocaotinhhinhhoatdongtrongngay_subreport4.jrxml";
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);
       JasperReport sub4Report = JasperCompileManager.compileReport(sub4Template);
       Map<String, Object> params = new HashMap();

       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       params.put("sub1", sub0Report);
       params.put("sub2", sub1Report);
       params.put("sub3", sub2Report);
       params.put("sub4", sub3Report);
       params.put("sub5", sub4Report);
       params.put("time1", time1);
       params.put("time2", time2);
       params.put("time3", time3);

       params.put("soYTe", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("dayBefore", df.parse(this.tungay));
       params.put("currentDate", df.parse(this.denngay));

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "tinhhinhhoatdongtrongngay");
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

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B276_BaoCaoTinhHinhHoatDongTrongNgay

 * JD-Core Version:    0.7.0.1

 */