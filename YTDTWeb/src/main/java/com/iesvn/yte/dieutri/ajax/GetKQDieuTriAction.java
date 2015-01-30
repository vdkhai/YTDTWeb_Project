 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import java.util.List;

 public class GetKQDieuTriAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmKetQua = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmKetQua = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmKetQuaDieuTri", "dmkqdtNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmKetQua != null) {
       for (Object obj : listDtDmKetQua)
       {
         DmKetQuaDieuTri ketqua = (DmKetQuaDieuTri)obj;
         buf.append("<record MaSo='" + ketqua.getDmkqdtMaso() + "' Ma='" + ketqua.getDmkqdtMa() + "' Ten='" + ketqua.getDmkqdtTen() + "' DMKQDT_GHICHU='" + ketqua.getDmkqdtGhichu() + "' QL='" + ketqua.getDmkqdtQl() + "' DT='" + ketqua.getDmkqdtDt() + "' DP='" + ketqua.getDmkqdtDp() + "' NgayChinhSua='" + ketqua.getDmkqdtNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetKQDieuTriAction

 * JD-Core Version:    0.7.0.1

 */