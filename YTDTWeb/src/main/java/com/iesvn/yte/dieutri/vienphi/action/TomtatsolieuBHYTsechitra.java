 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
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

 @Name("B3331_TomtatsolieuBHYTsechitra")
 @Scope(ScopeType.CONVERSATION)
 public class TomtatsolieuBHYTsechitra
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
   private String resetFormB3331 = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private int index = 0;
   private String khoa_ma = "";
   private Integer khoa_maso = null;
   private boolean intheo = false;

   @Factory("resetFormB3331")
   public void initresetFormB3331()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     initresetFormB3331();
     return "BaoCaoVienPhi_BaoCaoBHYT_TomTatSoLieuBHYTSeChiTra";
   }

   @End
   public void endFunc() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB3331 = null;
     return "B3360_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.resetFormB3331 = "";
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     setKhoa_ma("");
     setIntheo(false);
   }

   public void XuatReport()
   {
     this.reportTypeVP = "TomtatsolieuBHYTsechitra";
     this.log.info("Vao Method XuatReport tom tat so lieu BHYT se chi tra", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V21_Solieutongquatkinhphidenghibhytthanhtoan.jrxml";
       this.log.info("da thay file templete " + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TUNGAYDATE", sdf.parse(this.tungay));
       params.put("DENNGAYDATE", sdf.parse(this.denngay));
       if (this.intheo == true) {
         params.put("BNTRA", new Double(0.0D));
       }
       if (!this.khoa_ma.equals(""))
       {
         DmKhoa khoa = new DmKhoa();
         DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
         Object obj = dtUtils.findByMa(this.khoa_ma, "DmKhoa", "dmkhoaMa");
         khoa = (DmKhoa)obj;
         params.put("KHOAMASO", khoa.getDmkhoaMaso());
         params.put("KHOARAVIEN", khoa.getDmkhoaTen());
       }
       else
       {
         params.put("KHOARAVIEN", "");
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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "TomtatsolieuBHYTsechitra");
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

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public boolean isIntheo()
   {
     return this.intheo;
   }

   public void setIntheo(boolean intheo)
   {
     this.intheo = intheo;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.TomtatsolieuBHYTsechitra

 * JD-Core Version:    0.7.0.1

 */