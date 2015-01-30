 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
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
 import java.util.List;
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
 @Name("B3115_Xacnhanthongtindieutri")
 @Synchronized(timeout=6000000L)
 public class XacNhanThongTinDieuTriAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(XacNhanThongTinDieuTriAction.class);
   private String ngayHt;
   private String ngayVaov;
   private String khoaMa;
   private String soVaoVien;
   private String ngaySinh;
   private String khoaDt;
   private String khoaDangDt;
   private Hsba hsba;
   private HsbaChuyenMon hsbaCm;
   private HsbaBhyt hsbabhyt;
   private Boolean hienThiInPhieu;
   private String ngayRaVien;
   private String gioRaVien;

   public String getGioRaVien()
   {
     return this.gioRaVien;
   }

   public String getNgayRaVien()
   {
     return this.ngayRaVien;
   }

   public void setNgayRaVien(String ngayRaVien)
   {
     this.ngayRaVien = ngayRaVien;
   }

   public void setGioRaVien(String gioRaVien)
   {
     this.gioRaVien = gioRaVien;
   }

   @Begin(join=true)
   public String init()
   {
     log.info("-----init()-----");
     reset();
     this.hienThiInPhieu = Boolean.valueOf(false);
     return "VienPhiTaiKhoa_SoLieuBNSuDung_XacNhanThongTinDieuTri";
   }

   @End
   public void endFunct() {}

   public void xacNhan()
   {
     HsbaDelegate hsbaDele = HsbaDelegate.getInstance();

     RemoveUtil.removeAllNullFromHSBA(this.hsba);
     if ((this.ngayRaVien != null) && (!this.ngayRaVien.equals("")) && (this.gioRaVien != null) && (!this.gioRaVien.equals("")))
     {
       Calendar cTemp = Utils.getDBDate(this.gioRaVien, this.ngayRaVien);
       if (cTemp != null) {
         this.hsba.setHsbaNgaygiorav(cTemp.getTime());
       }
     }
     else
     {
       this.ngayRaVien = Utils.getCurrentDate();
       Date date = new Date();
       this.gioRaVien = Utils.getGioPhut(date);
       Calendar cTemp = Utils.getDBDate(this.gioRaVien, this.ngayRaVien);
       if (cTemp != null) {
         this.hsba.setHsbaNgaygiorav(cTemp.getTime());
       }
     }
     ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();
     ClsMoDelegate clsmoDel = ClsMoDelegate.getInstance();

     List<ThuocNoiTru> listTntTruocNgayVV = tntDel.checkThuocNoiTruTruocNgayVaoVien(this.soVaoVien);
     List<ThuocNoiTru> listTntSauNgayRV = tntDel.checkThuocNoiTruSauNgayRaVien(this.soVaoVien, this.hsba.getHsbaNgaygiorav());
     List<ClsMo> listClsmoTruocNgayVV = clsmoDel.checkClsmoTruocNgayVaoVien(this.soVaoVien);
     List<ClsMo> listClsmoSauNgayRV = clsmoDel.checkClsmoSauNgayRaVien(this.soVaoVien, this.hsba.getHsbaNgaygiorav());

     int countThuocTruocNgayVV = listTntTruocNgayVV == null ? 0 : listTntTruocNgayVV.size();
     int countThuocSauNgayRV = listTntSauNgayRV == null ? 0 : listTntSauNgayRV.size();
     int countClsTruocNgayVV = listClsmoTruocNgayVV == null ? 0 : listClsmoTruocNgayVV.size();
     int countClsSauNgayRV = listClsmoSauNgayRV == null ? 0 : listClsmoSauNgayRV.size();
     if ((countThuocTruocNgayVV > 0) && (countClsTruocNgayVV > 0)) {
       FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN, new Object[] { IConstantsRes.THUOC_VA_CAN_LAM_SANG });
     } else if ((countThuocTruocNgayVV > 0) && (countClsTruocNgayVV == 0)) {
       FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN, new Object[] { IConstantsRes.THUOC });
     } else if ((countThuocTruocNgayVV == 0) && (countClsTruocNgayVV > 0)) {
       FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_TRUOC_NGAY_VAO_VIEN, new Object[] { IConstantsRes.CAN_LAM_SANG });
     }
     if ((countThuocSauNgayRV > 0) && (countClsSauNgayRV > 0)) {
       FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN, new Object[] { IConstantsRes.THUOC_VA_CAN_LAM_SANG });
     } else if ((countThuocSauNgayRV > 0) && (countClsSauNgayRV == 0)) {
       FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN, new Object[] { IConstantsRes.THUOC });
     } else if ((countThuocSauNgayRV == 0) && (countClsSauNgayRV > 0)) {
       FacesMessages.instance().add(IConstantsRes.BENH_NHAN_CO_THUOC_CLS_SAU_NGAY_RA_VIEN, new Object[] { IConstantsRes.CAN_LAM_SANG });
     }
     if ((countThuocTruocNgayVV > 0) || (countClsTruocNgayVV > 0) || (countThuocSauNgayRV > 0) || (countClsSauNgayRV > 0))
     {
       this.hienThiInPhieu = Boolean.valueOf(false);
       return;
     }
     String hsbaType = this.hsba.getHsbaType() == null ? "NULL" : this.hsba.getHsbaType();
     if ((IConstantsRes.CO_KIEM_TRA_TIEN_GIUONG_KHI_RA_VIEN.equals("YES")) && (!hsbaType.equals("DT_NGOAITRU")))
     {
       int songayDT = new Integer("" + Utils.getSoNgayDieuTri(this.hsba.getHsbaNgaygiovaov(), this.hsba.getHsbaNgaygiorav())).intValue();
       log.info("So Ngay DT = " + songayDT);

       List<ClsMo> listClsMo = ClsMoDelegate.getInstance().findBySoVaoVien(this.soVaoVien);
       int countClsGiuong = 0;
       if (listClsMo != null) {
         for (ClsMo eachClsMo : listClsMo) {
           if ((eachClsMo.getClsmoLoai() != null) && (eachClsMo.getClsmoLoai().equalsIgnoreCase("GI"))) {
             countClsGiuong += (eachClsMo.getClsmoLan() == null ? 0 : eachClsMo.getClsmoLan().intValue()) - (eachClsMo.getClsmoTra() == null ? 0 : eachClsMo.getClsmoTra().intValue());
           }
         }
       }
       String[] arrSaiso = IConstantsRes.SAI_SO_CHO_PHEP_GIUA_NGAY_DIEU_TRI_VA_GIUONG.split("/");
       int down = Integer.valueOf(arrSaiso[0]).intValue();
       int up = Integer.valueOf(arrSaiso[1]).intValue();
       int saiso = songayDT - countClsGiuong;
       if ((saiso < down) || (saiso > up))
       {
         FacesMessages.instance().add(IConstantsRes.SAI_TIEN_GIUONG_KHONG_CHO_RA_VIEN, new Object[0]);
         this.hienThiInPhieu = Boolean.valueOf(false);
         return;
       }
     }
     if (this.hsba.getHsbaKhoadangdt() != null) {
       this.hsba.setHsbaKhoarav(this.hsba.getHsbaKhoadangdt());
     }
     if ((this.hsba.getHsbaKetqua() != null) &&
       (this.hsba.getHsbaKetqua().getDmkqdtMaso().intValue() == 5) &&
       (this.hsba.getHsbaMachdravien() != null)) {
       this.hsba.setHsbaTuvong(this.hsba.getHsbaMachdravien());
     }
     if ((this.hsba.getHsbaYeuCau() == null) || (this.hsba.getHsbaYeuCau().equals(""))) {
       this.hsba.setHsbaYeuCau("  ");
     }
     hsbaDele.edit(this.hsba);


     HsbaChuyenMonDelegate hsbacmDele = HsbaChuyenMonDelegate.getInstance();
     HsbaChuyenMon hsbacm_temp = hsbacmDele.findBySoVaoVien_MaKhoa(this.soVaoVien, this.khoaMa);
     if (hsbacm_temp != null)
     {
       this.hsbaCm = hsbacm_temp;
       this.hsbaCm.setHsbacmHuongdieutri("3");
       if (this.hsba.getHsbaKetqua() != null) {
         this.hsbaCm.setKetquaMa(this.hsba.getHsbaKetqua());
       }
       hsbacmDele.edit(this.hsbaCm);
       log.info("### Edit thanh cong HSBACM.HuongDieuTri: 3 ###");
     }
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     this.hienThiInPhieu = Boolean.valueOf(true);
   }

   public void reset()
   {
     this.ngayHt = Utils.getCurrentDate();
     this.ngayVaov = "";
     this.ngaySinh = "";
     this.khoaMa = "";
     this.soVaoVien = "";

     this.khoaDt = "";
     this.khoaDangDt = "";
     this.hsba = new Hsba();
     this.hsbaCm = new HsbaChuyenMon();
     this.hsbabhyt = new HsbaBhyt();
     this.hienThiInPhieu = Boolean.valueOf(false);
     this.ngayRaVien = Utils.getCurrentDate();
     Date date = new Date();
     this.gioRaVien = Utils.getGioPhut(date);
   }

   public void loadHsba()
   {
     if ((this.soVaoVien == null) || (this.soVaoVien.equals("")))
     {
       log.info("hsba.getHsbaSovaovien() == null");
       reset();
       return;
     }
     try
     {
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
       Hsba hsbaCur = hsbaDelegate.find(this.soVaoVien);
       if (hsbaCur == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.soVaoVien });
         reset();
         return;
       }
       this.hsba = hsbaCur;
       this.soVaoVien = this.hsba.getHsbaSovaovien();
       this.hienThiInPhieu = Boolean.valueOf(this.hsba.getHsbaYeuCau() != null);
       if (this.hsba.getHsbaKhoadangdt() == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.soVaoVien });
         log.info("khoaDangDieuTri == null");
         reset();
         return;
       }
       this.khoaMa = this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa();

       this.khoaDangDt = ((this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa() == null ? "" : this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa()) + (this.hsba.getHsbaKhoadangdt(true).getDmkhoaTen() == null ? "" : new StringBuilder().append(" - ").append(this.hsba.getHsbaKhoadangdt(true).getDmkhoaTen()).toString()));


       HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
       HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(this.hsba.getHsbaSovaovien(), this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa());
       if (hsbaKhoa == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.soVaoVien });
         reset();
         return;
       }
       List<HsbaKhoa> lstHsbaKhoa = hsbaKhoaDelegate.findBySoVaoVien(this.soVaoVien);
       this.khoaDt = "";
       for (HsbaKhoa hK : lstHsbaKhoa) {
         this.khoaDt = (this.khoaDt + hK.getKhoaMa().getDmkhoaMa() + "   ");
       }
       HsbaBhytDelegate hsbabhytDele = HsbaBhytDelegate.getInstance();
       HsbaBhyt tmphsbabhyt = hsbabhytDele.findBySoVaoVienLastHsbaBhyt(this.soVaoVien);
       if (tmphsbabhyt != null) {
         this.hsbabhyt = tmphsbabhyt;
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.soVaoVien });
       e.printStackTrace();
       reset();
     }
     Date dNgaySinh = this.hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
     if (dNgaySinh != null)
     {
       Calendar cal = Calendar.getInstance();
       cal.setTime(dNgaySinh);
       this.ngaySinh = Utils.convertCalendar2Str(cal);
     }
     Date dNgayVaov = this.hsba.getHsbaNgaygiovaov();
     if (dNgayVaov != null)
     {
       Calendar cal = Calendar.getInstance();
       cal.setTime(dNgayVaov);
       this.ngayVaov = Utils.convertCalendar2Str(cal);
     }
     Date dNgayRav = this.hsba.getHsbaNgaygiorav();
     if (dNgayRav != null)
     {
       Calendar cal = Calendar.getInstance();
       cal.setTime(dNgayRav);
       this.ngayRaVien = Utils.convertCalendar2Str(cal);
       this.gioRaVien = Utils.getGioPhut(dNgayRav);
     }
     if ((this.hsba.getHsbaMachdoanbd() != null) &&
       (this.hsba.getHsbaMachdravien() == null)) {
       this.hsba.setHsbaMachdravien(this.hsba.getHsbaMachdoanbd());
     }
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
     this.reportTypeVP = "Xacnhanthongtindieutri";
     log.info("Vao Method XuatReport Xacnhanthongtindieutri");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/Xacnhanthongtindieutri.jrxml";

       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       String gt = null;
       params.put("TEN", this.hsba.getBenhnhanMa().getBenhnhanHoten());

       DieuTriUtilDelegate dieuTriDelegate = DieuTriUtilDelegate.getInstance();
       if (this.hsba.getBenhnhanMa().getDmgtMaso() != null)
       {
         DmGioiTinh gioiTinh = (DmGioiTinh)dieuTriDelegate.findByMa(this.hsba.getBenhnhanMa().getDmgtMaso(true).getDmgtMa(), "DmGioiTinh", "dmgtMa");
         if (gioiTinh != null) {
           gt = gioiTinh.getDmgtTen();
         } else {
           gt = "";
         }
       }
       log.info("Gioi tinh " + gt);
       params.put("GT", gt);

       log.info("Dan toc " + this.hsba.getBenhnhanMa().getDantocMa(true).getDmdantocTen());
       params.put("DANTOC", this.hsba.getBenhnhanMa().getDantocMa(true).getDmdantocTen());


       String diachistr = "";
       if (this.hsba.getBenhnhanMa().getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa().getBenhnhanDiachi() + ", ";
       }
       if (this.hsba.getBenhnhanMa().getXaMa(true).getDmxaMa() != null)
       {
         DmXa xa = (DmXa)dieuTriDelegate.findByMa(this.hsba.getBenhnhanMa().getXaMa(true).getDmxaMa(), "DmXa", "dmxaMa");
         diachistr = diachistr + xa.getDmxaTen() + ", ";
       }
       if (this.hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenMa() != null)
       {
         DmHuyen huyen = (DmHuyen)dieuTriDelegate.findByMa(this.hsba.getBenhnhanMa().getHuyenMa(true).getDmhuyenMa(), "DmHuyen", "dmhuyenMa");
         diachistr = diachistr + huyen.getDmhuyenTen() + ", ";
       }
       if (this.hsba.getBenhnhanMa().getTinhMa(true).getDmtinhMa() != null)
       {
         DmTinh tinh = (DmTinh)dieuTriDelegate.findByMa(this.hsba.getBenhnhanMa().getTinhMa(true).getDmtinhMa(), "DmTinh", "dmtinhMa");
         diachistr = diachistr + tinh.getDmtinhTen();
       }
       log.info("Dia chi " + diachistr);
       params.put("DIACHI", diachistr);

       DieuTriUtilDelegate dieutridele = DieuTriUtilDelegate.getInstance();
       DmKhoa khoa = (DmKhoa)dieutridele.findByMa(this.khoaMa, "DmKhoa", "dmkhoaMa");
       params.put("KHOA", khoa.getDmkhoaTen());

       String donViTuoi = this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 3 ? "ng" : this.hsba.getBenhnhanMa().getBenhnhanDonvituoi().shortValue() == 2 ? "th" : "";
       params.put("TUOI", this.hsba.getBenhnhanMa().getBenhnhanTuoi() + donViTuoi);

       DmBenhIcd benhIcd = (DmBenhIcd)dieutridele.findByMa(this.hsba.getHsbaMachdravien(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
       if (benhIcd != null) {
         params.put("CHANDOAN", benhIcd.getDmbenhicdTen());
       } else {
         params.put("CHANDOAN", "");
       }
       DmKetQuaDieuTri ketqua = (DmKetQuaDieuTri)dieutridele.findByMa(this.hsba.getHsbaKetqua(true).getDmkqdtMa(), "DmKetQuaDieuTri", "dmkqdtMa");
       if (ketqua != null) {
         params.put("KETQUA", ketqua.getDmkqdtTen());
       } else {
         params.put("KETQUA", "");
       }
       params.put("YEUCAU", this.hsba.getHsbaYeuCau());

       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("SOBENHAN", this.soVaoVien);

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Xacnhanthongtindieutri");
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

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public String getSoVaoVien()
   {
     return this.soVaoVien;
   }

   public void setSoVaoVien(String soVaoVien)
   {
     this.soVaoVien = soVaoVien;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getNgayVaov()
   {
     return this.ngayVaov;
   }

   public void setNgayVaov(String ngayVaov)
   {
     this.ngayVaov = ngayVaov;
   }

   public HsbaBhyt getHsbabhyt()
   {
     return this.hsbabhyt;
   }

   public void setHsbabhyt(HsbaBhyt hsbabhyt)
   {
     this.hsbabhyt = hsbabhyt;
   }

   public HsbaChuyenMon getHsbaCm()
   {
     return this.hsbaCm;
   }

   public void setHsbaCm(HsbaChuyenMon hsbaCm)
   {
     this.hsbaCm = hsbaCm;
   }

   public String getKhoaDt()
   {
     return this.khoaDt;
   }

   public void setKhoaDt(String khoaDt)
   {
     this.khoaDt = khoaDt;
   }

   public String getKhoaDangDt()
   {
     return this.khoaDangDt;
   }

   public void setKhoaDangDt(String khoaDangDt)
   {
     this.khoaDangDt = khoaDangDt;
   }

   public Boolean getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(Boolean hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.XacNhanThongTinDieuTriAction

 * JD-Core Version:    0.7.0.1

 */