 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ToDieuTriDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.ToDieuTri;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHocVi;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
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
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B239_Todieutri")
 @Synchronized(timeout=6000000L)
 public class B239_Todieutri
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
   private static final long serialVersionUID = 10L;
   private String gio;
   private String ngay;
   private String ngayhientai;
   private String hsbaMaso;
   private String tenKhoa;
   private String hotenBN;
   private String gioitinh;
   private String tuoi;
   private String donvituoi;
   private String sogiuong;
   private String sobuong;
   private String chandoan;
   private String thuoc;
   private ToDieuTri tdt;
   private List<ToDieuTri> listTdt;
   private boolean isEdit;
   private Hsba hsba;
   private String loaiBA;
   private String tenBs = "";
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintDT = null;
   @Out(required=false)
   @In(required=false)
   private String duongdanStrDT = null;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String initB239_Todieutri;
   private static Logger log = Logger.getLogger(B239_Todieutri.class);

   @Factory("initB239_Todieutri")
   public void init()
   {
     resetValue();
     SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());
     this.ngayhientai = df.format(cal.getTime());
     this.initB239_Todieutri = "";
     DtDmNhanVien nguoidung = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
     if (nguoidung != null) {
       this.tenBs = ((nguoidung.getDmhocviMaso(true).getDmhocviMa() == null ? "" : new StringBuilder().append(nguoidung.getDmhocviMaso(true).getDmhocviMa()).append(". ").toString()) + (nguoidung.getDtdmnhanvienTen() == null ? "" : nguoidung.getDtdmnhanvienTen()));
     }
   }

   public void resetValue()
   {
     this.hsbaMaso = (this.gio = this.ngay = this.tenKhoa = "");
     this.hotenBN = (this.gioitinh = this.tuoi = this.donvituoi = "");
     this.sogiuong = (this.sobuong = this.chandoan = this.loaiBA = "");
     this.tdt = new ToDieuTri();
     this.hsba = new Hsba();
     this.isEdit = false;
     this.listTdt = new ArrayList();
   }

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
         this.tdt = new ToDieuTri();
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
     log.info("End displayInfo, listTdt.size = " + this.listTdt.size());
   }

   public void get_thuoc_info()
   {
     this.thuoc = "";
     if ((this.ngay != null) && (!this.ngay.equals("")))
     {
       if (Utils.getDBDate("00:00", this.ngay) == null)
       {
         log.info("### To dieu tri -- get_thuoc_info(): ERROR Parse NGAY !! ");
         return;
       }
       ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
       List<ThuocNoiTru> listThuocNoiTru = tntDelegate.findBySoVaoVienAndKhoaMaAndNgay(this.hsba.getHsbaSovaovien(), this.hsba.getHsbaKhoadangdt() == null ? "" : this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa(), this.ngay);
       if ((listThuocNoiTru != null) && (listThuocNoiTru.size() > 0)) {
         for (ThuocNoiTru each : listThuocNoiTru)
         {
           this.thuoc = (this.thuoc + "- " + each.getThuocnoitruMathuoc(true).getDmthuocTen());
           if (each.getThuocnoitruSoluong() != null) {
             this.thuoc = (this.thuoc + ", " + each.getThuocnoitruSoluong().toString());
           }
           this.thuoc = (this.thuoc + " " + each.getThuocnoitruMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen());
           this.thuoc += "\n";
         }
       } else {
         this.thuoc = IConstantsRes.KHONG_DUNG_THUOC;
       }
       if (this.thuoc.length() > 2040) {
         this.thuoc = (this.thuoc.substring(0, 2039) + " ...");
       }
       this.tdt.setTodieutriThuchienchamsoc(this.thuoc);
     }
   }

   public void resetList()
   {
     this.listTdt.clear();
     ToDieuTriDelegate tdtDel = ToDieuTriDelegate.getInstance();
     this.listTdt = tdtDel.findByHsba(this.hsbaMaso);
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
       this.tdt.setTodieutriNgaygio(cal.getTime());
       this.tdt.setHsbaSovaovien(this.hsba);
       this.tdt.setTodieutriTenbs(this.tenBs);

       this.tdt = ToDieuTriDelegate.getInstance().createToDieuTri(this.tdt);





       resetList();

       this.tdt = new ToDieuTri();
       this.gio = (this.ngay = "");
       this.isEdit = false;
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
   }

   public void editChitiet(ToDieuTri tdtTmp)
   {
     log.info("Begin editChitiet, tdtTmp = " + tdtTmp);
     this.tdt = tdtTmp;
     this.isEdit = true;
     this.gio = showGio(this.tdt.getTodieutriNgaygio());
     this.ngay = showNgay(this.tdt.getTodieutriNgaygio());
     log.info("End editChitiet");
   }

   public void deleteChitiet(ToDieuTri tdtTmp)
   {
     this.listTdt.remove(tdtTmp);

     ToDieuTriDelegate.getInstance().remove(tdtTmp);
     this.tdt = new ToDieuTri();
     this.gio = "";
     this.ngay = "";
     this.isEdit = false;
   }

   private String showGio(Date dateTmp)
   {
     Calendar cal = Calendar.getInstance();
     cal.setTime(dateTmp);
     String strGio = (cal.get(11) < 10 ? "0" + cal.get(11) : Integer.valueOf(cal.get(11))) + ":" + (cal.get(12) < 10 ? "0" + cal.get(12) : Integer.valueOf(cal.get(12)));
     return strGio;
   }

   private String showNgay(Date dateTmp)
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
     this.loaiBCDT = "ToDieuTri";
     log.info("Vao Method XuatReport To Dieu tri");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       log.info("bat dau method XuatReport ");
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/D39_todieutri.jrxml";


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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "ToDieuTri");
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

   public ToDieuTri getTdt()
   {
     return this.tdt;
   }

   public void setTdt(ToDieuTri tdt)
   {
     this.tdt = tdt;
   }

   public List<ToDieuTri> getListTdt()
   {
     return this.listTdt;
   }

   public void setListTdt(List<ToDieuTri> listTdt)
   {
     this.listTdt = listTdt;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B239_Todieutri

 * JD-Core Version:    0.7.0.1

 */