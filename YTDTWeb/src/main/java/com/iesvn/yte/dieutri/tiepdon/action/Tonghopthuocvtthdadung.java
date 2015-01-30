 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
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

 @Name("B124_Tonghopthuocvtthdadung")
 @Scope(ScopeType.CONVERSATION)
 public class Tonghopthuocvtthdadung
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
   private String resetFormB124 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private String bankham_ma = "";
   private String loaithuoc_ma = "";
   private Integer bankham_maso = null;
   private Integer loaithuoc_maso = null;

   public Integer getLoaithuoc_maso()
   {
     return this.loaithuoc_maso;
   }

   public void setLoaithuoc_maso(Integer loaithuocMaso)
   {
     this.loaithuoc_maso = loaithuocMaso;
   }

   @Begin(join=true)
   public String init(String loaiKham)
   {
     if ("CCL".equals(loaiKham)) {
       this.bankham_ma = "CCL";
     } else if ("CCN".equals(loaiKham)) {
       this.bankham_ma = "CCN";
     } else {
       this.bankham_ma = "";
     }
     initTemp();
     return "TiepDon_KhamBenh_TongHopThuocVTTHDaDung";
   }

   @Factory("resetFormB124")
   public void initTemp()
   {
     this.ngayhientai = Utils.getCurrentDate();
     this.resetFormB124 = "";
     resetForm();
   }

   @End
   public void endFunc() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB124 = null;
     return "B160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);

     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);

     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);

     setLoaithuoc_ma("");
   }

   public void XuatReport()
   {
     this.reportTypeTD = "Tonghopthuocvtthdadung";
     this.log.info("Vao Method XuatReport Tong hop thuoc vtth da dung", new Object[0]);
     String baocao1 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T03_Tinhhinhsudungthuocvtth.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       if (!this.bankham_ma.trim().equals(""))
       {
         DtDmBanKham bankham = new DtDmBanKham();
         params.put("BANKHAMMASO", this.bankham_maso);
         Object obj = dtUtils.findByMa(this.bankham_ma, "DtDmBanKham", "dtdmbankhamMa");
         bankham = (DtDmBanKham)obj;
         params.put("BANKHAM", bankham.getDtdmbankhamTen());
       }
       params.put("TUNGAYDATE", sdf.parse(this.tungay));
       params.put("DENNGAYDATE", sdf.parse(this.denngay));

       this.log.info("da thay file thanh " + this.loaithuoc_ma.trim(), new Object[0]);
       this.log.info("da thay file thanh " + this.loaithuoc_maso, new Object[0]);
       this.log.info("da thay file bankham_maso " + this.bankham_maso, new Object[0]);
       if (!this.loaithuoc_ma.trim().equals("")) {
         params.put("LOAITHUOCMASO", this.loaithuoc_maso);
       } else {
         params.put("LOAITHUOCMASO", null);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "Tonghopthuocvtthdadung");
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

   public String getBankham_ma()
   {
     return this.bankham_ma;
   }

   public void setBankham_ma(String bankham_ma)
   {
     this.bankham_ma = bankham_ma;
   }

   public String getLoaithuoc_ma()
   {
     return this.loaithuoc_ma;
   }

   public void setLoaithuoc_ma(String loaithuocMa)
   {
     this.loaithuoc_ma = loaithuocMa;
   }

   public Integer getBankham_maso()
   {
     return this.bankham_maso;
   }

   public void setBankham_maso(Integer bankham_maso)
   {
     this.bankham_maso = bankham_maso;
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
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.Tonghopthuocvtthdadung

 * JD-Core Version:    0.7.0.1

 */