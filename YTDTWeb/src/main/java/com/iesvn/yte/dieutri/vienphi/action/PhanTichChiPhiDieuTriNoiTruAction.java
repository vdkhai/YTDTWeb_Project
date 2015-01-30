 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDoiTuong;
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
 @Name("B3327_Phantichchiphidieutrinoitru")
 @Synchronized(timeout=6000000L)
 public class PhanTichChiPhiDieuTriNoiTruAction
   implements Serializable
 {
   private static Logger logger = Logger.getLogger(PhanTichChiPhiDieuTriNoiTruAction.class);
   private String thangHt;
   private String namHt;
   private String ngayHt;
   private String tuNgay;
   private String denNgay;
   private DmKhoa khoa;
   private DmDoiTuong dt;
   @Out(required=false)
   @In(required=false)
   private String loaiBC = null;

   @Begin(join=true)
   public String init(String loai)
   {
     logger.debug("-----init()-----");
     this.loaiBC = loai;
     reset();
     return "BaoCaoVienPhi_HoSoBaoCao_PhanTichChiPhiDieuTriNoiTru";
   }

   @End
   public void endFunc() {}

   public void reset()
   {
     this.ngayHt = Utils.getCurrentDate();
     this.khoa = new DmKhoa();
     this.dt = new DmDoiTuong();


     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.thangHt = String.valueOf(currentDate.getMonth() + 1);
     this.namHt = String.valueOf(currentDate.getYear() + 1900);
     this.tuNgay = sdf.format(currentDate);
     this.denNgay = sdf.format(currentDate);
   }

   int index = 0;

   public String thuchienAction()
   {
     XuatReport();

     return "B3360_Chonmenuxuattaptin";
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

   public void XuatReport()
   {
     this.reportTypeVP = "V14_Thanhtoanchiphibenhnhandieutridoituongthuphi";
     logger.info("Vao Method XuatReport V14_Thanhtoanchiphibenhnhandieutridoituongthuphi");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       logger.info("bat dau method XuatReport ");
       if ("dieutrinoitru".equals(this.loaiBC)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V14_Thanhtoanchiphibenhnhandieutridoituongthuphi.jrxml";
       } else if ("tienphong".equals(this.loaiBC)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V16_PTTienphong.jrxml";
       } else if ("mienphingoaitru".equals(this.loaiBC)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V18_THChiphimienphidantocngoaitru.jrxml";
       } else if ("mienphinoitru".equals(this.loaiBC)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V18b_THChiphimienphidantocnoitru.jrxml";
       }
       logger.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();

       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("tungay", formatter.parse(this.tuNgay));
       params.put("denngay", formatter.parse(this.denNgay));



       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       DmDoiTuong dmDT = null;
       if ((this.dt.getDmdoituongMa() != null) && (!this.dt.getDmdoituongMa().equals(""))) {
         dmDT = (DmDoiTuong)dtUtils.findByMa(this.dt.getDmdoituongMa(), "DmDoiTuong", "dmdoituongMa");
       }
       if (dmDT != null)
       {
         params.put("TENDOITUONG", dmDT.getDmdoituongTen());
         params.put("MADOITUONG", dmDT.getDmdoituongMa());
         params.put("MaSoDoiTuong", dmDT.getDmdoituongMaso());
         logger.info("MaSoDoiTuong:" + dmDT.getDmdoituongMaso());
       }
       else
       {
         params.put("TENDOITUONG", null);
         params.put("MADOITUONG", null);
         params.put("MaSoDoiTuong", null);
         logger.info("MaSoDoiTuong:");
       }
       DmKhoa khoaTmp = null;
       if ((this.khoa.getDmkhoaMa() != null) && (!this.khoa.getDmkhoaMa().equals(""))) {
         khoaTmp = (DmKhoa)dtUtils.findByMa(this.khoa.getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
       }
       if (khoaTmp != null)
       {
         params.put("MaSoKhoa", khoaTmp.getDmkhoaMaso());
         params.put("TenKhoa", khoaTmp.getDmkhoaTen());
       }
       else
       {
         params.put("MaSoKhoa", null);
         params.put("TenKhoa", null);
       }
       logger.info("tungay:" + formatter.parse(this.tuNgay));
       logger.info("denngay:" + formatter.parse(this.denNgay));


       logger.info("End set params cho report");

       logger.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       logger.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         logger.info(e.getMessage());
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "V14_Thanhtoanchiphibenhnhandieutridoituongthuphi");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       logger.info("duong dan file xuat report :" + baocao1);
       logger.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       logger.info("Index :" + this.index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     logger.info("Thoat Method XuatReport");
   }

   public String troVe()
   {
     try
     {
       logger.info("***** troVe()");
       return "/B3_Vienphi/BaoCao/B3327_Phantichchiphidieutrinoitru.xhtml";
     }
     catch (Exception e)
     {
       logger.info("***** End exception = " + e);
     }
     return null;
   }

   public String getThangHt()
   {
     return this.thangHt;
   }

   public void setThangHt(String thangHt)
   {
     this.thangHt = thangHt;
   }

   public String getNamHt()
   {
     return this.namHt;
   }

   public void setNamHt(String namHt)
   {
     this.namHt = namHt;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public DmKhoa getKhoa()
   {
     return this.khoa;
   }

   public void setKhoa(DmKhoa khoa)
   {
     this.khoa = khoa;
   }

   public String getTuNgay()
   {
     return this.tuNgay;
   }

   public void setTuNgay(String tuNgay)
   {
     this.tuNgay = tuNgay;
   }

   public String getDenNgay()
   {
     return this.denNgay;
   }

   public void setDenNgay(String denNgay)
   {
     this.denNgay = denNgay;
   }

   public DmDoiTuong getDt()
   {
     return this.dt;
   }

   public void setDt(DmDoiTuong dt)
   {
     this.dt = dt;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.PhanTichChiPhiDieuTriNoiTruAction

 * JD-Core Version:    0.7.0.1

 */