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
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3326_Phantichtienphongmotheoyc")
 @Synchronized(timeout=6000000L)
 public class PhanTichTienPhongMoYc
   implements Serializable
 {
   private static Logger log = Logger.getLogger(PhanTichTienPhongMoYc.class);
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private DmKhoa khoa;
   private Boolean moYc;
   private Boolean phongYc;
   private String ptTheo;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";

   @Begin(join=true)
   public String init()
   {
     log.debug("-----init()-----");
     reset();
     return "BaoCaoVienPhi_HoSoBaoCao_PhanTichTienPhongMoYeuCau";
   }

   @End
   public void endFunct() {}

   public void reset()
   {
     this.khoa = new DmKhoa();
     this.moYc = Boolean.valueOf(false);
     this.phongYc = Boolean.valueOf(false);
     this.ptTheo = "0";
     this.ngayhientai = Utils.getCurrentDate();
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   int index = 0;

   public void XuatReport()
   {
     this.reportTypeVP = "V11_PTTienphongvamoyeucau";
     log.info("Vao Method XuatReport So lieu thu chi toan vien");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       if ("0".equals(this.ptTheo)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V11_PTTienphongvamoyeucau.jrxml";
       } else if ("1".equals(this.ptTheo)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V12_PTTienphongvamoyeucautheongay.jrxml";
       } else if ("2".equals(this.ptTheo)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V13_PTTienphongvamoyeucau.jrxml";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V11_PTTienphongvamoyeucau.jrxml";
       }
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();

       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TuNgay", sdf.parse(this.tungay));
       params.put("DenNgay", sdf.parse(this.denngay));
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       String tenKhoa = "";
       if ((this.khoa.getDmkhoaMa() == null) || (this.khoa.getDmkhoaMa().equals("")))
       {
         this.khoa = new DmKhoa();
       }
       else
       {
         DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();
         DmKhoa khoa_temp = (DmKhoa)dtDelegate.findByMa(this.khoa.getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
         if (khoa_temp != null) {
           tenKhoa = khoa_temp.getDmkhoaTen();
         }
       }
       params.put("MaSoKhoa", this.khoa.getDmkhoaMaso());
       params.put("MaKhoa", this.khoa.getDmkhoaMa());
       params.put("PhongYC", this.phongYc);
       params.put("MoYC", this.moYc);
       params.put("TenKhoa", tenKhoa);



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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "V11_PTTienphongvamoyeucau");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       log.info("Index :" + this.index);
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

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "/B3_Vienphi/BaoCao/B3326_Phantichtienphongmotheoyeucau.xhtml";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public DmKhoa getKhoa()
   {
     return this.khoa;
   }

   public void setKhoa(DmKhoa khoa)
   {
     this.khoa = khoa;
   }

   public Boolean getMoYc()
   {
     return this.moYc;
   }

   public void setMoYc(Boolean moYc)
   {
     this.moYc = moYc;
   }

   public Boolean getPhongYc()
   {
     return this.phongYc;
   }

   public void setPhongYc(Boolean phongYc)
   {
     this.phongYc = phongYc;
   }

   public String getPtTheo()
   {
     return this.ptTheo;
   }

   public void setPtTheo(String ptTheo)
   {
     this.ptTheo = ptTheo;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
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

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.PhanTichTienPhongMoYc

 * JD-Core Version:    0.7.0.1

 */