 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.Hsba;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B236_Soravaovien")
 @Scope(ScopeType.CONVERSATION)
 public class Soravaovien
   implements Serializable
 {
   @Logger
   private Log log;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   @Out(required=false)
   @In(required=false)
   private String resetFormB236 = null;
   private int index = 0;
   private String sovaovien_tu = "";
   private String sovaovien_den = "";

   @Factory("resetFormB236")
   public void initresetFormB236()
   {
     this.log.info("init()", new Object[0]);
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     resetForm();
     return "DieuTri_LapVanBanTheoMau_SoRaVaoVien";
   }

   @End
   public void end() {}

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void loadSoVaoVienTu()
   {
     this.log.info("-----So vao vien-----" + this.sovaovien_tu, new Object[0]);
     if ((this.sovaovien_tu != null) && (!this.sovaovien_tu.equals(""))) {
       try
       {
         HsbaDelegate hsbaDAO = HsbaDelegate.getInstance();
         Hsba hsbaTmp = hsbaDAO.find(this.sovaovien_tu);
         if (hsbaTmp == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.sovaovien_tu });
           this.sovaovien_tu = "";
           return;
         }
         this.sovaovien_tu = hsbaTmp.getHsbaSovaovien();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.sovaovien_tu });
         e.printStackTrace();
       }
     }
   }

   public void loadSoVaoVienDen()
   {
     this.log.info("-----So vao vien-----" + this.sovaovien_den, new Object[0]);
     if ((this.sovaovien_den != null) && (!this.sovaovien_den.equals(""))) {
       try
       {
         HsbaDelegate hsbaDAO = HsbaDelegate.getInstance();
         Hsba hsbaTmp = hsbaDAO.find(this.sovaovien_den);
         if (hsbaTmp == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.sovaovien_den });
           this.sovaovien_den = "";
           return;
         }
         this.sovaovien_den = hsbaTmp.getHsbaSovaovien();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.sovaovien_den });
         e.printStackTrace();
       }
     }
   }

   public void resetForm()
   {
     this.sovaovien_den = "";
     this.sovaovien_tu = "";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "Soravaovien";
     this.log.info("Vao Method XuatReport so ra vao vien", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/sovaovienravien.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       Map<String, Object> params = new HashMap();
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       if (!this.sovaovien_tu.trim().equals("")) {
         params.put("SOBATU", this.sovaovien_tu.trim());
       }
       if (!this.sovaovien_den.trim().equals("")) {
         params.put("SOBADEN", this.sovaovien_den.trim());
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Soravaovien");
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

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getSovaovien_tu()
   {
     return this.sovaovien_tu;
   }

   public void setSovaovien_tu(String sovaovien_tu)
   {
     this.sovaovien_tu = sovaovien_tu;
   }

   public String getSovaovien_den()
   {
     return this.sovaovien_den;
   }

   public void setSovaovien_den(String sovaovien_den)
   {
     this.sovaovien_den = sovaovien_den;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.Soravaovien

 * JD-Core Version:    0.7.0.1

 */