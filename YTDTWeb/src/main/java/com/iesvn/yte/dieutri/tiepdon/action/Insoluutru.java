 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.YteSequenceDelegate;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B147_Insoluutru")
 @Scope(ScopeType.CONVERSATION)
 public class Insoluutru
   implements Serializable
 {
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
   private String resetFormB147 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private String soTDtuStr = "";
   private String soTDdenStr = "";
   private String bankham_ma = "";
   private String tugio = "";
   private String dengio = "";
   private Integer bankham_maso = null;
   private Integer inTheo = null;
   private int index = 0;

   @Factory("resetFormB147")
   public void initresetFormB147()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB147 = null;
     return "B160_Chonmenuxuattaptin";
   }

   public void loadMTDTu()
   {
     if (!this.soTDtuStr.trim().equals(""))
     {
       this.log.info("bat dau load MTD tu ", new Object[0]);
       YteSequenceDelegate yteUtils = YteSequenceDelegate.getInstance();
       setSoTDtuStr(yteUtils.formatMa(null, this.soTDtuStr));
       this.log.info("ket thuc load MTD tu ", new Object[0]);
     }
   }

   public void loadMTDDen()
   {
     if (!this.soTDdenStr.trim().equals(""))
     {
       this.log.info("bat dau load MTD den", new Object[0]);
       YteSequenceDelegate yteUtils = YteSequenceDelegate.getInstance();
       setSoTDdenStr(yteUtils.formatMa(null, this.soTDdenStr));
       this.log.info("ket thuc load MTD den ", new Object[0]);
     }
   }

   public void resetForm()
   {
     this.log.info("Bat dau ham reset form", new Object[0]);
     this.tugio = (this.dengio = "");
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.resetFormB147 = "";
     if (currentDate.getHours() < 10) {
       this.tugio = (this.tugio + "0" + currentDate.getHours());
     } else {
       this.tugio += currentDate.getHours();
     }
     if (currentDate.getMinutes() < 10) {
       this.tugio = (this.tugio + ":0" + currentDate.getMinutes());
     } else {
       this.tugio = (this.tugio + ":" + currentDate.getMinutes());
     }
     this.dengio = this.tugio;









     this.tugio = "00:00";
     this.dengio = "23.59";

     setInTheo(new Integer(1));
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);
     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);
     this.soTDtuStr = "";
     this.soTDdenStr = "";
     this.bankham_ma = "";
     this.log.info("ket thuc ham reset form", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeTD = "Insoluutru";
     this.log.info("Vao Method XuatReport In so luu tru", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if (this.inTheo.intValue() == 1) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T13_DSBenhnhandangkykhambenh_ngaykham.jrxml";
       } else if (this.inTheo.intValue() == 2) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T13_DSBenhnhandangkykhambenh_bankham.jrxml";
       }
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("TUNGAY", sdf.parse(this.tungay));
       params.put("DENNGAY", sdf.parse(this.denngay));
       params.put("TUGIO", this.tugio);
       params.put("DENGIO", this.dengio);
       if (!this.bankham_ma.trim().equals("")) {
         params.put("BANKHAM", this.bankham_maso);
       }
       if (!this.soTDtuStr.equals(""))
       {
         this.log.info("set gia tri cho so tiep don tu " + Integer.parseInt(this.soTDtuStr.trim().substring(4, 12)), new Object[0]);
         params.put("STDTU", Integer.valueOf(Integer.parseInt(this.soTDtuStr.trim().substring(4, 12))));
         this.log.info("STDTU", new Object[] { Integer.valueOf(Integer.parseInt(this.soTDtuStr.trim().substring(4, 12))) });
       }
       if (!this.soTDdenStr.equals(""))
       {
         this.log.info("set gia tri cho so tiep don den " + Integer.parseInt(this.soTDdenStr.trim().substring(4, 12)), new Object[0]);
         params.put("STDDEN", Integer.valueOf(Integer.parseInt(this.soTDdenStr.trim().substring(4, 12))));
         this.log.info("STDDEN", new Object[] { Integer.valueOf(Integer.parseInt(this.soTDdenStr.trim().substring(4, 12))) });
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "Insoluutru");
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

   public Integer getInTheo()
   {
     return this.inTheo;
   }

   public void setInTheo(Integer inTheo)
   {
     this.inTheo = inTheo;
   }

   public String getSoTDtuStr()
   {
     return this.soTDtuStr;
   }

   public void setSoTDtuStr(String soTDtuStr)
   {
     this.soTDtuStr = soTDtuStr;
   }

   public String getSoTDdenStr()
   {
     return this.soTDdenStr;
   }

   public void setSoTDdenStr(String soTDdenStr)
   {
     this.soTDdenStr = soTDdenStr;
   }

   public String getBankham_ma()
   {
     return this.bankham_ma;
   }

   public void setBankham_ma(String bankham_ma)
   {
     this.bankham_ma = bankham_ma;
   }

   public Integer getBankham_maso()
   {
     return this.bankham_maso;
   }

   public void setBankham_maso(Integer bankham_maso)
   {
     this.bankham_maso = bankham_maso;
   }

   public String getTugio()
   {
     return this.tugio;
   }

   public void setTugio(String tugio)
   {
     this.tugio = tugio;
   }

   public String getDengio()
   {
     return this.dengio;
   }

   public void setDengio(String dengio)
   {
     this.dengio = dengio;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.Insoluutru

 * JD-Core Version:    0.7.0.1

 */