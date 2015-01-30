 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
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
 @Name("ThongKeSoLuotTungBN")
 @Synchronized(timeout=6000000L)
 public class BHYT_ThongKeSoLuotTungBN
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
   private Integer maSoNhomDT = null;
   private String maNhomDT = "";
   private String chonLoaiBaoCao = "";
   private Integer soDau;
   private Integer soCuoi;
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

     return "BaoCaoVienPhi_BHYT_ThongKeSoLuotTungBN";
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

     this.maSoKCBBHYT = null;
     this.maKCBBHYT = "";
     this.chonLoaiBaoCao = "5";
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
     this.reportTypeVP = "ThongKeSoLuotBN";
     log.info("Vao Method XuatReport bao cao ThongKeSoLuotBN");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       if ("tkso_luot_bn_ngoai_tru".equals(this.mauSo)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V34_ThongKeSoLuongTungBenhNhanDiKhamNgoaiTru.jrxml";
       } else if ("tkso_luot_bn_noi_tru".equals(this.mauSo)) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/V35_ThongKeSoLuongTungBenhNhanChuaTriNoiTru.jrxml";
       }
       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TuNgay", sdf.parse(this.tungay));

       params.put("DenNgay", sdf.parse(this.denngay));

       params.put("MaSoNhomDT", this.maSoNhomDT);
       params.put("MaNhomDT", this.maNhomDT);
       if ("1".equals(this.chonLoaiBaoCao))
       {
         params.put("SoDau", this.soDau);
         params.put("SoCuoi", this.soDau);
       }
       else if ("1".equals(this.chonLoaiBaoCao))
       {
         params.put("SoDau", this.soDau);
         params.put("SoCuoi", new Integer(1000));
       }
       else if ("1".equals(this.chonLoaiBaoCao))
       {
         params.put("SoDau", Integer.valueOf(1));
         params.put("SoCuoi", this.soDau);
       }
       else if ("1".equals(this.chonLoaiBaoCao))
       {
         params.put("SoDau", this.soDau);
         params.put("SoCuoi", this.soCuoi);
       }
       else if ("1".equals(this.chonLoaiBaoCao))
       {
         params.put("SoDau", Integer.valueOf(1));
         params.put("SoCuoi", Integer.valueOf(1000));
       }
       DieuTriUtilDelegate dtUtils = DieuTriUtilDelegate.getInstance();
       if ((this.maKCBBHYT != null) && (!this.maKCBBHYT.equals("")))
       {
         DtDmKcbBhyt kcbbhyt = new DtDmKcbBhyt();
         Object obj = dtUtils.findByMa(this.maKCBBHYT, "DtDmKcbBhyt", "dtdmkcbbhytMa");
         kcbbhyt = (DtDmKcbBhyt)obj;
         params.put("TenKCBBHYT", kcbbhyt.getDtdmkcbbhytTen());
         params.put("MaKCBBHYT", kcbbhyt.getDtdmkcbbhytMa());
         log.info(" ma phan loai " + this.maKCBBHYT + "ten phan loai " + kcbbhyt.getDtdmkcbbhytTen());
       }
       params.put("NoiTinh", IConstantsRes.NOI_TINH);



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

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "ThongKeSoLuotBN");



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

   public static Logger getLog()
   {
     return log;
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

   public Integer getMaSoNhomDT()
   {
     return this.maSoNhomDT;
   }

   public void setMaSoNhomDT(Integer maSoNhomDT)
   {
     this.maSoNhomDT = maSoNhomDT;
   }

   public String getMaNhomDT()
   {
     return this.maNhomDT;
   }

   public void setMaNhomDT(String maNhomDT)
   {
     this.maNhomDT = maNhomDT;
   }

   public String getChonLoaiBaoCao()
   {
     return this.chonLoaiBaoCao;
   }

   public void setChonLoaiBaoCao(String chonLoaiBaoCao)
   {
     this.chonLoaiBaoCao = chonLoaiBaoCao;
   }

   public Integer getSoDau()
   {
     return this.soDau;
   }

   public void setSoDau(Integer soDau)
   {
     this.soDau = soDau;
   }

   public Integer getSoCuoi()
   {
     return this.soCuoi;
   }

   public void setSoCuoi(Integer soCuoi)
   {
     this.soCuoi = soCuoi;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BHYT_ThongKeSoLuotTungBN

 * JD-Core Version:    0.7.0.1

 */