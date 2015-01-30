 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;

 @Scope(ScopeType.CONVERSATION)
 @Name("B253_Timbenhnhantheonhieutieuchi")
 @Synchronized(timeout=6000000L)
 public class TimKiemBenhNhanNoiTruAction
   implements Serializable
 {
   private static final long serialVersionUID = -2423255254960254349L;
   private static Logger log = Logger.getLogger(TimKiemBenhNhanNoiTruAction.class);
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private String sobenhan;
   private String hoten;
   private String tuoi;
   private String namsinh;
   private Boolean gioi;
   private String matinh;
   private String tentinh;
   private String mahuyen;
   private String tenhuyen;
   private String maxa;
   private String tenxa;
   private String diachi;
   private String manghenghiep;
   private String tennghenghiep;
   private String madantoc;
   private String tendantoc;
   private String madoituong;
   private String tendoituong;
   private String madoituongBHYT;
   private String tendoituongBHYT;
   private String maloaitainan;
   private String tenloaitainan;
   private String mabvchuyen;
   private String tenbvchuyen;
   private String ngayvaovien;
   private String machandoanbd;
   private String tenchandoanbd;
   private String makhoadieutri;
   private String tenkhoadieutri;
   private String machandoanicd10;
   private String tenchandoanicd10;
   private String maketquadieutri;
   private String tenketquadieutri;
   private String ngayravien;

   @Create
   @Begin(join=true)
   public String init()
   {
     resetValue();
     return "DieuTri_DichVuTienIch_TimBenhNhanTheoNhieuTieuChi";
   }

   @End
   public void end() {}

   public void resetValue()
   {
     log.info("---resetValue");
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.ngayhientai = Utils.getCurrentDate();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     resetResultValue();
     this.sobenhan = null;
   }

   private void resetResultValue()
   {
     this.hoten = null;
     this.tuoi = null;
     this.namsinh = null;
     this.gioi = null;

     this.matinh = null;
     this.tentinh = null;
     this.mahuyen = null;
     this.tenhuyen = null;
     this.maxa = null;
     this.tenxa = null;
     this.diachi = null;

     this.manghenghiep = null;
     this.tennghenghiep = null;

     this.madantoc = null;
     this.tendantoc = null;

     this.madoituong = null;
     this.tendoituong = null;

     this.madoituongBHYT = null;
     this.tendoituongBHYT = null;

     this.maloaitainan = null;
     this.tenloaitainan = null;

     this.mabvchuyen = null;
     this.tenbvchuyen = null;

     this.ngayvaovien = null;

     this.machandoanbd = null;
     this.tenchandoanbd = null;

     this.makhoadieutri = null;
     this.tenkhoadieutri = null;

     this.machandoanicd10 = null;
     this.tenchandoanicd10 = null;



     this.maketquadieutri = null;
     this.tenketquadieutri = null;

     this.ngayravien = null;
   }

   public void refresh()
   {
     log.info("call refresh()");
     resetResultValue();
     setInforFromChoseValue();
     log.info("end call refresh()");
   }

   private void setInforFromChoseValue()
   {
     log.info("setInforFromChoseValue()");
     log.info(this.sobenhan);
     if ((this.sobenhan == null) || (this.sobenhan.equals(""))) {
       return;
     }
     HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
     Hsba hsba = hsbaDelegate.find(this.sobenhan);
     log.info(hsba);
     if (hsba == null) {
       return;
     }
     this.hoten = hsba.getBenhnhanMa(true).getBenhnhanHoten();
     this.tuoi = (hsba.getBenhnhanMa(true).getBenhnhanTuoi() + "");
     log.info(this.hoten);
     log.info(this.tuoi);

     Short donvituoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
     if (donvituoi.shortValue() != 1) {
       if (donvituoi.shortValue() == 2) {
         this.tuoi = (this.tuoi + " " + IConstantsRes.THANG);
       } else if (donvituoi.shortValue() == 3) {
         this.tuoi = (this.tuoi + " " + IConstantsRes.NGAY);
       }
     }
     SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
     Date dNamSinh = hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
     if (dNamSinh != null) {
       this.namsinh = df.format(dNamSinh);
     } else {
       this.namsinh = hsba.getBenhnhanMa(true).getBenhnhanNamsinh();
     }
     String sGioi = hsba.getBenhnhanMa(true).getDmgtMaso(true).getDmgtMa();
     if ("0".equals(sGioi)) {
       this.gioi = Boolean.valueOf(false);
     } else {
       this.gioi = Boolean.valueOf(true);
     }
     this.matinh = hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhMa();
     this.tentinh = hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();

     this.maxa = hsba.getBenhnhanMa(true).getXaMa(true).getDmxaMa();
     this.tenxa = hsba.getBenhnhanMa(true).getXaMa(true).getDmxaTen();

     this.mahuyen = hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenMa();
     this.tenhuyen = hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen();

     this.diachi = hsba.getBenhnhanMa(true).getBenhnhanDiachi();

     this.manghenghiep = hsba.getBenhnhanMa(true).getBenhnhanNghe(true).getDmnghenghiepMa();
     this.tennghenghiep = hsba.getBenhnhanMa(true).getBenhnhanNghe(true).getDmnghenghiepTen();

     this.madantoc = hsba.getBenhnhanMa(true).getDantocMa(true).getDmdantocMa();
     this.tendantoc = hsba.getBenhnhanMa(true).getDantocMa(true).getDmdantocTen();

     this.madoituong = hsba.getDoituongMa(true).getDmdoituongMa();
     this.tendoituong = hsba.getDoituongMa(true).getDmdoituongTen();



     this.maloaitainan = hsba.getTainanMa(true).getDmtainanMa();
     this.tenloaitainan = hsba.getTainanMa(true).getDmtainanTen();

     this.mabvchuyen = hsba.getHsbaDonvigoi(true).getDmbenhvienMa();
     this.tenbvchuyen = hsba.getHsbaDonvigoi(true).getDmbenhvienTen();

     Date dNgayGioVaoVien = hsba.getHsbaNgaygiovaov();
     if (dNgayGioVaoVien != null) {
       this.ngayvaovien = df.format(dNgayGioVaoVien);
     }
     this.machandoanbd = hsba.getHsbaMachdoanbd(true).getDmbenhicdMa();
     this.tenchandoanbd = hsba.getHsbaMachdoanbd(true).getDmbenhicdTen();

     this.machandoanicd10 = hsba.getHsbaMachdravien(true).getDmbenhicdMa();
     this.tenchandoanicd10 = hsba.getHsbaMachdravien(true).getDmbenhicdTen();

     this.makhoadieutri = hsba.getHsbaKhoadangdt(true).getDmkhoaMa();
     this.tenkhoadieutri = hsba.getHsbaKhoadangdt(true).getDmkhoaTen();

     this.maketquadieutri = hsba.getHsbaKetqua(true).getDmkqdtMa();
     this.tenketquadieutri = hsba.getHsbaKetqua(true).getDmkqdtTen();

     Date dNgayGioRaVien = hsba.getHsbaNgaygiorav();
     if (dNgayGioRaVien != null) {
       this.ngayravien = df.format(dNgayGioRaVien);
     }
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
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

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public static long getSerialVersionUID()
   {
     return -2423255254960254349L;
   }

   public String getSobenhan()
   {
     return this.sobenhan;
   }

   public void setSobenhan(String sobenhan)
   {
     this.sobenhan = sobenhan;
   }

   public String getHoten()
   {
     return this.hoten;
   }

   public void setHoten(String hoten)
   {
     this.hoten = hoten;
   }

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getNamsinh()
   {
     return this.namsinh;
   }

   public void setNamsinh(String namsinh)
   {
     this.namsinh = namsinh;
   }

   public Boolean getGioi()
   {
     return this.gioi;
   }

   public void setGioi(Boolean gioi)
   {
     this.gioi = gioi;
   }

   public String getMatinh()
   {
     return this.matinh;
   }

   public void setMatinh(String matinh)
   {
     this.matinh = matinh;
   }

   public String getTentinh()
   {
     return this.tentinh;
   }

   public void setTentinh(String tentinh)
   {
     this.tentinh = tentinh;
   }

   public String getMahuyen()
   {
     return this.mahuyen;
   }

   public void setMahuyen(String mahuyen)
   {
     this.mahuyen = mahuyen;
   }

   public String getTenhuyen()
   {
     return this.tenhuyen;
   }

   public void setTenhuyen(String tenhuyen)
   {
     this.tenhuyen = tenhuyen;
   }

   public String getMaxa()
   {
     return this.maxa;
   }

   public void setMaxa(String maxa)
   {
     this.maxa = maxa;
   }

   public String getTenxa()
   {
     return this.tenxa;
   }

   public void setTenxa(String tenxa)
   {
     this.tenxa = tenxa;
   }

   public String getDiachi()
   {
     return this.diachi;
   }

   public void setDiachi(String diachi)
   {
     this.diachi = diachi;
   }

   public String getManghenghiep()
   {
     return this.manghenghiep;
   }

   public void setManghenghiep(String manghenghiep)
   {
     this.manghenghiep = manghenghiep;
   }

   public String getTennghenghiep()
   {
     return this.tennghenghiep;
   }

   public void setTennghenghiep(String tennghenghiep)
   {
     this.tennghenghiep = tennghenghiep;
   }

   public String getMadantoc()
   {
     return this.madantoc;
   }

   public void setMadantoc(String madantoc)
   {
     this.madantoc = madantoc;
   }

   public String getTendantoc()
   {
     return this.tendantoc;
   }

   public void setTendantoc(String tendantoc)
   {
     this.tendantoc = tendantoc;
   }

   public String getMadoituong()
   {
     return this.madoituong;
   }

   public void setMadoituong(String madoituong)
   {
     this.madoituong = madoituong;
   }

   public String getTendoituong()
   {
     return this.tendoituong;
   }

   public void setTendoituong(String tendoituong)
   {
     this.tendoituong = tendoituong;
   }

   public String getMadoituongBHYT()
   {
     return this.madoituongBHYT;
   }

   public void setMadoituongBHYT(String madoituongBHYT)
   {
     this.madoituongBHYT = madoituongBHYT;
   }

   public String getTendoituongBHYT()
   {
     return this.tendoituongBHYT;
   }

   public void setTendoituongBHYT(String tendoituongBHYT)
   {
     this.tendoituongBHYT = tendoituongBHYT;
   }

   public String getMaloaitainan()
   {
     return this.maloaitainan;
   }

   public void setMaloaitainan(String maloaitainan)
   {
     this.maloaitainan = maloaitainan;
   }

   public String getTenloaitainan()
   {
     return this.tenloaitainan;
   }

   public void setTenloaitainan(String tenloaitainan)
   {
     this.tenloaitainan = tenloaitainan;
   }

   public String getMabvchuyen()
   {
     return this.mabvchuyen;
   }

   public void setMabvchuyen(String mabvchuyen)
   {
     this.mabvchuyen = mabvchuyen;
   }

   public String getTenbvchuyen()
   {
     return this.tenbvchuyen;
   }

   public void setTenbvchuyen(String tenbvchuyen)
   {
     this.tenbvchuyen = tenbvchuyen;
   }

   public String getNgayvaovien()
   {
     return this.ngayvaovien;
   }

   public void setNgayvaovien(String ngayvaovien)
   {
     this.ngayvaovien = ngayvaovien;
   }

   public String getMachandoanbd()
   {
     return this.machandoanbd;
   }

   public void setMachandoanbd(String machandoanbd)
   {
     this.machandoanbd = machandoanbd;
   }

   public String getTenchandoanbd()
   {
     return this.tenchandoanbd;
   }

   public void setTenchandoanbd(String tenchandoanbd)
   {
     this.tenchandoanbd = tenchandoanbd;
   }

   public String getMakhoadieutri()
   {
     return this.makhoadieutri;
   }

   public void setMakhoadieutri(String makhoadieutri)
   {
     this.makhoadieutri = makhoadieutri;
   }

   public String getTenkhoadieutri()
   {
     return this.tenkhoadieutri;
   }

   public void setTenkhoadieutri(String tenkhoadieutri)
   {
     this.tenkhoadieutri = tenkhoadieutri;
   }

   public String getMachandoanicd10()
   {
     return this.machandoanicd10;
   }

   public void setMachandoanicd10(String machandoanicd10)
   {
     this.machandoanicd10 = machandoanicd10;
   }

   public String getTenchandoanicd10()
   {
     return this.tenchandoanicd10;
   }

   public void setTenchandoanicd10(String tenchandoanicd10)
   {
     this.tenchandoanicd10 = tenchandoanicd10;
   }

   public String getMaketquadieutri()
   {
     return this.maketquadieutri;
   }

   public void setMaketquadieutri(String maketquadieutri)
   {
     this.maketquadieutri = maketquadieutri;
   }

   public String getTenketquadieutri()
   {
     return this.tenketquadieutri;
   }

   public void setTenketquadieutri(String tenketquadieutri)
   {
     this.tenketquadieutri = tenketquadieutri;
   }

   public String getNgayravien()
   {
     return this.ngayravien;
   }

   public void setNgayravien(String ngayravien)
   {
     this.ngayravien = ngayravien;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.TimKiemBenhNhanNoiTruAction

 * JD-Core Version:    0.7.0.1

 */