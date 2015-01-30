 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
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

 @Name("B4138_Indstonkhokhonghandung")
 @Scope(ScopeType.CONVERSATION)
 public class Dstonkhokhonghandung
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC = null;
   @In(scope=ScopeType.SESSION, required=false)
   @Out(scope=ScopeType.SESSION, required=false)
   private String resetForm4138 = null;
   private String ngayhientai = null;
   private static int index = 0;
   private Integer lthuoc_maso = null;
   private Integer pthuoc_maso = null;
   private Integer khoa_maso = null;
   private Integer nguonct_maso = null;
   private Integer nguonkp_maso = null;
   private String lthuoc_ma = null;
   private String pthuoc_ma = null;
   private String khoa_ma = null;
   private String nguonct_ma = null;
   private String nguonkp_ma = null;
   private String nht = null;
   String dmKhoXuat = "";

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetForm4138")
   public void init()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     this.nht = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init(String kho)
   {
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_CanhBaoMatHangTonKhoKhongHanDung";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     this.resetForm4138 = null;
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     setLthuoc_ma(null);
     this.resetForm4138 = "";
     setPthuoc_ma(null);
     setNguonct_ma(null);
     setNguonkp_ma(null);
     this.lthuoc_maso = null;
     this.pthuoc_maso = null;
     this.nguonct_maso = null;
     this.nguonkp_maso = null;
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Dstonkhokhonghandung";
     this.log.info("Vao Method XuatReport mat hang khong han dung", new Object[0]);
     String baocao1 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D11_hangkhongcoghihandung.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       this.log.info("truyen tham so thoi diem tinh " + this.ngayhientai, new Object[0]);

       String strInputTonDauDate = IConstantsRes.INPUT_TONDAU;
       Date dNgayTonCuoi = sdf.parse(strInputTonDauDate);
       Date dNgayHienTai = sdf.parse(this.ngayhientai);
       if ((dNgayHienTai.before(dNgayTonCuoi)) || (dNgayHienTai.equals(dNgayTonCuoi))) {
         params.put("THOIDIEMTINH", dNgayTonCuoi);
       } else {
         params.put("THOIDIEMTINH", dNgayHienTai);
       }
       this.log.info("truyen tham so loai thuoc " + this.lthuoc_maso, new Object[0]);
       if (this.lthuoc_maso != null) {
         params.put("LOAITHUOCMASO", this.lthuoc_maso);
       }
       this.log.info("truyen tham so  phan loai thuoc " + this.pthuoc_maso, new Object[0]);
       if (this.pthuoc_maso != null) {
         params.put("PLTHUOCMASO", this.pthuoc_maso);
       }
       this.log.info("truyen tham so  khoa " + this.khoa_maso, new Object[0]);
       if (this.khoa_maso != null) {
         params.put("DMKHOAMASO", this.khoa_maso);
       }
       this.log.info("truyen tham so  nguon chuong trinh " + this.nguonct_maso, new Object[0]);
       if (this.nguonct_maso != null) {
         params.put("NGUONCTMASO", this.nguonct_maso);
       }
       this.log.info("truyen tham so  nguon kinh phi " + this.nguonkp_maso, new Object[0]);
       if (this.nguonkp_maso != null) {
         params.put("NGUONKPMASO", this.nguonkp_maso);
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
       this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Dstonkhokhonghandung");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathKC, new Object[0]);
       index += 1;
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

   public static void setIndex(int index)
   {
     index = index;
   }

   public static int getIndex()
   {
     return index;
   }

   public Integer getLthuoc_maso()
   {
     return this.lthuoc_maso;
   }

   public void setLthuoc_maso(Integer lthuoc_maso)
   {
     this.lthuoc_maso = lthuoc_maso;
   }

   public Integer getPthuoc_maso()
   {
     return this.pthuoc_maso;
   }

   public void setPthuoc_maso(Integer pthuoc_maso)
   {
     this.pthuoc_maso = pthuoc_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getNguonct_maso()
   {
     return this.nguonct_maso;
   }

   public void setNguonct_maso(Integer nguonct_maso)
   {
     this.nguonct_maso = nguonct_maso;
   }

   public Integer getNguonkp_maso()
   {
     return this.nguonkp_maso;
   }

   public void setNguonkp_maso(Integer nguonkp_maso)
   {
     this.nguonkp_maso = nguonkp_maso;
   }

   public String getLthuoc_ma()
   {
     return this.lthuoc_ma;
   }

   public void setLthuoc_ma(String lthuoc_ma)
   {
     this.lthuoc_ma = lthuoc_ma;
   }

   public String getPthuoc_ma()
   {
     return this.pthuoc_ma;
   }

   public void setPthuoc_ma(String pthuoc_ma)
   {
     this.pthuoc_ma = pthuoc_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getNguonct_ma()
   {
     return this.nguonct_ma;
   }

   public void setNguonct_ma(String nguonct_ma)
   {
     this.nguonct_ma = nguonct_ma;
   }

   public String getNguonkp_ma()
   {
     return this.nguonkp_ma;
   }

   public void setNguonkp_ma(String nguonkp_ma)
   {
     this.nguonkp_ma = nguonkp_ma;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNht(String nht)
   {
     this.nht = nht;
   }

   public String getNht()
   {
     return this.nht;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Dstonkhokhonghandung

 * JD-Core Version:    0.7.0.1

 */