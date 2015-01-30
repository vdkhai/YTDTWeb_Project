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
 import java.util.HashMap;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B3134_Xeminlientucphieucongkhai")
 @Scope(ScopeType.CONVERSATION)
 public class Xeminlientucphieucongkhai
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
   private String resetFormB3134 = null;
   private int index = 0;
   private String ngayhientai = "";
   private String khoaMa = "";
   private String ngaydungstr = "";

   @Begin(join=true)
   public String init()
   {
     this.log.info("init()", new Object[0]);
     initresetFormB3134();
     return "VienPhiTaiKhoa_XemInPhieu_XemInLienTucPhieuCongKhai";
   }

   @Factory("resetFormB3134")
   public void initresetFormB3134()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB3134 = null;
     return "B3360_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.resetFormB3134 = null;
     this.khoaMa = "";
     this.ngaydungstr = "";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "Xeminlientucphieucongkhai";
     this.log.info("Vao Method XuatReport Xem in lien tuc phieu cong khai", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/xeminlientucphieucongkhai.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("NGAYDUNG", this.ngaydungstr);
       params.put("NGAYDUNGDATE", sdf.parse(this.ngaydungstr));
       if (!this.khoaMa.equals(""))
       {
         DmKhoa dmkhoa = new DmKhoa();
         DieuTriUtilDelegate dtUtilDAO = DieuTriUtilDelegate.getInstance();
         Object obj = dtUtilDAO.findByMa(this.khoaMa, "DmKhoa", "dmkhoaMa");
         dmkhoa = (DmKhoa)obj;
         params.put("KHOA", dmkhoa.getDmkhoaTen());
         params.put("KHOAMASO", dmkhoa.getDmkhoaMaso());
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Xeminlientucphieucongkhai");
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

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public String getNgaydungstr()
   {
     return this.ngaydungstr;
   }

   public void setNgaydungstr(String ngaydungstr)
   {
     this.ngaydungstr = ngaydungstr;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.Xeminlientucphieucongkhai

 * JD-Core Version:    0.7.0.1

 */