 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmNoiSinh;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaNop;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDiaDiem;
 import com.iesvn.yte.entity.DmDieuTri;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;

 public class GetHSBAAction
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println(request);
     StringBuffer buf = new StringBuffer();
     buf.append("<list>");

     String hsbaMa = request.substring(0, request.indexOf(","));
     String khoaMa = request.substring(request.indexOf(",") + 1);
     try
     {
       HsbaChuyenMonDelegate hsbaCmDelegate = HsbaChuyenMonDelegate.getInstance();
       HsbaChuyenMon hsbacm = hsbaCmDelegate.findBySoVaoVien_MaKhoa(hsbaMa, khoaMa);
       if (hsbacm == null)
       {
         buf.append("</list>");
         return buf.toString();
       }
       Hsba hsba = hsbacm.getHsbaSovaovien();
       if (hsba == null)
       {
         buf.append("</list>");
         return buf.toString();
       }
       System.out.println("Hsba ma " + hsba.getHsbaSovaovien());
       String hsbama = hsba.getHsbaSovaovien();
       String tiepdonma = hsba.getTiepdonMa() == null ? "" : hsba.getTiepdonMa();
       String benhnhanma = hsba.getBenhnhanMa() == null ? "" : hsba.getBenhnhanMa().getBenhnhanMa();
       String donvigoi = hsba.getHsbaDonvigoi() == null ? "" : hsba.getHsbaDonvigoi().getDmbenhvienMa();
       String ngaygiovaov = "";
       String ngaygiorav = "";
       String ngaygiotv = "";
       String chandoanbd = hsba.getHsbaChandoanbd() == null ? "" : hsba.getHsbaChandoanbd();
       String machandoantt = hsba.getHsbaMachdoantuyent() == null ? "" : hsba.getHsbaMachdoantuyent().getDmbenhicdMa();
       String dgtt = hsba.getHsbaDiengiaituyent() == null ? "" : hsba.getHsbaDiengiaituyent();
       String machandoanbd = hsba.getHsbaMachdoanbd() == null ? "" : hsba.getHsbaMachdoanbd().getDmbenhicdMa();
       String dgbd = hsba.getHsbaDiengiaibd() == null ? "" : hsba.getHsbaDiengiaibd();
       String tainan = hsba.getTainanMa() == null ? "" : hsba.getTainanMa().getDmtainanMaso().toString();
       String thanhtoantn = hsba.getHsbaThanhtoantn() == null ? "" : hsba.getHsbaThanhtoantn().toString();
       String phuongtien = hsba.getDmptgtnMaso() == null ? "" : hsba.getDmptgtnMaso().getDmptgtnMaso().toString();
       String nguyennhan = hsba.getHsbaNguyennhan() == null ? "" : hsba.getHsbaNguyennhan();
       String diadiem = hsba.getDiadiemMa() == null ? "" : hsba.getDiadiemMa().getDmdiadiemMa();
       String bophan = "";
       String capcuu = hsba.getHsbaCapcuu() == null ? "" : hsba.getHsbaCapcuu().toString();
       String doituong = hsba.getDoituongMa() == null ? "" : hsba.getDoituongMa().getDmdoituongMaso().toString();
       String benhsu = hsba.getHsbaBenhsu() == null ? "" : hsba.getHsbaBenhsu();
       String ruoubia = hsba.getHsbaRuoubia() == null ? "" : hsba.getHsbaRuoubia().toString();
       String tiepnhan = hsba.getHsbaTiepnhan() == null ? "" : hsba.getHsbaTiepnhan().getDmkhoaMa();
       String dieutri = hsba.getHsbaDieutri() == null ? "" : hsba.getHsbaDieutri().getDmdieutriMa();
       String ketqua = hsba.getHsbaKetqua() == null ? "" : hsba.getHsbaKetqua().getDmkqdtMaso().toString();
       String tuvong = hsba.getHsbaTuvong() == null ? "" : hsba.getHsbaTuvong().getDmbenhicdMa();
       String tuvong24h = hsba.getHsbaTuvvong24g() == null ? "" : hsba.getHsbaTuvvong24g().toString();
       String muon = hsba.getHsbaMuon() == null ? "" : hsba.getHsbaMuon().toString();
       String nang = hsba.getHsbaNang() == null ? "" : hsba.getHsbaNang().toString();
       String kncc = "";
       String ngaynop = "";
       String ngaygiao = "";
       String masogiao = hsba.getHsbaMasogiao() == null ? "" : hsba.getHsbaMasogiao().getHsbanopMa().toString();
       String nhommau = hsba.getHsbaNhommau() == null ? "" : hsba.getHsbaNhommau();
       String rh = hsba.getHsbaRh() == null ? "" : hsba.getHsbaRh();
       String trluong = "";
       String noisinh = hsba.getNoisinhMa() == null ? "" : hsba.getNoisinhMa().getDtdmnoisinhMa();
       String ngaygiocn = "";
       String nhanvien = hsba.getNhanvienMa() == null ? "" : hsba.getNhanvienMa().getDtdmnhanvienMa();
       String ngaybc = "";
       String baotin = hsba.getHsbaBaotin() == null ? "" : hsba.getHsbaBaotin();
       String lydovao = hsba.getHsbaLydovao() == null ? "" : hsba.getHsbaLydovao();
       String vungton = "";
       String dgtv = hsba.getHsbaDiengiaitv() == null ? "" : hsba.getHsbaDiengiaitv();
       String tinhtrangcv = hsba.getHsbaTitrchvi() == null ? "" : hsba.getHsbaTitrchvi();
       String hanhchanh = hsba.getHsbaHanhchanh() == null ? "" : hsba.getHsbaHanhchanh();
       String chmon = hsba.getHsbaChmon() == null ? "" : hsba.getHsbaChmon();
       String ngaycappt = "";
       String khoadangdt = hsba.getHsbaKhoadangdt() == null ? "" : hsba.getHsbaKhoadangdt().getDmkhoaMa();
       String khoavaov = hsba.getHsbaKhoavaov() == null ? "" : hsba.getHsbaKhoavaov().getDmkhoaMa();
       String khoarav = hsba.getHsbaKhoarav() == null ? "" : hsba.getHsbaKhoarav().getDmkhoaMa();
       String chandoanrav = hsba.getHsbaChandoanrav() == null ? "" : hsba.getHsbaChandoanrav();
       String dongy = hsba.getHsbaDongy() == null ? "" : hsba.getHsbaDongy().toString();
       if (hsba.getHsbaNgaygiovaov() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = hsba.getHsbaNgaygiovaov();
         ngaygiovaov = df.format(cal);
       }
       if (hsba.getHsbaNgaygiorav() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         Date cal = hsba.getHsbaNgaygiorav();
         ngaygiorav = df.format(cal);
       }
       if (hsba.getHsbaNgaygiotv() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         Date cal = hsba.getHsbaNgaygiotv();
         ngaygiotv = df.format(cal);
       }
       if (hsba.getHsbaNgaynop() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = hsba.getHsbaNgaynop();
         ngaynop = df.format(cal);
       }
       if (hsba.getHsbaNgaygiao() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = hsba.getHsbaNgaygiao();
         ngaygiao = df.format(cal);
       }
       if (hsba.getHsbaNgaygiocn() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         Date cal = hsba.getHsbaNgaygiocn();
         ngaygiocn = df.format(Long.valueOf(cal.getTime()));
       }
       buf.append("<HSBA MAPHU='' HSBA_SOVAOVIEN='" + hsba.getHsbaSovaovien() + "' TIEPDON_MA='" + tiepdonma + "' BENHNHAN_MA='" + benhnhanma + "' HSBA_DONVIGOI='" + donvigoi + "' HSBA_NGAYGIOVAOV='" + ngaygiovaov + "' HSBA_NGAYGIORAV='" + ngaygiorav + "' HSBA_NGAYGIOTV='" + ngaygiotv + "' HSBA_CHANDOANBD='" + chandoanbd + "' HSBA_MACHDOANTUYENT='" + machandoantt + "' HSBA_DIENGIAITUYENT='" + dgtt + "' HSBA_MACHDOANBD='" + machandoanbd + "' HSBA_DIENGIAIBD='" + dgbd + "' TAINAN_MA='" + tainan + "' HSBA_THANHTOANTN='" + thanhtoantn + "' PHUONGTIEN_MA='" + phuongtien + "' HSBA_NGUYENNHAN='" + nguyennhan + "' DIADIEM_MA='" + diadiem + "' HSBA_BOPHAN='" + bophan + "' HSBA_CAPCUU='" + capcuu + "' DOITUONG_MA='" + doituong + "' HSBA_BENHSU='" + benhsu + "' HSBA_RUOUBIA='" + ruoubia + "' HSBA_TIEPNHAN='" + tiepnhan + "' HSBA_DIEUTRI='" + dieutri + "' HSBA_KETQUA='" + ketqua + "' HSBA_TUVONG='" + tuvong + "' HSBA_TUVVONG24G='" + tuvong24h + "' HSBA_MUON='" + muon + "' HSBA_NANG='" + nang + "' HSBA_KNCC='" + kncc + "' HSBA_NGAYNOP='" + ngaynop + "' HSBA_NGAYGIAO='" + ngaygiao + "' HSBA_MASOGIAO='" + masogiao + "' HSBA_NHOMMAU='" + nhommau + "' HSBA_RH='" + rh + "' HSBA_TRLUONG='" + trluong + "' NOISINH_MA='" + noisinh + "' HSBA_NGAYGIOCN='" + ngaygiocn + "' NHANVIEN_MA='" + nhanvien + "' HSBA_NGAYBC='" + ngaybc + "' HSBA_BAOTIN='" + baotin + "' HSBA_LYDOVAO='" + lydovao + "' HSBA_VUNGTON='" + vungton + "' HSBA_DIENGIAITV='" + dgtv + "' HSBA_TITRCHVI='" + tinhtrangcv + "' HSBA_HANHCHANH='" + hanhchanh + "' HSBA_CHMON='" + chmon + "' HSBA_NGAYCAPPT='" + ngaycappt + "' HSBA_KHOADANGDT='" + khoadangdt + "' HSBA_KHOAVAOV='" + khoavaov + "' HSBA_KHOARAV='" + khoarav + "' HSBA_CHANDOANRAV='" + chandoanrav + "' HSBA_DONGY='" + dongy + "'>");
       try
       {
         BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
         System.out.println(benhnhanma);
         BenhNhan bn = bnDelegate.find(benhnhanma);
         System.out.println("bn ma " + bn.getBenhnhanMa());

         String hoten = bn.getBenhnhanHoten() == null ? "" : bn.getBenhnhanHoten();
         String ngaysinh = "";

         String tuoi = "";
         if (bn.getBenhnhanTuoi() != null) {
           tuoi = String.valueOf(bn.getBenhnhanTuoi().intValue());
         }
         String donvituoi = "";
         if (bn.getBenhnhanDonvituoi() != null) {
           donvituoi = String.valueOf(bn.getBenhnhanDonvituoi().intValue());
         }
         String dantoc = bn.getDantocMa() == null ? "" : bn.getDantocMa().getDmdantocMaso().toString();
         String quocgia = bn.getQuocgiaMa() == null ? "" : bn.getQuocgiaMa().getDmquocgiaMa();
         String tinh = bn.getTinhMa() == null ? "" : bn.getTinhMa().getDmtinhMa();
         String huyen = bn.getHuyenMa() == null ? "" : bn.getHuyenMa().getDmhuyenMa();
         String xa = bn.getXaMa() == null ? "" : bn.getXaMa().getDmxaMa();
         String diachi = bn.getBenhnhanDiachi() == null ? "" : bn.getBenhnhanDiachi();
         String nghe = bn.getBenhnhanNghe() == null ? "" : bn.getBenhnhanNghe().getDmnghenghiepMaso().toString();
         String noisinhbn = "";
         String lanvao = bnDelegate.getLanVao(bn.getBenhnhanMa()).toString();
         String nhanvienbn = bn.getNhanvienMa() == null ? "" : bn.getNhanvienMa().getDtdmnhanvienMa();
         String ngaygiocnbn = "";
         String gioitinh = bn.getDmgtMaso() == null ? "" : bn.getDmgtMaso().getDmgtMaso().toString();
         if (bn.getBenhnhanNgaysinh() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = bn.getBenhnhanNgaysinh();
           ngaysinh = df.format(Long.valueOf(cal.getTime()));
         }
         if (bn.getBenhnhanNgaygiocn() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = bn.getBenhnhanNgaygiocn();
           ngaygiocnbn = df.format(Long.valueOf(cal.getTime()));
         }
         buf.append("<BENH_NHAN MAPHU='' BENHNHAN_MA='" + bn.getBenhnhanMa() + "' BENHNHAN_HOTEN='" + hoten + "' BENHNHAN_NGAYSINH='" + ngaysinh + "' BENHNHAN_TUOI='" + tuoi + "' BENHNHAN_DONVITUOI='" + donvituoi + "' BENHNHAN_GIOI='" + gioitinh + "' DANTOC_MA='" + dantoc + "' QUOCGIA_MA='" + quocgia + "' TINH_MA='" + tinh + "' HUYEN_MA='" + huyen + "' XA_MA='" + xa + "' BENHNHAN_DIACHI='" + diachi + "' BENHNHAN_NGHE='" + nghe + "' BENHNHAN_NOISINH='" + noisinhbn + "' BENHNHAN_LANVAO='" + lanvao + "' BENHNHAN_NGAYGIOCN='" + ngaygiocnbn + "' NHANVIEN_MA='" + nhanvienbn + "' />");
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
       try
       {
         HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
         HsbaBhyt hsbabhyt = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsbama);
         System.out.println("hsba bhyt ma " + hsbabhyt.getHsbabhytMa());

         String sovaovien = hsbabhyt.getHsbaSovaovien() == null ? "" : hsbabhyt.getHsbaSovaovien().getHsbaSovaovien();
         String khoibh = hsbabhyt.getHsbabhytKhoibh() == null ? "" : hsbabhyt.getHsbabhytKhoibh().getDtdmkhoibhytMa();
         String tinhbh = hsbabhyt.getHsbabhytTinhbh() == null ? "" : hsbabhyt.getHsbabhytTinhbh().getDmtinhMaso().toString();
         String huyenbh = hsbabhyt.getHsbabhytHuyenbh() == null ? "" : hsbabhyt.getHsbabhytHuyenbh();
         String coquanbh = hsbabhyt.getHsbabhytCoquanbh() == null ? "" : hsbabhyt.getHsbabhytCoquanbh();
         String sothebh = hsbabhyt.getHsbabhytSothebh() == null ? "" : hsbabhyt.getHsbabhytSothebh();
         String nambhyt = hsbabhyt.getHsbabhytNambhyt() == null ? "" : hsbabhyt.getHsbabhytNambhyt();
         String namtg = hsbabhyt.getHsbabhytNamtg() == null ? "" : hsbabhyt.getHsbabhytNamtg().toString();
         String giatri0 = "";
         String giatri1 = "";
         String giatri2 = "";
         String giatri3 = "";
         String tuyen = hsbabhyt.getHsbabhytTuyen() == null ? "" : hsbabhyt.getHsbabhytTuyen().toString();
         System.out.println(tuyen);
         String makcb = hsbabhyt.getHsbabhytMakcb() == null ? "" : hsbabhyt.getHsbabhytMakcb().getDmbenhvienMa();
         String ngayghithe = "";
         String ngaytrathe = "";
         String ngaygiocnbh = "";
         String nhanviencn = hsbabhyt.getHsbabhytNhanvienMa() == null ? "" : hsbabhyt.getHsbabhytNhanvienMa();
         String sothete = hsbabhyt.getHsbabhytSothete() == null ? "" : hsbabhyt.getHsbabhytSothete();
         String sothengheo = hsbabhyt.getHsbabhytSothengheo() == null ? "" : hsbabhyt.getHsbabhytSothengheo();
         String khaisinh = hsbabhyt.getHsbabhytKhaisinh() == null ? "" : hsbabhyt.getHsbabhytKhaisinh();
         String chungsinh = hsbabhyt.getHsbabhytChungsinh() == null ? "" : hsbabhyt.getHsbabhytChungsinh();
         String cnxa = hsbabhyt.getHsbabhytCnxa() == null ? "" : hsbabhyt.getHsbabhytCnxa();
         String giamho = hsbabhyt.getHsbabhytGiamho() == null ? "" : hsbabhyt.getHsbabhytGiamho();
         String xnmientn = hsbabhyt.getHsbabhytXnmientn() == null ? "" : hsbabhyt.getHsbabhytXnmientn().toString();
         String tylebh = hsbabhyt.getHsbabhytTylebh() == null ? "" : hsbabhyt.getHsbabhytTylebh().toString();
         if (hsbabhyt.getHsbabhytGiatri0() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbabhyt.getHsbabhytGiatri0();
           giatri0 = df.format(Long.valueOf(cal.getTime()));
         }
         if (hsbabhyt.getHsbabhytGiatri1() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbabhyt.getHsbabhytGiatri1();
           giatri1 = df.format(Long.valueOf(cal.getTime()));
         }
         if (hsbabhyt.getHsbabhytGiatri2() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbabhyt.getHsbabhytGiatri2();
           giatri2 = df.format(Long.valueOf(cal.getTime()));
         }
         if (hsbabhyt.getHsbabhytGiatri3() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbabhyt.getHsbabhytGiatri3();
           giatri3 = df.format(Long.valueOf(cal.getTime()));
         }
         if (hsbabhyt.getHsbabhytNgayghithe() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbabhyt.getHsbabhytNgayghithe();
           ngayghithe = df.format(Long.valueOf(cal.getTime()));
         }
         if (hsbabhyt.getHsbabhytNgaytrathe() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbabhyt.getHsbabhytNgaytrathe();
           ngaytrathe = df.format(Long.valueOf(cal.getTime()));
         }
         if (hsbabhyt.getHsbabhytNgaygiocn() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbabhyt.getHsbabhytNgaygiocn();
           ngaygiocnbh = df.format(Long.valueOf(cal.getTime()));
         }
         buf.append("<HSBA_BHYT MAPHU='' HSBABHYT_MA='" + hsbabhyt.getHsbabhytMa() + "' HSBA_SOVAOVIEN='" + sovaovien + "' HSBABHYT_KHOIBH='" + khoibh + "' HSBABHYT_TINHBH='" + tinhbh + "' HSBABHYT_HUYENBH='" + huyenbh + "' HSBABHYT_COQUANBH='" + coquanbh + "' HSBABHYT_SOTHEBH='" + sothebh + "' HSBABHYT_NAMBHYT='" + nambhyt + "' HSBABHYT_NAMTG='" + namtg + "' HSBABHYT_GIATRI0='" + giatri0 + "' HSBABHYT_GIATRI1='" + giatri1 + "' HSBABHYT_GIATRI2='" + giatri2 + "' HSBABHYT_GIATRI3='" + giatri3 + "' HSBABHYT_TUYEN='" + tuyen + "' HSBABHYT_MAKCB='" + makcb + "' HSBABHYT_NGAYGHITHE='" + ngayghithe + "' HSBABHYT_NGAYTRATHE='" + ngaytrathe + "' HSBABHYT_NGAYGIOCN='" + ngaygiocnbh + "' HSBABHYT_NHANVIEN_MA='" + nhanviencn + "' HSBABHYT_SOTHETE='" + sothete + "' HSBABHYT_SOTHENGHEO='" + sothengheo + "' HSBABHYT_KHAISINH='" + khaisinh + "' HSBABHYT_CHUNGSINH='" + chungsinh + "' HSBABHYT_CNXA='" + cnxa + "' HSBABHYT_GIAMHO='" + giamho + "' HSBABHYT_XNMIENTN='" + xnmientn + "' HSBABHYT_TYLEBH='" + tylebh + "' />");
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
       buf.append("</list>");
       return buf.toString();
     }
     buf.append("</HSBA>");
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetHSBAAction

 * JD-Core Version:    0.7.0.1

 */