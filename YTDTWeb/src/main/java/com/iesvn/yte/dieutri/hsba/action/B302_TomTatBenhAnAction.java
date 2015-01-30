 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaGiayRaVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaTomTatBenhAnDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
 import com.iesvn.yte.dieutri.entity.HsbaGiayRaVien;
 import com.iesvn.yte.dieutri.entity.HsbaTomTatBenhAn;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
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
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B302_Tomtatbenhan")
 @Synchronized(timeout=6000000L)
 public class B302_TomTatBenhAnAction
   implements Serializable
 {
   private static final long serialVersionUID = 8711728630888663422L;
   private static Logger log = Logger.getLogger(B302_TomTatBenhAnAction.class);
   private HsbaTomTatBenhAn ttba;
   private HsbaChuyenMon cm;
   private boolean isUpdate;
   private String nosuccess;
   private String nofound;
   private String nofoundHSBA;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   private int index = 0;
   public String ngayCMT = "";

   public String getNgayCMT()
   {
     return this.ngayCMT;
   }

   public void setNgayCMT(String ngayCMT)
   {
     this.ngayCMT = ngayCMT;
   }

   public String ngayCap = "";

   public String getNgayCap()
   {
     return this.ngayCap;
   }

   public void setNgayCap(String ngayCap)
   {
     this.ngayCap = ngayCap;
   }

   public String maHsba = "";
   private static final long ONE_HOUR = 3600000L;

   public String getMaHsba()
   {
     return this.maHsba;
   }

   public void setMaHsba(String maHsba)
   {
     this.maHsba = maHsba;
   }

   @Begin(join=true)
   public String init()
   {
     resetValue();
     return "DieuTri_LapVanBanTheoMau_BangTomTatBenhAn";
   }

   @End
   public void endFunc() {}

   public void resetValue()
   {
     log.info("---resetValue");

     this.ttba = new HsbaTomTatBenhAn();
     setInfoIfNullForHsbaTomTatBenhAn(this.ttba);

     this.ngayCMT = "";
     this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());


     this.nosuccess = (this.nofound = this.nofoundHSBA = "false");
     this.isUpdate = false;
   }

   private void setInfoIfNullForHsbaTomTatBenhAn(HsbaTomTatBenhAn obj)
   {
     if (obj.getHsbaSovaovien() == null)
     {
       Hsba _hsba = new Hsba();
       setInfoIfNullForHsba(_hsba);
       obj.setHsbaSovaovien(_hsba);
     }
   }

   private void setInfoIfNullForHsba(Hsba obj)
   {
     if (obj.getBenhnhanMa() == null)
     {
       BenhNhan _benhnhan = new BenhNhan();
       setInfoIfNullForBenhNhan(_benhnhan);
       obj.setBenhnhanMa(_benhnhan);
     }
   }

   private void setInfoIfNullForBenhNhan(BenhNhan obj)
   {
     if (obj.getDmgtMaso() == null) {
       obj.setDmgtMaso(new DmGioiTinh());
     }
     if (obj.getDantocMa() == null) {
       obj.setDantocMa(new DmDanToc());
     }
     if (obj.getTinhMa() == null) {
       obj.setTinhMa(new DmTinh());
     }
     if (obj.getHuyenMa() == null) {
       obj.setHuyenMa(new DmHuyen());
     }
     if (obj.getXaMa() == null) {
       obj.setXaMa(new DmXa());
     }
     if (obj.getBenhnhanNghe() == null) {
       obj.setBenhnhanNghe(new DmNgheNghiep());
     }
   }

   public void displayHSBA()
   {
     log.info("---displayHSBA");
     Hsba hsba_tmp = null;
     this.maHsba = this.ttba.getHsbaSovaovien(true).getHsbaSovaovien();
     if (!this.maHsba.trim().equals(""))
     {
       hsba_tmp = HsbaDelegate.getInstance().find(this.maHsba.trim());
       if (hsba_tmp == null)
       {
         this.nofoundHSBA = "true";
         hsba_tmp = new Hsba();
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.maHsba });
       }
       setInfoIfNullForHsba(hsba_tmp);
       this.ttba.setHsbaSovaovien(hsba_tmp);
       this.maHsba = hsba_tmp.getHsbaSovaovien();
     }
     else
     {
       this.nofoundHSBA = "true";
       hsba_tmp = new Hsba();
       setInfoIfNullForHsba(hsba_tmp);
       this.ttba.setHsbaSovaovien(hsba_tmp);
     }
   }

   public void displayTomTatBenhAn()
   {
     log.info("---displayTomTatBenhAn");
     String maGiay = this.ttba.getHsbattbaMa().trim();
     HsbaTomTatBenhAn ttba_tmp = null;
     if (!maGiay.equals(""))
     {
       ttba_tmp = HsbaTomTatBenhAnDelegate.getInstance().findByHsbattbaMa(maGiay);
       if (ttba_tmp == null)
       {
         this.nofound = "true";
         this.ttba = new HsbaTomTatBenhAn();
         FacesMessages.instance().add(IConstantsRes.TTBA_NULL, new Object[] { maGiay });
       }
       else
       {
         HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(ttba_tmp.getHsbaSovaovien(true).getHsbaSovaovien(), ttba_tmp.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());
         if (cm_tmp != null) {
           this.cm = cm_tmp;
         }
         setInfoIfNullForHsbaTomTatBenhAn(ttba_tmp);
         this.ttba = ttba_tmp;
         if (this.ttba.getHsbattbaNgaycap() != null) {
           this.ngayCap = new SimpleDateFormat("dd/MM/yyyy").format(this.ttba.getHsbattbaNgaycap());
         }
         this.maHsba = ttba_tmp.getHsbaSovaovien().getHsbaSovaovien();
         this.isUpdate = true;
       }
     }
     else
     {
       this.nofound = "true";
       this.ttba = new HsbaTomTatBenhAn();
       setInfoIfNullForHsbaTomTatBenhAn(this.ttba);
     }
   }

   public void updateChild()
   {
     BenhNhan benhNhan = BenhNhanDelegate.getInstance().find(this.ttba.getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanMa());
     benhNhan.setBenhnhanCmnd(this.ttba.getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanCmnd());
     RemoveUtil.removeAllNullFromBN(benhNhan);
     BenhNhanDelegate.getInstance().edit(benhNhan);
   }

   public void ghiNhan()
     throws ParseException
   {
     log.info("---ghiNhan");
     removeInfoIfNullForHsbaTomTatBenhAn(this.ttba);
     String result = "";
     if (!this.ngayCap.trim().equals("")) {
       this.ttba.setHsbattbaNgaycap(Utils.getDBDate("00:00", this.ngayCap).getTime());
     }
     this.ttba.setHsbattbaNgaygiocn(new Date());

     String maGiay = this.ttba.getHsbattbaMa().trim();
     if (!maGiay.equals("")) {
       this.isUpdate = true;
     }
     System.out.println("Magiay = " + maGiay);
     System.out.println("IsUpdate = " + this.isUpdate);
     if (this.isUpdate)
     {
       result = HsbaTomTatBenhAnDelegate.getInstance().update(this.ttba);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.TTBA_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.ttba.setHsbattbaMa(result);
         setInfoIfNullForHsbaTomTatBenhAn(this.ttba);
         FacesMessages.instance().add(IConstantsRes.TTBA_CN_THANHCONG, new Object[] { result });
         updateChild();
       }
     }
     else
     {
       result = HsbaTomTatBenhAnDelegate.getInstance().insert(this.ttba);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.TTBA_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.ttba.setHsbattbaMa(result);
         setInfoIfNullForHsbaTomTatBenhAn(this.ttba);
         FacesMessages.instance().add(IConstantsRes.TTBA_LT_THANHCONG, new Object[] { result });
         updateChild();
       }
     }
     if ((result != null) && (!result.equals("")))
     {
       this.ttba = HsbaTomTatBenhAnDelegate.getInstance().findByHsbattbaMa(result);

       maGiay = result;
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "Bangtomtatbenhan";
     log.info("Vao Method XuatReport giay chuyen vien");
     String baocao1 = null;
     String pathTemplate = "";
     String sub1Template = "";
     String sub2Template = "";
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Bangtomtatbenhan.jrxml";
       sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Bangtomtatbenhan_sub1.jrxml";
       sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Bangtomtatbenhan_sub2.jrxml";

       log.info("Da thay file template: " + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();
       JasperReport rpt1 = JasperCompileManager.compileReport(sub1Template);
       JasperReport rpt2 = JasperCompileManager.compileReport(sub2Template);
       params.put("sub1", rpt1);
       params.put("sub2", rpt2);
       params.put("BENHVIEN_HEADER", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);

       HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(this.ttba.getHsbaSovaovien(true).getHsbaSovaovien(), this.ttba.getHsbaSovaovien(true).getHsbaKhoadangdt(true).getDmkhoaMa());
       if (hsbaCm != null)
       {
         System.out.println("hsbacm khac null");
         params.put("BUONG", hsbaCm.getHsbacmSobuong());
         params.put("KHOADANGDT", hsbaCm.getKhoaMa(true).getDmkhoaTen());
         params.put("BACSI", hsbaCm.getHsbacmBacsi(true).getDtdmnhanvienTen());
         params.put("CHUANDOAN_KHOA", hsbaCm.getHsbacmBenhchinh(true).getDmbenhicdTen());
         if (hsbaCm.getKetquaMa() != null)
         {
           if (hsbaCm.getKetquaMa().getDmkqdtMaso().intValue() == 1) {
             params.put("DIEUTRI_KHOI", "X");
           }
           if (hsbaCm.getKetquaMa(true).getDmkqdtMaso().intValue() == 3) {
             params.put("DIEUTRI_KHONGDO", "X");
           }
           if (hsbaCm.getKetquaMa(true).getDmkqdtMaso().intValue() == 4) {
             params.put("DIEUTRI_NANGTHEM", "X");
           }
         }
         if (hsbaCm.getHsbacmHuongdieutri() != null)
         {
           if (hsbaCm.getHsbacmHuongdieutri().equals("2"))
           {
             params.put("CHUYENVIEN_TUYENSAU", "X");
             HsbaChuyenVien cv = HsbaChuyenVienDelegate.getInstance().findBySoVaoVien(this.ttba.getHsbaSovaovien(true).getHsbaSovaovien());
             if (cv != null)
             {
               params.put("CHUYENVIEN_TINHTRANG", cv.getHsbacvTinhtrangnguoibenh());
               params.put("CHUYENVIEN_GIOXUATPHAT", cv.getHsbacvNgagiochvien());
               params.put("CHUYENVIEN_PHUONGTIEN", cv.getHsbacvPhuongtienvanchuyen());
             }
           }
           if (hsbaCm.getHsbacmHuongdieutri().equals("3"))
           {
             HsbaGiayRaVien grv = HsbaGiayRaVienDelegate.getInstance().findBySoVaoVien(this.ttba.getHsbaSovaovien(true).getHsbaSovaovien());
             if (grv != null)
             {
               if (grv.getHsbagrvLoidan() != null) {
                 params.put("RAVIEN_LOIDAN", "X");
               }
               params.put("RAVIEN_NGAYTAIKHAM", grv.getHsbagrvTaikham().intValue() + "");
             }
           }
         }
       }
       params.put("GIAMDOC", IConstantsRes.REPORT_DIEUTRI_GIAMDOC_BV);
       System.out.println("ma giay " + this.ttba.getHsbattbaMa());
       params.put("MaGiay", this.ttba.getHsbattbaMa());


       System.out.println("MaGiay = " + this.ttba.getHsbattbaMa());
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

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "TomTatBenhAnnamvien");



       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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
     log.info("Thoat Method XuatReport");
   }

   private static long daysBetween(Date d1, Date d2)
   {
     if (d1 == null) {
       d1 = new Date();
     }
     if (d2 == null) {
       d2 = new Date();
     }
     return (d2.getTime() - d1.getTime() + 3600000L) / 86400000L;
   }

   private void removeInfoIfNullForHsbaTomTatBenhAn(HsbaTomTatBenhAn obj)
   {
     if (Utils.reFactorString(obj.getHsbattbaBacsi(true).getDtdmnhanvienMa()).equals("")) {
       obj.setHsbattbaBacsi(null);
     }
   }

   public String formatDate(Date date)
   {
     return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
   }

   public String formatDateTime(Date date)
   {
     return date == null ? "" : Utils.getGioPhut(date);
   }

   public String formatGtBenhNhan(String gioitinh)
   {
     if ((gioitinh == null) || (gioitinh.trim().equals(""))) {
       return "";
     }
     return gioitinh.equals("1") ? "true" : "false";
   }

   public HsbaTomTatBenhAn getTtba()
   {
     return this.ttba;
   }

   public void setTtba(HsbaTomTatBenhAn ttba)
   {
     this.ttba = ttba;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNofoundHSBA()
   {
     return this.nofoundHSBA;
   }

   public void setNofoundHSBA(String nofoundHSBA)
   {
     this.nofoundHSBA = nofoundHSBA;
   }

   public HsbaChuyenMon getCm()
   {
     return this.cm;
   }

   public void setCm(HsbaChuyenMon cm)
   {
     this.cm = cm;
   }

   public boolean isUpdate()
   {
     return this.isUpdate;
   }

   public void setUpdate(boolean isUpdate)
   {
     this.isUpdate = isUpdate;
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

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B302_TomTatBenhAnAction

 * JD-Core Version:    0.7.0.1

 */