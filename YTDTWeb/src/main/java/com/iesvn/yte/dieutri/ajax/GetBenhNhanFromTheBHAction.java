 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmTinh;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;

 public class GetBenhNhanFromTheBHAction
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println("##### GetBenhNhanFromTheBHAction #####");

     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     try
     {
       BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
       TiepDon td = bnDelegate.findBySoTheBHYT(request);
       if (td == null)
       {
         buf.append("</list>");
         return buf.toString();
       }
       System.out.println("td ma " + td.getTiepdonMa());





       String maphieuk = "";
       String ngaygio = "";
       String benhnhan = td.getBenhnhanMa() == null ? "" : td.getBenhnhanMa().getBenhnhanMa();
       String doituong = td.getDoituongMa() == null ? "" : td.getDoituongMa().getDmdoituongMa();
       String phanloai = td.getTiepdonPhanloai() == null ? "" : td.getTiepdonPhanloai();
       String tainan = "";
       String donvigoi = "";

       String khobh = td.getKhoibhytMa() == null ? "" : td.getKhoibhytMa().getDtdmkhoibhytMa();
       String tinhbh = td.getTinhbhytMa() == null ? "" : td.getTinhbhytMa().getDmtinhBHYT();
       String dpbh = td.getDpbhytMa() == null ? "" : td.getDpbhytMa();
       String macoquan = td.getTiepdonMacoquan() == null ? "" : td.getTiepdonMacoquan();
       String sothebh = td.getTiepdonSothebh() == null ? "" : td.getTiepdonSothebh();
       String nambh = td.getTiepdonNambhyt() == null ? "" : td.getTiepdonNambhyt();
       String sonambh = td.getTiepdonSonambh() == null ? "" : td.getTiepdonSonambh().toString();
       String tuyen = td.getTiepdonTuyen() == null ? "" : td.getTiepdonTuyen().toString();
       String kcb = td.getKcbbhytMa() == null ? "" : td.getKcbbhytMa().getDmbenhvienMa();

       String giatri1 = "";
       String giatri2 = "";
       String giatri3 = "";
       String giatri4 = "";
       String moc1 = "";
       String moc2 = "";
       String moc3 = "";

       String khaisinh = td.getTiepdonKhaisinh() == null ? "" : td.getTiepdonKhaisinh();
       String chungsinh = td.getTiepdonChungsinh() == null ? "" : td.getTiepdonChungsinh();
       String sothete = td.getTiepdonSothete() == null ? "" : td.getTiepdonSothete();
       String thengheo = td.getTiepdonThengheo() == null ? "" : td.getTiepdonThengheo();
       String bankham = td.getTiepdonBankham() == null ? "" : td.getTiepdonBankham().getDtdmbankhamMa();
       String sott = "";
       String sotts = "";
       String bntra = "";
       String loaikham = td.getTiepdonLoaikham();

       String doituongbh = td.getTiepdonDoituongbhyt() == null ? "" : td.getTiepdonDoituongbhyt().getDtdmphloaibhytMa();
       String tylebh = td.getTiepdonTylebh() == null ? "" : td.getTiepdonTylebh().toString();
       String bacsi = td.getTiepdonBacsi() == null ? "" : td.getTiepdonBacsi().getDtdmnhanvienMa();
       String nguoiduyet = td.getTiepdonNguoiduyet() == null ? "" : td.getTiepdonNguoiduyet().getDtdmnhanvienMa();
       String kyhieu = "";
       String quyen = "";
       String bienlai = "";
       String maphieud = "";

       String nhanviencn = "";
       String thungan = "";
       String phuongtien = "";
       String nguyennhan = "";
       String diadiem = "";
       String ruoubia = "";
       String lydovaov = "";
       String chandoantt = "";
       String dgchandoantt = "";
       String chandoanbd = "";
       String dgchandoanbd = "";

       String madich = "";
       String vuottuyen = td.getTiepdonVuottuyen() == null ? "" : td.getTiepdonVuottuyen();
       String trongluong = td.getTiepdonTrluong() == null ? "" : td.getTiepdonTrluong().toString();
       String chieucao = td.getTiepdonChieucao() == null ? "" : td.getTiepdonChieucao().toString();
       String nhommau = td.getTiepdonNhommau() == null ? "" : td.getTiepdonNhommau();
       String rh = td.getTiepdonRh() == null ? "" : td.getTiepdonRh();
       String giuong = "";
       String baotin = "";
       String ketqua = "";
       String dieutri = "";
       String nghe = td.getTiepdonNghe() == null ? "" : td.getTiepdonNghe();

       String para1 = "";
       String para2 = "";
       String para3 = "";
       String para4 = "";
       String chkhoa = "";
       String chvien = "";
       String ngaygiora = "";
       String tinhtrangra = "";
       String bschuyen = "";
       String lydochv = "";

       String taikham = "";
       String loidan = "";
       String tuvong = "";
       String cum = "";
       String ngaygiocn = "";
       String status = "";
       String ngaytt = "";
       if (td.getTiepdonGiatri1() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonGiatri1();
         giatri1 = df.format(Long.valueOf(cal.getTime()));
       }
       if (td.getTiepdonGiatri2() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonGiatri2();
         giatri2 = df.format(Long.valueOf(cal.getTime()));
       }
       if (td.getTiepdonGiatri3() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonGiatri3();
         giatri3 = df.format(Long.valueOf(cal.getTime()));
       }
       if (td.getTiepdonGiatri4() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonGiatri4();
         giatri4 = df.format(Long.valueOf(cal.getTime()));
       }
       if (td.getTiepdonMoc1() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonMoc1();
         moc1 = df.format(Long.valueOf(cal.getTime()));
       }
       if (td.getTiepdonMoc2() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonMoc2();
         moc2 = df.format(Long.valueOf(cal.getTime()));
       }
       if (td.getTiepdonMoc3() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonMoc3();
         moc3 = df.format(Long.valueOf(cal.getTime()));
       }
       Boolean coGiayChuyenVien = td.getTiepdonCoGiayGioiThieu();
       String sCoGiayGioiThieu;
       if ((coGiayChuyenVien != null) && (coGiayChuyenVien.booleanValue() == true)) {
         sCoGiayGioiThieu = "1";
       } else {
         sCoGiayGioiThieu = "0";
       }
       Boolean khamDaLieu = td.getTiepdonKhamDaLieu();
       String sKhamDaLieu;
       if ((khamDaLieu != null) && (khamDaLieu.booleanValue() == true)) {
         sKhamDaLieu = "1";
       } else {
         sKhamDaLieu = "0";
       }
       buf.append("<TIEP_DON MAPHU='' TIEPDON_MA='' TIEPDON_MAPHIEUK='" + maphieuk + "' TIEPDON_NGAYGIO='" + ngaygio + "' BENHNHAN_MA='" + benhnhan + "' DOITUONG_MA='" + doituong + "' TIEPDON_PHANLOAI='" + phanloai + "' TAINAN_MA='" + tainan + "' TIEPDON_DONVIGOI='" + donvigoi + "' KHOIBHYT_MA='" + khobh + "' TINHBHYT_MA='" + tinhbh + "' DPBHYT_MA='" + dpbh + "' TIEPDON_MACOQUAN='" + macoquan + "' TIEPDON_SOTHEBH='" + sothebh + "' TIEPDON_NAMBHYT='" + nambh + "' TIEPDON_SONAMBH='" + sonambh + "' TIEPDON_TUYEN='" + tuyen + "' KCBBHYT_MA='" + kcb + "' TIEPDON_GIATRI1='" + giatri1 + "' TIEPDON_GIATRI2='" + giatri2 + "' TIEPDON_KHAISINH='" + khaisinh + "' TIEPDON_CHUNGSINH='" + chungsinh + "' TIEPDON_SOTHETE='" + sothete + "' TIEPDON_THENGHEO='" + thengheo + "' TIEPDON_BANKHAM='" + bankham + "' TIEPDON_THUTU='" + sott + "' TIEPDON_THUTUS='" + sotts + "' TIEPDON_LOAIKHAM='" + loaikham + "' TIEPDON_BNTRA='" + bntra + "' TIEPDON_DOITUONGBHYT='" + doituongbh + "' TIEPDON_TYLEBH='" + tylebh + "' TIEPDON_BACSI='" + bacsi + "' TIEPDON_NGUOIDUYET='" + nguoiduyet + "' TIEPDON_KYHIEU='" + kyhieu + "' TIEPDON_QUYEN='" + quyen + "' TIEPDON_BIENLAI='" + bienlai + "' TIEPDON_MAPHIEUD='" + maphieud + "' TIEPDON_NHANVIENCN='" + nhanviencn + "' TIEPDON_THUNGAN='" + thungan + "' PHUONGTIEN_MA='" + phuongtien + "' NGUYENHAN_MA='" + nguyennhan + "' DIADIEM_MA='" + diadiem + "' TIEPDON_RUOUBIA='" + ruoubia + "' TIEPDON_LYDOVAOV='" + lydovaov + "' TIEPDON_MACHDOANB0='" + chandoantt + "' TIEPDON_DGCHDOANB0='" + dgchandoantt + "' TIEPDON_MACHDOANBD='" + chandoanbd + "' TIEPDON_DGCHDOANBD='" + dgchandoanbd + "' TIEPDON_MADICH='" + madich + "' TIEPDON_VUOTTUYEN='" + vuottuyen + "' TIEPDON_TRLUONG='" + trongluong + "' TIEPDON_CHIEUCAO='" + chieucao + "' TIEPDON_NHOMMAU='" + nhommau + "' TIEPDON_RH='" + rh + "' TIEPDON_GIUONG='" + giuong + "' TIEPDON_BAOTIN='" + baotin + "' KETQUA_MA='" + ketqua + "' DIEUTRI_MA='" + dieutri + "' TIEPDON_NGHE='" + nghe + "' TIEPDON_PARA1='" + para1 + "' TIEPDON_PARA2='" + para2 + "' TIEPDON_PARA3='" + para3 + "' TIEPDON_PARA4='" + para4 + "' TIEPDON_CHKHOA='" + chkhoa + "' TIEPDON_CHVIEN='" + chvien + "' TIEPDON_NGAYGIORA='" + ngaygiora + "' TIEPDON_TITRANGRA='" + tinhtrangra + "' TIEPDON_BSCHUYEN='" + bschuyen + "' TIEPDON_LYDOCHVI='" + lydochv + "' TIEPDON_TAIKHAM='" + taikham + "' TIEPDON_LOIDAN='" + loidan + "' TIEPDON_TUVONG='" + tuvong + "' TIEPDON_CUM='" + cum + "' TIEPDON_NGAYGIOCN='" + ngaygiocn + "' TIEPDON_STATUS='" + status + "' TIEPDON_GIATRI3='" + giatri3 + "' TIEPDON_GIATRI4='" + giatri4 + "' TIEPDON_MOC1='" + moc1 + "' TIEPDON_MOC2='" + moc2 + "' TIEPDON_MOC3='" + moc3 + "' TIEPDON_CO_GIAY_GIOI_THIEU='" + sCoGiayGioiThieu + "' TIEPDON_KHAM_DA_LIEU='" + sKhamDaLieu + "' TIEPDON_NGAYTT='" + ngaytt + "'/>");
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetBenhNhanFromTheBHAction

 * JD-Core Version:    0.7.0.1

 */