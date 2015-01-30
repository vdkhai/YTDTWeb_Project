 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuChamSocDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.PhieuChamSoc;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHocVi;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
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
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B240_Phieuchamsoc")
 @Synchronized(timeout=6000000L)
 public class B240_Phieuchamsoc
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
   private static final long serialVersionUID = 10L;
   private String hsbaMaso;
   private String tenKhoa;
   private String hotenBN;
   private String gioitinh;
   private String tuoi;
   private String donvituoi;
   private String sogiuong;
   private String sobuong;
   private String chandoan;
   private boolean isEdit;
   private Hsba hsba;
   private String gio;
   private String ngay;
   private String ngayhientai;
   private PhieuChamSoc pcs;
   private List<PhieuChamSoc> listPcs;
   private String loaiBA;
   private String tenBs = "";
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @Out(required=false)
   @In(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintDT = null;
   private static Logger log = Logger.getLogger(B240_Phieuchamsoc.class);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String initB240_Phieuchamsoc;

   @Factory("initB240_Phieuchamsoc")
   public void init()
   {
     this.tenChuongTrinh = MyMenuYTDTAction.vienPhiTaiKhoa;
     resetValue();
     SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());
     this.ngayhientai = df.format(cal.getTime());
     this.initB240_Phieuchamsoc = "";
     DtDmNhanVien nguoidung = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
     if (nguoidung != null) {
       this.tenBs = (nguoidung.getDmhocviMaso(true).getDmhocviMa() + ". " + nguoidung.getDtdmnhanvienTen());
     }
   }

   public void resetValue()
   {
     System.out.println("TÃªn ngÆ°á»�i Ä‘Äƒng nháº­p");
     this.hsbaMaso = (this.gio = this.ngay = this.tenKhoa = "");
     this.hotenBN = (this.gioitinh = this.tuoi = this.donvituoi = "");
     this.sogiuong = (this.sobuong = this.chandoan = this.loaiBA = "");
     this.pcs = new PhieuChamSoc();
     this.hsba = new Hsba();
     this.isEdit = false;
     this.listPcs = new ArrayList();
   }

   @End
   public void destroy() {}

   public void displayInfor()
   {
     log.info("Begin displayInfo, hsbaMaso = " + this.hsbaMaso);
     if (this.hsbaMaso.trim().length() > 0)
     {
       this.hsba = HsbaDelegate.getInstance().find(this.hsbaMaso);
       if (this.hsba != null)
       {
         this.hsbaMaso = this.hsba.getHsbaSovaovien();
         log.info("hsba.getHsbaKhoadangdt() = " + this.hsba.getHsbaKhoadangdt());
         this.tenKhoa = (this.hsba.getHsbaKhoadangdt() == null ? "" : this.hsba.getHsbaKhoadangdt().getDmkhoaTen());
         this.hotenBN = (this.hsba.getBenhnhanMa() == null ? "" : this.hsba.getBenhnhanMa().getBenhnhanHoten());
         this.gioitinh = (this.hsba.getBenhnhanMa().getDmgtMaso() == null ? "" : this.hsba.getBenhnhanMa() == null ? "" : this.hsba.getBenhnhanMa().getDmgtMaso().getDmgtMa());
         this.tuoi = ("" + this.hsba.getBenhnhanMa().getBenhnhanTuoi());
         this.donvituoi = (this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 2 ? "(ThÃ¡ng)" : this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 1 ? "" : this.hsba.getBenhnhanMa() == null ? "" : "(NgÃ y)");
         HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(this.hsbaMaso);
         this.sobuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSobuong());
         this.sogiuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong());
         this.chandoan = (this.hsba.getHsbaMachdoanbd() == null ? "" : this.hsba.getHsbaMachdoanbd().getDmbenhicdTen());
         this.loaiBA = (this.hsba.getHsbaType().trim().equalsIgnoreCase("BA_LUU") ? "1" : this.hsba.getHsbaType() == null ? "0" : "0");
         resetList();
         this.gio = (this.ngay = "");
         this.pcs = new PhieuChamSoc();
         this.isEdit = false;
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_KHONG_TON_TAI, new Object[] { this.hsbaMaso });
         resetValue();
       }
     }
     else
     {
       resetValue();
     }
     log.info("End displayInfo, listTdt.size = " + this.listPcs.size());
   }

   public void resetList()
   {
     this.listPcs.clear();
     PhieuChamSocDelegate pcsDel = PhieuChamSocDelegate.getInstance();
     this.listPcs = pcsDel.findByHsba(this.hsbaMaso);
   }

   public void addChitiet()
   {
     log.info("Begin addChitiet, hsba = " + this.hsba);
     Calendar cal = Calendar.getInstance();
     String gioTmp = this.gio.trim().length() < 1 ? "00:00" : this.gio;
     try
     {
       SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_TIME);
       cal.setTime(df.parse(this.ngay + " " + gioTmp));
       this.pcs.setPhieuchamsocNgaygio(cal.getTime());
       this.pcs.setHsbaSovaovien(this.hsba);
       this.pcs.setPhieuchamsocTenbs(this.tenBs);

       this.pcs = PhieuChamSocDelegate.getInstance().createPhieuChamSoc(this.pcs);




       resetList();

       this.pcs = new PhieuChamSoc();
       this.gio = (this.ngay = "");
       this.isEdit = false;
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
   }

   public void editChitiet(PhieuChamSoc pcsTmp)
   {
     log.info("Begin editChitiet, pcsTmp = " + pcsTmp);
     this.pcs = pcsTmp;
     this.isEdit = true;
     this.gio = showGio(this.pcs.getPhieuchamsocNgaygio());
     this.ngay = showNgay(this.pcs.getPhieuchamsocNgaygio());
     log.info("End editChitiet");
   }

   public void deleteChitiet(PhieuChamSoc pcsTmp)
   {
     this.listPcs.remove(pcsTmp);

     PhieuChamSocDelegate.getInstance().remove(pcsTmp);
     this.pcs = new PhieuChamSoc();
     this.gio = "";
     this.ngay = "";
     this.isEdit = false;
   }

   public String showGio(Date dateTmp)
   {
     Calendar cal = Calendar.getInstance();
     cal.setTime(dateTmp);
     String strGio = (cal.get(11) < 10 ? "0" + cal.get(11) : Integer.valueOf(cal.get(11))) + ":" + (cal.get(12) < 10 ? "0" + cal.get(12) : Integer.valueOf(cal.get(12)));
     return strGio;
   }

   public String showNgay(Date dateTmp)
   {
     SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
     Calendar cal = Calendar.getInstance();
     cal.setTime(dateTmp);
     String strNgay = df.format(cal.getTime());
     return strNgay;
   }

   public String showGioNgay(Date dateTmp)
   {
     return showGio(dateTmp) + " " + showNgay(dateTmp);
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "phieuchamsoc";
     log.info("Vao Method XuatReport To Dieu tri");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       log.info("bat dau method XuatReport ");
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/D09_phieuchamsoc.jrxml";


       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       params.put("tenso", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sovaovien", this.hsbaMaso);
       params.put("hoten", this.hotenBN);
       params.put("tuoi", this.tuoi);

       params.put("gioitinh", this.gioitinh);
       params.put("khoa", this.tenKhoa);
       params.put("buong", this.sobuong);
       params.put("giuong", this.sogiuong);
       params.put("chandoan", this.chandoan);
       params.put("loaiba", this.loaiBA);
       params.put("donvituoi", "" + this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue());
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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "phieuchamsoc");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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

   public String getHsbaMaso()
   {
     return this.hsbaMaso;
   }

   public void setHsbaMaso(String hsbaMaso)
   {
     this.hsbaMaso = hsbaMaso;
   }

   public String getTenKhoa()
   {
     return this.tenKhoa;
   }

   public void setTenKhoa(String tenKhoa)
   {
     this.tenKhoa = tenKhoa;
   }

   public String getHotenBN()
   {
     return this.hotenBN;
   }

   public void setHotenBN(String hotenBN)
   {
     this.hotenBN = hotenBN;
   }

   public String getGioitinh()
   {
     return this.gioitinh;
   }

   public void setGioitinh(String gioitinh)
   {
     this.gioitinh = gioitinh;
   }

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getDonvituoi()
   {
     return this.donvituoi;
   }

   public void setDonvituoi(String donvituoi)
   {
     this.donvituoi = donvituoi;
   }

   public String getSogiuong()
   {
     return this.sogiuong;
   }

   public void setSogiuong(String sogiuong)
   {
     this.sogiuong = sogiuong;
   }

   public String getSobuong()
   {
     return this.sobuong;
   }

   public void setSobuong(String sobuong)
   {
     this.sobuong = sobuong;
   }

   public String getChandoan()
   {
     return this.chandoan;
   }

   public void setChandoan(String chandoan)
   {
     this.chandoan = chandoan;
   }

   public String getGio()
   {
     return this.gio;
   }

   public void setGio(String gio)
   {
     this.gio = gio;
   }

   public String getNgay()
   {
     return this.ngay;
   }

   public void setNgay(String ngay)
   {
     this.ngay = ngay;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public PhieuChamSoc getPcs()
   {
     return this.pcs;
   }

   public void setPcs(PhieuChamSoc pcs)
   {
     this.pcs = pcs;
   }

   public List<PhieuChamSoc> getListPcs()
   {
     return this.listPcs;
   }

   public void setListPcs(List<PhieuChamSoc> listPcs)
   {
     this.listPcs = listPcs;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B240_Phieuchamsoc

 * JD-Core Version:    0.7.0.1

 */