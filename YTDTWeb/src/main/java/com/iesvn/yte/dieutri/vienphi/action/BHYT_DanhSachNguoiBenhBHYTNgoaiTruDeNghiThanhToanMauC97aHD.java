 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
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
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.security.Restrict;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3340_DSNguoiBenhKCBNgoaiTru_Mau97aHD")
 @Synchronized(timeout=6000000L)
 public class BHYT_DanhSachNguoiBenhBHYTNgoaiTruDeNghiThanhToanMauC97aHD
   implements Serializable
 {
   private static Logger log = Logger.getLogger(B3335_BaoCaoBHYTNgoaiTru.class);
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private Integer maSoKCBBHYT = null;
   private String maKCBBHYT = "";
   private Integer maSoPLBHYT = null;
   private String maPLBHYT = "";
   private Integer tuyen;
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
   String mauSo = null;
   private int index = 0;

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Begin(join=true)
   public String init(String mauSoTemp)
     throws Exception
   {
     log.info("***Starting init ***");
     emtyData();
     log.info("***Finished init ***");

     SimpleDateFormat format = new SimpleDateFormat(Utils.FORMAT_DATE);

     this.ngayhientai = format.format(new Date());

     this.mauSo = mauSoTemp;

     return "BaoCaoVienPhi_BaoCaoBHYT_BHYT_DanhSachNguoiBenhKCBNgoaiTruDeNghiThanhToanMauC98aHD";
   }

   @End
   public void endFunct() {}

   public void emtyData()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);

     setMaPLBHYT("");
     setTuyen(null);

     this.maSoKCBBHYT = null;
     this.maKCBBHYT = "";
   }

   public void resetValue()
   {
     emtyData();
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeVP = "V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD";
     log.info("Vao Method XuatReport bao cao V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       Map<String, Object> params = new HashMap();
       if ("C97aHD".equals(this.mauSo))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/MauC79aHD_Template.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/MauC79aHD_Template_TramYTe.jrxml";
         log.info("da thay file templete " + pathTemplate);
         log.info("da thay file sub 0 templete " + sub0Template);
         log.info("da thay file sub 1 templete " + sub1Template);


         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);

         params.put("noitru", Boolean.valueOf(false));
       }
       else if ("C80aHD".equals(this.mauSo))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/MauC79aHD_Template.jrxml";
         String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V26_DSNguoibenhkhamchuabenhnoitrudenghithanhtoan_MauC80aHD.jrxml";
         String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/MauC79aHD_Template_TramYTe.jrxml";
         log.info("da thay file templete " + pathTemplate);
         log.info("da thay file sub 0 templete " + sub0Template);
         log.info("da thay file sub 1 templete " + sub1Template);


         JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
         JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

         params.put("sub0", sub0Report);
         params.put("sub1", sub1Report);

         params.put("noitru", Boolean.valueOf(true));
       }
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);






       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TuNgay", sdf.parse(this.tungay));

       params.put("DenNgay", sdf.parse(this.denngay));

       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       if ((this.maPLBHYT != null) && (!this.maPLBHYT.equals("")))
       {
         DtDmPlBhyt plbhyt = new DtDmPlBhyt();
         Object obj = dtUtils.findByMa(this.maPLBHYT, "DtDmPlBhyt", " dtdmphloaibhytMa");
         plbhyt = (DtDmPlBhyt)obj;
         params.put("TenPLDoiTuong", plbhyt.getDtdmphloaibhytTen());
         params.put("MaPLDoiTuong", plbhyt.getDtdmphloaibhytMa());
         log.info(" ma phan loai " + this.maPLBHYT + "ten phan loai " + plbhyt.getDtdmphloaibhytTen());
       }
       if ((this.maKCBBHYT != null) && (!this.maKCBBHYT.equals("")))
       {
         DtDmKcbBhyt kcbbhyt = new DtDmKcbBhyt();
         Object obj = dtUtils.findByMa(this.maKCBBHYT, "DtDmKcbBhyt", "dtdmkcbbhytMa");
         kcbbhyt = (DtDmKcbBhyt)obj;
         params.put("TenKCBBHYT", kcbbhyt.getDtdmkcbbhytTen());
         params.put("MaKCBBHYT", kcbbhyt.getDtdmkcbbhytMa());
         log.info(" ma phan loai " + this.maKCBBHYT + "ten phan loai " + kcbbhyt.getDtdmkcbbhytTen());
       }
       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "V25_DSNguoibenhkhamchuabenhngoaitrudenghithanhtoan_MauC97aHD");



       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       log.info("Index :" + getIndex());
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   public Integer getTuyen()
   {
     return this.tuyen;
   }

   public void setTuyen(Integer tuyen)
   {
     this.tuyen = tuyen;
   }

   public static Logger getLog()
   {
     return log;
   }

   public Integer getMaSoPLBHYT()
   {
     return this.maSoPLBHYT;
   }

   public void setMaSoPLBHYT(Integer maSoPLBHYT)
   {
     this.maSoPLBHYT = maSoPLBHYT;
   }

   public String getMaPLBHYT()
   {
     return this.maPLBHYT;
   }

   public void setMaPLBHYT(String maPLBHYT)
   {
     this.maPLBHYT = maPLBHYT;
   }

   public void ghinhan()
     throws Exception
   {}

   public void nhaplai()
     throws Exception
   {
     ResetForm();
   }

   private void ResetForm()
   {
     log.info("Begining ResetForm(): ");
     emtyData();
     log.info("End ResetForm(): ");
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

   public String getMauSo()
   {
     return this.mauSo;
   }

   public void setMauSo(String mauSo)
   {
     this.mauSo = mauSo;
   }

   public Integer getMaSoKCBBHYT()
   {
     return this.maSoKCBBHYT;
   }

   public void setMaSoKCBBHYT(Integer maSoKCBBHYT)
   {
     this.maSoKCBBHYT = maSoKCBBHYT;
   }

   public String getMaKCBBHYT()
   {
     return this.maKCBBHYT;
   }

   public void setMaKCBBHYT(String maKCBBHYT)
   {
     this.maKCBBHYT = maKCBBHYT;
   }

   public String getReportPathVP()
   {
     return this.reportPathVP;
   }

   public void setReportPathVP(String reportPathVP)
   {
     this.reportPathVP = reportPathVP;
   }

   public String getReportTypeVP()
   {
     return this.reportTypeVP;
   }

   public void setReportTypeVP(String reportTypeVP)
   {
     this.reportTypeVP = reportTypeVP;
   }

   public JasperPrint getJasperPrintVP()
   {
     return this.jasperPrintVP;
   }

   public void setJasperPrintVP(JasperPrint jasperPrintVP)
   {
     this.jasperPrintVP = jasperPrintVP;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BHYT_DanhSachNguoiBenhBHYTNgoaiTruDeNghiThanhToanMauC97aHD

 * JD-Core Version:    0.7.0.1

 */