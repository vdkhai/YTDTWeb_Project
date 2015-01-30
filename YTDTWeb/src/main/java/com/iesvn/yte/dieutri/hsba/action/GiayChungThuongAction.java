 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaGiayChungThuongDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaGiayChungThuong;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
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
 @Name("B231_Giaychungthuong")
 @Synchronized(timeout=6000000L)
 public class GiayChungThuongAction
   implements Serializable
 {
   private static final long serialVersionUID = -1896799593376801640L;
   private static Logger log = Logger.getLogger(GiayChungThuongAction.class);
   private HsbaGiayChungThuong gct;
   private HsbaChuyenMon cm;
   private Hsba hsba_tmp;
   private String nnTru;
   private String maHSTD;
   private String nosuccess;
   private String nofound;
   private String nofoundBATD;
   private boolean isUpdate;
   private String ngaycn;
   private String ngayyc;
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

   @Begin(join=true)
   public String init()
   {
     resetValue();
     return "DieuTri_LapVanBanTheoMau_GiayChungThuong";
   }

   @End
   public void endFunc() {}

   public void resetValue()
   {
     log.info("---resetValue");
     this.gct = new HsbaGiayChungThuong();
     setInfoIfNullForHsbaGiayChungThuong(this.gct);
     this.cm = new HsbaChuyenMon();
     setInfoIfNullForHsbaChuyenMon(this.cm);
     this.hsba_tmp = new Hsba();
     setInfoIfNullForHsba(this.hsba_tmp);
     this.nnTru = "noi";
     this.maHSTD = "";
     this.ngaycn = (this.ngayyc = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
     this.nosuccess = (this.nofound = this.nofoundBATD = "false");
     this.isUpdate = false;
   }

   public void ghiNhan()
   {
     log.info("---ghiNhan");
     String result = "";
     if (!this.ngayyc.trim().equals("")) {
       this.gct.setHsbagctNgayyc(Utils.getDBDate("00:00", this.ngayyc).getTime());
     }
     if (!this.ngaycn.trim().equals("")) {
       this.gct.setHsbagctNgaygiocn(Utils.getDBDate("00:00", this.ngaycn).getTime());
     }
     if (this.isUpdate)
     {
       result = HsbaGiayChungThuongDelegate.getInstance().update(this.gct);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCT_CN_THATBAI, new Object[0]);
       }
       else
       {
         this.gct.setHsbagctMa(result);
         FacesMessages.instance().add(IConstantsRes.GCT_CN_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       result = HsbaGiayChungThuongDelegate.getInstance().insert(this.gct);
       if ((result == null) || (result.equals("")))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.GCT_LT_THATBAI, new Object[0]);
       }
       else
       {
         this.gct.setHsbagctMa(result);
         FacesMessages.instance().add(IConstantsRes.GCT_LT_THANHCONG, new Object[] { result });
       }
     }
   }

   public void displayGiayCT()
   {
     log.info("---displayGiayRaVien");
     String maGct = this.gct.getHsbagctMa().trim();
     HsbaGiayChungThuong gct_tmp = null;
     if (!maGct.equals(""))
     {
       gct_tmp = HsbaGiayChungThuongDelegate.getInstance().findByHsbagctMa(maGct);
       if (gct_tmp == null)
       {
         this.nofound = "true";
         gct_tmp = new HsbaGiayChungThuong();
         FacesMessages.instance().add(IConstantsRes.GCT_NULL, new Object[] { maGct });
       }
       setInfoIfNullForHsbaGiayChungThuong(gct_tmp);
       this.gct = gct_tmp;
       this.maHSTD = gct_tmp.getHsbaSovaovien().getHsbaSovaovien();
       this.nnTru = "noi";
       this.isUpdate = true;
     }
     else
     {
       this.nofound = "true";
       this.gct = new HsbaGiayChungThuong();
       setInfoIfNullForHsbaGiayChungThuong(this.gct);
     }
   }

   public void displayHsba()
   {
     log.info("---displayHSBA");
     this.hsba_tmp = null;
     if (!this.maHSTD.trim().equals(""))
     {
       if (this.nnTru.equals("noi"))
       {
         this.hsba_tmp = HsbaDelegate.getInstance().find(this.maHSTD.trim());
         if (this.hsba_tmp == null)
         {
           this.nofoundBATD = "true";
           this.hsba_tmp = new Hsba();
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.maHSTD });
         }
         else if (this.hsba_tmp.getHsbaKhoarav() != null)
         {
           HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(this.hsba_tmp.getHsbaSovaovien(), this.hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
           if (cm_tmp != null)
           {
             this.cm = cm_tmp;
             setInfoIfNullForHsbaChuyenMon(this.cm);
           }
         }
       }
       else if (this.nnTru.equals("ngoai"))
       {
         this.hsba_tmp = HsbaDelegate.getInstance().findByTiepDonMa(this.maHSTD.trim());
         if (this.hsba_tmp == null)
         {
           this.nofoundBATD = "true";
           this.hsba_tmp = new Hsba();
           FacesMessages.instance().add(IConstantsRes.HSBA_BY_MTD_NULL, new Object[] { this.maHSTD });
         }
         else if (this.hsba_tmp.getHsbaKhoarav() != null)
         {
           HsbaChuyenMon cm_tmp = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(this.hsba_tmp.getHsbaSovaovien(), this.hsba_tmp.getHsbaKhoarav().getDmkhoaMa());
           if (cm_tmp != null)
           {
             this.cm = cm_tmp;
             setInfoIfNullForHsbaChuyenMon(this.cm);
           }
         }
       }
       setInfoIfNullForHsba(this.hsba_tmp);
       this.gct.setHsbaSovaovien(this.hsba_tmp);
       this.maHSTD = this.hsba_tmp.getHsbaSovaovien();
     }
     else
     {
       this.nofoundBATD = "true";
       this.hsba_tmp = new Hsba();
       setInfoIfNullForHsba(this.hsba_tmp);
       this.gct.setHsbaSovaovien(this.hsba_tmp);
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "Giaychungnhanthuongtich";
     log.info("Vao Method XuatReport Giaychungnhanthuongtich");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giaychungnhanthuongtich.jrxml";
       log.info("Da thay file template: " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Hsba hsba = this.gct.getHsbaSovaovien();

       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("tenDonViFull", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI_FULL);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("soGCT", this.gct.getHsbagctMa());
       params.put("soVaoVien", hsba.getHsbaSovaovien());

       params.put("hoTen", hsba.getBenhnhanMa().getBenhnhanHoten());
       params.put("gioiTinh", hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtTen());


       Date dNgaySinh = hsba.getBenhnhanMa().getBenhnhanNgaysinh();
       if (dNgaySinh != null)
       {
         params.put("ngaySinh", "" + sdf.format(hsba.getBenhnhanMa().getBenhnhanNgaysinh()));
         if (hsba.getBenhnhanMa().getBenhnhanNamsinh() != null) {
           params.put("namSinh", hsba.getBenhnhanMa().getBenhnhanNamsinh());
         } else {
           params.put("namSinh", "");
         }
       }
       else if (hsba.getBenhnhanMa().getBenhnhanNamsinh() != null)
       {
         params.put("namSinh", hsba.getBenhnhanMa().getBenhnhanNamsinh());
       }
       else
       {
         params.put("namSinh", "");
       }
       params.put("ngheTen", hsba.getBenhnhanMa().getBenhnhanNghe(true).getDmnghenghiepTen());
       params.put("cmnd", hsba.getBenhnhanMa().getBenhnhanCmnd());


       String diachistr = "";
       if (hsba.getBenhnhanMa().getBenhnhanDiachi() != null) {
         diachistr = diachistr + hsba.getBenhnhanMa().getBenhnhanDiachi();
       }
       if (hsba.getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + ", " + hsba.getBenhnhanMa().getXaMa(true).getDmxaTen();
       }
       if (hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + ", " + hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenTen();
       }
       if (hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + ", " + hsba.getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("diaChi", diachistr);


       String maChanDoan = "";
       String tenChanDoan = "";
       if (hsba.getHsbaMachdoanbd() != null)
       {
         if (hsba.getHsbaMachdoanbd().getDmbenhicdMa() != null) {
           maChanDoan = hsba.getHsbaMachdoanbd().getDmbenhicdMa();
         }
         if (hsba.getHsbaMachdoanbd().getDmbenhicdTen() != null) {
           tenChanDoan = hsba.getHsbaMachdoanbd().getDmbenhicdTen();
         }
       }
       params.put("CHANDOAN", maChanDoan + " - " + tenChanDoan);


       Date dVaov = hsba.getHsbaNgaygiovaov();
       if (dVaov != null)
       {
         Calendar cal = Calendar.getInstance();
         cal.setTime(dVaov);
         params.put("gioVaov", "" + cal.get(11));
         params.put("phutVaov", "" + cal.get(12));
         params.put("ngayVaov", "" + cal.get(5));
         params.put("thangVaov", "" + (cal.get(2) + 1));
         params.put("namVaov", "" + cal.get(1));
       }
       else
       {
         params.put("gioVaov", "");
         params.put("phutVaov", "");
         params.put("ngayVaov", "");
         params.put("thangVaov", "");
         params.put("namVaov", "");
       }
       Date dRav = hsba.getHsbaNgaygiorav();
       if (dRav != null)
       {
         Calendar cal = Calendar.getInstance();
         cal.setTime(dRav);
         params.put("gioRav", "" + cal.get(11));
         params.put("phutRav", "" + cal.get(12));
         params.put("ngayRav", "" + cal.get(5));
         params.put("thangRav", "" + (cal.get(2) + 1));
         params.put("namRav", "" + cal.get(1));
       }
       else
       {
         params.put("gioRav", "");
         params.put("phutRav", "");
         params.put("ngayRav", "");
         params.put("thangRav", "");
         params.put("namRav", "");
       }
       params.put("thuongtichvaovien", this.gct.getHsbagctTtvv());
       params.put("thuongtichravien", this.gct.getHsbagctTtrv());
       params.put("dieutri", this.gct.getHsbagctNoidung());



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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Giaychungnhanthuongtich");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report : " + baocao1);
       log.info("duong dan -------------------- : " + this.duongdanStrDT);
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

   private void setInfoIfNullForHsbaGiayChungThuong(HsbaGiayChungThuong obj)
   {
     if (obj.getHsbaSovaovien() == null)
     {
       Hsba _hsba = new Hsba();
       setInfoIfNullForHsba(_hsba);
       obj.setHsbaSovaovien(_hsba);
     }
     if (obj.getHsbaSovaovien().getHsbaKhoarav() == null) {
       obj.getHsbaSovaovien().setHsbaKhoarav(new DmKhoa());
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

   private void setInfoIfNullForHsbaChuyenMon(HsbaChuyenMon _cm)
   {
     if (_cm.getHsbacmBenhchinh() == null) {
       _cm.setHsbacmBenhchinh(new DmBenhIcd());
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

   public Hsba getHsba_tmp()
   {
     return this.hsba_tmp;
   }

   public void setHsba_tmp(Hsba hsbaTmp)
   {
     this.hsba_tmp = hsbaTmp;
   }

   public HsbaChuyenMon getCm()
   {
     return this.cm;
   }

   public void setCm(HsbaChuyenMon cm)
   {
     this.cm = cm;
   }

   public String getMaHSTD()
   {
     return this.maHSTD;
   }

   public void setMaHSTD(String maHSTD)
   {
     this.maHSTD = maHSTD;
   }

   public String getNnTru()
   {
     return this.nnTru;
   }

   public void setNnTru(String nnTru)
   {
     this.nnTru = nnTru;
   }

   public HsbaGiayChungThuong getGct()
   {
     return this.gct;
   }

   public void setGct(HsbaGiayChungThuong gct)
   {
     this.gct = gct;
   }

   public String getNofoundBATD()
   {
     return this.nofoundBATD;
   }

   public void setNofoundBATD(String nofoundBATD)
   {
     this.nofoundBATD = nofoundBATD;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getNgaycn()
   {
     return this.ngaycn;
   }

   public void setNgaycn(String ngaycn)
   {
     this.ngaycn = ngaycn;
   }

   public String getNgayyc()
   {
     return this.ngayyc;
   }

   public void setNgayyc(String ngayyc)
   {
     this.ngayyc = ngayyc;
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

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.GiayChungThuongAction

 * JD-Core Version:    0.7.0.1

 */