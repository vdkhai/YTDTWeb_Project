 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmKetQuaDieuTriAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmKQDT = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmKQDT = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmKetQuaDieuTri", "dmkqdtNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmKQDT != null) {
       for (Object obj : listDmKQDT)
       {
         DmKetQuaDieuTri dvt = (DmKetQuaDieuTri)obj;
         buf.append("<record MaSo='" + dvt.getDmkqdtMaso() + "' Ma='" + dvt.getDmkqdtMa() + "' Ten='" + dvt.getDmkqdtTen() + "' DMKQDT_GHICHU='" + Utils.reFactorString(dvt.getDmkqdtGhichu()) + "' NgayChinhSua='" + Utils.reFactorString(dvt.getDmkqdtNgaygiocn()) + "' QL='" + Utils.reFactorString(dvt.getDmkqdtQl()) + "' DT='" + Utils.reFactorString(dvt.getDmkqdtDt()) + "' DP='" + Utils.reFactorString(dvt.getDmkqdtDp()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmKetQuaDieuTriAction

 * JD-Core Version:    0.7.0.1

 */