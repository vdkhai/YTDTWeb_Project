 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtdmKhoiAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List list = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       list = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmKhoiBhyt", "dtdmkhoibhytNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (list != null) {
       for (Object obj : list)
       {
         DtDmKhoiBhyt dmkhoibhyt = (DtDmKhoiBhyt)obj;
         String loaiBHYT = "";
         if (dmkhoibhyt.getDtdmkhoibhytLoai() != null) {
           loaiBHYT = String.valueOf(dmkhoibhyt.getDtdmkhoibhytLoai().getDtdmloaibhytMaso());
         }
         String ploaiBHYT = "";
         if (dmkhoibhyt.getDtdmkhoibhytPhanloai() != null) {
           ploaiBHYT = String.valueOf(dmkhoibhyt.getDtdmkhoibhytPhanloai().getDtdmphloaibhytMaso());
         }
         buf.append("<record MaSo='" + dmkhoibhyt.getDtdmkhoibhytMaso() + "' Ma='" + dmkhoibhyt.getDtdmkhoibhytMa() + "' Ten='" + dmkhoibhyt.getDtdmkhoibhytTen() + "' NgayChinhSua='" + dmkhoibhyt.getDtdmkhoibhytNgaygiocn() + "' DTDMKHOIBHYT_TYLEBHYT1='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytTylebhyt1()) + "' DTDMKHOIBHYT_TYLEBHYT2='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytTylebhyt2()) + "' DTDMKHOIBHYT_TYLEKTC='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytTylektc()) + "' DTDMKHOIBHYT_GHTYLE='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytGioiHanTyLe()) + "' DTDMKHOIBHYT_MINKTC='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytMinKTC()) + "' DTDMKHOIBHYT_TLMINKTC='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytTLMinKTC()) + "' DTDMKHOIBHYT_MAXKTC='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytMaxKTC()) + "' DTDMKHOIBHYT_TLMAXKTC='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytTLMaxKTC()) + "' DTDMKHOIBHYT_GOMYC='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytGomyc()) + "' DTDMKHOIBHYT_LOAI='" + loaiBHYT + "' DTDMKHOIBHYT_GHICHU='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytGhichu()) + "' DTDMKHOIBHYT_PHANLOAI='" + ploaiBHYT + "' DT='" + Utils.reFactorString(dmkhoibhyt.getDtdmkhoibhytChon()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtdmKhoiAction

 * JD-Core Version:    0.7.0.1

 */