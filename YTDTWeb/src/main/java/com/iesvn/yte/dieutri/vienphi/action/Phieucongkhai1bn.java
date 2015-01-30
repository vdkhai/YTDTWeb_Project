 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.HoSoThanhToanUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import com.lowagie.text.pdf.PdfCopyFields;
 import com.lowagie.text.pdf.PdfReader;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
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

 @Name("B3133_Phieucongkhai1bn")
 @Scope(ScopeType.CONVERSATION)
 public class Phieucongkhai1bn
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
   private String resetFormB3133 = null;
   private HsbaKhoa hsbaKhoa = null;
   private Hsba hsba = null;
   private HsbaBhyt hsbaBhyt = null;
   private int index = 0;
   private String ngayhientai = "";
   private String khoaMa = "";
   private String hsbaMa = "";
   private String ngaydungstr = "";
   private String denngayStr = "";
   private String fromMenu = "";

   @Begin(join=true)
   public String init(String fromType)
   {
     this.log.info("init(), fromType : " + fromType, new Object[0]);
     initresetFormB3133();
     this.fromMenu = fromType;
     return "VienPhiTaiKhoa_XemInPhieu_PhieuCongKhaiCuaBN";
   }

   @End
   public void EndFunc() {}

   @Factory("resetFormB3133")
   public void initresetFormB3133()
   {
     this.log.info("initresetFormB3133()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   public void loadHsba()
   {
     this.log.info("-----loadHsba()-----", new Object[0]);
     this.hsbaKhoa = new HsbaKhoa();
     this.hsba = new Hsba();
     this.hsbaBhyt = new HsbaBhyt();
     if ((!this.khoaMa.equals("")) && (!this.hsbaMa.equals("")))
     {
       HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
       try
       {
         HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();

         this.hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(this.hsbaMa, this.khoaMa);
         if (this.hsbaKhoa == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.hsbaMa });
           this.hsbaMa = "";
           return;
         }
         HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
         this.hsba = hsbaDelegate.find(this.hsbaMa);
         if (this.hsba == null)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.hsbaMa });
           this.hsbaMa = "";
           return;
         }
         this.hsbaMa = this.hsba.getHsbaSovaovien();
         this.log.info("-----hsba: " + this.hsba, new Object[0]);
         this.log.info("-----ho ten: " + this.hsba.getBenhnhanMa().getBenhnhanHoten(), new Object[0]);
         this.hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hsbaMa);
         if (this.hsbaBhyt == null) {
           this.hsbaBhyt = new HsbaBhyt();
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.hsbaMa });
         e.printStackTrace();
       }
     }
   }

   public String thuchienAction()
   {
     HsThtoan hsbaHsThtoan_temp = HsThtoanDelegate.getInstance().findBySovaovien(this.hsba.getHsbaSovaovien());
     if ((hsbaHsThtoan_temp != null) && (hsbaHsThtoan_temp.getHsthtoanNgaytt() == null))
     {
       HsThtoan hsThtoan = new HsThtoan();
       hsThtoan.setHsbaSovaovien(this.hsba);
       HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(this.hsba);
       hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
     }
     XuatReport2();
     this.resetFormB3133 = null;
     return "B3360_Chonmenuxuattaptin";
   }

   public void resetForm()
   {
     this.khoaMa = "";
     this.hsbaMa = "";
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.ngaydungstr = sdf.format(currentDate);
     this.denngayStr = sdf.format(currentDate);
     this.hsbaKhoa = new HsbaKhoa();
     this.hsba = new Hsba();
     this.hsbaBhyt = new HsbaBhyt();
     FacesMessages.instance().clear();
   }

   public void XuatReport()
   {
     this.reportTypeVP = "Phieucongkhai1bn";
     this.log.info("Vao Method XuatReport phieu cong khai 1 benh nhan", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     String pathTemplate_sub1 = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieucongkhaicuabenhnhan.jrxml";
       pathTemplate_sub1 = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieucongkhaicuabenhnhan_sub1.jrxml";
       this.log.info("da thay file templete 5" + pathTemplate, new Object[0]);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       this.log.info("da thay file template ", new Object[0]);
       this.log.info("da thay file templete 5" + pathTemplate_sub1, new Object[0]);
       JasperReport sub1Report = JasperCompileManager.compileReport(pathTemplate_sub1);
       this.log.info("da thay file template ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("SUBREPORT_DIR", sub1Report);
       if (this.hsbaKhoa.getHsbaSovaovien() != null)
       {
         params.put("HSBAKHOAMASO", this.hsbaKhoa.getHsbakhoaMaso());
         params.put("TENNGUOIBENH", this.hsba.getBenhnhanMa().getBenhnhanHoten());
         params.put("NGAYDUNG", this.ngaydungstr);
         params.put("NGAYDUNGDATE", sdf.parse(this.ngaydungstr));
         params.put("DENNGAY", this.denngayStr);
         params.put("DENNGAYDATE", sdf.parse(this.denngayStr));
         String temp = "";
         if (this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 2) {
           temp = temp + IConstantsRes.THANG;
         } else if (this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 3) {
           temp = temp + IConstantsRes.NAM;
         }
         params.put("TUOI", String.valueOf(this.hsba.getBenhnhanMa().getBenhnhanTuoi()) + " " + temp);
         params.put("GIOITINH", this.hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());
         if (this.hsba.getHsbaNgaygiovaov() != null) {
           params.put("NGAYVAOVIEN", sdf.format(this.hsba.getHsbaNgaygiovaov()));
         }
         if (this.hsba.getHsbaNgaygiorav() != null) {
           params.put("NGAYRAVIEN", sdf.format(this.hsba.getHsbaNgaygiorav()));
         }
         params.put("CHANDOAN", this.hsba.getHsbaMachdoanbd(true).getDmbenhicdTen());

         params.put("SOBENHAN", this.hsbaMa);
         params.put("KHOAMA", this.khoaMa);
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Phieucongkhai1bn");
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

   public void XuatReport2()
   {
     this.reportTypeVP = "Phieucongkhai1bn";
     this.log.info("Vao Method XuatReport phieu cong khai 1 benh nhan", new Object[0]);
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/phieucongkhaithuocvatonghopvienphi.jasper";
       this.log.info("da thay file template ", new Object[0]);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("SUBREPORT_DIR", IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/");
       if (this.hsba.getHsbaSovaovien() != null)
       {
         Date dTungay = sdf.parse(this.ngaydungstr);
         Date dDenngay = sdf.parse(this.denngayStr);
         Calendar cal = Calendar.getInstance();
         List<String> listTenfile = new ArrayList();
         params.put("SOVAOVIEN", this.hsbaMa);


         params.put("KHOA", this.hsbaKhoa.getKhoaMa() == null ? "" : this.hsbaKhoa == null ? "" : this.hsbaKhoa.getKhoaMa().getDmkhoaTen());
         params.put("KHOAMASO", this.hsbaKhoa.getKhoaMa() == null ? null : this.hsbaKhoa == null ? null : this.hsbaKhoa.getKhoaMa().getDmkhoaMaso());
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
         cal.setTimeInMillis(dTungay.getTime());
         Date tungayTmp = dTungay;
         while ((listTenfile.size() == 0) || (tungayTmp.compareTo(dDenngay) <= 0))
         {
           params.put("TUNGAY", tungayTmp);
           params.put("DENNGAY", tungayTmp);

           params.put("NGAY_01", tungayTmp);

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_02", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_03", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_04", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_05", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_06", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_07", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_08", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_09", cal.getTime());

           cal.add(5, 1);
           if (cal.getTime().compareTo(dDenngay) <= 0) {
             params.put("DENNGAY", cal.getTime());
           }
           params.put("NGAY_10", cal.getTime());

           cal.add(5, 1);
           tungayTmp = cal.getTime();

           this.jasperPrintVP = JasperFillManager.fillReport(pathTemplate, params, conn);
           baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Phieucongkhai1bn");
           listTenfile.add(baocao1);
           this.index += 1;
         }
         if (conn != null) {
           conn.close();
         }
         String appendFileName = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/PhieucongkhaithuocvaThvp_" + new Date().getTime() + ".pdf";
         PdfCopyFields copy = new PdfCopyFields(new FileOutputStream(appendFileName));
         for (String eachFile : listTenfile)
         {
           PdfReader reader = new PdfReader(eachFile);
           copy.addDocument(reader);
         }
         copy.close();
         for (String eachFile : listTenfile)
         {
           File f = new File(eachFile);
           f.delete();
         }
         this.reportPathVP = appendFileName.replaceFirst(IConstantsRes.PATH_BASE, "");
         this.log.info("duong dan -------------------- :" + this.reportPathVP, new Object[0]);
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

   public String getHsbaMa()
   {
     return this.hsbaMa;
   }

   public void setHsbaMa(String hsbaMa)
   {
     this.hsbaMa = hsbaMa;
   }

   public String getNgaydungstr()
   {
     return this.ngaydungstr;
   }

   public void setNgaydungstr(String ngaydungstr)
   {
     this.ngaydungstr = ngaydungstr;
   }

   public String getDenngayStr()
   {
     return this.denngayStr;
   }

   public void setDenngayStr(String denngayStr)
   {
     this.denngayStr = denngayStr;
   }

   public HsbaKhoa getHsbaKhoa()
   {
     return this.hsbaKhoa;
   }

   public void setHsbaKhoa(HsbaKhoa hsbaKhoa)
   {
     this.hsbaKhoa = hsbaKhoa;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public HsbaBhyt getHsbaBhyt()
   {
     return this.hsbaBhyt;
   }

   public void setHsbaBhyt(HsbaBhyt hsbaBhyt)
   {
     this.hsbaBhyt = hsbaBhyt;
   }

   public String getFromMenu()
   {
     return this.fromMenu;
   }

   public void setFromMenu(String fromMenu)
   {
     this.fromMenu = fromMenu;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.Phieucongkhai1bn

 * JD-Core Version:    0.7.0.1

 */