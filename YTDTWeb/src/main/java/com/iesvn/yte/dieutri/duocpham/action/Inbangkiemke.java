 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
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
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.log.Log;

 @Name("B4145_Inbangkiemke")
 @Scope(ScopeType.CONVERSATION)
 public class Inbangkiemke
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
   @Out(required=false)
   @In(required=false)
   private String resetFormB4145 = null;
   private Integer inTheo = null;
   private int index = 0;
   private String ngayhientai;
   private Integer loaihang_maso = null;
   private Integer plthuoc_maso = null;
   private Integer khoa_maso = null;
   private Integer ct_maso = null;
   private Integer kp_maso = null;
   private String plthuoc_ma = "";
   private String loaihang_ma = "";
   private String khoa_ma = "";
   private String ct_ma = "";
   private String kp_ma = "";
   private String thoidiemtinh = "";
   private Boolean chonTK;
   private Boolean chonGD;
   private Boolean chonGT;
   private Boolean chonTT;

   @Factory("resetFormB4145")
   public void initresetFormB4145()
   {
     this.resetFormB4145 = "";
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     this.thoidiemtinh = Utils.getCurrentDate();
     resetForm();
   }

   String dmKhoXuat = "";

   @Begin(join=true)
   public String init(String kho)
   {
     this.dmKhoXuat = kho;
     return "QuanLyKhoChinh_KiemKeKhoChinh_InBangKiemKe";
   }

   @End
   public void end() {}

   public void resetForm()
   {
     setPlthuoc_ma("");
     setLoaihang_ma("");
     setCt_ma("");
     setKp_ma("");
     setInTheo(new Integer(2));
     setChonGD(Boolean.valueOf(true));
     setChonGT(Boolean.valueOf(true));
     setChonTK(Boolean.valueOf(true));
     setChonTT(Boolean.valueOf(true));
   }

   public void XuatReport()
   {
     this.reportTypeKC = "inbangkiemke";
     this.log.info("Vao Method XuatReport in bang kiem ke", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       this.log.info("bat dau method XuatReport ", new Object[0]);
       if (this.inTheo.intValue() != 1) {
         if (this.inTheo.intValue() == 2) {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/D13_bangkiemkevattucongcusanphamhanghoa_tenhang.jrxml";
         }
       }
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params = new HashMap();
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       this.log.info("chon cot thua thieu " + this.chonTT, new Object[0]);
       if (!this.chonTT.booleanValue()) {
         params.put("CHONTT", null);
       } else {
         params.put("CHONTT", "");
       }
       this.log.info("chon cot ton kho " + this.chonTK, new Object[0]);
       if (!this.chonTK.booleanValue()) {
         params.put("CHONTK", null);
       } else {
         params.put("CHONTK", "");
       }
       this.log.info("chon cot ton kho " + this.chonTT, new Object[0]);
       if (!this.chonTT.booleanValue()) {
         params.put("CHONTT", null);
       } else {
         params.put("CHONTT", "");
       }
       if (!this.chonGT.booleanValue()) {
         params.put("CHONGT", null);
       } else {
         params.put("CHONGT", "");
       }
       this.log.info("chonGT: " + this.chonGT, new Object[0]);



       this.log.info("thoi diem tinh " + this.thoidiemtinh, new Object[0]);
       if (!this.thoidiemtinh.equals("")) {
         params.put("TINHDENNGAY", sdf.parse(this.thoidiemtinh));
       }
       if (this.loaihang_ma.trim().equals("")) {
         setLoaihang_ma(null);
       } else {
         params.put("LTHUOCMASO", this.loaihang_maso);
       }
       if (this.khoa_ma.trim().equals("")) {
         setKhoa_ma(null);
       } else {
         params.put("KHOAMASO", this.khoa_maso);
       }
       if (this.ct_ma.trim().equals("")) {
         setCt_ma(null);
       } else {
         params.put("NCTMASO", this.ct_maso);
       }
       if (this.kp_ma.trim().equals("")) {
         setKp_ma(null);
       } else {
         params.put("NKPMASO", this.kp_maso);
       }
       if (this.plthuoc_ma.trim().equals("")) {
         setPlthuoc_ma(null);
       } else {
         params.put("PLTHUOCMASO", this.plthuoc_maso);
       }
       this.log.info("=========Thong so truoc khi tim kiem================", new Object[0]);
       this.log.info("kinh phi " + this.kp_ma, new Object[0]);
       this.log.info("chuong trinh " + this.ct_ma, new Object[0]);
       this.log.info("khoa " + this.khoa_ma, new Object[0]);
       this.log.info("loai hang " + this.loaihang_ma, new Object[0]);
       this.log.info("phan loai loai hang " + this.plthuoc_ma, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "inbangkiemke");
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

   public String thuchienAction()
   {
     XuatReport();
     this.resetFormB4145 = null;
     return "B4160_Chonmenuxuattaptin";
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihang_maso)
   {
     this.loaihang_maso = loaihang_maso;
   }

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuoc_maso)
   {
     this.plthuoc_maso = plthuoc_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Integer getCt_maso()
   {
     return this.ct_maso;
   }

   public void setCt_maso(Integer ct_maso)
   {
     this.ct_maso = ct_maso;
   }

   public Integer getKp_maso()
   {
     return this.kp_maso;
   }

   public void setKp_maso(Integer kp_maso)
   {
     this.kp_maso = kp_maso;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihang_ma)
   {
     this.loaihang_ma = loaihang_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public String getCt_ma()
   {
     return this.ct_ma;
   }

   public void setCt_ma(String ct_ma)
   {
     this.ct_ma = ct_ma;
   }

   public String getKp_ma()
   {
     return this.kp_ma;
   }

   public void setKp_ma(String kp_ma)
   {
     this.kp_ma = kp_ma;
   }

   public void setInTheo(Integer inTheo)
   {
     this.inTheo = inTheo;
   }

   public Integer getInTheo()
   {
     return this.inTheo;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setThoidiemtinh(String thoidiemtinh)
   {
     this.thoidiemtinh = thoidiemtinh;
   }

   public String getThoidiemtinh()
   {
     return this.thoidiemtinh;
   }

   public Boolean getChonTK()
   {
     return this.chonTK;
   }

   public void setChonTK(Boolean chonTK)
   {
     this.chonTK = chonTK;
   }

   public Boolean getChonGD()
   {
     return this.chonGD;
   }

   public void setChonGD(Boolean chonGD)
   {
     this.chonGD = chonGD;
   }

   public Boolean getChonGT()
   {
     return this.chonGT;
   }

   public void setChonGT(Boolean chonGT)
   {
     this.chonGT = chonGT;
   }

   public Boolean getChonTT()
   {
     return this.chonTT;
   }

   public void setChonTT(Boolean chonTT)
   {
     this.chonTT = chonTT;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public String getReportPathKC()
   {
     return this.reportPathKC;
   }

   public void setReportPathKC(String reportPathKC)
   {
     this.reportPathKC = reportPathKC;
   }

   public String getReportTypeKC()
   {
     return this.reportTypeKC;
   }

   public void setReportTypeKC(String reportTypeKC)
   {
     this.reportTypeKC = reportTypeKC;
   }

   public JasperPrint getJasperPrintKC()
   {
     return this.jasperPrintKC;
   }

   public void setJasperPrintKC(JasperPrint jasperPrintKC)
   {
     this.jasperPrintKC = jasperPrintKC;
   }

   public String getResetFormB4145()
   {
     return this.resetFormB4145;
   }

   public void setResetFormB4145(String resetFormB4145)
   {
     this.resetFormB4145 = resetFormB4145;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Inbangkiemke

 * JD-Core Version:    0.7.0.1

 */