 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetBanKhamAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmBanKham = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmBanKham = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmBanKham", "dtdmbankhamNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmBanKham != null) {
       for (Object obj : listDtDmBanKham)
       {
         DtDmBanKham bankham = (DtDmBanKham)obj;
         DmKhoa loaiKhoa = bankham.getDmkhoaMaso();
         String maSoTmp = "";
         if (loaiKhoa != null) {
           maSoTmp = String.valueOf(loaiKhoa.getDmkhoaMaso());
         }
         DtDmNhanVien bs1 = bankham.getDtdmnhanvienBacsi1();
         String maBs1 = "";
         if (bs1 != null) {
           maBs1 = String.valueOf(bs1.getDtdmnhanvienMaso());
         }
         DtDmNhanVien bs2 = bankham.getDtdmnhanvienBacsi1();
         String maBs2 = "";
         if (bs2 != null) {
           maBs2 = String.valueOf(bs2.getDtdmnhanvienMaso());
         }
         DtDmNhanVien bs3 = bankham.getDtdmnhanvienBacsi1();
         String maBs3 = "";
         if (bs3 != null) {
           maBs3 = String.valueOf(bs3.getDtdmnhanvienMaso());
         }
         buf.append("<record  MaSo='" + bankham.getDtdmbankhamMaso() + "'" + " Ma='" + bankham.getDtdmbankhamMa() + "'" + " Ten='" + bankham.getDtdmbankhamTen() + "'" + " NgayChinhSua='" + bankham.getDtdmbankhamNgaygiocn() + "'" + " DTDMBANKHAM_MA0='" + Utils.reFactorString(bankham.getDtdmbankhamMa0()) + "'" + " DTDMBANKHAM_MA2='" + Utils.reFactorString(bankham.getDtdmbankhamMa2()) + "'" + " DTDMBANKHAM_KYHIEU='" + Utils.reFactorString(bankham.getDtdmbankhamKyhieu()) + "'" + " DMLOAIKHOA_MASO='" + maSoTmp + "'" + " DTDMBANKHAM_THOIGIAN='" + Utils.reFactorString(bankham.getDtdmbankhamThoigian()) + "'" + " DTDMBANKHAM_TINHCHAT='" + Utils.reFactorString(bankham.getDtdmbankhamTinhchat()) + "'" + " DTDMNHANVIEN_BACSI1='" + maBs1 + "'" + " DTDMNHANVIEN_BACSI2='" + maBs2 + "'" + " DTDMNHANVIEN_BACSI3='" + maBs3 + "'" + " DTDMNHANVIEN_TENBC='" + Utils.reFactorString(bankham.getDtdmnhanvienTenbc()) + "'" + " DT='" + Utils.reFactorString(bankham.getDtdmbankhamChon()) + "'" + "/>");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetBanKhamAction

 * JD-Core Version:    0.7.0.1

 */