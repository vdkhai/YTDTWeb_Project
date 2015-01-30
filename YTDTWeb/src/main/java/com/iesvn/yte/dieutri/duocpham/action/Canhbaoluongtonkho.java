 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
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

 @Name("B4161_Canhbaoluongtonkho")
 @Scope(ScopeType.CONVERSATION)
 public class Canhbaoluongtonkho
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
   private String resetFormB4161 = null;
   private int index = 0;
   private Integer lthuoc_maso = null;
   private Integer pthuoc_maso = null;
   private Integer khoa_maso = null;
   private Integer nguonct_maso = null;
   private Integer nguonkp_maso = null;
   private Double dSoLuongMin = null;
   private Double dSoLuongMax = null;
   private String lthuoc_ma = null;
   private String pthuoc_ma = null;
   private String khoa_ma = null;
   private String nguonct_ma = null;
   private String nguonkp_ma = null;
   String dmKhoXuat = "";

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetFormB4161")
   public void init()
   {
     this.log.info("init()", new Object[0]);
     resetForm();
   }

   @Begin(join=true)
   public String init(String kho)
   {
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_TruyXuatThongTinKhoChinh_Canhbaoluongtonkho";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     this.log.info("****Begin thuchienAction*****", new Object[0]);
     XuatReport();
     resetForm();
     this.resetFormB4161 = null;
     this.log.info("****End thuchienAction*****", new Object[0]);
     return "B4160_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     setLthuoc_ma(null);
     setPthuoc_ma(null);
     setNguonct_ma(null);
     setNguonkp_ma(null);
     setDSoLuongMax(null);
     setDSoLuongMin(null);
   }

   public void XuatReport()
   {
     this.reportTypeKC = "Canhbaoluongtonkho";
     this.log.info("Vao Method XuatReport Canhbaoluongtonkho", new Object[0]);
     String baocao1 = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/LuongHangTonKho.jrxml";
       this.log.info("da thay file templete : " + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       if (this.lthuoc_maso != null) {
         params.put("LOAITHUOCMASO", this.lthuoc_maso);
       }
       if (this.pthuoc_maso != null) {
         params.put("PLTHUOCMASO", this.pthuoc_maso);
       }
       if (this.khoa_maso != null) {
         params.put("DMKHOAMASO", this.khoa_maso);
       }
       if (this.nguonct_maso != null) {
         params.put("NGUONCTMASO", this.nguonct_maso);
       }
       if (this.nguonkp_maso != null) {
         params.put("NGUONKPMASO", this.nguonkp_maso);
       }
       if (this.dSoLuongMin != null) {
         params.put("SOLUONGMIN", this.dSoLuongMin);
       }
       if (this.dSoLuongMax != null) {
         params.put("SOLUONGMAX", this.dSoLuongMax);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "Canhbaoluongtonkho");
       this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       this.log.info("duong dan file xuat report :" + baocao1, new Object[0]);
       this.log.info("duong dan -------------------- :" + this.reportPathKC, new Object[0]);
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

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
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

   public Double getDSoLuongMin()
   {
     return this.dSoLuongMin;
   }

   public void setDSoLuongMin(Double soLuongMin)
   {
     this.dSoLuongMin = soLuongMin;
   }

   public Double getDSoLuongMax()
   {
     return this.dSoLuongMax;
   }

   public void setDSoLuongMax(Double soLuongMax)
   {
     this.dSoLuongMax = soLuongMax;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Canhbaoluongtonkho

 * JD-Core Version:    0.7.0.1

 */