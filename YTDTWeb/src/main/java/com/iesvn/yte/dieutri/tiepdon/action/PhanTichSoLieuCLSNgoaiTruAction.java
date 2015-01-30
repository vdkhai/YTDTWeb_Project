 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
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

 @Name("B145_Phantichsolieuclsngoaitru")
 @Scope(ScopeType.CONVERSATION)
 public class PhanTichSoLieuCLSNgoaiTruAction
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
   private String resetFormB145 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private Integer nhanvien_maso = null;
   private Integer doituong_maso = null;
   private Integer loaicls_maso = null;
   private String nhanvien_ma = "";
   private String doituong_ma = "";
   private String loaicls_ma = "";
   private String ngayhientai;
   private int index = 0;

   @Factory("resetFormB145")
   public void initresetFormB145()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     resetForm();
     return "TiepDon_PhanTichBaoCao_PhanTichSoLieuCanLamSan";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB145 = null;
     return "B160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.log.info("Bat dau ham reset form", new Object[0]);
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.resetFormB145 = "";
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);


     Calendar cal = Calendar.getInstance();
     cal.set(5, 1);

     this.tungay = sdf.format(cal.getTime());
     this.denngay = sdf.format(currentDate);
     this.nhanvien_ma = "";
     this.doituong_ma = "";
     this.loaicls_ma = "";
     this.log.info("ket thuc ham reset form", new Object[0]);
   }

   public void XuatReport()
   {
     this.reportTypeTD = "ptsolieuclsngoaitru";
     this.log.info("Vao Method XuatReport phan tich so lieu can lam san ngoai tru", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/T11_PTSolieuthuchiencanlamsangngoaitruccl.jrxml";
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
       if (!this.nhanvien_ma.trim().equals(""))
       {
         this.log.info("set gia trin cho nhan vien " + this.nhanvien_ma, new Object[0]);
         params.put("THUNGAN", this.nhanvien_maso);
       }
       if (!this.doituong_ma.trim().equals(""))
       {
         this.log.info("set gia trin cho doi tuong " + this.doituong_ma, new Object[0]);
         params.put("DOITUONG", this.doituong_maso);
       }
       if (!this.loaicls_ma.trim().equals(""))
       {
         this.log.info("set gia trin cho loai CLS " + this.loaicls_ma, new Object[0]);
         params.put("LOAICLS", this.loaicls_maso);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "ptsolieuclsngoaitru");
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

   public Integer getNhanvien_maso()
   {
     return this.nhanvien_maso;
   }

   public void setNhanvien_maso(Integer nhanvien_maso)
   {
     this.nhanvien_maso = nhanvien_maso;
   }

   public Integer getDoituong_maso()
   {
     return this.doituong_maso;
   }

   public void setDoituong_maso(Integer doituong_maso)
   {
     this.doituong_maso = doituong_maso;
   }

   public Integer getLoaicls_maso()
   {
     return this.loaicls_maso;
   }

   public void setLoaicls_maso(Integer loaicls_maso)
   {
     this.loaicls_maso = loaicls_maso;
   }

   public String getNhanvien_ma()
   {
     return this.nhanvien_ma;
   }

   public void setNhanvien_ma(String nhanvien_ma)
   {
     this.nhanvien_ma = nhanvien_ma;
   }

   public String getDoituong_ma()
   {
     return this.doituong_ma;
   }

   public void setDoituong_ma(String doituong_ma)
   {
     this.doituong_ma = doituong_ma;
   }

   public String getLoaicls_ma()
   {
     return this.loaicls_ma;
   }

   public void setLoaicls_ma(String loaicls_ma)
   {
     this.loaicls_ma = loaicls_ma;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.PhanTichSoLieuCLSNgoaiTruAction

 * JD-Core Version:    0.7.0.1

 */