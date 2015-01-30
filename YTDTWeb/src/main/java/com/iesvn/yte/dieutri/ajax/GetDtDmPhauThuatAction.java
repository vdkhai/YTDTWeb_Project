 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
 import com.iesvn.yte.dieutri.entity.DtDmPhauThuat;
 import com.iesvn.yte.dieutri.entity.DtDmPlPhauThuat;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmPhauThuatAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmPhauThuat = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmPhauThuat = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmPhauThuat", "dtdmphauthuatNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmPhauThuat != null) {
       for (Object obj : listDtDmPhauThuat)
       {
         DtDmPhauThuat dmphauthuat = (DtDmPhauThuat)obj;
         String loai = "";
         if (dmphauthuat.getDtdmphauthuatLoai1() != null) {
           loai = String.valueOf(dmphauthuat.getDtdmphauthuatLoai1().getDtdmloaiptMaso());
         }
         String ploai = "";
         if (dmphauthuat.getDtdmphauthuatPhanloai1() != null) {
           ploai = String.valueOf(dmphauthuat.getDtdmphauthuatPhanloai1().getDtdmplptMaso());
         }
         String ten = dmphauthuat.getDtdmphauthuatTen();
         ten = Utils.findAndreplace(ten);
         buf.append("<record MaSo='" + dmphauthuat.getDtdmphauthuatMaso() + "' Ma='" + dmphauthuat.getDtdmphauthuatMa() + "' Ten='" + ten + "' DTDMPHAUTHUAT_LOAI1='" + loai + "' DTDMPHAUTHUAT_LOAI2='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatLoai2()) + "' DTDMPHAUTHUAT_PHANBIET='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatPhanbiet()) + "' DTDMPHAUTHUAT_PHANLOAI1='" + ploai + "' DTDMPHAUTHUAT_PHANLOAI2='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatPhanloai2()) + "' DTDMPHAUTHUAT_GOPCA='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatGopca()) + "' DTDMPHAUTHUAT_MO='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatMo()) + "' DTDMPHAUTHUAT_CHON1='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatChon1()) + "' DTDMPHAUTHUAT_CHON2='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatChon2()) + "' DTDMPHAUTHUAT_LIENHE='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatLienhe()) + "' DTDMPHAUTHUAT_MAMO='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatMamo()) + "' DTDMPHAUTHUAT_ORDER='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatOrder()) + "' DT='" + Utils.reFactorString(dmphauthuat.getDtdmphauthuatChon()) + "' NgayChinhSua='" + dmphauthuat.getDtdmphauthuatNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmPhauThuatAction

 * JD-Core Version:    0.7.0.1

 */