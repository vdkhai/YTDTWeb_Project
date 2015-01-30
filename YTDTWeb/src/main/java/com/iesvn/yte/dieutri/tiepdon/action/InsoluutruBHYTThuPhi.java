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
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B147_1_Insoluutru_bhyt_thuphi")
 @Scope(ScopeType.CONVERSATION)
 public class InsoluutruBHYTThuPhi
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
   private String bankham_ma = "";
   private String tugio = "";
   private String dengio = "";
   private Integer bankham_maso = null;
   private int index = 0;
   private String myLoai = "";

   @Begin(join=true)
   public String init(String loai)
   {
     this.log.info("init()", new Object[0]);
     this.myLoai = loai;
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
     return "TiepDon_PhanTichBaoCao_InSoLuuTruBHYTThuPhi";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB147 = null;
     return "B160_Chonmenuxuattaptin";
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
     this.dengio = "23:59";


     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);
     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);

     this.bankham_ma = "";
     this.log.info("ket thuc ham reset form", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeTD = "Insoluutrubhytthuphi";
     this.log.info("Vao Method XuatReport Insoluutrubhytthuphi", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if ("bhyt".equals(this.myLoai)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/baohiemxahoitinh.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/insoluutru_thuphi.jrxml";
       }
       this.log.info("da thay file templete" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();




       params.put("TUYEN_PARAM", Integer.valueOf(Integer.parseInt(IConstantsRes.MASO_TUYEN_BV_TRIEN_KHAI)));

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("TUNGAY", sdf.parse(this.tungay));
       params.put("DENNGAY", sdf.parse(this.denngay));
       if (!this.bankham_ma.trim().equals("")) {
         params.put("BANKHAM", this.bankham_maso);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "Insoluutrubhytthuphi");
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

   public String getMyLoai()
   {
     return this.myLoai;
   }

   public void setMyLoai(String myLoai)
   {
     this.myLoai = myLoai;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.InsoluutruBHYTThuPhi

 * JD-Core Version:    0.7.0.1

 */