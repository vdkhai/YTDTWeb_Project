 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;

 public class GetThuocNoiTruAction
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println(request);
     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     List<ThuocNoiTru> listTNT = null;
     try
     {
       ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
       listTNT = tntDelegate.findByMaPhieu(request);
       if (listTNT != null) {
         for (ThuocNoiTru tnt : listTNT)
         {
           System.out.println("Thuoc noi tru ma " + tnt.getThuocnoitruMaphieu());
           String ma = tnt.getThuocnoitruMa() == null ? "" : tnt.getThuocnoitruMa().toString();
           String sovaovien = tnt.getHsbaKhoa().getHsbaSovaovien() == null ? "" : tnt.getHsbaKhoa().getHsbaSovaovien().getHsbaSovaovien();
           String maphieu = tnt.getThuocnoitruMaphieu() == null ? "" : tnt.getThuocnoitruMaphieu();
           String khoa = tnt.getThuocnoitruKhoa() == null ? "" : tnt.getThuocnoitruKhoa().getDmkhoaMa();
           String phong = tnt.getThuocnoitruPhong() == null ? "" : tnt.getThuocnoitruPhong();
           String bosung = tnt.getThuocnoitruBosung() == null ? "" : tnt.getThuocnoitruBosung();
           String ngaygio = "";
           String mathuoc = tnt.getThuocnoitruMathuoc() == null ? "" : tnt.getThuocnoitruMathuoc().getDmthuocMa();
           String maphong = tnt.getThuocnoitruMaphong() == null ? "" : tnt.getThuocnoitruMaphong().getDtdmclsbgMa();
           String quocgia = tnt.getThuocnoitruQuocgia() == null ? "" : tnt.getThuocnoitruQuocgia().getDmquocgiaMa();
           String nguon = tnt.getThuocnoitruNguon() == null ? "" : tnt.getThuocnoitruNguon().getDmnctMa();
           String phanloai = tnt.getThuocnoitruPhanloai() == null ? "" : tnt.getThuocnoitruPhanloai().getDmphanloaithuocMa();
           String loai = tnt.getThuocnoitruLoai() == null ? "" : tnt.getThuocnoitruLoai();
           String yeucau = tnt.getThuocnoitruYeucau() == null ? "" : tnt.getThuocnoitruYeucau().toString();
           String mien = tnt.getThuocnoitruMien() == null ? "" : tnt.getThuocnoitruMien().toString();
           String nhi = tnt.getThuocnoitruNhi() == null ? "" : tnt.getThuocnoitruNhi().toString();
           String lao = tnt.getThuocnoitruLao() == null ? "" : tnt.getThuocnoitruLao().toString();
           String soluong = tnt.getThuocnoitruSoluong() == null ? "" : tnt.getThuocnoitruSoluong().toString();
           String tra = tnt.getThuocnoitruTra() == null ? "" : tnt.getThuocnoitruTra().toString();
           String dongia = tnt.getThuocnoitruDongia() == null ? "" : tnt.getThuocnoitruDongia().toString();
           String dongiabh = tnt.getThuocnoitruDongiabh() == null ? "" : tnt.getThuocnoitruDongiabh().toString();
           String ngaytt = "";
           String cum = tnt.getThuocnoitruCum() == null ? "" : tnt.getThuocnoitruCum().toString();
           String lanlinh = tnt.getThuocnoitruLanlinh() == null ? "" : tnt.getThuocnoitruLanlinh().toString();
           String hangsx = tnt.getThuocnoitruHangsx() == null ? "" : tnt.getThuocnoitruHangsx().getDmnhasanxuatMa();
           String sodangky = tnt.getThuocnoitruSodangky() == null ? "" : tnt.getThuocnoitruSodangky();
           String khongthu = tnt.getThuocnoitruKhongthu() == null ? "" : tnt.getThuocnoitruKhongthu().toString();
           String bacsi = tnt.getThuocnoitruBacsi() == null ? "" : tnt.getThuocnoitruBacsi().getDtdmnhanvienMa();
           String kho = tnt.getThuocnoitruKho() == null ? "" : tnt.getThuocnoitruKho().getDmkhoaMa();
           String status = tnt.getThuocnoitruStatus() == null ? "" : tnt.getThuocnoitruStatus();
           String ngayinlinh = "";
           String lanintra = tnt.getThuocnoitruLanintra() == null ? "" : tnt.getThuocnoitruLanintra().toString();
           String ngayintra = "";
           String dongianhap = tnt.getThuocnoitruDongianhap() == null ? "" : tnt.getThuocnoitruDongianhap().toString();
           String dongiaban = tnt.getThuocnoitruDongiaban() == null ? "" : tnt.getThuocnoitruDongiaban().toString();
           String nhanviencn = tnt.getThuocnoitruNhanviencn() == null ? "" : tnt.getThuocnoitruNhanviencn().getDtdmnhanvienMa();
           String thungan = tnt.getThuocnoitruThungan() == null ? "" : tnt.getThuocnoitruThungan().getDtdmnhanvienMa();
           String sott = tnt.getThuocnoitruStt() == null ? "" : tnt.getThuocnoitruStt().toString();
           String ngaygiocn = "";
           if (tnt.getThuocnoitruNgaygio() != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             Date cal = tnt.getThuocnoitruNgaygio();
             ngaygio = df.format(Long.valueOf(cal.getTime()));
           }
           if (tnt.getThuocnoitruNgaytt() != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             Date cal = tnt.getThuocnoitruNgaytt();
             ngaytt = df.format(Long.valueOf(cal.getTime()));
           }
           if (tnt.getThuocnoitruNgayinlinh() != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             Date cal = tnt.getThuocnoitruNgayinlinh();
             ngayinlinh = df.format(Long.valueOf(cal.getTime()));
           }
           if (tnt.getThuocnoitruNgayintra() != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             Date cal = tnt.getThuocnoitruNgayintra();
             ngayintra = df.format(Long.valueOf(cal.getTime()));
           }
           if (tnt.getThuocnoitruNgaygiocn() != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             Date cal = tnt.getThuocnoitruNgaygiocn();
             ngaygiocn = df.format(Long.valueOf(cal.getTime()));
           }
           buf.append("<THUOC_NOI_TRU MAPHU='' THUOCNOITRU_MA='" + ma + "' HSBA_SOVAOVIEN='" + sovaovien + "' THUOCNOITRU_MAPHIEU='" + maphieu + "' THUOCNOITRU_KHOA='" + khoa + "' THUOCNOITRU_PHONG='" + phong + "' THUOCNOITRU_BOSUNG='" + bosung + "' THUOCNOITRU_NGAYGIO='" + ngaygio + "' THUOCNOITRU_MATHUOC='" + mathuoc + "' THUOCNOITRU_MAPHONG='" + maphong + "' THUOCNOITRU_QUOCGIA='" + quocgia + "' THUOCNOITRU_NGUON='" + nguon + "' THUOCNOITRU_PHANLOAI='" + phanloai + "' THUOCNOITRU_LOAI='" + loai + "' THUOCNOITRU_YEUCAU='" + yeucau + "' THUOCNOITRU_MIEN='" + mien + "' THUOCNOITRU_NHI='" + nhi + "' THUOCNOITRU_LAO='" + lao + "' THUOCNOITRU_SOLUONG='" + soluong + "' THUOCNOITRU_TRA='" + tra + "' THUOCNOITRU_DONGIA='" + dongia + "' THUOCNOITRU_DONGIABH='" + dongiabh + "' THUOCNOITRU_NGAYTT='" + ngaytt + "' THUOCNOITRU_CUM='" + cum + "' THUOCNOITRU_LANLINH='" + lanlinh + "' THUOCNOITRU_HANGSX='" + hangsx + "' THUOCNOITRU_SODANGKY='" + sodangky + "' THUOCNOITRU_KHONGTHU='" + khongthu + "' THUOCNOITRU_BACSI='" + bacsi + "' THUOCNOITRU_KHO='" + kho + "' THUOCNOITRU_STATUS='" + status + "' THUOCNOITRU_NGAYINLINH='" + ngayinlinh + "' THUOCNOITRU_LANINTRA='" + lanintra + "' THUOCNOITRU_NGAYINTRA='" + ngayintra + "' THUOCNOITRU_DONGIANHAP='" + dongianhap + "' THUOCNOITRU_DONGIABAN='" + dongiaban + "' THUOCNOITRU_NGAYGIOCN='" + ngaygiocn + "' THUOCNOITRU_NHANVIENCN='" + nhanviencn + "' THUOCNOITRU_THUNGAN='" + thungan + "' THUOCNOITRU_STT='" + sott + "' />");
         }
       }
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

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetThuocNoiTruAction

 * JD-Core Version:    0.7.0.1

 */