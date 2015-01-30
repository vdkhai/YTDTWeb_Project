 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmKhoaByt;
 import com.iesvn.yte.entity.DmLoaiKhoa;
 import com.iesvn.yte.entity.DmNhomKhoa;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetKhoaAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listKhoa = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listKhoa = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmKhoa", "dmkhoaNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listKhoa != null) {
       for (Object obj : listKhoa)
       {
         DmKhoa k = (DmKhoa)obj;
         String khoaBhyt = "";
         if (k.getDmkhoaMabyt() != null) {
           khoaBhyt = k.getDmkhoaMabyt().getDmkhoabytMaso().toString();
         }
         String khoaBhyt3 = "";
         if (k.getDmkhoaMabyt3() != null) {
           khoaBhyt3 = k.getDmkhoaMabyt3().getDmkhoabytMaso().toString();
         }
         String khoaMas = "";
         if (k.getDmkhoaMakhoas() != null) {
           khoaMas = k.getDmkhoaMakhoas().getDmkhoaMaso().toString();
         }
         String loaiKhoa = "";
         if (k.getDmloaikhoaMa() != null) {
           loaiKhoa = k.getDmloaikhoaMa().getDmloaikhoaMaso().toString();
         }
         String nhomKhoa = "";
         if (k.getDmnhomkhoaMaso() != null) {
           nhomKhoa = k.getDmnhomkhoaMaso().getDmnhomkhoaMaso().toString();
         }
         buf.append("<record MaSo='" + k.getDmkhoaMaso() + "' Ma='" + k.getDmkhoaMa() + "' Ten='" + k.getDmkhoaTen() + "' DMKHOA_CAPMAU='" + Utils.reFactorString(k.getDmkhoaCapmau()) + "' DMKHOA_CON='" + Utils.reFactorString(k.getDmkhoaCon()) + "' DMKHOA_CLS='" + Utils.reFactorString(k.getDmkhoaCls()) + "' DMKHOA_CUM='" + Utils.reFactorString(k.getDmkhoaCum()) + "' DMKHOA_DIENTHOAI='" + Utils.reFactorString(k.getDmkhoaDienthoai()) + "' DP='" + Utils.reFactorString(k.getDmkhoaDp()) + "' DT='" + Utils.reFactorString(k.getDmkhoaDt()) + "' DMKHOA_DUOC='" + Utils.reFactorString(k.getDmkhoaDuoc()) + "' DMKHOA_DVVESINH='" + Utils.reFactorString(k.getDmkhoaDvvesinh()) + "' DMKHOA_GIATRAN='" + Utils.reFactorString(k.getDmkhoaGiatran()) + "' DMKHOA_GIUONGKH='" + Utils.reFactorString(k.getDmkhoaGiuongkh()) + "' DMKHOA_GIUONGTK='" + Utils.reFactorString(k.getDmkhoaGiuongtk()) + "' DMKHOA_KHAM='" + Utils.reFactorString(k.getDmkhoaKham()) + "' DMKHOA_KHAMCC='" + Utils.reFactorString(k.getDmkhoaKhamcc()) + "' DMKHOA_KHAMICD='" + Utils.reFactorString(k.getDmkhoaKhamicd()) + "' DMKHOA_KYHIEU='" + Utils.reFactorString(k.getDmkhoaKyhieu()) + "' DMKHOA_MAKHOADA='" + Utils.reFactorString(k.getDmkhoaMakhoada()) + "' DMKHOA_NGOAITRU='" + Utils.reFactorString(k.getDmkhoaNgoaitru()) + "' DMKHOA_NHAPPT='" + Utils.reFactorString(k.getDmkhoaNhappt()) + "' DMKHOA_NOITRU='" + Utils.reFactorString(k.getDmkhoaNoitru()) + "' DMKHOA_PHTHUAT='" + Utils.reFactorString(k.getDmkhoaPhthuat()) + "' QL='" + Utils.reFactorString(k.getDmkhoaQl()) + "' DMKHOA_SOTRET='" + Utils.reFactorString(k.getDmkhoaSotret()) + "' DMKHOA_TEN2='" + Utils.reFactorString(k.getDmkhoaTen2()) + "' DMKHOA_TENBC='" + Utils.reFactorString(k.getDmkhoaTenbc()) + "' DMKHOA_THUPHI='" + Utils.reFactorString(k.getDmkhoaThuphi()) + "' DMKHOA_THUTU='" + Utils.reFactorString(k.getDmkhoaThutu()) + "' DMKHOA_TIENGANH='" + Utils.reFactorString(k.getDmkhoaTienganh()) + "' DMKHOA_YEUCAU='" + Utils.reFactorString(k.getDmkhoaYeucau()) + "' DMKHOA_MABYT='" + khoaBhyt + "' DMKHOA_MABYT3='" + khoaBhyt3 + "' DMKHOA_MAKHOAS='" + khoaMas + "' DMLOAIKHOA_MA='" + loaiKhoa + "' DMNHOMKHOA_MASO='" + nhomKhoa + "' DMKHOA_KHO='" + Utils.reFactorString(k.getDmkhoaKho()) + "' NgayChinhSua='" + k.getDmkhoaNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetKhoaAction

 * JD-Core Version:    0.7.0.1

 */