 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuTheoDoiTruyenDichDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.PhieuTheoDoiTruyenDich;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
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
 import javax.faces.model.SelectItem;
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

 @Scope(ScopeType.CONVERSATION)
 @Name("B270_Theodoitruyendich")
 @Synchronized(timeout=6000000L)
 public class B270_Phieutheodoitruyendich
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
   private static String DEFAULT_HOUR = "00:00";
   private static final long serialVersionUID = 10L;
   private String giobd;
   private String ngaybd;
   private String giokt;
   private String ngaykt;
   private String dmThuocMa;
   private Integer dmThuocMaso;
   private String bacsiMa;
   private Integer bacsiMaso;
   private String ytaMa;
   private Integer ytaMaso;
   private String ngayhientai;
   private String hsbaMaso;
   private String tenKhoa;
   private String khoaMa;
   private String hotenBN;
   private String gioitinh;
   private String tuoi;
   private String donvituoi;
   private String sogiuong;
   private String sobuong;
   private String chandoan;
   private Integer tonkhoMaso;
   private PhieuTheoDoiTruyenDich ptdtd;
   private List<PhieuTheoDoiTruyenDich> listPtdtd;
   private boolean isEdit;
   private Hsba hsba;
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
   private String initB270_Theodoitruyendich;
   private static Logger log = Logger.getLogger(B270_Phieutheodoitruyendich.class);
   private DmThuocDelegate dmThuocDelegate;
   private String dmThuocTen;

   @Factory("initB270_Theodoitruyendich")
   public void init()
   {
     resetValue();
     SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
     Calendar cal = Calendar.getInstance();
     cal.setTime(new Date());
     this.ngayhientai = df.format(cal.getTime());
     this.initB270_Theodoitruyendich = "";

     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     this.listDmThuocs.clear();
     for (DmThuoc each : this.dmThuocDelegate.findAll()) {
       this.listDmThuocs.add(new SelectItem(each.getDmthuocTen()));
     }
   }

   public void resetValue()
   {
     this.hsbaMaso = (this.giobd = this.ngaybd = this.giokt = this.ngaykt = this.tenKhoa = this.khoaMa = "");
     this.hotenBN = (this.gioitinh = this.tuoi = this.donvituoi = "");
     this.sogiuong = (this.sobuong = this.chandoan = "");
     this.dmThuocMa = (this.dmThuocTen = this.bacsiMa = this.ytaMa = "");
     this.dmThuocMaso = (this.bacsiMaso = this.ytaMaso = this.tonkhoMaso = null);
     this.ptdtd = new PhieuTheoDoiTruyenDich();
     this.ptdtd.setPtdtdSoluong(new Short("1"));
     this.hsba = new Hsba();
     this.isEdit = false;
     this.listPtdtd = new ArrayList();
   }

   private List<SelectItem> listDmThuocs = new ArrayList();

   public String getDmThuocTen()
   {
     return this.dmThuocTen;
   }

   public void setDmThuocTen(String dmThuocTen)
   {
     this.dmThuocTen = dmThuocTen;
   }

   public List<SelectItem> getListDmThuocs()
   {
     return this.listDmThuocs;
   }

   public void setListDmThuocs(List<SelectItem> listDmThuocs)
   {
     this.listDmThuocs = listDmThuocs;
   }

   public void onblurMaThuocAction()
   {
     log.info("-----BEGIN onblurMaThuocAction()-----");


     DmThuoc dmThuoc = this.dmThuocDelegate.findByMaThuoc(this.dmThuocMa);
     if (dmThuoc != null)
     {
       setDmThuocTen(dmThuoc.getDmthuocTen());
       log.info("-----DA THAY DMTHUOC-----");
     }
     else
     {
       setDmThuocTen("");
     }
     log.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     log.info("-----BEGIN onblurTenThuocAction()-----");

     DmThuoc dmThuoc = this.dmThuocDelegate.findByTenThuoc(this.dmThuocTen);
     if (dmThuoc != null)
     {
       setDmThuocMa(dmThuoc.getDmthuocMa());
       log.info("-----DA THAY DMTHUOC-----");
     }
     else
     {
       setDmThuocMa("");
     }
     log.info("-----END onblurTenThuocAction()-----");
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     for (DmThuoc each : this.dmThuocDelegate.findAll()) {
       this.listDmThuocs.add(new SelectItem(each.getDmthuocTen()));
     }
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
         this.khoaMa = (this.hsba.getHsbaKhoadangdt() == null ? "" : this.hsba.getHsbaKhoadangdt().getDmkhoaMa());
         this.hotenBN = (this.hsba.getBenhnhanMa() == null ? "" : this.hsba.getBenhnhanMa().getBenhnhanHoten());
         this.gioitinh = (this.hsba.getBenhnhanMa().getDmgtMaso() == null ? "" : this.hsba.getBenhnhanMa() == null ? "" : this.hsba.getBenhnhanMa().getDmgtMaso().getDmgtMa());
         this.tuoi = ("" + this.hsba.getBenhnhanMa().getBenhnhanTuoi());
         this.donvituoi = (this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 2 ? "(ThÃ¡ng)" : this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().intValue() == 1 ? "" : this.hsba.getBenhnhanMa() == null ? "" : "(NgÃ y)");
         HsbaChuyenMon hsbaCm = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(this.hsbaMaso);
         this.sobuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSobuong());
         this.sogiuong = (hsbaCm == null ? "" : hsbaCm.getHsbacmSogiuong());
         this.chandoan = (this.hsba.getHsbaMachdoanbd() == null ? "" : this.hsba.getHsbaMachdoanbd().getDmbenhicdTen());
         resetPhieuTheoDoiTruyenDich();
         this.giobd = (this.ngaybd = this.giokt = this.ngaykt = "");
         this.dmThuocMa = (this.dmThuocTen = this.bacsiMa = this.ytaMa = "");
         this.dmThuocMaso = (this.bacsiMaso = this.ytaMaso = this.tonkhoMaso = null);
         this.ptdtd = new PhieuTheoDoiTruyenDich();
         this.ptdtd.setPtdtdSoluong(new Short("1"));
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
     log.info("End displayInfo, listPtdtd.size = " + this.listPtdtd.size());
   }

   public void resetPhieuTheoDoiTruyenDich()
   {
     this.listPtdtd.clear();
     PhieuTheoDoiTruyenDichDelegate ptdtdDel = PhieuTheoDoiTruyenDichDelegate.getInstance();
     this.listPtdtd = ptdtdDel.findByHsba(this.hsbaMaso);
   }

   public void addChitiet()
   {
     log.info("Begin addChitiet, hsba = " + this.hsba);
     Calendar cal = Calendar.getInstance();
     String gioBdTmp = this.giobd.trim().length() < 1 ? DEFAULT_HOUR : this.giobd;
     String gioKtTmp = this.giokt.trim().length() < 1 ? DEFAULT_HOUR : this.giokt;
     try
     {
       SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_TIME);

       cal.setTime(df.parse(this.ngaybd + " " + gioBdTmp));
       this.ptdtd.setPtdtdBatdau(cal.getTime());
       if (this.ngaykt.trim().length() > 0)
       {
         cal.setTime(df.parse(this.ngaykt + " " + gioKtTmp));
         this.ptdtd.setPtdtdKetthuc(cal.getTime());
       }
       else
       {
         this.ptdtd.setPtdtdKetthuc(null);
       }
       this.ptdtd.setHsbaSovaovien(this.hsba);

       this.ptdtd.setDmthuocMaso(null);
       this.ptdtd.setBacsiMaso(null);
       this.ptdtd.setYtaMaso(null);
       this.ptdtd.setTonkhoMa(null);
       DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
       if (this.dmThuocMa.trim().length() > 0)
       {
         DmThuoc dmt = (DmThuoc)utilsDelegate.findByMa(this.dmThuocMa, "DmThuoc", "dmthuocMa");
         this.ptdtd.setDmthuocMaso(dmt);
       }
       if (this.bacsiMa.trim().length() > 0)
       {
         DtDmNhanVien nv = (DtDmNhanVien)utilsDelegate.findByMa(this.bacsiMa, "DtDmNhanVien", "dtdmnhanvienMa");
         this.ptdtd.setBacsiMaso(nv);
       }
       if (this.ytaMa.trim().length() > 0)
       {
         DtDmNhanVien nv = (DtDmNhanVien)utilsDelegate.findByMa(this.ytaMa, "DtDmNhanVien", "dtdmnhanvienMa");
         this.ptdtd.setYtaMaso(nv);
       }
       if (this.tonkhoMaso != null)
       {
         TonKho tk = (TonKho)utilsDelegate.findByMaSo(this.tonkhoMaso, "TonKho", "tonkhoMa");
         this.ptdtd.setTonkhoMa(tk);
       }
       this.ptdtd = PhieuTheoDoiTruyenDichDelegate.getInstance().createPhieuTheoDoiTruyenDich(this.ptdtd);




       resetPhieuTheoDoiTruyenDich();

       this.ptdtd = new PhieuTheoDoiTruyenDich();
       this.ptdtd.setPtdtdSoluong(new Short("1"));
       this.giobd = (this.ngaybd = this.giokt = this.ngaykt = "");
       this.dmThuocMa = (this.dmThuocTen = this.bacsiMa = this.ytaMa = "");
       this.dmThuocMaso = (this.bacsiMaso = this.ytaMaso = null);
       this.isEdit = false;
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
   }

   public void editChitiet(PhieuTheoDoiTruyenDich ptdtdTmp)
   {
     log.info("Begin editChitiet, ptdtdTmp = " + ptdtdTmp);
     this.ptdtd = ptdtdTmp;
     this.isEdit = true;
     this.giobd = showGio(this.ptdtd.getPtdtdBatdau());
     this.ngaybd = showNgay(this.ptdtd.getPtdtdBatdau());
     this.giokt = showGio(this.ptdtd.getPtdtdKetthuc());
     this.ngaykt = showNgay(this.ptdtd.getPtdtdKetthuc());

     this.dmThuocMa = (this.ptdtd.getDmthuocMaso() == null ? "" : this.ptdtd.getDmthuocMaso().getDmthuocMa());
     this.dmThuocTen = (this.ptdtd.getDmthuocMaso() == null ? "" : this.ptdtd.getDmthuocMaso().getDmthuocTen());
     this.dmThuocMaso = (this.ptdtd.getDmthuocMaso() == null ? null : this.ptdtd.getDmthuocMaso().getDmthuocMaso());

     this.bacsiMa = (this.ptdtd.getBacsiMaso() == null ? "" : this.ptdtd.getBacsiMaso().getDtdmnhanvienMa());
     this.bacsiMaso = (this.ptdtd.getBacsiMaso() == null ? null : this.ptdtd.getBacsiMaso().getDtdmnhanvienMaso());

     this.ytaMa = (this.ptdtd.getYtaMaso() == null ? "" : this.ptdtd.getYtaMaso().getDtdmnhanvienMa());
     this.ytaMaso = (this.ptdtd.getYtaMaso() == null ? null : this.ptdtd.getYtaMaso().getDtdmnhanvienMaso());

     this.tonkhoMaso = (this.ptdtd.getTonkhoMa() == null ? null : this.ptdtd.getTonkhoMa().getTonkhoMa());
     log.info("End editChitiet");
   }

   public void deleteChitiet(PhieuTheoDoiTruyenDich ptdtdTmp)
   {
     this.listPtdtd.remove(ptdtdTmp);

     PhieuTheoDoiTruyenDichDelegate.getInstance().remove(ptdtdTmp);

     this.ptdtd = new PhieuTheoDoiTruyenDich();
     this.ptdtd.setPtdtdSoluong(new Short("1"));
     this.giobd = (this.ngaybd = this.giokt = this.ngaykt = "");
     this.dmThuocMa = (this.dmThuocTen = this.bacsiMa = this.ytaMa = "");
     this.dmThuocMaso = (this.bacsiMaso = this.ytaMaso = this.tonkhoMaso = null);
     this.isEdit = false;
   }

   private String showGio(Date dateTmp)
   {
     String strGio = "";
     if (dateTmp != null)
     {
       Calendar cal = Calendar.getInstance();
       cal.setTime(dateTmp);
       strGio = (cal.get(11) < 10 ? "0" + cal.get(11) : Integer.valueOf(cal.get(11))) + ":" + (cal.get(12) < 10 ? "0" + cal.get(12) : Integer.valueOf(cal.get(12)));
     }
     return strGio;
   }

   private String showNgay(Date dateTmp)
   {
     String strNgay = "";
     if (dateTmp != null)
     {
       SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE);
       Calendar cal = Calendar.getInstance();
       cal.setTime(dateTmp);
       strNgay = df.format(cal.getTime());
     }
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
     this.loaiBCDT = "PhieuTheoDoiTruyenDich";
     log.info("Vao Method XuatReport To Dieu tri");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       log.info("bat dau method XuatReport ");
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/D17_phieutheodoitruyendich.jrxml";


       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       params.put("tenso", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tendonvi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sovaovien", this.hsbaMaso);
       params.put("hoten", this.hotenBN);
       params.put("tuoi", this.tuoi + (this.donvituoi.trim().length() > 0 ? this.donvituoi : ""));
       params.put("gioitinh", this.gioitinh.equals("0") ? IConstantsRes.GIOI_TINH_NU : IConstantsRes.GIOI_TINH_NAM);
       params.put("khoa", this.tenKhoa);
       params.put("buong", this.sobuong);
       params.put("giuong", this.sogiuong);
       params.put("chandoan", this.chandoan);

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, new Long(new Date().getTime()).intValue(), IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "PhieuTheoDoiTruyenDich");
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

   public String getGiobd()
   {
     return this.giobd;
   }

   public void setGiobd(String giobd)
   {
     this.giobd = giobd;
   }

   public String getNgaybd()
   {
     return this.ngaybd;
   }

   public void setNgaybd(String ngaybd)
   {
     this.ngaybd = ngaybd;
   }

   public String getGiokt()
   {
     return this.giokt;
   }

   public void setGiokt(String giokt)
   {
     this.giokt = giokt;
   }

   public String getNgaykt()
   {
     return this.ngaykt;
   }

   public void setNgaykt(String ngaykt)
   {
     this.ngaykt = ngaykt;
   }

   public String getDmThuocMa()
   {
     return this.dmThuocMa;
   }

   public void setDmThuocMa(String dmThuocMa)
   {
     this.dmThuocMa = dmThuocMa;
   }

   public Integer getDmThuocMaso()
   {
     return this.dmThuocMaso;
   }

   public void setDmThuocMaso(Integer dmThuocMaso)
   {
     this.dmThuocMaso = dmThuocMaso;
   }

   public String getBacsiMa()
   {
     return this.bacsiMa;
   }

   public void setBacsiMa(String bacsiMa)
   {
     this.bacsiMa = bacsiMa;
   }

   public Integer getBacsiMaso()
   {
     return this.bacsiMaso;
   }

   public void setBacsiMaso(Integer bacsiMaso)
   {
     this.bacsiMaso = bacsiMaso;
   }

   public String getYtaMa()
   {
     return this.ytaMa;
   }

   public void setYtaMa(String ytaMa)
   {
     this.ytaMa = ytaMa;
   }

   public Integer getYtaMaso()
   {
     return this.ytaMaso;
   }

   public void setYtaMaso(Integer ytaMaso)
   {
     this.ytaMaso = ytaMaso;
   }

   public PhieuTheoDoiTruyenDich getPtdtd()
   {
     return this.ptdtd;
   }

   public void setPtdtd(PhieuTheoDoiTruyenDich ptdtd)
   {
     this.ptdtd = ptdtd;
   }

   public List<PhieuTheoDoiTruyenDich> getListPtdtd()
   {
     return this.listPtdtd;
   }

   public void setListPtdtd(List<PhieuTheoDoiTruyenDich> listPtdtd)
   {
     this.listPtdtd = listPtdtd;
   }

   public Integer getTonkhoMaso()
   {
     return this.tonkhoMaso;
   }

   public void setTonkhoMaso(Integer tonkhoMaso)
   {
     this.tonkhoMaso = tonkhoMaso;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B270_Phieutheodoitruyendich

 * JD-Core Version:    0.7.0.1

 */